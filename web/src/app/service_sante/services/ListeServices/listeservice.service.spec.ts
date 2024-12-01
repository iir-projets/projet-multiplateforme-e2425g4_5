import { TestBed } from '@angular/core/testing';

import { ListeserviceService } from './listeservice.service';

describe('ListeserviceService', () => {
  let service: ListeserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListeserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
