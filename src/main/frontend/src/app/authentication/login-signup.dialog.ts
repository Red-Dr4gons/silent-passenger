import {Component} from "@angular/core";
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from "./authentication.service";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'login-signup-dialog',
  templateUrl: 'login-signup.dialog.html',
  styleUrls: ['login-signup.dialog.css']
})
export class LoginSignupDialog {
  loginError = '';
  registerError = ''

  constructor(public dialog: MatDialog, private router: Router, private auth: AuthenticationService) {
  }

  loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    }
  )

  registerForm = new FormGroup({
    address: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    name: new FormControl('', Validators.required),
    surname: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    phonenumber: new FormControl('', Validators.required)
  })


  login() {
    this.loginError = '';
    if (this.loginForm.controls.email.errors) {
      this.loginError = 'Email is not correct or missing';
      return;
    } else if (this.loginForm.controls.password.errors) {
      this.loginError = 'Password is missing'
      return;
    }
    this.auth.login(this.loginForm.value).then(result => {
      if (result.status === 'SUCCESS') {
        this.auth.setToken(result.message);
        this.dialog.closeAll();
        this.router.navigate(['/dashboard'])
      } else {
        this.loginError = result.message;
      }
    }).catch(err => {
      this.loginError = 'Internal error. Try again!'
    })
  }

  register() {
    this.registerError = '';
    if (this.registerForm.errors) {
      this.registerError = 'There are some invalid field or are missing';
      return;
    }
    this.auth.register(this.registerForm.value).then(result => {
      if (result.status === 'SUCCESS') {
        this.registerError = "Registration successful. You can sign in now."
      } else {
        this.loginError = result.message;
      }
    }).catch(err => {
      this.loginError = 'Internal error. Try again!'
    })
  }
}
