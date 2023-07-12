import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstanceForProviderCardComponent } from './instance-for-provider-card.component';

describe('InstanceForProviderCardComponent', () => {
  let component: InstanceForProviderCardComponent;
  let fixture: ComponentFixture<InstanceForProviderCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstanceForProviderCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InstanceForProviderCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
