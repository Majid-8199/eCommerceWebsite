import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AdminService } from '../../services/admin.service';
import { Router } from '@angular/router';
import { category } from '../../interfaces/category';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrl: './category.component.css'
})
export class CategoryComponent implements OnInit{

  @ViewChild('myform') myform!:NgForm;

  constructor(private service:AdminService, private router:Router){}

  ngOnInit(): void {

  }

  addCategory(category:category){
    if(this.myform.invalid){
      alert("Please fill out all required fields.")
    }
    else{
      this.service.addCategory(category).subscribe( ()=>{
        alert("Category Added Successfully!")
        this.router.navigate(['admin/product'])
      });
    }
  }

}
