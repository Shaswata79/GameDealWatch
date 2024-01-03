import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateGameListComponent } from './update-game-list.component';

describe('UpdateGameListComponent', () => {
  let component: UpdateGameListComponent;
  let fixture: ComponentFixture<UpdateGameListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UpdateGameListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateGameListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
