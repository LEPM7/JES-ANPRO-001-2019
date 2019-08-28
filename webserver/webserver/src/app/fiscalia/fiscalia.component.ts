import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NgxSpinnerService } from 'ngx-spinner';
import { FiscaliasService } from '../shared/fiscalias.service';
import { Fiscalia } from '../shared/fiscalia';

@Component({
  selector: 'app-fiscalia',
  templateUrl: './fiscalia.component.html',
  styleUrls: ['./fiscalia.component.css']
})
export class FiscaliaComponent implements OnInit{

  @Output() finish = new EventEmitter();
  @Input() fiscalia;
  isEdit = false;

  fiscaliaForm: FormGroup;

  constructor(private spinner: NgxSpinnerService, private fiscaliaService: FiscaliasService) { }

  ngOnInit(): void {
    this.fiscaliaForm = new FormGroup({
      nombre: new FormControl('', Validators.compose([Validators.required])),
      descripcion: new FormControl('', Validators.compose([Validators.required])),
      direccion: new FormControl('', Validators.compose([Validators.required])),
      telefono: new FormControl('', Validators.compose([Validators.required])),
      latitud: new FormControl(0.0, Validators.compose([Validators.required])),
      longitud: new FormControl(0.0, Validators.compose([Validators.required])),
    });
    if (this.fiscalia) {
      this.isEdit = true;
      this.fiscaliaForm.get('nombre').setValue(this.fiscalia.nombre);
      this.fiscaliaForm.get('descripcion').setValue(this.fiscalia.descripcion);
      this.fiscaliaForm.get('direccion').setValue(this.fiscalia.direccion);
      this.fiscaliaForm.get('telefono').setValue(this.fiscalia.telefono);
      this.fiscaliaForm.get('latitud').setValue(this.fiscalia.latitud);
      this.fiscaliaForm.get('telefono').setValue(this.fiscalia.longitud);
    }
  }


  save() {
    console.log(this.fiscaliaForm.getRawValue());
    this.spinner.show();
    if (!this.fiscaliaForm.valid) {
      alert('Por favor, llena el formulario');
    } else if (this.isEdit) {
      this.fiscaliaService.UpdateFiscalia(this.fiscalia.id, this.fiscaliaForm.getRawValue())
      .subscribe((data) => {
        console.log(data);
        this.finish.emit('');
        this.spinner.hide();
      }, (e) => {
        alert('Ocurrio un error, intente de nuevo mas tarde');
        this.spinner.hide();
        console.log(e);
      });
    } else {
      this.spinner.show();
      this.fiscaliaService.CreateFiscalia(this.fiscaliaForm.getRawValue())
      .subscribe((data) => {
        console.log(data);
        this.finish.emit('');
        this.spinner.hide();
      }, (e) => {
        alert('Ocurrio un error, intente de nuevo mas tarde');
        console.log(e);
      });
    }
  }

  back() {
    this.finish.emit('');
  }

}
