import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { LoginFormsComponent } from '../components/login-forms/login-forms.component';
import { LoginTemplate } from 'src/loginTemplate';
import { UserInformation } from 'src/userInformation';
import { RegisterFormsComponent } from '../components/register-forms/register-forms.component';
import { RegisterTemplate } from 'src/registerTemplate';
import { EditUserDialogComponent } from '../components/dialogs/edit-user-dialog/edit-user-dialog.component';
import {base64ToFile } from 'ngx-image-cropper';
import { AreaInformation } from 'src/areaInformation';
import { ServiceInformation } from 'src/serviceInformation';

@Injectable({
  providedIn: 'root'
})
export class ServerConnectionService {
  requestsUrl:string;
  loginHeader = new HttpHeaders();
  loginParams = new HttpParams();
  test:UserInformation;
  constructor(private http:HttpClient) {
    this.requestsUrl = window.location.href.slice(0,window.location.href.length-1) + ":80/";
    //console.log(this.requestsUrl);
    this.loginParams = this.loginParams.append("type", "01");
  }


  TryToLogin(forms:LoginTemplate):Observable<UserInformation>{
      //console.log(this.requestsUrl); 
      try{
      return this.http.post<UserInformation>(this.requestsUrl+"login",JSON.stringify(forms));
      } catch(error){
        //console.log(error)
        return null;
      }
  }

  TryToRegister(forms:RegisterTemplate):Observable<string>{
    return this.http.post(this.requestsUrl+"registering",JSON.stringify(forms),{responseType: 'text'});
  }

  TryToUpdateUserPicture(dia:EditUserDialogComponent):Observable<string>{
    return this.http.post(this.requestsUrl+"update/userimage",base64ToFile(dia.croppedImage),{responseType: 'text'});
  }

  TryToUpdateUserName(dia:EditUserDialogComponent):Observable<string>{
    return this.http.post(this.requestsUrl+"update/username",dia.newName,{responseType: 'text'});
  }

  ReloadUser():Observable<UserInformation>{
    return this.http.get<UserInformation>(this.requestsUrl+"reload/user");
  }

  GetAreas():Observable<AreaInformation[]>{
    return this.http.get<AreaInformation[]>(this.requestsUrl+"areas");
  }

  GetUser(id:string):Observable<UserInformation>{
    try{
    return this.http.get<UserInformation>(this.requestsUrl+"users?id="+id);
    } catch (error){
      console.log(error);
      return null;
    }
  }

  GetService(id:string):Observable<ServiceInformation>{
    try{
    return this.http.get<ServiceInformation>(this.requestsUrl+"services?id="+id);
    } catch (error){
      console.log(error)
      return null;
    }
  }


  
}
