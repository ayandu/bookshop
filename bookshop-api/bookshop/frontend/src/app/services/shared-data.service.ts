import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Book } from '../model/book';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {

  private source = new Subject<Book>();
  
  getObservable(){
    return this.source.asObservable();
  }
  updateData( data: Book){ 
    this.source.next(data);
  }
  constructor() {}
}
