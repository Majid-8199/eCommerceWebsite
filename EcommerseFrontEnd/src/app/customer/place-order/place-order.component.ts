import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';
import { placeOrder } from '../../interfaces/placeorder';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrl: './place-order.component.css'
})
export class PlaceOrderComponent {

@ViewChild('myform') myform!:NgForm;

  selectedPaymentMethod: string = ''
  orderDetails: any = {};

  constructor(private service:CustomerService, private router:Router){
    const navigation = this.router.getCurrentNavigation();
    if (navigation && navigation.extras.state) {
      this.orderDetails = navigation.extras.state as any;
    }
  }

  onPaymentMethodChange(event: Event) {
    const target = event.target as HTMLSelectElement;
    this.selectedPaymentMethod = target.value;
  }

  placeOrder(order: placeOrder) {
    if (this.myform.invalid) {
      alert("Please fill out all required fields.");
    } else {
      order.amount = this.orderDetails.amount;
      order.totalAmount = this.orderDetails.totalAmount;
      order.discount = this.orderDetails.discount;
      order.delivery = this.orderDetails.delivery;

      this.service.placeOrder(order).subscribe(() => {
        this.router.navigate(['customer/orders']);
        alert("Order placed successfully!");
      });
    }
  }


}
