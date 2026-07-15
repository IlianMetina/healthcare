import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNotesModal } from './add-notes-modal';

describe('AddNotesModal', () => {
  let component: AddNotesModal;
  let fixture: ComponentFixture<AddNotesModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddNotesModal]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddNotesModal);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
