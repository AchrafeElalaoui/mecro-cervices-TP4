import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConferenceReviewsComponent } from './conference-reviews.component';

describe('ConferenceReviewsComponent', () => {
  let component: ConferenceReviewsComponent;
  let fixture: ComponentFixture<ConferenceReviewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConferenceReviewsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConferenceReviewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
