import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookListComponent } from './book-list/book-list.component';
import { BookDetailComponent } from './book-list/book-detail/book-detail.component';
import { BookFormComponent } from './book-form/book-form.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { OrderInfoComponent } from './order-info/order-info.component';
import { CheckoutPageComponent } from './checkout-page/checkout-page.component';
import { AdminComponent } from './admin/admin.component';
import { BooksComponent } from './admin/books/books.component';
import { OrdersComponent } from './admin/orders/orders.component';


const routes: Routes = [
  { path:"", redirectTo:"books", pathMatch:"full"},
  {path:"books", component: BookListComponent},
  {path: "book/detail", component: BookDetailComponent},
  {path: "admin/new", component: BookFormComponent},
  { path: "shopping-cart", component: ShoppingCartComponent},
  { path: "order-info", component: OrderInfoComponent},
  { path: "checkout", component: CheckoutPageComponent },
  { path: "admin", redirectTo:"admin/books", pathMatch:"full"},
  {path: "admin/books", component: BooksComponent},
  { path: "admin/orders", component: OrdersComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
