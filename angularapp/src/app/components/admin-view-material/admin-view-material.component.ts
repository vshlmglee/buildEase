import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Material } from 'src/app/models/material.model';
import { MaterialService } from 'src/app/services/material.service';

@Component({
  selector: 'app-admin-view-material',
  templateUrl: './admin-view-material.component.html',
  styleUrls: ['./admin-view-material.component.css']

})
export class AdminViewMaterialComponent implements OnInit {
  materials: Material[] = [];
  filteredMaterials: Material[] = [];

  selectedMaterial: Material | null = null;
  errorMessage: string | null = null;

  searchQuery: string = ''

  constructor(
    private service: MaterialService,
    private router: Router) { }

  ngOnInit(): void {
    this.adminGetAllMaterials();
  }

  adminGetAllMaterials(): void {
    this.service.getAllMaterials().subscribe((data) => {
      this.filteredMaterials = data;
      this.materials = data;
    },
      (error) => {
        console.error('Error fetching materials', error);
        this.errorMessage = 'Failed to load materials. Please try again later.';
      }
    );
  }

  getMaterialById(materialId: number): void {
    this.service.getMaterialById(materialId).subscribe((data) => {
      this.selectedMaterial = data;
    },
      (error) => {
        console.error('Error fetching material by ID', error);
        this.errorMessage = 'Failed to load material details. Please try again later.';
      }
    );
  }


  toggleStockStatus(material: Material): void {
    // Toggle the availabilityStatus
    console.log(material);
    this.selectedMaterial = { ...material };

    let availabilityStatus = material.availabilityStatus === 'In Stock' ? 'Out of Stock':'In Stock';
      this.selectedMaterial.availabilityStatus = availabilityStatus;
      this.service.updateMaterial(this.selectedMaterial.materialId, this.selectedMaterial).subscribe((res) => {

        console.log(res);
        this.adminGetAllMaterials();
      })
    }

  

  searchByMaterialName(): void {
    if (this.searchQuery.trim() === '') {

      this.filteredMaterials = this.materials; // Reset to all if query is empty

    } else {

      this.filteredMaterials = this.materials.filter((material) =>

        material.materialName.toLowerCase().includes(this.searchQuery.toLowerCase())

      );

    }

  }


  clearSelectedMaterial(): void {
    this.selectedMaterial = null;
  }

  // Method to delete material
  adminDeleteMaterial(materialId: number): void {
    if (confirm("Are you sure you want to delete this material?")) {
      this.service.deleteMaterial(materialId).subscribe(
        () => {
          this.adminGetAllMaterials();
          alert("Material deleted successfully!");
        },
        error => {
          console.error('Error deleting material:', error);
          this.errorMessage = 'Failed to delete material.'; // error message
        }
      );
    }
  }

  adminUpdateMaterial(materialId: number, material: Material): void {
    console.log(materialId, material);
    // this.service.updateMaterial(materialId, material).subscribe(()=>{
    //   this.router.navigate(["admin/edit-material"])
    // })
    this.router.navigate(["admin/edit-material"], {
      queryParams: {
        materialId: materialId
      }
    })


  }
}


