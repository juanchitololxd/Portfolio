import { NgModule } from '@angular/core';
import { Routes, RouterModule, PreloadAllModules } from '@angular/router';

import { DashBoardComponent } from './dashBoard/components/dashBoard.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/dash',
    pathMatch: 'full',
    // children: [
    //   {
    //     path: '',
    //     redirectTo: '/dash',
    //     pathMatch: 'full',
    //   },
    //   {
    //     path: 'dash',
    //     //canActivate: [AdminGuard],
    //     component: DashBoardComponent
    //     //loadChildren: () => import('./dashBoard/dashBoard.module').then(m => m.DashBoardModule)
    //   }
    // ]
  },
  {
    path: 'dash',
    loadChildren: () => import('./dashBoard/dashBoard.module').then(m => m.DashBoardModule)
  },
  {
    path: '**',
    loadChildren: () => import('./page-not-found/page-not-found.module').then(m => m.PageNotFoundModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    preloadingStrategy: PreloadAllModules
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
