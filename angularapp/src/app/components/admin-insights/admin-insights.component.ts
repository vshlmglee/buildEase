import { Component, OnInit } from '@angular/core';
import { MaterialRequestService } from 'src/app/services/material-request.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-insights',
  templateUrl: './admin-insights.component.html',
  styleUrls: ['./admin-insights.component.css']
})
export class AdminInsightsComponent implements OnInit {
  insights: any[] = [];
  selectedOrder: any | null = null;

  constructor(
    private readonly materialRequestService: MaterialRequestService,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    this.getAdminInsights();
  }

  getAdminInsights(): void {
    this.materialRequestService.getAdminInsights().subscribe(
      (data) => {
        this.insights = data;
      },
      (error) => {
        console.error('Error fetching admin insights:', error);
      }
    );
  }

  showOrderDetails(order: any): void {
    this.selectedOrder = order;
  }

  closeModal(): void {
    this.selectedOrder = null;
  }
}
