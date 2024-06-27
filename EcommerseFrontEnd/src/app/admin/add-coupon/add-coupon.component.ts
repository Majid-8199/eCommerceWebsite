import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AdminService } from '../../services/admin.service';
import { Router } from '@angular/router';
import { Coupon } from '../../interfaces/coupon';

@Component({
  selector: 'app-add-coupon',
  templateUrl: './add-coupon.component.html',
  styleUrl: './add-coupon.component.css'
})
export class AddCouponComponent implements OnInit{

  @ViewChild('myform') myform!:NgForm;

  constructor(private service:AdminService, private router:Router){}
  
  ngOnInit(): void {}

addCoupon(coupon:Coupon){
  if(this.myform.invalid){
    alert("Please fill out all required fields.")
  }
  else{
    this.service.addCoupon(coupon).subscribe( ()=>{
      alert("Coupon Added Successfully!")
      this.router.navigate(['admin/coupons'])
    });
  }
}

}
