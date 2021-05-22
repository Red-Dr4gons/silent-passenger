import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RideComponent} from "./ride/ride.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {ProfileComponent} from "./profile/profile.component";
import {IsLoggedIn} from "./Guards/IsLoggedIn";

const routes: Routes = [
  {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'rides', component: RideComponent, canActivate: [IsLoggedIn]},
  {path: 'profile', component: ProfileComponent, canActivate: [IsLoggedIn]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
