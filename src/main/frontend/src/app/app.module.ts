import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatOptionModule} from "@angular/material/core";
import {MaterialModule} from "./material-module";
import {RideComponent} from './ride/ride.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {ProfileComponent} from './profile/profile.component';
import {LoginSignupDialog} from "./authentication/login-signup.dialog";
import {FlexLayoutModule} from '@angular/flex-layout';
import {LeafletModule} from "@asymmetrik/ngx-leaflet";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AuthenticationService} from "./authentication/authentication.service";
import {HttpClientModule} from "@angular/common/http";
import {IsLoggedIn} from "./Guards/IsLoggedIn";
import {AddRideComponent} from './add-ride/add-ride.component';
import {AddDeliveryComponent} from './add-delivery/add-delivery.component';
import {VehicleComponent} from './vehicle/vehicle.component';

@NgModule({
  declarations: [
    AppComponent,
    RideComponent,
    DashboardComponent,
    ProfileComponent,
    LoginSignupDialog,
    AddRideComponent,
    VehicleComponent,
    AddDeliveryComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatToolbarModule,
    MatOptionModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    LeafletModule
  ],
  providers: [AuthenticationService,
    IsLoggedIn],
  bootstrap: [AppComponent]
})
export class AppModule {
}
