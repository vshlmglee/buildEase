import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Material } from 'src/app/models/material.model';
import { MaterialService } from 'src/app/services/material.service';

@Component({
  selector: 'app-userviewmaterial',
  templateUrl: './userviewmaterial.component.html',
  styleUrls: ['./userviewmaterial.component.css']
})
export class UserviewmaterialComponent implements OnInit {
  materials: Material[] = [];
  filteredMaterial: Material[] = [];
  searchText: string = '';
  errorMessage: string | null = null;
 

  constructor(private service:MaterialService,private router:Router){ }

  ngOnInit(): void {
   this.userGetAllMaterials()
  }
  userGetAllMaterials(): void {
    this.service.getAllMaterials().subscribe((data) => {
        this.materials = data;
        this.filteredMaterial = data;
      },
      (error) => {
        console.error('Error fetching materials', error);
        this.errorMessage = 'Failed to load materials.';
      }
    );
  }
    
 toggleRequest(materialId:any): void {
 this.router.navigate(["/user/add-request"],{queryParams:{materialId:materialId}});
}


searchByMaterialName(): void {
  if (this.searchText.trim() === '') {
    this.filteredMaterial = this.materials;  // Reset to all if text is empty
  } else {
    this.filteredMaterial = this.materials.filter((materials) => {
     // console.log(materialRequest.material.materialName.toLowerCase().includes(this.searchText.toLowerCase()));
     return materials.materialName.toLowerCase().includes(this.searchText.toLowerCase());
    });
  }
}

}