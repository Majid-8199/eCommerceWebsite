import { Component, OnInit, ViewChild, viewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { login } from '../interfaces/login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  @ViewChild('myform') myform!:NgForm

  constructor(private service:UserService, private router:Router){}

  ngOnInit(): void {
  }

  login(login:login){
    if(this.myform.invalid){
      alert('Please fill out all required fields.')
    }else{
      this.service.login(login).subscribe({
        next:(res)=>{
          this.service.saveToken(res.token);
          this.service.saveUser(res.user)
          alert('Logged In succuessfully');
          if(this.service.isAdminLoggedIn()){
            this.router.navigate(['/admin/dashboard'])
          } else if(this.service.isCustomerLoggedIn()){
            this.router.navigate(['/customer/dashboard'])
          }
      },
      error:(error)=>{
        alert('Login failed. Please check your credentials and try again.');
      }
    });
      
    }
  }


}
