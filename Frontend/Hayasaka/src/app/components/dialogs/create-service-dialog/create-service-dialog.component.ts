import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { ServerConnectionService } from 'src/app/services/server-connection.service';
import { ImageCroppedEvent} from 'ngx-image-cropper';
import { firstValueFrom } from 'rxjs';
import { GenericInformation } from 'src/genericInformation';
import { FailedUpdateDialogComponent } from '../failed-update-dialog/failed-update-dialog.component';

@Component({
  selector: 'app-create-service-dialog',
  templateUrl: './create-service-dialog.component.html',
  styleUrls: ['./create-service-dialog.component.css']
})
export class CreateServiceDialogComponent implements OnInit{
  Categories:GenericInformation[];
  Modalities:GenericInformation[];
  cat:number; mod:number; cost:number;
  availableDays:boolean[]; availableFroms:string[]; availableTos:string[];
  name:string; description:string;
  imageChangedEvent: any = '';
  public newName:string;
  public croppedImage: any = '';
    constructor(public buffer:BufferserviceService, private dialog:MatDialog, private conn:ServerConnectionService){
      this.croppedImage = conn.requestsUrl+"images/services/9999.png";
      this.availableDays = [false,false,false,false,false,false,false];
      this.availableFroms = new Array(7);
      this.availableTos = new Array(7);
    }
  async ngOnInit() {
    this.Categories = await firstValueFrom(this.conn.GetCategories());
    this.Modalities = await firstValueFrom(this.conn.GetModalities());
  }

  fileChangeEvent(event: any): void {
      this.imageChangedEvent = event;
  }
  imageCropped(event: ImageCroppedEvent) {
      this.croppedImage = event.base64;
  }
  imageLoaded() {
        // show cropper
  }
  cropperReady() {
        // cropper ready
  }
  loadImageFailed() {
        // show message
  }

  dayBtnClicked(day:number){
    this.availableDays[day] = !this.availableDays[day];
    if (this.availableDays[day]){
      document.getElementById("hour"+day).style.display = "flex";
    } else {
      document.getElementById("hour"+day).style.display = "none";
    }
  }

  cancel(){
    this.dialog.closeAll();
  } 
  async save(){
    var response = await firstValueFrom(this.conn.TryToCreateService(this));
    if (response.status != 201){
      this.dialog.open(FailedUpdateDialogComponent)
      return
    }

    response = await firstValueFrom(this.conn.TryToUpdateServicePicture(this.croppedImage,-1));
    if (response.status != 201){
      this.dialog.open(FailedUpdateDialogComponent)
      return
    }
    this.dialog.closeAll();
  }
}
