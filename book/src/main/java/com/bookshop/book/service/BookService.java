package com.bookshop.book.service;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.io.IOException;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import com.bookshop.book.model.Book;
import com.bookshop.book.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

 // private final String ROOT_DIR = "//home//nducele//Downloads";
  private final ResourceLoader resourceLoader;
  private final GridFsTemplate template;
  private final BookRepository bookRepository;

  public Flux<Book> getAll() {
    return bookRepository.findAll();
  }
  
  public Mono<Book> getById(String id) {
    return bookRepository.findById(id);
  }

  public Flux<Book> searchByTitle(String title) {
    return bookRepository.findByTitleContainingIgnoreCase(title);
  }
  
  public Mono<Book> create(Book book) {
	try {
		//this.template.store(new FileInputStream(ROOT_DIR + book.getImageName()), book.getImageName());
		this.template.store(resourceLoader.getResource("classpath:"+book.getImageName()).getInputStream(), book.getImageName());
	} catch ( IOException  e) {
		 e.printStackTrace();
	}
    return bookRepository.save(book);
  }

  public Mono<Book> update(String id, Book updatedBook) {
	return bookRepository.findById(id)
        .map(existingBook -> existingBook.toBuilder()
              .title(updatedBook.getTitle())
              .publisher(updatedBook.getPublisher())
              .author(updatedBook.getAuthor())
              .category(updatedBook.getCategory())
              .releasedDate(updatedBook.getReleasedDate())
              .price(updatedBook.getPrice())
              .quantity(updatedBook.getQuantity())
              .imageName(updatedBook.getImageName())
              .build())
        .flatMap(bookRepository::save);
  }
  
  public Mono<Book> deleteById(String id) {
    return bookRepository.findById(id)
        .flatMap(book -> { 
        	this.template.delete(new Query(Criteria.where("filename").is(book.getImageName())));
        	return bookRepository.delete(book).then(Mono.just(book));});
  }
  
  
  public Mono<GridFsResource> getImage(String id){
	  return this.getById(id).map(book 
			  -> this.template.getResource(book.getImageName()));
  }
  
  public Flux<GridFsResource> getImages(){
	  return this.getAll().map(book 
			  -> this.template.getResource(book.getImageName()));
  }

  public Mono<Book> byImageName(String filename) {
	return this.bookRepository.findFirstByImageNameIgnoreCase(filename);
  }
}