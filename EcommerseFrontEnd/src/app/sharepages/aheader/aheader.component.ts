import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-aheader',
  templateUrl: './aheader.component.html',
  styleUrl: './aheader.component.css'
})
export class AheaderComponent {

  constructor(private service:UserService, private router:Router){}
  
  logout(){
    this.service.signOut();
    this.router.navigate(['/login'])
  }
}
