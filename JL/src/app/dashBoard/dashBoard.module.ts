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
import { MenuListItemComponent } from './components/app-menu-list-item/app-menu-list-item.component';

@NgModule({
  declarations: [
    DashBoardComponent,
    MenuListItemComponent
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
  ]
})
export class DashBoardModule { }
