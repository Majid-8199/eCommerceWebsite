import { Component, OnInit, ViewChild } from '@angular/core';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { userDTO } from '../interfaces/userDTO';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{
  
  @ViewChild('myform') myform!:NgForm ;
  
  constructor(private service:UserService, private router:Router){}
  
  ngOnInit(): void {
  
  }

  registerUser(user:userDTO){
    if(this.myform.invalid){
      alert("Please fill out all required fields.")
    }
    else{
      this.service.register(user).subscribe( ()=>{
        alert("User Registered Successfully!")
        this.router.navigate(['login'])
      });
    }
  }

}
