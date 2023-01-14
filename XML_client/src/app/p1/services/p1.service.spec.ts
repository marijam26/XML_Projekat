import { TestBed } from '@angular/core/testing';

import { P1Service } from './p1.service';

describe('P1Service', () => {
  let service: P1Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(P1Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
