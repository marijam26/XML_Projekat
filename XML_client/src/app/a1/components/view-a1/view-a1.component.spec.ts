import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewA1Component } from './view-a1.component';

describe('ViewA1Component', () => {
  let component: ViewA1Component;
  let fixture: ComponentFixture<ViewA1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewA1Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewA1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
