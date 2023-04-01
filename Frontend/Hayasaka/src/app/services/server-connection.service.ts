import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { LoginFormsComponent } from '../components/login-forms/login-forms.component';
import { LoginTemplate } from 'src/loginTemplate';
import { UserInformation } from 'src/userInformation';

@Injectable({
  providedIn: 'root'
})
export class ServerConnectionService {
  requestsUrl:string;
  loginHeader = new HttpHeaders();
  loginParams = new HttpParams();
  test:UserInformation;
  constructor(private http:HttpClient) {
    this.requestsUrl = "http://127.0.0.1:80/";
    this.loginParams = this.loginParams.append("type", "01");
  }


  TryToLogin(forms:LoginTemplate):Observable<UserInformation>{
      console.log(this.requestsUrl); 
      return this.http.get<UserInformation>(this.requestsUrl+"login");
  }

  TryToRegister(username:string,email:string,password:string,birthday:Date):boolean{
    return true;
  }

  
}
