import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditHerbComponent } from './edit-herb.component';

describe('EditHerbComponent', () => {
  let component: EditHerbComponent;
  let fixture: ComponentFixture<EditHerbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditHerbComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditHerbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
