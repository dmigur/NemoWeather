import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {        

  today: number = Date.now();

  constructor() {
    setInterval(() => {this.today = Date.now()}, 1);
  }
  title = 'weather-client';
}
