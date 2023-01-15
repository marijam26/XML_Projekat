import { TestBed } from '@angular/core/testing';

import { Z1Service } from './z1.service';

describe('Z1Service', () => {
  let service: Z1Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Z1Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
