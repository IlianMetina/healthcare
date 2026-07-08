import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurveCard } from './curve-card';

describe('CurveCard', () => {
  let component: CurveCard;
  let fixture: ComponentFixture<CurveCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CurveCard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CurveCard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
