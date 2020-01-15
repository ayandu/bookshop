import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Book } from 'src/app/model/book';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {
  @Output() hideDetailEventEmitter= new EventEmitter<Boolean>();
  @Input() book: Book;

  constructor( ) { 
  
  }

  hideDetail(){
    this.hideDetailEventEmitter.emit(true);
  }
  ngOnInit() {
  }

}
