import { TestBed } from '@angular/core/testing';

import { JoinstatsService } from './joinstats.service';

describe('JoinstatsService', () => {
  let service: JoinstatsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JoinstatsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
