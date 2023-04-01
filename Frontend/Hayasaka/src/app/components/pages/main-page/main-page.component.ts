import { Component } from '@angular/core';
import { BufferserviceService } from 'src/app/services/bufferservice.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent {
 test:string = "TESTE";

 constructor(public buffer:BufferserviceService){}

}
