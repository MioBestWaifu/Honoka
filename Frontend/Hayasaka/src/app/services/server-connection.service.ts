import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';
import { LoginFormsComponent } from '../components/login-forms/login-forms.component';
import { LoginTemplate } from 'src/loginTemplate';
import { UserInformation } from 'src/userInformation';
import { RegisterFormsComponent } from '../components/register-forms/register-forms.component';
import { RegisterTemplate } from 'src/registerTemplate';
import { EditUserDialogComponent } from '../components/dialogs/edit-user-dialog/edit-user-dialog.component';
import {base64ToFile } from 'ngx-image-cropper';
import { GenericInformation } from 'src/genericInformation';
import { ServiceInformation } from 'src/serviceInformation';
import { CreateServiceDialogComponent } from '../components/dialogs/create-service-dialog/create-service-dialog.component';

@Injectable({
  providedIn: 'root'
})
export class ServerConnectionService {
  requestsUrl:string;
  loginHeader = new HttpHeaders();
  loginParams = new HttpParams();
  test:UserInformation;
  constructor(private http:HttpClient) {
    //this.requestsUrl = window.location.href.slice(0,window.location.href.length-1) + ":80/";
    //console.log(this.requestsUrl);
    const x = window.location.href;
    const y = x.split("/");
    console.log(y);
    this.requestsUrl = y[0] + "//" +y[2] + ":80/"
    console.log(this.requestsUrl);
    this.loginParams = this.loginParams.append("type", "01");
  }

  EstablishConnection(){
    return this.http.get(this.requestsUrl+"pages?type=establish",{responseType:'text'});
  }
  
  TryToLogin(forms:LoginTemplate):Observable<HttpResponse<UserInformation>>{
      //console.log(this.requestsUrl); 
      try{
      return this.http.post<UserInformation>(this.requestsUrl+"login",JSON.stringify(forms),{observe:'response'});
      } catch(error){
        return null;
      }
  }

  TryToRegister(forms:RegisterTemplate):Observable<HttpResponse<string>>{
    return this.http.post(this.requestsUrl+"registering",JSON.stringify(forms),{observe:'response',responseType: 'text'});
  }

  TryToCreateService(dia:CreateServiceDialogComponent):Observable<HttpResponse<string>>{
    const x = new ServiceInformation();
    x.serviceName = dia.name;
    x.description = dia.description;
    x.costPerHour = dia.cost.toString();
    x.category = dia.cat;
    x.modality = dia.mod;
    x.availableDays = dia.availableDays;
    x.availableFroms = dia.availableFroms;
    x.availableTos = dia.availableTos;
    return this.http.post(this.requestsUrl+"services?type=create",JSON.stringify(x),{observe:'response',responseType: 'text'});
  }

  TryToUpdateServicePicture(image:string,id:number):Observable<HttpResponse<string>>{
    return this.http.post(this.requestsUrl+"services?type=imageUpdate&id="+id,base64ToFile(image),{observe:'response',responseType: 'text'});
  }

  TryToUpdateUserPicture(dia:EditUserDialogComponent):Observable<HttpResponse<string>>{
    return this.http.post(this.requestsUrl+"personal?type=imageUpdate",base64ToFile(dia.croppedImage),{observe:'response',responseType: 'text'});
  }

  TryToUpdateUserName(dia:EditUserDialogComponent):Observable<HttpResponse<string>>{
    return this.http.post(this.requestsUrl+"personal?type=nameUpdate",dia.newName,{observe:'response',responseType: 'text'});
  }

  ReloadUser():Observable<UserInformation>{
    return this.http.get<UserInformation>(this.requestsUrl+"personal?type=reload");
  }

  GetAreas():Observable<GenericInformation[]>{
    return this.http.get<GenericInformation[]>(this.requestsUrl+"info?type=request&category=areas");
  }
  GetModalities():Observable<GenericInformation[]>{
    return this.http.get<GenericInformation[]>(this.requestsUrl+"info?type=request&category=mod");
  }
  GetCategories():Observable<GenericInformation[]>{
    return this.http.get<GenericInformation[]>(this.requestsUrl+"info?type=request&category=cat");
  }

  GetUser(id:string):Observable<UserInformation>{
    try{
    return this.http.get<UserInformation>(this.requestsUrl+"users?type=request&id="+id);
    } catch (error){
      console.log(error);
      return null;
    }
  }

  GetService(id:string):Observable<ServiceInformation>{
    try{
    return this.http.get<ServiceInformation>(this.requestsUrl+"services?type=request&id="+id);
    } catch (error){
      console.log(error)
      return null;
    }
  }

  GetTargetPage():Observable<string>{
    return this.http.get(this.requestsUrl+"pages?type=target",{responseType: 'text'});
  }

  SetLastPage(currentPage:string):Observable<HttpResponse<string>>{
    console.log("CHAMADO DE: " +currentPage)
    return this.http.post(this.requestsUrl+"pages?type=target",currentPage,{observe:'response',responseType: 'text'});
  }

  PrepareRefresh():Observable<HttpResponse<string>>{
    return this.http.get(this.requestsUrl+"pages?type=refresh",{observe:'response',responseType: 'text'});
  }
  
}
