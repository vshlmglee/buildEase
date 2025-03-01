import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-adminviewfeedback',
  templateUrl: './adminviewfeedback.component.html',
  styleUrls: ['./adminviewfeedback.component.css']
})
export class AdminviewfeedbackComponent implements OnInit {

  feedback: Feedback[] = [];
  categories: string[] = ['All Categories', 'Concrete','Cement' , 'Brick' , 'Steel' , 'Stone' , 'Wood' , 'Glass' , 'Sand' , 'Blocks']; // Add your categories here
  selectedCategory: string = 'All Categories';
  allFeedbacks: Feedback[] = []; // Store all feedbacks for filtering
  selectedUser: any = null;
  selectedMaterial: any = null;

  constructor(private feedbackService: FeedbackService) { }

  ngOnInit(): void {
    this.getFeedbacks();
  }

  getFeedbacks() {
    this.feedbackService.getFeedbacks().subscribe((data: Feedback[]) => {
      console.log(data);
      this.allFeedbacks = data; // Store all feedbacks
      this.filterFeedbacks(); // Apply initial filter
    });
  }

  filterFeedbacks() {
    if (this.selectedCategory === 'All Categories') {
      this.feedback = this.allFeedbacks;
    } else {
      this.feedback = this.allFeedbacks.filter(fb => fb.category === this.selectedCategory);
    }
  }

  showProfile(user: any) {
    this.selectedUser = user;
  }

  viewMaterialInfo(material: any) {
    this.selectedMaterial = material;
  }

  closeModal() {
    this.selectedUser = null;
    this.selectedMaterial = null;
  }
}
