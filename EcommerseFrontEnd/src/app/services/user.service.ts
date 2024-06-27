import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { userDTO } from '../interfaces/userDTO';
import { login } from '../interfaces/login';
import { Observable } from 'rxjs';
import { user } from '../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  TOKEN = 'ecom-token';
  USER = 'ecom-user'

  constructor(private http:HttpClient) { }

  public register(user:userDTO){
   return this.http.post("http://localhost:8080/all/register",user);
  }

  public login(login:login):Observable<jwtrequest>{
   return this.http.post<jwtrequest>("http://localhost:8080/all/login",login);
  }

  public saveToken(token:string){
    window.localStorage.removeItem(this.TOKEN);
    window.localStorage.setItem(this.TOKEN,token);
  }

  public saveUser(user:user){
    window.localStorage.removeItem(this.USER);
    window.localStorage.setItem(this.USER,JSON.stringify(user));
  }

  public getToken(){
    return localStorage.getItem(this.TOKEN);
  }

  public getUser(){
    const user = window.localStorage.getItem(this.USER);
    return user ? JSON.parse(user) : null;
  }
  
  public getUserId(){
    const user = this.getUser();
    if(user==null){
      return '';
    }
    return user.id;
  }

  public getUserRole(){
    const user = this.getUser();
    if(user==null){
      return '';
    }
    return user.role;
  }

  public isAdminLoggedIn(){
    if(this.getToken == null){
      return false;
    }
    const role=this.getUserRole();
    return role=='Admin'
  }

  public isCustomerLoggedIn(){
    if(this.getToken == null){
      return false;
    }
    const role=this.getUserRole();
    return role=='User'
  }

  signOut(){
    window.localStorage.removeItem(this.TOKEN)
    window.localStorage.removeItem(this.USER)
  }
}

export interface jwtrequest{
  token:string,
  refreshToken:string,
  user:user
}
