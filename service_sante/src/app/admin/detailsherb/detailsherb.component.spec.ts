import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsherbComponent } from './detailsherb.component';

describe('DetailsherbComponent', () => {
  let component: DetailsherbComponent;
  let fixture: ComponentFixture<DetailsherbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailsherbComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailsherbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
