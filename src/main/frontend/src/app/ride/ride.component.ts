import {Component, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {AddRideComponent} from "../add-ride/add-ride.component";
import {AddDeliveryComponent} from "../add-delivery/add-delivery.component";
import {TransferService} from "../services/transfer.service";
import {DeliveryService} from "../services/delivery.service";

@Component({
  selector: 'app-ride',
  templateUrl: './ride.component.html',
  styleUrls: ['./ride.component.css']
})
export class RideComponent implements OnInit {

  transfers: any;
  deliveries: any;

  constructor(private transferService: TransferService,
              public dialog: MatDialog,
              private deliveryService: DeliveryService) {

  }

  getTransfers() {
    this.transferService.getTransfers().then(res => {
      this.transfers = res;
    });
  }

  getDeliveries() {
    this.deliveryService.getDeliveries().then(res => {
      console.log(res);
      this.deliveries = res;
    });
  }

  openAddTransportDialog() {
    const dialogRef = this.dialog.open(AddRideComponent);

    dialogRef.afterClosed().subscribe(result => {
      this.getTransfers();
    });
  }

  openAddDeliveryDialog() {
    const dialogRef = this.dialog.open(AddDeliveryComponent);
    dialogRef.afterClosed().subscribe(result => {
      this.getDeliveries();
    });
  }

  ngOnInit(): void {
    this.getTransfers();
    this.getDeliveries();
  }

}
