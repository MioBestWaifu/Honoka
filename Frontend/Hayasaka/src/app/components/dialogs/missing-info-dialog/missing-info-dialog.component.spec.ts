import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MissingInfoDialogComponent } from './missing-info-dialog.component';

describe('MissingInfoDialogComponent', () => {
  let component: MissingInfoDialogComponent;
  let fixture: ComponentFixture<MissingInfoDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MissingInfoDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MissingInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
