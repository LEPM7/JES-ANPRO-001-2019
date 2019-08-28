import { TestBed } from '@angular/core/testing';

import { FiscaliasService } from './fiscalias.service';

describe('FiscaliasService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FiscaliasService = TestBed.get(FiscaliasService);
    expect(service).toBeTruthy();
  });
});
