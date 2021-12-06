import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { UserService } from './../../../services/user.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserDTO } from './../../../models/user-model';
import { Component, Inject, Input, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CustomValidators } from 'src/app/validators/custom-validators';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  title:string = "";

  @Input()
  userDTO!: UserDTO;

  addForm!: boolean;
  addOrEditButton: string = "Add";

  userForm: FormGroup;
  name: FormControl;
  username: FormControl;
  password: FormControl;
  passwordConfirmation: FormControl;
  email: FormControl;
  location: FormControl;
  bio: FormControl;
  pictureUrl: FormControl;

  constructor(private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: UserDTO,
    private snackBar: MatSnackBar) {
    this.name = new FormControl('', [Validators.required]);
    this.username = new FormControl('', [Validators.required]);
    this.password = new FormControl('', [Validators.required]);
    this.passwordConfirmation = new FormControl('', [Validators.required]);
    this.email = new FormControl('', [Validators.required, Validators.email]);
    this.location = new FormControl('', [Validators.required]);
    this.bio = new FormControl('', [Validators.required]);
    this.pictureUrl = new FormControl('', [Validators.required]);
    this.userForm = new FormGroup({
      name: this.name,
      username: this.username,
      email: this.email,
      location: this.location,
      bio: this.bio,
      pictureUrl: this.pictureUrl,
      password: this.password,
      passwordConfirmation: this.passwordConfirmation,
    }, [CustomValidators.checkPassword],)
   }

  ngOnInit(): void {
    // this.userDTO = this.data;
    // this.addOrEdit();
  }

  ngAfterViewInit(): void {
    this.userDTO = this.data;
    this.addOrEdit();
  }

  onSubmit(): void {
    if(this.userForm.valid) {
    if(this.addForm){
      this.addNewUser();
    } else {
      this.editUser();
    }
  }
  }

  addOrEdit(): void {
    if(this.userDTO != null){
      this.addForm = false;
      this.addOrEditButton = "Save Edit"
      this.title = "Edit details"
      this.fillFormToEdit();
    } else {
      this.title = "Enter details to sign up"
      this.addForm = true;
      this.addOrEditButton = "Sign up"
    }
  }

   fillFormToEdit():void {
    this.userForm.patchValue(this.userDTO);
  }

  addNewUser():void {
    const newUser: UserDTO = this.createUserDTOFromForm();
    this.userService.addUser(newUser).subscribe(result => {
      console.log(result)
    })
  }

  editUser():void {
    const editedUser: UserDTO = this.createUserDTOFromForm();
    this.userService.editUser(editedUser).subscribe(result => {
      console.log(result)
      this.snackBar.open("Details Updated Successfully")
    },
    error => {
      this.snackBar.open("Failed to update details, please try again")
    }
    )
  }


  private createUserDTOFromForm(): UserDTO {
      let id: number;
      if(this.userDTO == null){
        id = 0;
      } else {
        id = this.userDTO.id
      }
    return {
      id: id,
      name: this.name.value,
      username: this.username.value,
      password: this.password.value,
      email: this.email.value,
      location: this.location.value,
      bio: this.bio.value,
      pictureUrl: this.pictureUrl.value,
      roles: []
    };
  }

}
