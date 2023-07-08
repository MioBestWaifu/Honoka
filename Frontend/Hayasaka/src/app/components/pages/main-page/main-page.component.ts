import { Component, OnDestroy, OnInit } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { ServerConnectionService } from 'src/app/services/server-connection.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit{
 test:string = "TESTE";

 constructor(public buffer:BufferserviceService, private conn:ServerConnectionService){}

 async ngOnInit(){
  if (this.buffer.userInfo == null){
    this.buffer.userInfo = await firstValueFrom(this.conn.ReloadUser());
  }
  const x = await firstValueFrom (this.conn.SetLastPage("/pages/main"));
  }
}
