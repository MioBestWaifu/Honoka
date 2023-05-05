import { Component } from '@angular/core';
import { BufferserviceService } from 'src/app/services/bufferservice.service';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent {
  constructor(public buffer:BufferserviceService){}
}
