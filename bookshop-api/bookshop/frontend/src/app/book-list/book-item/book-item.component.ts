import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { Book } from 'src/app/model/book';
import { BookService } from 'src/app/services/book.service';

@Component({
  selector: 'app-book-item',
  templateUrl: './book-item.component.html',
  styleUrls: ['./book-item.component.css']
})
export class BookItemComponent implements OnInit {
  @Output() clickedEventEmitter = new EventEmitter<Book>();
  @Input() book: Book;

  constructor( private service: BookService ) {}

  ngOnInit() {
  }

  isClicked(){
    this.clickedEventEmitter.emit(this.book);
  }

}
