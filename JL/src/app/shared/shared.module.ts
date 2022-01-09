import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';

import { MenusComponent } from './components/menus/menus.component';
import {MatListModule} from '@angular/material/list';
import { HeaderComponent } from './components/header/header.component';


@NgModule({
  declarations: [
    MenusComponent,
    HeaderComponent
  ],
  exports: [
    MenusComponent,
    CommonModule
  ],
  imports: [
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatSidenavModule,
    CommonModule,
    MatListModule
  ]
})
export class SharedModule { }
