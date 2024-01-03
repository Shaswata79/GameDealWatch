import {Component, ViewChild} from '@angular/core';
import {MatSidenav} from "@angular/material/sidenav";
import {BreakpointObserver} from "@angular/cdk/layout";
import {AfterViewInit} from "@angular/core";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})

export class AppComponent implements AfterViewInit{
  title = 'GamesDealWatch';

  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;

  constructor(private observer: BreakpointObserver) {
  }

  ngAfterViewInit(){

    // if width of window is less than 1200px sidenav mode is switched to 'over', otherwise it stays 'side'
    // if mode is 'over' can toggle sidenav using button
    this.observer.observe(['(max-width: 1200px)']).subscribe(res => {
      if (res.matches) {
        this.sidenav.mode = 'over';
        this.sidenav.close();
      }else {
        this.sidenav.mode = 'side';
        this.sidenav.open();
      }
    });

  }

}
