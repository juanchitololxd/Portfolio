import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashBoardComponent } from './components/dashBoard.component';

const routes: Routes = [
    {
      path: 'dash',
      component: DashBoardComponent
    }
  ];

@NgModule({
  imports: [
    RouterModule.forChild(routes),
  ],
  exports: [
    RouterModule
  ]
})
export class HomeRoutingModule {}