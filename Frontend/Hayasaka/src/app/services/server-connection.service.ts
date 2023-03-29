import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ServerConnectionService {

  constructor() { }

  TryToLogin(user:string,psswrd:string):boolean{
    if (user == "Yan" && psswrd == "Costa"){
      return true
    } else{
      return false;
    }
  }
}
