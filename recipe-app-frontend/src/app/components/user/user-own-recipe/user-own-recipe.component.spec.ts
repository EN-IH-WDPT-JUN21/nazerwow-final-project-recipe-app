import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserOwnRecipeComponent } from './user-own-recipe.component';

describe('UserOwnRecipeComponent', () => {
  let component: UserOwnRecipeComponent;
  let fixture: ComponentFixture<UserOwnRecipeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserOwnRecipeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserOwnRecipeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
