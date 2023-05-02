import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActiveUserPageComponent } from './active-user-page.component';

describe('ActiveUserPageComponent', () => {
  let component: ActiveUserPageComponent;
  let fixture: ComponentFixture<ActiveUserPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActiveUserPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActiveUserPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
