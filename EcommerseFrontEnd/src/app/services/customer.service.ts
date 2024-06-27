import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserService } from './user.service';
import { placeOrder } from '../interfaces/placeorder';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http:HttpClient, private service:UserService) { }

  getAllProducts(){
    return this.http.get('http://localhost:8080/customer/allproducts',{
      headers: this.createAuthorizationHeader(),})
  }

  addToCart(productid:any){
    const cartDTO={
      userId : this.service.getUserId(),
      productId : productid
    }
    console.log(cartDTO)
    return this.http.post('http://localhost:8080/customer/cart',cartDTO ,{
      headers: this.createAuthorizationHeader(),})
  }

  increaseProductQuantity(productid:any){
    const cartDTO={
      userId : this.service.getUserId(),
      productId : productid
    }
    console.log(cartDTO)
    return this.http.post('http://localhost:8080/customer/addition',cartDTO ,{
      headers: this.createAuthorizationHeader(),})
  }

  decreaseProductQuantity(productid:any){
    const cartDTO={
      userId : this.service.getUserId(),
      productId : productid
    }
    console.log(cartDTO)
    return this.http.post('http://localhost:8080/customer/subtraction',cartDTO ,{
      headers: this.createAuthorizationHeader(),})
  }

  getCartByUserId(){
    const userId=this.service.getUserId()
    return this.http.get('http://localhost:8080/customer/cart/'+userId,{
      headers: this.createAuthorizationHeader(),})
  }

  applyCoupon(code:any){
    const userId=this.service.getUserId()
    return this.http.get('http://localhost:8080/customer/coupon/'+userId+'/'+code,{
      headers: this.createAuthorizationHeader(),})
  }

  placeOrder(order:placeOrder){
    order.userId=this.service.getUserId()
    return this.http.post('http://localhost:8080/customer/placeorder',order,{
      headers: this.createAuthorizationHeader(),})
  }

  createAuthorizationHeader(): HttpHeaders{
    return new HttpHeaders().set(
      'Authorization', 'Bearer ' + this.service.getToken()
    )
  }

  getOrders(){
    const userId=this.service.getUserId()
    return this.http.get('http://localhost:8080/customer/orders/'+userId,{
      headers: this.createAuthorizationHeader(),})
  }
}
