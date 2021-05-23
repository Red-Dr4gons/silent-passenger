import {Component, Inject} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {VehicleService} from "../services/vehicle.service";
import {MAT_DIALOG_DATA, MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent {
  vehicleError = '';
  vehicleForm = new FormGroup({
    plate: new FormControl('', [Validators.required]),
    fuelType: new FormControl('', [Validators.required]),
    fuelConsumption: new FormControl('', [Validators.required])
  })

  fuelTypes = [
    {name: 'GAS', value: 'GAS'},
    {name: 'DIESEL', value: 'DIESEL'},
    {name: 'HYBRID', value: 'HYBRID'},
    {name: 'BIOGAS', value: 'BIOGAS'},
    {name: 'NATURAL_GAS', value: 'NATURAL_GAS'},
    {name: 'ELECTRIC', value: 'ELECTRIC'},
    {name: 'BIODIESEL', value: 'BIODIESEL'},
    {name: 'ETHANOL_10', value: 'ETHANOL_10'},
    {name: 'ETHANOL_85', value: 'ETHANOL_85'},
    {name: 'PLUG_IN_HYBRID', value: 'PLUG_IN_HYBRID'}
  ]

  constructor(@Inject(MAT_DIALOG_DATA) public data: { refreshVehicles: any }, public dialog: MatDialog, private vehicleService: VehicleService) {
  }

  add() {
    this.vehicleError = '';
    if (this.vehicleForm.errors) {
      this.vehicleError = 'Missing some fields'
    }
    this.vehicleService.add(this.vehicleForm.value).then(res => {
      // this.dialog.closeAll();
    }).catch(err => {
      this.vehicleError = 'Internal error. Try again.'
    })
  }
}
