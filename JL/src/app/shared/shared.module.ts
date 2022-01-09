import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuLateralComponent } from './components/menu-lateral/menu-lateral.component';
import { HeaderComponent } from './components/header/header.component';

@NgModule({
  declarations: [
    MenuLateralComponent,
    HeaderComponent
  ],
  exports: [
    MenuLateralComponent,
    HeaderComponent, 
    CommonModule
  ],
  imports: [
  ]
})
export class SharedModule { }
