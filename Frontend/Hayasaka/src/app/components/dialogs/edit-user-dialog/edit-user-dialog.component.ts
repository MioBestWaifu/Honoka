import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { ImageCroppedEvent, LoadedImage, base64ToFile } from 'ngx-image-cropper';
import { ServerConnectionService } from 'src/app/services/server-connection.service';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-edit-user-dialog',
  templateUrl: './edit-user-dialog.component.html',
  styleUrls: ['./edit-user-dialog.component.css']
})
export class EditUserDialogComponent {
  imageChangedEvent: any = '';
  public croppedImage: any = '';
    constructor(public buffer:BufferserviceService, private dialog:MatDialog, private conn:ServerConnectionService){}

  fileChangeEvent(event: any): void {
      this.imageChangedEvent = event;
  }
  imageCropped(event: ImageCroppedEvent) {
      this.croppedImage = event.base64;
      console.log(base64ToFile(this.croppedImage))
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

  cancel(){
  } 
  async save(){
    const response = await firstValueFrom(this.conn.TryToUpdateUserProfile(this));
    if(response != "OK"){
      return
    }
    //Aq vai o update do user no frontend
    //this.buffer.userInfo.ImageUrl =
    this.dialog.closeAll();
  }
}
