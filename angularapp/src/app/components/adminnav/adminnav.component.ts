import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-adminnav',
  templateUrl: './adminnav.component.html',
  styleUrls: ['./adminnav.component.css']
})
export class AdminnavComponent implements OnInit {


  @ViewChild('Dropdown', { static: false }) navbarDropdown: ElementRef;

 // @ViewChild('navbarDropdown', { static: false }) navbarDropdown: ElementRef;

  userRole: string = '';
  userName: string = '';
  showLogoutPopup: boolean = false;
  showSuccessPopup: boolean = false;
  showDropdown: boolean = false;

  // auth = this.authService;

  title = 'BuildEase';
  adminRoute = '/admin';

  constructor(private authService: AuthService, private router: Router) {}

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
    this.userName = this.authService.getUsername();
  }
}