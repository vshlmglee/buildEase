import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Material } from '../models/material.model';
import { API_END_POINTS } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class MaterialService {


  constructor(private readonly http:HttpClient) { }
  getAllMaterials():Observable<Material[]>{
    return this.http.get<Material[]>(`${API_END_POINTS.URL}/material`);
  }

  getMaterialById(materialId:number):Observable<Material>{
    return this.http.get<Material>(`${API_END_POINTS.URL}/material/${materialId}`);
  }
  addMaterial(material:Material):Observable<Material>{
    return this.http.post<Material>(`${API_END_POINTS.URL}/material`,material);
  }
  updateMaterial(materialId:number,material:Material):Observable<Material>{
    return this.http.put<Material>(`${API_END_POINTS.URL}/material/${materialId}`,material)
  }
  deleteMaterial(materialId:number):Observable<Material>{
    return this.http.delete<Material>(`${API_END_POINTS.URL}/material/${materialId}`);
  }


}
