import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import {  RxReactiveFormsModule } from "@rxweb/reactive-form-validators"
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { FooterComponent } from './shared/footer/footer.component';
import { BookItemComponent } from './book-list/book-item/book-item.component';
import { BookDetailComponent } from './book-list/book-detail/book-detail.component';
import { BookFormComponent } from './book-form/book-form.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { OrderInfoComponent } from './order-info/order-info.component';
import { CheckoutPageComponent } from './checkout-page/checkout-page.component';
import { BookListComponent } from './book-list/book-list.component';
import { AdminComponent } from './admin/admin.component';
import { BooksComponent } from './admin/books/books.component';
import { OrdersComponent } from './admin/orders/orders.component';
import { BookService } from './services/book.service';
import { OrderService } from './services/order.service';
import { CartService } from './services/cart.service';
import { CustomerService } from './services/customer.service';
import { HttpClientModule } from '@angular/common/http';
import { TableComponent } from './admin/books/table/table.component';
import { SearchFormComponent } from './admin/books/search-form/search-form.component';
import { PagenateComponent } from './admin/books/pagenate/pagenate.component';
import { EditComponent } from './admin/book/edit/edit.component';
import { DetailInfoComponent } from './book-list/detail-info/detail-info.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    BookListComponent,
    BookItemComponent,
    BookDetailComponent,
    BookFormComponent,
    ShoppingCartComponent,
    OrderInfoComponent,
    CheckoutPageComponent,
    AdminComponent,
    BooksComponent,
    OrdersComponent,
    TableComponent,
    SearchFormComponent,
    PagenateComponent,
    EditComponent,
    DetailInfoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,ReactiveFormsModule, HttpClientModule
  ],
  providers: [BookService, OrderService, CartService, CustomerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
