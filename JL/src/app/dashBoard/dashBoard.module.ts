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
@NgModule({
  declarations: [
    DashBoardComponent
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
