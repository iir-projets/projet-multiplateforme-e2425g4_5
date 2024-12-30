import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllergiesAdminComponent } from './allergies-admin.component';

describe('AllergiesAdminComponent', () => {
  let component: AllergiesAdminComponent;
  let fixture: ComponentFixture<AllergiesAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AllergiesAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllergiesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
