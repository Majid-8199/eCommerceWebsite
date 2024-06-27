import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AdminService } from '../../services/admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{

  @ViewChild('myform') myform!:NgForm;

  categories:any;
  selectedFile!: File;
  imagePreview!:string|ArrayBuffer|null;

  constructor(private service:AdminService, private router:Router){}

  ngOnInit(): void {
    this.service.getAllCategories().subscribe(e=>this.categories=e)
  }

  onFileSelected(event:any){
    this.selectedFile=event.target.files[0];
    this.previewImage();
  }

  previewImage(){
    const reader=new FileReader();
    reader.onload=()=>{
      this.imagePreview=reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

  addProduct(): void{
    if(this.myform.invalid){
      alert("Please fill out all required fields.")
    }
    else{
      const formData = new FormData();
      formData.append('img', this.selectedFile);
      formData.append('categoryid', this.myform.value.categoryid);
      formData.append('name', this.myform.value.name);
      formData.append('description', this.myform.value.description);
      formData.append('price', this.myform.value.price);
      this.service.addProduct(formData).subscribe( ()=>{
        alert("Product Added Successfully!")
        this.router.navigate(['admin/dashboard'])
      },
      (error)=>{
        alert("An error occurred while adding the product. Please try again later.");
      });
    }
  }

}
