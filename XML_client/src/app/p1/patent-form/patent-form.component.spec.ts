import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatentFormComponent } from './patent-form.component';

describe('PatentFormComponent', () => {
  let component: PatentFormComponent;
  let fixture: ComponentFixture<PatentFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatentFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatentFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
