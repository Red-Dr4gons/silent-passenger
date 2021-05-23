import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AccountService} from "../services/account.service";
import {Vehicle} from "../models/vehicle";
import {VehicleService} from "../services/vehicle.service";
import {MatDialog} from "@angular/material/dialog";
import {VehicleComponent} from "../vehicle/vehicle.component";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  profileError = ''
  profileForm: FormGroup | undefined
  displayedColumns: string[] = ['plate', 'fuelType', 'fuelConsumption', 'delete'];
  dataSource: Vehicle[];

  constructor(private accountService: AccountService, private vehicleService: VehicleService, public dialog: MatDialog) {
    this.dataSource = [];
    accountService.getMyInfo().then(res => {
      this.profileForm = new FormGroup({
        email: new FormControl({value: res.email, disabled: true}),
        name: new FormControl(res.name, Validators.required),
        surname: new FormControl(res.surname, Validators.required),
        address: new FormControl(res.address, Validators.required),
        phoneNumber: new FormControl(res.phoneNumber, Validators.required)
      })
    })
    this.getData();

  }

  getData() {
    this.vehicleService.getMyVehicles().then(res => {
      this.dataSource = res;
    })
  }

  update() {
    this.profileError = ''
    this.accountService.update(this.profileForm?.value).then(() => {
      this.profileError = 'Update successful';
    }).catch(err => this.profileError = 'Internal error. Try again!');
  }

  delete(plate: string) {
    this.vehicleService.deleteVehicle(plate).then(() => {
      this.getData();
    }).catch(err => console.error(err));
  }

  openVehicleDialog() {
    const dialogRef = this.dialog.open(VehicleComponent);

    dialogRef.afterClosed().subscribe(result => {
      this.getData();
    });
  }
}
