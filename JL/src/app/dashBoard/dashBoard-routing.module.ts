import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EstudioComponent } from './../estudio/components/estudio.component';
import { FinanzasComponent } from './../finanzas/components/finanzas.component';
import { DashBoardComponent } from './components/dashBoard.component';


const routes: Routes = [
    {
      path: '',
      children: [
        {path: 'estudio', component: EstudioComponent},
        {path: 'finanzas', component: FinanzasComponent},
        {path: '**', component: DashBoardComponent},

      ]
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
export class DashBoardRoutingModule {}