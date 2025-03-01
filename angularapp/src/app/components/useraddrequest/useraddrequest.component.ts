import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MaterialRequest } from 'src/app/models/material-request.model';
import { AuthService } from 'src/app/services/auth.service';
import { MaterialRequestService } from 'src/app/services/material-request.service';

@Component({
  selector: 'app-useraddrequest',
  templateUrl: './useraddrequest.component.html',
  styleUrls: ['./useraddrequest.component.css']
})
export class UseraddrequestComponent implements OnInit {

  materialRequestForm: FormGroup; // Form for material request
  isEditMode: boolean = false; // Flag to determine edit mode
  materialRequestId: number; // Store the ID of the material request being edited
  showValidationMessage = false;
  showSuccessPopup = false;

  
  constructor(private readonly materialRequestService: MaterialRequestService, private readonly fb: FormBuilder, private readonly router: Router, private authservice: AuthService,private readonly route:ActivatedRoute) {
    // Initialize the form with FormBuilder
    this.materialRequestForm = this.fb.group({
      deliveryAddress: ['', Validators.required],
      contactNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]], // Validating for a 10-digit number
      quantity: [null, [Validators.required, Validators.min(1)]],
      urgencyLevel: ['', Validators.required],
      preferredDeliveryDate: ['', Validators.required],
      timeSlot: ['', Validators.required],
      comments: ['']
    });

  }

  ngOnInit(): void {
    // You can check if we are in edit mode and load the data here if needed
    console.log("User Add Request Component Initialized");
  }

  // Method to add a new material request
  addRequest(): void {
    console.log(this.authservice.getUserId());
    console.log(this.materialRequestForm.value);
    let materialId:any; 
    this.route.queryParams.subscribe((res)=>{
      console.log(res);
      console.log(res.materialId);
      materialId = res.materialId;
    })

    let newMaterialRequest: MaterialRequest = {
      material: {
        materialId:materialId
      },
      user: {
        userId:this.authservice.getUserId()
      },
      status: "Pending",
      requestDate: new Date(),
      materialId:1,
      quantity: this.materialRequestForm.value.quantity,
      
      urgencyLevel: this.materialRequestForm.value.urgencyLevel, 
      preferredDeliveryDate: this.materialRequestForm.value.preferredDeliveryDate,
      timeSlot: this.materialRequestForm.value.timeSlot,
      deliveryAddress: this.materialRequestForm.value.deliveryAddress,
      contactNumber: this.materialRequestForm.value.contactNumber,
      comments: this.materialRequestForm.value.comments
    }
    console.log(newMaterialRequest);
    if (this.materialRequestForm.valid) {
      //const newRequest: MaterialRequest = this.materialRequestForm.value;
      //newRequest.userId = this.authservice.getUserId()
      //console.log(newRequest);
      this.materialRequestService.addMaterialRequest(newMaterialRequest).subscribe(
        response => {
          console.log("Material Request added successfully:", response);
          this.materialRequestForm.reset(); // Reset the form
          this.router.navigate(['/user/view/appliedrequest']);
        },
        error => {
          console.error("Error adding Material Request:", error);
        }            
      );
    } else {
      console.warn("Form is not valid");
    }
  }

  updateRequest(): void {
    if (this.materialRequestForm.valid && this.materialRequestId) {
      const updatedRequest: MaterialRequest = this.materialRequestForm.value;
      this.materialRequestService.updateMaterialRequest(this.materialRequestId, updatedRequest).subscribe(
        response => {
          console.log("Material Request updated successfully:", response);
          this.materialRequestForm.reset(); // Reset the form
          this.isEditMode = false; // Reset edit mode
        },
        error => {
          console.error("Error updating Material Request:", error);
        }
      );
    } else {
      console.warn("Form is not valid or ID is missing");
    }
  }

  // Method to load material request details when in edit mode
  loadMaterialRequest(materialRequestId: number): void {
    this.materialRequestId = materialRequestId; // Set the ID
    this.isEditMode = true; // Set edit mode
    this.materialRequestService.getMaterialRequestsById(materialRequestId).subscribe(
      response => {
        // Assuming response is of type MaterialRequest
        this.materialRequestForm.patchValue(response[0]); // Fill the form with existing request data
      },
      error => {
        console.error("Error loading Material Request:", error);
      }
    );
  }

  // Navigate to userviewmaterial component
  navigateToUserViewMaterial() {
    this.router.navigate(['/user/view-material']);
  }


  // Close success popup and navigate to userviewmaterial component
  closePopup() {
    this.showSuccessPopup = false;
    this.router.navigate(['/user/view-material']);
  }

}

