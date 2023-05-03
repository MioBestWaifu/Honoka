import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FailedUpdateDialogComponent } from './failed-update-dialog.component';

describe('FailedUpdateDialogComponent', () => {
  let component: FailedUpdateDialogComponent;
  let fixture: ComponentFixture<FailedUpdateDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FailedUpdateDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FailedUpdateDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
