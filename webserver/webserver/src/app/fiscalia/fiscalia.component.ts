import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';
import { FiscaliasService } from '../shared/fiscalias.service';

@Component({
  selector: 'app-fiscalia',
  templateUrl: './fiscalia.component.html',
  styleUrls: ['./fiscalia.component.css']
})
export class FiscaliaComponent {

  @Output() finish = new EventEmitter();

  fiscaliaForm: FormGroup = new FormGroup({
    nombre: new FormControl('', Validators.compose([Validators.required])),
    descripcion: new FormControl('', Validators.compose([Validators.required])),
    direccion: new FormControl('', Validators.compose([Validators.required])),
    telefono: new FormControl('', Validators.compose([Validators.required])),
    latitud: new FormControl(0.0, Validators.compose([Validators.required])),
    longitud: new FormControl(0.0, Validators.compose([Validators.required])),
  });


  constructor(private spinner: NgxSpinnerService, private fiscaliaService: FiscaliasService) { }

  save() {
    if (!this.fiscaliaForm.valid) {
      alert('Por favor, llena el formulario');
    } else {
      console.log(this.fiscaliaForm.getRawValue());
      this.spinner.show();
      this.fiscaliaService.CreateFiscalia(this.fiscaliaForm.getRawValue())
      .subscribe((data) => {
        console.log(data);
        this.finish.emit('');
        this.spinner.hide();
      },(e) => {
        alert('Ocurrio un error, intente de nuevo mas tarde');
        console.log(e)
      });
    }
  }

  back() {
    this.finish.emit('');
  }

}
