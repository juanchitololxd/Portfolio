import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashBoardComponent } from './components/dashBoard.component';
import { SharedModule } from './../shared/shared.module'
import { DashBoardRoutingModule } from './dashBoard-routing.module';

@NgModule({
  declarations: [
    DashBoardComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    DashBoardRoutingModule
  ]
})
export class DashBoardModule { }
