import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { ServerConnectionService } from 'src/app/services/server-connection.service';
import { ServiceInformation } from 'src/serviceInformation';
import { ScheduleServiceDialogComponent } from '../../dialogs/schedule-service-dialog/schedule-service-dialog.component';

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

  constructor(public buffer:BufferserviceService, private router:ActivatedRoute, private conn:ServerConnectionService,private dialog:MatDialog){}

  async ngOnInit(){
    if (this.buffer.userInfo == null){
      this.buffer.userInfo = await firstValueFrom(this.conn.ReloadUser());
    }
    
    this.sub = this.router.params.subscribe(params => {
      this.id = +params['id']; 
  
   })
   const x = await firstValueFrom (this.conn.SetLastPage("/services/"+this.id))
   this.info = await firstValueFrom(this.conn.GetService(this.id.toString()))
   this.buffer.lastService = this.info;
   console.log(this.info);
  }

  schedule(){
    const x = this.dialog.open(ScheduleServiceDialogComponent)
  }

}
