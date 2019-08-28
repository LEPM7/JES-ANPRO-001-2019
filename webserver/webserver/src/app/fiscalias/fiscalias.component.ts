import { Component, OnInit } from '@angular/core';
import { Fiscalia } from '../shared/fiscalia';
import { FiscaliasService } from '../shared/fiscalias.service';


@Component({
  selector: 'app-fiscalias',
  templateUrl: './fiscalias.component.html',
  styleUrls: ['./fiscalias.component.css']
})
export class FiscaliasComponent implements OnInit {

  fiscalias: Fiscalia[] = [];

  constructor(private fiscaliasService: FiscaliasService) {}

  async ngOnInit() {
    this.fiscaliasService.GetFiscalias().subscribe((fiscalias: Fiscalia[]) => this.fiscalias = fiscalias );
  }

}
