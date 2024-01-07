import { TestBed } from '@angular/core/testing';

import { GamelistService } from './gamelist.service';

describe('GamelistService', () => {
  let service: GamelistService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GamelistService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
