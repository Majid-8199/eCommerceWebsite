import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerComponent } from './customer.component';
import { OrdersComponent } from './orders/orders.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CartComponent } from './cart/cart.component';
import { ProfileComponent } from './profile/profile.component';
import { PlaceOrderComponent } from './place-order/place-order.component';

const routes: Routes = [
  { path: '', component: CustomerComponent },
  { path: 'orders', component: OrdersComponent}  ,
  { path: 'dashboard', component: DashboardComponent},
  { path: 'cart', component: CartComponent}  ,
  { path: 'profile', component: ProfileComponent},
  { path: 'place', component: PlaceOrderComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule { }
