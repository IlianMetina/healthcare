import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPatientModale } from './add-patient-modale';

describe('AddPatientModale', () => {
  let component: AddPatientModale;
  let fixture: ComponentFixture<AddPatientModale>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddPatientModale]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPatientModale);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
