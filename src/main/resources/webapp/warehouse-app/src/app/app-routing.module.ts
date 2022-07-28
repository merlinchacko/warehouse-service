import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AddBoxComponent} from "./warehouse/add-box/add-box.component";

const routes: Routes = [
  {path: 'list-boxes', redirectTo: '/', pathMatch: 'full'},
  {path: 'add-box', component: AddBoxComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
