
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MaterialService } from 'src/app/services/material.service';
import { Material } from 'src/app/models/material.model'; // Assuming you have a Material model
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-admin-edit-material',
  templateUrl: './admin-edit-material.component.html',
  styleUrls: ['./admin-edit-material.component.css']
})
export class AdminEditMaterialComponent implements OnInit {
  material: Material | null = null;  // Variable to hold the fetched material details
  errorMessage: string | null = null;
  editForm: FormGroup

  materialId: any;

  constructor(
    private service: MaterialService,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private readonly router:Router) {

    this.editForm = this.formBuilder.group({
      materialName: ['', [Validators.required,Validators.pattern(/^[a-zA-Z0-9\s]{2,}$/)]],
      description: ['', Validators.required],
      category: ['', Validators.required],
      pricePerUnit: ['', [Validators.required, Validators.min(1)]],
      unitType: ['', Validators.required],
      availabilityStatus: ['', Validators.required]
    });

  }

  ngOnInit(): void {


    this.activatedRoute.queryParams.subscribe((res) => {

      this.materialId = res.materialId;

      if (this.materialId) {
        this.service.getMaterialById(this.materialId).subscribe((res) => {
          console.log(res);
          this.editForm.patchValue({
            materialName: res.materialName,
            description: res.description,
            category: res.category,
            pricePerUnit: res.pricePerUnit,
            unitType: res.unitType,
            availabilityStatus: res.availabilityStatus
          });
        },
          error => {
            this.errorMessage = 'Error fetching material details';
            console.error('Error fetching material:', error);
          });
      }



    })
  }

  adminEditMaterial(): void {
    this.material = this.editForm.value;
    this.material.materialId = this.materialId;

    if (this.editForm.valid) {
      this.service.updateMaterial(this.materialId, this.material).subscribe((res) => {
        console.log('response recieved: ',res);
        this.router.navigate(['/admin/view-material']);

      })
    }

  }


  get f() {
    return this.editForm.controls;
  }
}