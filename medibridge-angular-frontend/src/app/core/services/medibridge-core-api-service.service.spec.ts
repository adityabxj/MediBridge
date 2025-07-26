import { TestBed } from '@angular/core/testing';

import { MedibridgeCoreApiServiceService } from './medibridge-core-api-service.service';

describe('MedibridgeCoreApiServiceService', () => {
  let service: MedibridgeCoreApiServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedibridgeCoreApiServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
