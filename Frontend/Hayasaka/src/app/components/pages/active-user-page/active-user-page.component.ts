import { Component } from '@angular/core';
import { BufferserviceService } from 'src/app/services/bufferservice.service';

@Component({
  selector: 'app-active-user-page',
  templateUrl: './active-user-page.component.html',
  styleUrls: ['./active-user-page.component.css']
})
export class ActiveUserPageComponent {

  constructor(public buffer:BufferserviceService){
    
  }

}
