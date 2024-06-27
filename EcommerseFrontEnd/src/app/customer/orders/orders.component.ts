import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit{

  constructor(private service:CustomerService){}

  orders:any;
  ngOnInit(): void {
    this.service.getOrders().subscribe(e=>this.orders=e);
  }

}
