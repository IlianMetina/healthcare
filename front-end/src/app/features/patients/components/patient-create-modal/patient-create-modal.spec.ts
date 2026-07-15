import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientCreateModal } from './patient-create-modal';

describe('PatientCreateModal', () => {
  let component: PatientCreateModal;
  let fixture: ComponentFixture<PatientCreateModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PatientCreateModal]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatientCreateModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
