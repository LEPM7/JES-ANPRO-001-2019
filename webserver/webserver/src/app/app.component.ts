import { Component, OnInit } from '@angular/core';
import { Fiscalia } from './shared/fiscalia';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  showAll = true;
  fiscalia:Fiscalia = null;

  newFiscalia() {
    this.fiscalia = null;
    this.showAll = false;
  }

  editFiscalia(f:Fiscalia ) {
    this.fiscalia = f;
    this.showAll = false;
  }

  show() {
    this.showAll = true;
  }
}
