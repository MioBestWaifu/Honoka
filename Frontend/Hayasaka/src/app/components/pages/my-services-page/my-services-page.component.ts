import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { firstValueFrom } from 'rxjs';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { ServerConnectionService } from 'src/app/services/server-connection.service';
import { CreateServiceDialogComponent } from '../../dialogs/create-service-dialog/create-service-dialog.component';
import { ServiceSchedule } from 'src/serviceSchedule';

@Component({
  selector: 'app-my-services-page',
  templateUrl: './my-services-page.component.html',
  styleUrls: ['./my-services-page.component.css']
})
export class MyServicesPageComponent implements OnInit{
  public schedule:ServiceSchedule;
  constructor(public buffer:BufferserviceService, private conn:ServerConnectionService,private dialog:MatDialog){}

  async ngOnInit(){
    if (this.buffer.userInfo == null){
      this.buffer.userInfo = await firstValueFrom(this.conn.ReloadUser());
    }
    this.schedule = await firstValueFrom(this.conn.GetSchedule());
    const x = await firstValueFrom (this.conn.SetLastPage("/myservices"));
  }

  create(){
    const x = this.dialog.open(CreateServiceDialogComponent)
    x.updateSize("70vw","80vh")
  }
}
