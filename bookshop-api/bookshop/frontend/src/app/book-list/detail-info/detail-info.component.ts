import { Component, OnInit, Input } from '@angular/core';
import { Book } from 'src/app/model/book';

@Component({
  selector: 'app-detail-info',
  templateUrl: './detail-info.component.html',
  styleUrls: ['./detail-info.component.css']
})
export class DetailInfoComponent implements OnInit {

  @Input() book: Book;
  constructor( ) { }

  ngOnInit() {
  }

}
