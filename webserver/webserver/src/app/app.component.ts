import { Component, OnInit } from '@angular/core';
import { Fiscalia } from './shared/fiscalia';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  showAll = true;
  new = true;
  edit = false;

  newFiscalia(){
    this.showAll = false;
  }

  editFiscalia(f:Fiscalia){
    this.showAll = false;
  }

  save(){
    this.showAll = true;
  }



 

  

}
