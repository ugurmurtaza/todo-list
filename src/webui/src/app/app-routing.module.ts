import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path : '',
    children: [
      {path : '', pathMatch : 'full', redirectTo: 'item'},
      { path: 'item', loadChildren: () => import('src/app/pages/item/item.module').then(m => m.ItemModule) }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
