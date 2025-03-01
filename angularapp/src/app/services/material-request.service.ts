import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MaterialRequest } from '../models/material-request.model';
import { API_END_POINTS } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class MaterialRequestService {


  public apiUrl:string='https://8080-fcefecaadabeaddefddcffbdffdadffbedf.premiumproject.examly.io/api';
  constructor(private  readonly http:HttpClient) { }

  getAllMaterialRequests():Observable<MaterialRequest[]>{
    return this.http.get<MaterialRequest[]>(`${API_END_POINTS.URL}/materialRequest`)
  }

  getMaterialRequestsByUserId(userId:number):Observable<MaterialRequest[]>{
    return this.http.get<MaterialRequest[]>(`${API_END_POINTS.URL}/materialRequest/user/${userId}`)
  }

  addMaterialRequest(materialRequest:MaterialRequest):Observable<MaterialRequest>{
    return this.http.post<MaterialRequest>(`${API_END_POINTS.URL}/materialRequest`,materialRequest)
  }

  updateMaterialRequest(materialRequestId:number, request:MaterialRequest):Observable<MaterialRequest>{
    return this.http.put<MaterialRequest>(`${API_END_POINTS.URL}/materialRequest/${materialRequestId}`,request)
  }

  deleteMaterialRequest(materialRequestId:number):Observable<void>{
    return this.http.delete<void>(`${API_END_POINTS.URL}/materialRequest/${materialRequestId}`)
  }

  getAdminInsights():Observable<any[]>{
    return this.http.get<any[]>(`${API_END_POINTS.URL}/materialRequest/admin/insights`)
  }

  getMaterialRequestsById(materialRequestId:number):Observable<MaterialRequest[]>{
    return this.http.get<MaterialRequest[]>(`${API_END_POINTS.URL}/materialRequest/${materialRequestId}`)
  }

}
