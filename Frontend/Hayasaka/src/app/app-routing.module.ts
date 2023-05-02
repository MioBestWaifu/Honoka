import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './components/pages/login-page/login-page.component';
import { MainPageComponent } from './components/pages/main-page/main-page.component';
import { RegisterPageComponent } from './components/pages/register-page/register-page.component';
import { UserPageComponent } from './components/pages/user-page/user-page.component';
import { ServicePageComponent } from './components/pages/service-page/service-page.component';
import { ActiveUserPageComponent } from './components/pages/active-user-page/active-user-page.component';

const routes: Routes = [
  {path:'',component:LoginPageComponent},
  {path:'register',component:RegisterPageComponent},
  {path:'main',component:MainPageComponent},
  {path:'user/:id',component:UserPageComponent},
  {path:'service/:id',component:ServicePageComponent},
  {path:'viewProfile',component:ActiveUserPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
