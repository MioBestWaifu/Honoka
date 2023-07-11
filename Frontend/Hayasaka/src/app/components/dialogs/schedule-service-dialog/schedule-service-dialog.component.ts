import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { ServerConnectionService } from 'src/app/services/server-connection.service';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatCardModule} from '@angular/material/card';
import {MatNativeDateModule} from '@angular/material/core';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-schedule-service-dialog',
  templateUrl: './schedule-service-dialog.component.html',
  styleUrls: ['./schedule-service-dialog.component.css']
})
export class ScheduleServiceDialogComponent {
  selected:Date | null;
  cost:number;
  startHour:string; endHour:string; 
  constructor(public buffer:BufferserviceService, private dialog:MatDialog, private conn:ServerConnectionService){
  }

  myFilter = (d: Date | null): boolean => {
    const day = (d || new Date()).getDay();
    return this.buffer.lastService.availableDays[day];
  };

  dayChanged(){
    document.getElementById("from").textContent = "From (Min "+this.buffer.lastService.availableFroms[this.selected.getDay()]+"):";
    document.getElementById("to").textContent = "To (Max "+this.buffer.lastService.availableTos[this.selected.getDay()]+"):";
    document.getElementById("fromInput").setAttribute("min",this.buffer.lastService.availableFroms[this.selected.getDay()].substring(11));
    document.getElementById("toInput").setAttribute("min",this.buffer.lastService.availableFroms[this.selected.getDay()].substring(11));
    document.getElementById("fromInput").setAttribute("max",this.buffer.lastService.availableTos[this.selected.getDay()].substring(11));
    document.getElementById("toInput").setAttribute("max",this.buffer.lastService.availableTos[this.selected.getDay()].substring(11));
  }

  hourChanged(){
    const x = Date.parse("1970-01-01:"+this.startHour+":00");
    const y = Date.parse("1970-01-01:"+this.endHour+":00");
    console.log(this.startHour);
    console.log(this.endHour);
    console.log(x);
    console.log(y);
    console.log(this.buffer.lastService.temp/60);
    var diffMs = (y - x);
    var diffMins = Math.round(diffMs/60000);
    console.log(diffMs);
    console.log(diffMins);
    this.cost = diffMins * this.buffer.lastService.temp/60;
  }

  cancel(){
    this.dialog.closeAll();
  }

  async save(){
    const currStart = Date.parse("1970-01-01:"+this.startHour+":00");
    const currEnd = Date.parse("1970-01-01:"+this.endHour+":00");
    const min = Date.parse(this.buffer.lastService.availableFroms[this.selected.getDay()]);
    const max = Date.parse(this.buffer.lastService.availableTos[this.selected.getDay()]);
    console.log(currStart);
    console.log(currEnd);
    console.log(min);
    console.log(max);

    if ((currStart < min || currStart > max) || (currEnd < min || currEnd > max)){
      alert("Select a valid time range");
    } else {
    
    const x = await firstValueFrom(this.conn.TryToScheduleService(this));

    if(x.status != 201){
      alert("A server error has happened");
    }
    
    this.dialog.closeAll();
    }
  }
}
