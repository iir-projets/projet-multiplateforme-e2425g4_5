import { TestBed } from '@angular/core/testing';

import { DeseaseService } from './desease.service';

describe('DeseaseService', () => {
  let service: DeseaseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeseaseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
