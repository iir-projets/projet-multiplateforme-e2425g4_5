import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardherbComponent } from './cardherb.component';

describe('CardherbComponent', () => {
  let component: CardherbComponent;
  let fixture: ComponentFixture<CardherbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CardherbComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardherbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
