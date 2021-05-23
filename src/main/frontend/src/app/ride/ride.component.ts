import {Component, OnInit} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {AddRideComponent} from "../add-ride/add-ride.component";
import {AddDeliveryComponent} from "../add-delivery/add-delivery.component";
import {TransferService} from "../services/transfer.service";

@Component({
  selector: 'app-ride',
  templateUrl: './ride.component.html',
  styleUrls: ['./ride.component.css']
})
export class RideComponent implements OnInit {

  transfers: any;

  constructor(private transferService: TransferService,
              public dialog: MatDialog) {

  }

  getTransfers() {
    this.transferService.getTransfers().then(res => {
      this.transfers = res;
    });
  }

  openAddTransportDialog() {
    const dialogRef = this.dialog.open(AddRideComponent);

    dialogRef.afterClosed().subscribe(result => {
      this.getTransfers();
    });
  }

  openAddDeliveryDialog() {
    this.dialog.open(AddDeliveryComponent);
  }

  ngOnInit(): void {
    this.getTransfers();
  }

}
