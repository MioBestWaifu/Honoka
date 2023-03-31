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
  constructor(private http:HttpClient) {
    this.requestsUrl = "http://127.0.0.1:80/";
    this.loginParams = this.loginParams.append("type", "01");
  }


  TryToLogin(forms:LoginTemplate):boolean{
      console.log(this.requestsUrl); 
      var user = this.http.post<UserInformation>(this.requestsUrl+"login",JSON.stringify(forms));
      let test = new UserInformation();
      user.subscribe(data => {test.birthday = data.birthday;test.description=data.description;test.id=data.id;test.email=data.email;test.username=data.username});
      console.log(user);
      if (test.id == -1){
        return false;
      }
      return true;
  }

  TryToRegister(username:string,email:string,password:string,birthday:Date):boolean{
    return true;
  }

  
}
