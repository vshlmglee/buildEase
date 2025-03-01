import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FeedbackService } from 'src/app/services/feedback.service';
import { Feedback } from 'src/app/models/feedback.model';
import { AuthService } from 'src/app/services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-useraddfeedback',
  templateUrl: './useraddfeedback.component.html',
  styleUrls: ['./useraddfeedback.component.css']
})
export class UseraddfeedbackComponent implements OnInit {

  feedbackForm: FormGroup;
  categories: string[] = ['All Categories', 'Concrete', 'Brick' , 'Steel' , 'Stone' , 'Wood' , 'Glass' , 'Sand' , 'Blocks']; // Add your categories here
  successMessage: string;
  errorMessage: string;

  constructor(private fb: FormBuilder, 
    private feedbackService: FeedbackService,
    private readonly authService:AuthService, 
    private readonly route:ActivatedRoute,
    private readonly router:Router) { }

  ngOnInit(): void {
    this.feedbackForm = this.fb.group({
      category: ['', Validators.required],
      feedbackText: ['', [Validators.required,Validators.minLength(5)]]
    });
  }

  onSubmit() {
    // let feedback:Feedback = {
    //   feedbackId?:number,
    // userId:number,
    // materialId:number,
    // category:string,
    // feedbackText:string,
    // date:new Date(),
    // }

    let materialId : any;
    this.route.queryParams.subscribe((res)=>{
      materialId = res.materialId;
    })
    if (this.feedbackForm.invalid) {
      this.errorMessage = 'Please fill in all fields.';
      return;
    }

    const feedback: Feedback = {
      user:{

        userId: this.authService.getUserId() // Replace with actual user ID
      },

      material:{

        materialId:materialId  // Replace with actual material ID if needed
      },
      category: this.feedbackForm.value.category,
      feedbackText: this.feedbackForm.value.feedbackText,
      date: new Date()
    };

    
    console.log(feedback)
    this.feedbackService.sendFeedback(feedback).subscribe(
      (data) => {
        console.log(data)
        this.successMessage = 'Successfully Added!';
        this.feedbackForm.reset();
        this.router.navigate(['/user/view-feedback']);
        
      },
      error => {
        this.errorMessage = 'Failed to add feedback. Please try again.';
      }
    );
  }
}