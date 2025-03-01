import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminEditMaterialComponent } from './admin-edit-material.component';

describe('AdminEditMaterialComponent', () => {
  let component: AdminEditMaterialComponent;
  let fixture: ComponentFixture<AdminEditMaterialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminEditMaterialComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminEditMaterialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
