import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ErrorComponent } from './components/error/error.component';
import { SignupComponent } from './components/signup/signup.component';
import { LoginComponent } from './components/login/login.component';
import { AdminnavComponent } from './components/adminnav/adminnav.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { AdminMaterialComponent } from './components/admin-material/admin-material.component';
import { AdminViewMaterialComponent } from './components/admin-view-material/admin-view-material.component';
import { AdminEditMaterialComponent } from './components/admin-edit-material/admin-edit-material.component';
import { AdminviewappliedrequestComponent } from './components/adminviewappliedrequest/adminviewappliedrequest.component';
import { AdminviewfeedbackComponent } from './components/adminviewfeedback/adminviewfeedback.component';
import { AdminInsightsComponent } from './components/admin-insights/admin-insights.component';
import { UserviewmaterialComponent } from './components/userviewmaterial/userviewmaterial.component';
import { UseraddrequestComponent } from './components/useraddrequest/useraddrequest.component';
import { UserviewappliedrequestComponent } from './components/userviewappliedrequest/userviewappliedrequest.component';
import { UseraddfeedbackComponent } from './components/useraddfeedback/useraddfeedback.component';
import { UserviewfeedbackComponent } from './components/userviewfeedback/userviewfeedback.component';

import { AuthGuard } from './guards/auth.guard';


const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'home-page', component: HomePageComponent },
  { path: 'error', component: ErrorComponent },
  { path: 'register', component: SignupComponent },
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminnavComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'user', component: UsernavComponent, canActivate: [AuthGuard], data: { roles: ['User'] } },
  { path: 'admin/add-material', component: AdminMaterialComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'admin/view-material', component: AdminViewMaterialComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'admin/edit-material', component: AdminEditMaterialComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'admin/view-appliedrequest', component: AdminviewappliedrequestComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'admin/view-feedbacks', component: AdminviewfeedbackComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'admin-insights', component: AdminInsightsComponent, canActivate: [AuthGuard], data: { roles: ['Admin'] } },
  { path: 'user/view-material', component: UserviewmaterialComponent, canActivate: [AuthGuard], data: { roles: ['User'] } },
  { path: 'user/add-request', component: UseraddrequestComponent, canActivate: [AuthGuard], data: { roles: ['User'] } },
  { path: 'user/view/appliedrequest', component: UserviewappliedrequestComponent, canActivate: [AuthGuard], data: { roles: ['User'] } },
  { path: 'user/add-feedback', component: UseraddfeedbackComponent, canActivate: [AuthGuard], data: { roles: ['User'] } },
  { path: 'user/view-feedback', component: UserviewfeedbackComponent, canActivate: [AuthGuard], data: { roles: ['User'] } },
  { path: '**',  redirectTo:'error',pathMatch:'full'}
];




@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}

