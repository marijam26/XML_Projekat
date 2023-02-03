import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchApplicationsComponent } from './search-applications.component';

describe('SearchApplicationsComponent', () => {
  let component: SearchApplicationsComponent;
  let fixture: ComponentFixture<SearchApplicationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchApplicationsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchApplicationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
