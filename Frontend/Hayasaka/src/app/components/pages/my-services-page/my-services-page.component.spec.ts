import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyServicesPageComponent } from './my-services-page.component';

describe('MyServicesPageComponent', () => {
  let component: MyServicesPageComponent;
  let fixture: ComponentFixture<MyServicesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyServicesPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MyServicesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
