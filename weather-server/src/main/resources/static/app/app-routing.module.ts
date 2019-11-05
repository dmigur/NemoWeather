import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WeatherListComponent } from './weather-list/weather-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'weather', pathMatch: 'full' },
  { path: 'weathers', component: WeatherListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }