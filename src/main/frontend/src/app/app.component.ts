import {Component} from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {LoginSignupDialog} from "./authentication/login-signup.dialog";
import {AuthenticationService} from "./authentication/authentication.service";
import {NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  loggedIn: boolean | undefined;

  constructor(public dialog: MatDialog, private router: Router, private authenticationService: AuthenticationService) {
    router.events.subscribe((val) => {
      if (val instanceof NavigationEnd) {
        this.loggedIn = this.authenticationService.isLoggedIn();
      }
    })
  }

  openDialog() {
    const dialogRef = this.dialog.open(LoginSignupDialog);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/dashboard'])
  }
}
