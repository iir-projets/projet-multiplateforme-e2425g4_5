import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsPlantesComponent } from './details-plantes.component';

describe('DetailsPlantesComponent', () => {
  let component: DetailsPlantesComponent;
  let fixture: ComponentFixture<DetailsPlantesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailsPlantesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailsPlantesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
