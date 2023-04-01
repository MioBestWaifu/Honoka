import { Injectable } from '@angular/core';
import { UserInformation } from 'src/userInformation';

@Injectable({
  providedIn: 'root'
})
export class BufferserviceService {
  userInfo:UserInformation;
  constructor() { }
}
