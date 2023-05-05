import { Component } from '@angular/core';
import { BufferserviceService } from 'src/app/services/bufferservice.service';

@Component({
  selector: 'app-service-page',
  templateUrl: './service-page.component.html',
  styleUrls: ['./service-page.component.css']
})
export class ServicePageComponent {
  constructor(public buffer:BufferserviceService){}

}
