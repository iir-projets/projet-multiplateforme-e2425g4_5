import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicamentsAdminComponent } from './medicaments-admin.component';

describe('MedicamentsAdminComponent', () => {
  let component: MedicamentsAdminComponent;
  let fixture: ComponentFixture<MedicamentsAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MedicamentsAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedicamentsAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
