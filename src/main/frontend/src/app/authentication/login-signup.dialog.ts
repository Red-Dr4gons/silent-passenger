import {Component} from "@angular/core";
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'login-signup-dialog',
  templateUrl: 'login-signup.dialog.html',
  styleUrls: ['login-signup.dialog.css']
})
export class LoginSignupDialog {
  loginError = '';
  registerError = ''

  loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    }
  )

  registerForm = new FormGroup({
    address: new FormControl(''),
    email: new FormControl(''),
    name: new FormControl(''),
    surname: new FormControl(''),
    password: new FormControl(''),
    phonenumber: new FormControl('')
  })


  login() {

  }

  register() {

  }
}
