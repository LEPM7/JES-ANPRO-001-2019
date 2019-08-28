import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Fiscalia } from '../shared/fiscalia';
import { FiscaliasService } from '../shared/fiscalias.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-fiscalias',
  templateUrl: './fiscalias.component.html',
  styleUrls: ['./fiscalias.component.css']
})
export class FiscaliasComponent implements OnInit {

  fiscalias: Fiscalia[] = [];
  @Output() selectedFiscalia = new EventEmitter();

  constructor(private fiscaliasService: FiscaliasService, private spinner: NgxSpinnerService) {}

  async ngOnInit() {
    this.getAllFiscalias();
  }

  getAllFiscalias(){
    this.spinner.show();
    this.fiscaliasService.GetFiscalias()
    .subscribe((fiscalias: Fiscalia[]) => {
      this.fiscalias = fiscalias;
      this.spinner.hide();
    }, (e) => {
      console.trace(e);
      this.spinner.hide();
    });
  }

  deleteFiscalia(id:number){
    this.spinner.show();
    this.fiscaliasService.DeleteFiscalia(id)
    .subscribe((data) => {
      if(!data){
        alert('Ocurrio un error al borrar, intenta mas tarde');
      }
      this.getAllFiscalias();
      this.spinner.hide();
    }, (e) => {
      console.trace(e);
      this.spinner.hide()
    });
  }

  editFiscalia(f){
    this.selectedFiscalia.emit(f);
  }
}
