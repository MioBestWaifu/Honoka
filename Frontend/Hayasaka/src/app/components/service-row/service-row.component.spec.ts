import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceRowComponent } from './service-row.component';

describe('ServiceRowComponent', () => {
  let component: ServiceRowComponent;
  let fixture: ComponentFixture<ServiceRowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServiceRowComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServiceRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
