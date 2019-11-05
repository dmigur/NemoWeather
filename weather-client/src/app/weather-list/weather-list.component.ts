import { Observable,interval, Subscription } from "rxjs";
import { WeatherService } from "../weather.service";
import { Weather } from "../weather";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-weather-list",
  templateUrl: "./weather-list.component.html",
  styleUrls: ["./weather-list.component.css"]
})
export class WeatherListComponent implements OnInit {
  constructor(private weatherService: WeatherService,
    private router: Router) {}

  private weathers: Weather[];
  private updateSubscription: Subscription;
  private tableIndex: number ;
  private showItems: number ;


  ngOnInit() {

      this.showItems  = 20;
      this.tableIndex = 0;

      this.reloadData();

      this.updateSubscription = interval(20000).subscribe(
        (val) => { this.reloadData() ;
       console.log("reloading data");
      }
  )};


  reloadData() {

  this.weatherService.getWeatherList().subscribe(res => {
      let response = JSON.parse(JSON.stringify(res))
      this.weathers = response.data;

      //console.log("loaded weather = " + this.weathers.length);
    })

 // this.weathers = this.weatherService.getWeatherList();

  }

  onRequestWeather(){


   document.body.style.cursor="wait";
   console.log("request data");
   this.weatherService.getWeatherNow().subscribe(res => {
        this.reloadData();
        document.body.style.cursor="default";
    })
  }

  showItem(index) : boolean {
    let show = this.tableIndex <= index && index < this.tableIndex + this.showItems;
    return show;
  }
  onNavLeft()  {
    if (this.tableIndex<=this.showItems )
       this.tableIndex = 0;
    else
       this.tableIndex -= this.showItems;
  }

  isNavLeft() : boolean {

       if (!this.weathers) return false;

       return this.tableIndex > 0;
  }

  isNavRight() : boolean {

       if (!this.weathers) return false;

       return this.tableIndex < this.weathers.length - this.showItems;

 //        return true;
  }

  onNavRight()  {

     if (!this.weathers) return;
     if (this.tableIndex >= this.weathers.length - this.showItems) return;
     this.tableIndex += this.showItems;

  }


  getInfo(): string  {
      if (!this.weathers || this.weathers.length == 0  ) return "";
      let lastShown = this.tableIndex >= this.weathers.length - this.showItems ? this.weathers.length : this.tableIndex+this.showItems;
      let info = (this.tableIndex+1) + "-" + lastShown + " of " + this.weathers.length + " items shown";
      return info;
  }

}
