import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentsSidebar } from './appointments-sidebar';

describe('AppointmentsSidebar', () => {
  let component: AppointmentsSidebar;
  let fixture: ComponentFixture<AppointmentsSidebar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AppointmentsSidebar]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentsSidebar);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
