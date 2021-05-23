import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-delivery',
  templateUrl: './add-delivery.component.html',
  styleUrls: ['./add-delivery.component.css']
})
export class AddDeliveryComponent {
  deliveryError = '';
  addDeliveryForm = new FormGroup({
    startLocAddr: new FormControl('', [Validators.required]),
    startLocCity: new FormControl('', [Validators.required]),
    startLocPostalCode: new FormControl('', [Validators.required]),
    endLocAddr: new FormControl('', [Validators.required]),
    endLocCity: new FormControl('', [Validators.required]),
    endLocPostalCode: new FormControl('', [Validators.required]),
    status: new FormControl(''),
    description: new FormControl('', [Validators.required])
  })

  addDelivery() {
    console.log(this.addDeliveryForm?.value);
  }
}
