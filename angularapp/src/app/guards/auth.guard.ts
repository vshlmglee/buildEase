import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private readonly authService:AuthService,private route:ActivatedRoute,private router:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      const expectedRoles = route.data['roles'];
      const userRole = this.authService.getUserrole();

      if (this.authService.isLoggedIn() && expectedRoles.includes(userRole)) {
        return true;
      } else {
        this.router.navigate(['/error']);
        return false;
      }
  }
  
}
