import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {MatDialogModule} from "@angular/material/dialog";

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
import { ActiveUserPageComponent } from './components/pages/active-user-page/active-user-page.component';
import { UserPageComponent } from './components/pages/user-page/user-page.component';
import { ServicePageComponent } from './components/pages/service-page/service-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MissingInfoDialogComponent } from './components/dialogs/missing-info-dialog/missing-info-dialog.component';
import { WrongCredentialsDialogComponent } from './components/dialogs/wrong-credentials-dialog/wrong-credentials-dialog.component';

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
    ServiceCardComponent,
    ActiveUserPageComponent,
    UserPageComponent,
    ServicePageComponent,
    MissingInfoDialogComponent,
    WrongCredentialsDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
