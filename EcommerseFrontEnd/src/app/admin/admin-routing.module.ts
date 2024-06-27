import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { OrdersComponent } from './orders/orders.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CategoryComponent } from './category/category.component';
import { ProductsComponent } from './products/products.component';
import { AddCouponComponent } from './add-coupon/add-coupon.component';
import { CouponsComponent } from './coupons/coupons.component';

const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'orders', component: OrdersComponent}  ,
  { path: 'dashboard', component: DashboardComponent},
  { path: 'category', component: CategoryComponent},
  { path: 'product', component: ProductsComponent},
  { path: 'add-coupon', component: AddCouponComponent},
  { path: 'coupons', component: CouponsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
