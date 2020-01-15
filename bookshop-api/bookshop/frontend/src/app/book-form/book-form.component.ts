import { Component, OnInit} from '@angular/core';
import { FormArray, FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Book } from '../model/book';
import { BookService } from '../services/book.service';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {
  submitted = false;
  bookForm = this.fb.group({
    title: ['', Validators.required],
    publisher: ['', Validators.required],
    authors: this.fb.array([
      this.fb.control('', [Validators.required, Validators.minLength(3)])]
    ),
    categories: this.fb.array([
      this.fb.control('',[Validators.required, Validators.minLength(3)])
    ]),
    publishedDate: [Date, Validators.required],
    price: [0, Validators.required],
    imageName: ['', Validators.required],
    quantity:[0, Validators.required]
  });

  book : any;
  
  //books: Book[];

  constructor(private service: BookService,
    private fb: FormBuilder) { }

  ngOnInit() {
    //this.service.getAllBooks().subscribe( data => this.books = data)
  }

  add(book){
    this.service.createBook(book).subscribe(e => 
    //this.books.push(book)); 
    this.book = e)
  }
  get authors() {
    return this.bookForm.get('authors') as FormArray;
  }

  get categories() {
    return this.bookForm.get('categories') as FormArray;
  }

  addAuthor() {
    this.authors.push(this.fb.control(''));
  }

  addCategory() {
    this.categories.push(this.fb.control(''));
  }

  get f() { return this.bookForm.controls; }  

  
  onSubmit(){
      this.submitted = true;
      
      if(this.bookForm.invalid){  return;}

      let book = new Book(null,
        this.bookForm.get('title').value,
        this.bookForm.get('publisher').value,
        this.bookForm.get('authors').value,
        this.bookForm.get('categories').value,
        new Date(),
        this.bookForm.get('publishedDate').value,
        this.bookForm.get('price').value,
        this.bookForm.get('imageName').value,
        this.bookForm.get('quantity').value);
        this.add(book);

        alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.bookForm.value, null, 4));
    }



    onReset() {
      this.submitted = false;
      this.bookForm.reset();
      this.authors.clear();
      this.categories.clear();
    }


}
