import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaractmodalComponent } from './caractmodal.component';

describe('CaractmodalComponent', () => {
  let component: CaractmodalComponent;
  let fixture: ComponentFixture<CaractmodalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CaractmodalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CaractmodalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
