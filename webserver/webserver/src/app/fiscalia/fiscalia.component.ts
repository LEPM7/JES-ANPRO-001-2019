import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-fiscalia',
  templateUrl: './fiscalia.component.html',
  styleUrls: ['./fiscalia.component.css']
})
export class FiscaliaComponent{

  fiscaliaForm = new FormGroup({
    nombre: new FormControl(''),
    descripcion: new FormControl(''),
    direccion: new FormControl(''),
    telefono: new FormControl(''),
    latitud: new FormControl(0.0),
    longitud: new FormControl(0.0),
  });


  constructor() { }

  onSubmit(){

  }

}
