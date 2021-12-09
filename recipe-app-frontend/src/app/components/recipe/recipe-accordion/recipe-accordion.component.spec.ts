import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipeAccordionComponent } from './recipe-accordion.component';

describe('RecipeAccordionComponent', () => {
  let component: RecipeAccordionComponent;
  let fixture: ComponentFixture<RecipeAccordionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecipeAccordionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipeAccordionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
