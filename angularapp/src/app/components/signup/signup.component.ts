import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

    
  signupForm: FormGroup;
  user:User
  errorMessage: string = '';
  successMesssage: string = ''; 
  constructor(private fb: FormBuilder, private authService: AuthService, private router:Router) {
  }

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      email: ['', [Validators.required, Validators.email, Validators.pattern('^[a-zA-Z0-9._%+-]+@gmail\.com$')]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      username: ['', [Validators.required]],
      mobileNumber: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      userRole: ['', [Validators.required]]
    });
  }

  onSubmit(): void {
    if (this.signupForm.valid) {
      this.user = this.signupForm.value;
      this.authService.register(this.user).subscribe(response => {
        console.log('User registered successfully', response);
        this.errorMessage = '';
        this.successMesssage = 'Registered Successful'; 
        this.router.navigate(["/login"]); 

      }, error => {
        this.successMesssage ='';
        this.errorMessage = "User already exists! Try again";
        console.error('Error registering user', error);
      });
    } else {
      console.log('Form is invalid');
    }
  }
 
}