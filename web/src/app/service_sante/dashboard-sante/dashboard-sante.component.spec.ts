import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardSanteComponent } from './dashboard-sante.component';

describe('DashboardSanteComponent', () => {
  let component: DashboardSanteComponent;
  let fixture: ComponentFixture<DashboardSanteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardSanteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardSanteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
