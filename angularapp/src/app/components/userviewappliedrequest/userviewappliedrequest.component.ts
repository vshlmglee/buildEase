import { Component, OnInit } from '@angular/core';
import { MaterialRequestService } from 'src/app/services/material-request.service';
import { MaterialRequest } from 'src/app/models/material-request.model';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-userviewappliedrequest',
  templateUrl: './userviewappliedrequest.component.html',
  styleUrls: ['./userviewappliedrequest.component.css']
})
export class UserviewappliedrequestComponent implements OnInit {
  materialRequests: MaterialRequest[] = [];
  selectedMaterialRequest: MaterialRequest;
  filteredMaterialRequests: MaterialRequest[] = [];
  searchText: string = '';
  userId:any;
  constructor(private readonly materialRequestService: MaterialRequestService, private readonly router: Router,private readonly authService:AuthService) {

    this.userId = this.authService.getUserId();
  }
  
  
  ngOnInit(): void {
    this.getAllMaterialRequests();
  }

  getAllMaterialRequests(): void {
    this.materialRequestService.getMaterialRequestsByUserId(this.userId).subscribe(
      (data) => {
        console.log(data);
        this.materialRequests = data;
        this.filteredMaterialRequests = data; // Initialize filtered requests
      },
      (error) => {
        console.error('Error fetching material requests:', error);
      }
    );
    // this.materialRequestService.getAllMaterialRequests().subscribe(
    //   (data) => {
    //     console.log(data);
    //     this.materialRequests = data;
    //     this.filteredMaterialRequests = data; // Initialize filtered requests
    //   },
    //   (error) => {
    //     console.error('Error fetching material requests:', error);
    //   }
    // );
  }

  searchByMaterialName(): void {
    if (this.searchText.trim() === '') {
      this.filteredMaterialRequests = this.materialRequests;  // Reset to all if text is empty
    } else {
      this.filteredMaterialRequests = this.materialRequests.filter((materialRequest) => {
       // console.log(materialRequest.material.materialName.toLowerCase().includes(this.searchText.toLowerCase()));
       return materialRequest.material.materialName.toLowerCase().includes(this.searchText.toLowerCase());
      });
    }
  }

  showMoreDetails(materialRequest: MaterialRequest): void {
    console.log(materialRequest);
    this.selectedMaterialRequest = materialRequest;
  }

  closeModal(): void {
    this.selectedMaterialRequest = null;
  }


  deleteMaterialRequest(materialRequestId: number): void {
    if (confirm('Are you sure you want to delete this material request?')) {
      this.materialRequestService.deleteMaterialRequest(materialRequestId).subscribe(
        () => {
          alert('Material request deleted successfully.');
          this.getAllMaterialRequests(); // Refresh the list
        },
        (error) => {
          console.error('Error deleting material request:', error);
        }
      );
    }
  }

  navigateToAddFeedback(materialId:any): void {
    this.router.navigate(['/user/add-feedback'],{queryParams:{
      materialId:materialId
    }});
  }
}