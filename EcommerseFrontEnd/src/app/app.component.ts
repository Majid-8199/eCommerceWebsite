import { Component, OnInit } from '@angular/core';
import { UserService } from './services/user.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'EcommerseFrontEnd';
  
    isCustomerLoggedIn:boolean = this.service.isCustomerLoggedIn();
    isAdminLoggedIn:boolean = this.service.isAdminLoggedIn();
  
    constructor(private service:UserService, private router:Router){}
  
    ngOnInit(): void {
      this.router.events.subscribe(event=>{
        this.isCustomerLoggedIn= this.service.isCustomerLoggedIn();
        this.isAdminLoggedIn= this.service.isAdminLoggedIn();
      });
    }
  }