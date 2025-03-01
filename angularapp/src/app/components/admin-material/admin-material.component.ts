import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Material } from 'src/app/models/material.model';
import { MaterialService } from 'src/app/services/material.service';

@Component({
  selector: 'app-admin-material',
  templateUrl: './admin-material.component.html',
  styleUrls: ['./admin-material.component.css']
})
export class AdminMaterialComponent implements OnInit {
  materialForm: FormGroup;
  materials: Material[] = [];
  errorMessage: string = ''; 

  constructor(private service: MaterialService, 
    private formBuilder: FormBuilder,
    private readonly router:Router) { }

  ngOnInit(): void {
    this.initMaterialForm();
    this.loadMaterials(); // Load materials when component initializes
  }

  private initMaterialForm(): void {
    this.materialForm = this.formBuilder.group({
      materialName: ['', [Validators.required,Validators.pattern(/^[a-zA-Z0-9\s]{2,}$/)]],
      description: ['', Validators.required],
      category: ['', Validators.required],
      pricePerUnit: ['', [Validators.required, Validators.min(1)]],
      unitType: ['', Validators.required],
      availabilityStatus: ['', Validators.required]
    });
  }

  adminAddMaterial(): void {
    if (this.materialForm.valid) {
      this.service.addMaterial(this.materialForm.value).subscribe(
        () => {
          alert("Material added Successfully!");
          this.materialForm.reset(); // Reset the form after successful submission
          this.loadMaterials(); // Refresh the materials list
          this.router.navigate(['/admin/view-material'])
        },
        error => {
          console.error('Error adding material:', error);
          this.errorMessage = 'Failed to add material.'; // Set error message
        }
      );
    }
  }

  loadMaterials(): void {
    this.service.getAllMaterials().subscribe(
      (res) => {
        this.materials = res;
      },
      error => {
        console.error('Error loading materials:', error);
        this.errorMessage = 'Failed to load materials.'; //error message
      }
    );
  }

 

 

  get f(){
    return this.materialForm.controls;
  }
}