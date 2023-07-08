import { Component, OnDestroy, OnInit } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { ServerConnectionService } from 'src/app/services/server-connection.service';

@Component({
  selector: 'app-my-services-page',
  templateUrl: './my-services-page.component.html',
  styleUrls: ['./my-services-page.component.css']
})
export class MyServicesPageComponent implements OnInit{

  constructor(public buffer:BufferserviceService, private conn:ServerConnectionService){}

  async ngOnInit(){
    if (this.buffer.userInfo == null){
      this.buffer.userInfo = await firstValueFrom(this.conn.ReloadUser());
    }
    const x = await firstValueFrom (this.conn.SetLastPage("/myservices"));
  }
}
