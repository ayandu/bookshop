package bookshop.book.service;

import bookshop.book.model.Book;
import bookshop.book.model.Price;
import bookshop.book.repository.BookRepository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Digits;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

  private final ResourceLoader resourceLoader;
  private final GridFsTemplate template;
  private final BookRepository bookRepository;
  private final int ONE = 1;

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
	
	return this.bookRepository.findByTitleIgnoreCase(book.getTitle())
							.flatMap( b -> this.bookRepository.save(b.toBuilder().quantity(b.getQuantity() + book.getQuantity()).build()))
							.switchIfEmpty( this.save(book));
  }

  private Mono<Book> save(Book book) {
	  try {
			this.template.store(resourceLoader.getResource("classpath:"+book.getImageName()).getInputStream(), book.getImageName());
		} catch ( IOException  e) {
			 e.printStackTrace();
		}
	  return this.bookRepository.save(book);
}

public Mono<Book> updatePrice(String id, Price price){
      return this.bookRepository.findById(id).map( book -> book.toBuilder().price( price.getPrice()).build())
              .flatMap(book -> this.bookRepository.save(book));
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
    return bookRepository.findById(id).flatMap(b ->  b.deleteBook());
  }

    public Mono<Book> deleteById_ADMIN(String id) {
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