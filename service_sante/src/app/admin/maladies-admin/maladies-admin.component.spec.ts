import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MaladiesAdminComponent } from './maladies-admin.component';

describe('MaladiesAdminComponent', () => {
  let component: MaladiesAdminComponent;
  let fixture: ComponentFixture<MaladiesAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MaladiesAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MaladiesAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
