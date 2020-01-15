import { Component, OnInit } from '@angular/core';
import { Book } from '../model/book';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  book: Book;
  books = new Array<Book>();
  status: boolean = false;
  constructor(private service: BookService) { }

  ngOnInit() {
    this.getAllBooks();
    this.status = false;
  }

  getAllBooks(){ this.service.getAllBooks()
    .subscribe( booklist => this.books = booklist);
  }

  hideDetail(event){
    this.getAllBooks();
    this.status = false;
  }
  isClicked(event : Book){
    this.book = event;
    this.status = true;
  }
}
