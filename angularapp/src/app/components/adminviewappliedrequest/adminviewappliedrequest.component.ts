import { Component, OnInit } from '@angular/core';

import { MaterialRequestService } from 'src/app/services/material-request.service';

import { MaterialRequest } from 'src/app/models/material-request.model';

@Component({

  selector: 'app-adminviewappliedrequest',

  templateUrl: './adminviewappliedrequest.component.html',

  styleUrls: ['./adminviewappliedrequest.component.css']

})

export class AdminviewappliedrequestComponent implements OnInit {

  materialRequests: MaterialRequest[] = [];
  filteredMaterialRequests: MaterialRequest[] = [];
  searchQuery: string = '';
  selectedMaterialRequest: MaterialRequest | null = null;

  constructor(private readonly materialRequestService: MaterialRequestService) { }
  ngOnInit(): void {
    this.getAllMaterialRequests();

  }

  getAllMaterialRequests(): void {

    this.materialRequestService.getAllMaterialRequests().subscribe(

      (data) => {
        console.log(data);
        this.materialRequests = data;
        this.filteredMaterialRequests = data; // Initialize filtered list
      },
      (error) => {
        console.error('Error fetching material requests:', error);
      }

    );
  }



  searchByMaterialName(): void {

    if (this.searchQuery.trim() === '') {
      this.filteredMaterialRequests = this.materialRequests; // Reset to all if query is empty
    } else {
      this.filteredMaterialRequests = this.materialRequests.filter((material) =>
        material.material.materialName.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    }
  }



  filterByStatus(status: string): void {

    if (status === 'All') {
      this.filteredMaterialRequests = this.materialRequests;
    } else {
      this.filteredMaterialRequests = this.materialRequests.filter((material) => material.status === status);
    }
  }






  approveRequest(materialRequestId: any): void {
    // Find the material request by ID
    const materialToUpdate = this.materialRequests.find((material) => material.materialRequestId === materialRequestId);
    if (materialToUpdate) {
      // Create an updated request object with all required fields
      const updatedRequest: MaterialRequest = {
        ...materialToUpdate, // Spread operator to copy existing properties
        status: 'Approved', // Update the status
      };
      // Call the service to update the material request
      this.materialRequestService.updateMaterialRequest(materialRequestId, updatedRequest).subscribe(

        () => {
          alert('Request approved successfully.');
          this.getAllMaterialRequests(); // Refresh the list after updating
        },
        (error) => {
          console.error('Error approving request:', error);
        }

      );
    } else {
      console.error('Material request not found.');
    }
  }



  rejectRequest(materialRequestId: number): void {

    const materialToUpdate = this.materialRequests.find((material) => material.materialRequestId === materialRequestId);
    console.log(materialToUpdate);
    if (materialToUpdate) {
      const updatedRequest: MaterialRequest = {
        ...materialToUpdate,
        status: 'Rejected',
      };

      this.materialRequestService.updateMaterialRequest(materialRequestId, updatedRequest).subscribe(
        () => {
          alert('Request rejected successfully.');
          this.getAllMaterialRequests();
        },
        (error) => {
          console.error('Error rejecting request:', error);
        }
      );
    } else {
      console.error('Material request not found.');
    }

  }


  showMoreDetails(material: MaterialRequest): void {

    this.selectedMaterialRequest = material;

  }

  closeModal(): void {

    this.selectedMaterialRequest = null;

  }

}
