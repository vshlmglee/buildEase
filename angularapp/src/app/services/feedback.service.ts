import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Feedback } from '../models/feedback.model';
import { API_END_POINTS } from '../app.constants';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  constructor(private readonly httpClient:HttpClient) { }

  sendFeedback(feedback:Feedback):Observable<Feedback>{

    return this.httpClient.post<Feedback>(`${API_END_POINTS.URL}/feedback`,feedback)
  }

  getAllFeedbacksByUserId(userId:number):Observable<Feedback[]>{
    return this.httpClient.get<Feedback[]>(`${API_END_POINTS.URL}/feedback/user/${userId}`)
  }

  getAllFeedbacksByFeedbackId(feedbackId:number):Observable<Feedback[]>{
    return this.httpClient.get<Feedback[]>(`${API_END_POINTS.URL}/feedback/${feedbackId}`)
  }

  deleteFeedback(feedbackId:number):Observable<Feedback>{
    return this.httpClient.delete<Feedback>(`${API_END_POINTS.URL}/feedback/${feedbackId}`)
  }

  getFeedbacks():Observable<Feedback[]>{
    return this.httpClient.get<Feedback[]>(`${API_END_POINTS.URL}/feedback`)
  }
}
