import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MoviedialogComponent } from './moviedialog.component';

describe('MoviedialogComponent', () => {
  let component: MoviedialogComponent;
  let fixture: ComponentFixture<MoviedialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MoviedialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MoviedialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
