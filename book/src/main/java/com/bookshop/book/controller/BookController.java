package com.bookshop.book.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookshop.book.model.Book;
import com.bookshop.book.service.BookService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.mongodb.gridfs.GridFsResource;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@CrossOrigin ("*")
public class BookController {

  private final BookService bookService;
 
  @GetMapping
  public Flux<Book> getAllBooks() {
    return this.bookService.getAll();
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Book>> getBookById(@PathVariable String id) {
    return this.bookService.getById(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.noContent().build());
  }
  
  @GetMapping("/search/{title}")
  public Flux<Book> searchByTitle(@PathVariable String title) {
    return this.bookService.searchByTitle(title);
  }
  
  @PostMapping
  public Mono<ResponseEntity<Book>> createBook(@RequestBody @Valid Book newBook) {
	return this.bookService.create(newBook)
        .map(book -> ResponseEntity.created(URI.create("/books/" + book.getId())).body(book));
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<Book>> updateBook(@PathVariable String id, @RequestBody @Valid Book updatedBook) {
	  return this.bookService.update(id, updatedBook)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.noContent().build());
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> deleteBook(@PathVariable String id) {
	return bookService.deleteById(id)
        .map(r -> ResponseEntity.ok().<Void>build())
        .defaultIfEmpty(ResponseEntity.noContent().build());
  }
  /*
  @GetMapping("/{id}/file")
  public Mono<ResponseEntity<Binary>> getBookImage(@PathVariable String id){
	  return this.bookService.getBookImage(id)
			  .map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
  }
  */
  @GetMapping("/{id}/image")
  public Mono<ResponseEntity<GridFsResource>> getImage(@PathVariable String id){
	  return this.bookService.getImage(id)
			  .map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
  }
  
  @GetMapping("/images")
  public Flux<GridFsResource> getAllImages(){
	  return this.bookService.getImages();
  }
  
  @GetMapping("/images/{filename}")
  public Mono<Book> getBook(@PathVariable String filename ){
	  return this.bookService.byImageName(filename);
  }
  
}
