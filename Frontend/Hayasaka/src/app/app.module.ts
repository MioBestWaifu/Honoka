import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './components/pages/login-page/login-page.component';
import { RegisterPageComponent } from './components/pages/register-page/register-page.component';
import { MainPageComponent } from './components/pages/main-page/main-page.component';
import { LoginFormsComponent } from './components/login-forms/login-forms.component';
import { RegisterFormsComponent } from './components/register-forms/register-forms.component';
import { HeaderComponent } from './components/header/header.component';
import { FootTestComponent } from './components/foot-test/foot-test.component';
import { LateralBarComponent } from './components/lateral-bar/lateral-bar.component';
import { ServiceRowComponent } from './components/service-row/service-row.component';
import { ServiceCardComponent } from './components/service-card/service-card.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    RegisterPageComponent,
    MainPageComponent,
    LoginFormsComponent,
    RegisterFormsComponent,
    HeaderComponent,
    FootTestComponent,
    LateralBarComponent,
    ServiceRowComponent,
    ServiceCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
