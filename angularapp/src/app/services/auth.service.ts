import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Login } from '../models/login.model';

import { JwtService } from './jwt.service';
import { Observable } from 'rxjs';
import { NavigationEnd, Router } from '@angular/router';

import { API_END_POINTS } from '../app.constants';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http:HttpClient,private readonly jwtService:JwtService,private router:Router) { }



  register(user:User):Observable<any>{
    return this.http.post<any>(`${API_END_POINTS.URL}/register`,user)

  }

  login(login:Login):Observable<any>{
    return this.http.post<any>(`${API_END_POINTS.URL}/login`,login)
  }

  isAdmin():boolean{
    const token:string = localStorage.getItem('token');
    const role:string = this.jwtService.getClaim(token, 'userRole');
    return role == 'Admin'; 
  }

  isUser():boolean{
    const token:string = localStorage.getItem('token');
    const role:string = this.jwtService.getClaim(token, 'userRole');
    return role === 'User';
  }

  getUseremail():string{
    const token:string = localStorage.getItem('token');
    const email:string = this.jwtService.getClaim(token, 'email');
    return email;
  }

  getUserId():number{
    const token:string = localStorage.getItem('token');
    const userId:number = this.jwtService.getClaim(token, 'userId');
    return userId;
  }

  getUserrole():string{
    const token:string = localStorage.getItem('token');
    const role:string = this.jwtService.getClaim(token, 'userRole');
    return role;
  }

  getUsername():string{
    const token:string = localStorage.getItem('token');
    const username:string = this.jwtService.getClaim(token, 'username');
    return username;
  }


  logout(){
    localStorage.removeItem('token'); 
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
  
  reloadHomepage():void{
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd && event.urlAfterRedirects === '/home-page') {
        setTimeout(() => {
          window.location.reload();
        }, 3000);
      }
    });
  }
}
