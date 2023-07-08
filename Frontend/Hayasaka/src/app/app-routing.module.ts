import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './components/pages/login-page/login-page.component';
import { MainPageComponent } from './components/pages/main-page/main-page.component';
import { RegisterPageComponent } from './components/pages/register-page/register-page.component';
import { UserPageComponent } from './components/pages/user-page/user-page.component';
import { ServicePageComponent } from './components/pages/service-page/service-page.component';
import { ActiveUserPageComponent } from './components/pages/active-user-page/active-user-page.component';
import { SearchPageComponent } from './components/pages/search-page/search-page.component';
import { SettingsPageComponent } from './components/pages/settings-page/settings-page.component';
import { MyServicesPageComponent } from './components/pages/my-services-page/my-services-page.component';

const routes: Routes = [
  {path:'pages/login',component:LoginPageComponent},
  {path:'register',component:RegisterPageComponent},
  {path:'pages/main',component:MainPageComponent},
  {path:'users/:id',component:UserPageComponent},
  {path:'services/:id',component:ServicePageComponent},
  {path:'viewprofile',component:ActiveUserPageComponent},
  {path:'search',component:SearchPageComponent},
  {path:'settings',component:SettingsPageComponent},
  {path:'myservices',component:MyServicesPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
