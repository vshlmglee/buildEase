import { Component, OnInit } from '@angular/core';
import { Feedback } from 'src/app/models/feedback.model';
import { AuthService } from 'src/app/services/auth.service';
import { FeedbackService } from 'src/app/services/feedback.service';

@Component({
  selector: 'app-userviewfeedback',
  templateUrl: './userviewfeedback.component.html',
  styleUrls: ['./userviewfeedback.component.css']
})
export class UserviewfeedbackComponent implements OnInit {

  feedback: Feedback[] = [];
  userId: any; // Replace with actual user ID from the local storage
  selectedMaterial: any = null;
  deleteModalOpen: boolean = false;
  feedbackToDelete: number | null = null;

  constructor(private feedbackService: FeedbackService, private readonly authService: AuthService) {
    this.userId = this.authService.getUserId();
  }

  ngOnInit(): void {
    this.getFeedbacks();
  }

  getFeedbacks() {
    this.feedbackService.getAllFeedbacksByUserId(this.userId).subscribe((data: Feedback[]) => {
      this.feedback = data;
      console.log(this.feedback);
    });
  }

  viewMaterialInfo(material: any) {
    this.selectedMaterial = material;
  }

  closeModal() {
    this.selectedMaterial = null;
  }

  openDeleteModal(feedbackId: number) {
    this.deleteModalOpen = true;
    this.feedbackToDelete = feedbackId;
  }

  closeDeleteModal() {
    this.deleteModalOpen = false;
    this.feedbackToDelete = null;
  }

  confirmDelete() {
    if (this.feedbackToDelete !== null) {
      this.feedbackService.deleteFeedback(this.feedbackToDelete).subscribe(() => {
        this.getFeedbacks(); 
        this.closeDeleteModal();
      });
    }
  }
}
