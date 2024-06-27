import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{

  products!: any;

  constructor(private service: CustomerService, private router: Router) {}

  ngOnInit(): void {
    this.service.getAllProducts().subscribe((products) => {
      this.products = products;
      this.products.forEach((product: { decodedImg: string; img: string; }) => {
        product.decodedImg = 'data:image/jpeg;base64,' + product.img;
      });
    });
  }

  addToCart(id:any){
    this.service.addToCart(id).subscribe(()=>{
      alert("Product added to Cart Succeessfully!")
    })
  }
}
