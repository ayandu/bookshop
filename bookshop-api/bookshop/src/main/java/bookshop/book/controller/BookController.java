package bookshop.book.controller;

import java.math.BigDecimal;
import java.net.URI;

import javax.validation.Valid;

import bookshop.book.model.Book;
import bookshop.book.model.Price;
import bookshop.book.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import org.springframework.data.mongodb.gridfs.GridFsResource;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class BookController {

  private final BookService bookService;
 
  @GetMapping("/books")
  public Flux<Book> getAllBooks() {
    return this.bookService.getAll();
  }

  @GetMapping("/book/{id}")
  public Mono<ResponseEntity<Book>> getBookById(@PathVariable String id) {
    return this.bookService.getById(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.noContent().build());
  }
  
  @GetMapping("/book?search={title}")
  public Flux<Book> searchByTitle(@PathVariable String title) {
    return this.bookService.searchByTitle(title);
  }

  @PatchMapping("/book/{id}")
  public Mono<ResponseEntity<Book>> updatePrice(@PathVariable String id, @RequestBody @Valid Price price) {
      return this.bookService.updatePrice(id, price)
              .map(ResponseEntity::ok)
              .defaultIfEmpty(ResponseEntity.noContent().build());
  }
  @PostMapping("/book")
  public Mono<ResponseEntity<Book>> createBook(@RequestBody @Valid Book newBook) {
	return this.bookService.create(newBook)
        .map(book -> ResponseEntity.created(URI.create("/book/" + book.getId())).body(book));
  }

  @PutMapping("/book/{id}")
  public Mono<ResponseEntity<Book>> updateBook(@PathVariable String id, @RequestBody @Valid Book updatedBook) {
	  return this.bookService.update(id, updatedBook)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.noContent().build());
  }

  @DeleteMapping("/book/{id}")
  public Mono<ResponseEntity<Void>> deleteBook(@PathVariable String id) {
	return bookService.deleteById(id)
        .map(r -> ResponseEntity.ok().<Void>build())
        .defaultIfEmpty(ResponseEntity.noContent().build());
  }

  @GetMapping("/book/{id}/image")
  public Mono<ResponseEntity<GridFsResource>> getImage(@PathVariable String id){
	  return this.bookService.getImage(id)
			  .map(ResponseEntity::ok)
		        .defaultIfEmpty(ResponseEntity.noContent().build());
  }
  
  @GetMapping("/images")
  public Flux<GridFsResource> getAllImages(){
	  return this.bookService.getImages();
  }
  
  @GetMapping("/image/{filename}")
  public Mono<Book> getBook(@PathVariable String filename ){
	  return this.bookService.byImageName(filename);
  }
  
}
