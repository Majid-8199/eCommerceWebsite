import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { UserService } from '../../services/user.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit{

  myform!:NgForm;
  cartItems: any[]=[];
  orders:any;
  code:string=''
  couponApplied: boolean = false;

  constructor(private service:CustomerService, private userService:UserService, private router:Router){}
  ngOnInit(): void {
    this.getCart()
  }

  getCart(){
    this.cartItems=[]
    this.service.getCartByUserId().subscribe((orders)=>{
      this.orders=orders;
      this.orders.cartItems.forEach((product: { decodedImg: string; productImg: string; productName: string; productDescription: string;}) => {
        product.decodedImg = 'data:image/jpeg;base64,' + product.productImg;
        this.cartItems.push(product)
      });
    })
  }

  getTotalQuantity(): number {
    return this.cartItems.reduce((total, item) => total + item.quantity, 0);
  }

  applyCoupon(){
    if (this.couponApplied) {
      alert('Coupon has already been applied.');
      window.location.reload();
      console.log(this.couponApplied+"coupon")
      return;
    }
    this.service.applyCoupon(this.code).subscribe(()=>{
      alert("coupon applied successfully")
      this.couponApplied=true;
    })
  }

  increaseQuantity(productId:any){
    this.service.increaseProductQuantity(productId).subscribe(()=>{
      alert("Quantity Increased!")
      window.location.reload();
    })
  }

  decreaseQuantity(productId:any){
    this.service.decreaseProductQuantity(productId).subscribe(()=>{
      alert("Quantity Decreased!")
      window.location.reload();
    })
  }

  placeOrder() {
    const orderDetails = {
      amount: this.orders.amount,
      totalAmount: this.orders.totalAmount,
      discount: this.orders.discount,
      delivery: this.orders.delivery
    };
    this.router.navigate(['/customer/place'], { state: orderDetails });
  }
}
