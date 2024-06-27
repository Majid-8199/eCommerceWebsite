import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrl: './coupons.component.css'
})
export class CouponsComponent implements OnInit{

  coupons:any;

  constructor(private service:AdminService){}

  ngOnInit(): void {
    this.service.getAllCoupon().subscribe(data=>this.coupons=data)
  }
  
}
