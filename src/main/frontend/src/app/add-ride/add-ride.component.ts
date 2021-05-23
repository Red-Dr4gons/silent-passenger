import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Vehicle} from "../models/vehicle";
import {TransferService} from "../services/transfer.service";
import {VehicleComponent} from "../vehicle/vehicle.component";
import {VehicleService} from "../services/vehicle.service";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-add-ride',
  templateUrl: './add-ride.component.html',
  styleUrls: ['./add-ride.component.css']
})
export class AddRideComponent {

  constructor(private transferService: TransferService,
              private vehicleService: VehicleService,
              public dialog: MatDialog){
    this.getData();
  }

  transferError = '';
  addRideForm = new FormGroup({
    startLocAddr: new FormControl('', [Validators.required]),
    startLocCity: new FormControl('', [Validators.required]),
    startLocPostalCode: new FormControl('', [Validators.required]),
    startTime: new FormControl('', [Validators.required]),
    endLocAddr: new FormControl('', [Validators.required]),
    endLocCity: new FormControl('', [Validators.required]),
    endLocPostalCode: new FormControl('', [Validators.required]),
    vehicle: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    price: new FormControl('', [Validators.required])
  })

  vehicles: Vehicle[] | undefined;

  addRide() {
    this.transferService.create(this.addRideForm?.value).then(() => {
      this.transferError = 'Transfer was created successfully!';
    }).catch(err => this.transferError = 'Internal error. Try again!');
  }

  openVehicleDialog() {
    const dialogRef = this.dialog.open(VehicleComponent);

    dialogRef.afterClosed().subscribe(result => {
      this.getData();
    });
  }

  getData() {
    this.vehicleService.getMyVehicles().then(res => {
      this.vehicles = res;
    })
  }
}
