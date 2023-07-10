import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { ServerConnectionService } from 'src/app/services/server-connection.service';

@Component({
  selector: 'app-schedule-service-dialog',
  templateUrl: './schedule-service-dialog.component.html',
  styleUrls: ['./schedule-service-dialog.component.css']
})
export class ScheduleServiceDialogComponent {
  constructor(public buffer:BufferserviceService, private dialog:MatDialog, private conn:ServerConnectionService){
  }

  cancel(){
    this.dialog.closeAll();
  }

  save(){
    this.dialog.closeAll();
  }
}
