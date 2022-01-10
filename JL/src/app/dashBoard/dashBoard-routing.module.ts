import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EstudioComponent } from './../estudio/components/estudio.component';
import { FinanzasComponent } from './../finanzas/components/finanzas.component';
import { DashBoardComponent } from './components/dashBoard.component';


const routes: Routes = [
    {
      path: '',
      component: DashBoardComponent,
      children: [
        {path: 'estudio', component: EstudioComponent},
        {path: 'finanzas', component: FinanzasComponent},
        {
          path: '**', 
          loadChildren: () => import('./../page-not-found/page-not-found.module').then(m => m.PageNotFoundModule)
        }
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