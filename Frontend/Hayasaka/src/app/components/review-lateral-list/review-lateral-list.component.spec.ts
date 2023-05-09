import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewLateralListComponent } from './review-lateral-list.component';

describe('ReviewLateralListComponent', () => {
  let component: ReviewLateralListComponent;
  let fixture: ComponentFixture<ReviewLateralListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewLateralListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReviewLateralListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
