import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AccountService} from "../services/account.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  profileError = ''
  profileForm: FormGroup | undefined

  constructor(private accountService: AccountService) {
    accountService.getMyInfo().then(res => {
      this.profileForm = new FormGroup({
        email: new FormControl({value: res.email, disabled: true}),
        name: new FormControl(res.name, Validators.required),
        surname: new FormControl(res.surname, Validators.required),
        address: new FormControl(res.address, Validators.required),
        phoneNumber: new FormControl(res.phoneNumber, Validators.required)
      })
    })

  }


  update() {
    this.profileError = ''
    this.accountService.update(this.profileForm?.value).then(() => {
      this.profileError = 'Update successful';
    }).catch(err => this.profileError = 'Internal error. Try again!');
  }
}
