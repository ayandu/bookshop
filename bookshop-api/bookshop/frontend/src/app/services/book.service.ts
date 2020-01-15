import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Book } from '../model/book';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  getAllBooks(): Observable<any>{
    return this.http.get(`http://localhost:8080/books`);
  }

  getById(id: string){
    return this.http.get<Book>(`http://localhost:8080/book` + `/` + id);
  }
  createBook(book: Book): Observable<any>{
    return this.http.post(`http://localhost:8080/book/`, book);
  }
  getImage(id: string){
    return this.http.get<any>(`http://localhost:8080/book/`+id+`/image`)
  }

  deleteBook(book: Book){
    return this.http.delete<Book>(`/` + book.id);
  }

}
