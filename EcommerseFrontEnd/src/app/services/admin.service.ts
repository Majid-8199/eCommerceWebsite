import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient, private service:UserService) { }

  addCategory(category:any){
    return this.http.post('http://localhost:8080/admin/category',category,{
      headers: this.createAuthorizationHeader(),})
  }

  getAllCategories(){
    return this.http.get('http://localhost:8080/admin/allcategories',{
      headers: this.createAuthorizationHeader(),})
  }

  addProduct(product:any){
    return this.http.post('http://localhost:8080/admin/product',product,{
      headers: this.createAuthorizationHeader(),})
  }

  getAllProducts(){
    return this.http.get('http://localhost:8080/admin/allproducts',{
      headers: this.createAuthorizationHeader(),})
  }

  createAuthorizationHeader(): HttpHeaders{
    return new HttpHeaders().set(
      'Authorization', 'Bearer ' + this.service.getToken()
    )
  }

  addCoupon(coupon:any){
    return this.http.post('http://localhost:8080/admin/coupon',coupon,{
      headers: this.createAuthorizationHeader(),})
  }

  getAllCoupon(){
    return this.http.get('http://localhost:8080/admin/allcoupons',{
      headers: this.createAuthorizationHeader(),})
  }

  getOrders(){
    return this.http.get('http://localhost:8080/admin/orders',{
      headers: this.createAuthorizationHeader(),})
  }

  updateOrderStatus(orderId:number,orderStatus:any){
    return this.http.post(`http://localhost:8080/admin/status/${orderId}/${orderStatus}`,{},{
      headers: this.createAuthorizationHeader(),})
  }

}
