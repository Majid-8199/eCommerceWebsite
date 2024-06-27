import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit{
  constructor(private service:AdminService){}

  orders:any;
  ngOnInit(): void {
    this.service.getOrders().subscribe(e=>this.orders=e);
  }

  toggleActions(order: any): void {
    order.showActions = !order.showActions;
  }

  updateOrderStatus(orderId:any,orderStatus:any){
    this.service.updateOrderStatus(orderId,orderStatus).subscribe(()=>{
      window.location.reload();
    })
  }
}
