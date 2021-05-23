import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DeliveryService} from "../services/delivery.service";

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
    description: new FormControl('', [Validators.required])
  })

  constructor(private deliveryService: DeliveryService) {
  }

  addDelivery() {
    this.deliveryService.create(this.addDeliveryForm?.value).then(() => {
      this.deliveryError = 'Ride was created successfully!';
    }).catch(err => this.deliveryError = 'Internal error. Try again!');
  }
}
