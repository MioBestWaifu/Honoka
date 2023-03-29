import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FootTestComponent } from './foot-test.component';

describe('FootTestComponent', () => {
  let component: FootTestComponent;
  let fixture: ComponentFixture<FootTestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FootTestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FootTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
