import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{

  products!: any;

  constructor(private adminService: AdminService, private router: Router) {}

  ngOnInit(): void {
    this.adminService.getAllProducts().subscribe((products) => {
      this.products = products;
      this.products.forEach((product: { decodedImg: string; img: string; }) => {
        product.decodedImg = 'data:image/jpeg;base64,' + product.img;
      });
    });
  }

  deleteProduct(){

  }

  updateProduct(){

  }

  productFAQ(){
    
  }
}
