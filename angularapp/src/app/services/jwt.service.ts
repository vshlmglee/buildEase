import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor() { }
 
  decodeToken(token: string): any {
    try { return jwtDecode(token);
    } catch (err) {
      return null;
    }
  }
 
  getClaim(token: string, claim: string): any {
    const decodedToken = this.decodeToken(token);
    return decodedToken?.[claim] || null;
  }

  // private handleError(error: HttpErrorResponse) {
  //   if (error.status === 401) {
  //     return throwError('Invalid username or password!');
  //   } else {
  //     return throwError('An error occurred. Please try again later.');
  //   }
  // }
 
}
