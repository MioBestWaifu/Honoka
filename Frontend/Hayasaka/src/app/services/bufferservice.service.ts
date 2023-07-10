import { Injectable } from '@angular/core';
import { GenericInformation } from 'src/genericInformation';
import { UserInformation } from 'src/userInformation';
import { ServerConnectionService } from './server-connection.service';

@Injectable({
  providedIn: 'root'
})
export class BufferserviceService {
  userInfo:UserInformation;
  constructor() {
  }
}
