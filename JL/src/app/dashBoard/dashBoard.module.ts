import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashBoardComponent } from './components/dashBoard.component';

import { DashBoardRoutingModule } from './dashBoard-routing.module';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatDividerModule} from '@angular/material/divider';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatTreeModule} from '@angular/material/tree';
import { EstudioComponent } from './components/estudio/estudio.component';
import { MateriaDashComponent } from './components/estudio/materia-dash/materia-dash.component';
import { RepasosDashComponent } from './components/estudio/repasos-dash/repasos-dash.component';
import { ExamenesDashComponent } from './components/estudio/examenes-dash/examenes-dash.component';
@NgModule({
  declarations: [
    DashBoardComponent,
    EstudioComponent,
    MateriaDashComponent,
    RepasosDashComponent,
    ExamenesDashComponent
  ],
  imports: [
    CommonModule,
    DashBoardRoutingModule,
    MatSidenavModule, 
    MatToolbarModule,  
    MatIconModule, 
    MatButtonModule,
    MatListModule,
    MatDividerModule,
    MatExpansionModule,
    MatTreeModule
  ]
})
export class DashBoardModule { }
