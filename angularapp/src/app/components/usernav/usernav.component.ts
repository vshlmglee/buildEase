import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-usernav',
  templateUrl: './usernav.component.html',
  styleUrls: ['./usernav.component.css']
})
export class UsernavComponent implements OnInit {

  @ViewChild('navbarDropdown', { static: false }) navbarDropdown: ElementRef;
  userRole: string = '';
  userName: string = '';
  showLogoutPopup: boolean = false;
  showSuccessPopup: boolean = false;
  showDropdown: boolean = false;
  // auth = this.authService;

  title = 'BuildEase';
  userRoute = '/user';

  constructor(private authService:AuthService, private router: Router) {}

  toggleDropdown(event: Event) {
    event.preventDefault();
    const dropdownMenu = this.navbarDropdown.nativeElement.nextElementSibling;
    if (dropdownMenu.classList.contains('show')) {
      dropdownMenu.classList.remove('show');
    } else {
      dropdownMenu.classList.add('show');
    }
  }

  logout() {
    this.showLogoutPopup = true;
  }

  cancelLogout() {
    this.showLogoutPopup = false;
  }

  confirmLogout() {
     // this.authService.logout();
     this.showLogoutPopup = false;
     this.showSuccessPopup = true;
     this.authService.logout()
     setTimeout(() => {
       window.location.reload();
     }, 1000);
     this.showSuccessPopup = false;
     this.router.navigate(['/login']);
  }

  closeSuccessPopup() {
    this.showSuccessPopup = false;
    this.router.navigate(['/']);
  }

  
  ngOnInit(): void {
    // You can check if we are in edit mode and load the data here if needed
    console.log("User Navigation Request Component Initialized");
    this.userName = this.authService.getUsername();
  }
}