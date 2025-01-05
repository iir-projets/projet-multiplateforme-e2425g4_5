import { TestBed } from '@angular/core/testing';

import { SharedArticleServiceService } from './shared-article-service.service';

describe('SharedArticleServiceService', () => {
  let service: SharedArticleServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SharedArticleServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
