import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FiscaliasComponent } from './fiscalias/fiscalias.component';
import { FiscaliaComponent } from './fiscalia/fiscalia.component';
//Material
import { MatGridListModule } from '@angular/material/grid-list';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { HttpClientModule } from '@angular/common/http';
import { FiscaliasService } from './shared/fiscalias.service';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';
@NgModule({
  declarations: [
    AppComponent,
    FiscaliasComponent,
    FiscaliaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatGridListModule,
    MatButtonModule,
    MatIconModule,
    HttpClientModule,
    MatListModule,
    MatCardModule
  ],
  exports: [
    MatGridListModule,
    MatButtonModule,
    MatIconModule,
    HttpClientModule,
    MatListModule,
    MatCardModule
  ],
  providers: [FiscaliasService],
  bootstrap: [AppComponent]
})
export class AppModule { }
