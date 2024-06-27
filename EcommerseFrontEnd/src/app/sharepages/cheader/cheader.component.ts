import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cheader',
  templateUrl: './cheader.component.html',
  styleUrl: './cheader.component.css'
})
export class CheaderComponent {

  constructor(private service:UserService, private router:Router){}
  
  logout(){
    this.service.signOut();
    this.router.navigate(['/login'])
  }
}
