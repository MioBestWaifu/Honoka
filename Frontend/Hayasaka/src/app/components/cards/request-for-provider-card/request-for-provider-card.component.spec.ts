import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestForProviderCardComponent } from './request-for-provider-card.component';

describe('RequestForProviderCardComponent', () => {
  let component: RequestForProviderCardComponent;
  let fixture: ComponentFixture<RequestForProviderCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RequestForProviderCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RequestForProviderCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
