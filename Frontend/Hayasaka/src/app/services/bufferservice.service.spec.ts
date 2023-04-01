import { TestBed } from '@angular/core/testing';

import { BufferserviceService } from './bufferservice.service';

describe('BufferserviceService', () => {
  let service: BufferserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BufferserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
