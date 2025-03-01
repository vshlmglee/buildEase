import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from 'src/app/models/login.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  errorMessage: string = '';
  successMesssage: string = ''; 

  constructor(private formBuilder:FormBuilder, private authService: AuthService,private router:Router) {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    console.log("Login Component Initialized");
  }

  onSubmit(): void {
    if (this.loginForm.valid) {
      const login: Login = this.loginForm.value;
      this.authService.login(login).subscribe(userToken => {
        const token = userToken.token
        localStorage.setItem('token', token);    
        this.errorMessage = '';
        this.successMesssage = 'Login Successful'; 
        console.log("User Token :",token);
        console.log(this.authService.getUseremail());
        setTimeout(()=>{
          window.location.reload();
        },3000);
        this.router.navigate(["/home-page"]); 

      }, error => {
        this.successMesssage ='';
        this.errorMessage = "Invalid credientals";
        console.error('Error logging in user', error);
      });
    } else {
      console.log('Form is invalid');
    }
  }
}

