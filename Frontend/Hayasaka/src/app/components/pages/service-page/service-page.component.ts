import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { ServerConnectionService } from 'src/app/services/server-connection.service';
import { ServiceInformation } from 'src/serviceInformation';

@Component({
  selector: 'app-service-page',
  templateUrl: './service-page.component.html',
  styleUrls: ['./service-page.component.css']
})
export class ServicePageComponent implements OnInit{
  id:number;
  sub:any;
  info:ServiceInformation;
  w = "3.5vw"

  constructor(public buffer:BufferserviceService, private router:ActivatedRoute, private conn:ServerConnectionService){}

  async ngOnInit(){
    this.sub = this.router.params.subscribe(params => {
      this.id = +params['id']; 
  
   })
   this.info = await firstValueFrom(this.conn.GetService(this.id.toString()))
   console.log("SERVICE PAGE");
   console.log(this.info);
  }

}
