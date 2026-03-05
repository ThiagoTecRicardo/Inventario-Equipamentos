import { TestBed } from '@angular/core/testing';

import { Equipamento } from './equipamento';

describe('Equipamento', () => {
  let service: Equipamento;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Equipamento);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
