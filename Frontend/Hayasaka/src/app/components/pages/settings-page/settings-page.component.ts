import { Component, OnInit } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { ServerConnectionService } from 'src/app/services/server-connection.service';

@Component({
  selector: 'app-settings-page',
  templateUrl: './settings-page.component.html',
  styleUrls: ['./settings-page.component.css']
})
export class SettingsPageComponent implements OnInit{
  constructor(public buffer:BufferserviceService, private conn:ServerConnectionService){}

  async ngOnInit(){
    if (this.buffer.userInfo == null){
      this.buffer.userInfo = await firstValueFrom(this.conn.ReloadUser());
    }
    const x = await firstValueFrom (this.conn.SetLastPage("/settings"));
  }
}
