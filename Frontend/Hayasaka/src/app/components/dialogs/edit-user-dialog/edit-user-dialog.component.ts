import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { ImageCroppedEvent, LoadedImage, base64ToFile } from 'ngx-image-cropper';
import { ServerConnectionService } from 'src/app/services/server-connection.service';
import { firstValueFrom } from 'rxjs';
import { FailedUpdateDialogComponent } from '../failed-update-dialog/failed-update-dialog.component';

@Component({
  selector: 'app-edit-user-dialog',
  templateUrl: './edit-user-dialog.component.html',
  styleUrls: ['./edit-user-dialog.component.css']
})
export class EditUserDialogComponent {
  imageChangedEvent: any = '';
  public newName:string;
  public croppedImage: any = '';
    constructor(public buffer:BufferserviceService, private dialog:MatDialog, private conn:ServerConnectionService){
      this.croppedImage = buffer.userInfo.imageUrl;
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

  cancel(){
    this.dialog.closeAll();
  } 
  async save(){
    if (this.croppedImage != this.buffer.userInfo.imageUrl){
      var response = await firstValueFrom(this.conn.TryToUpdateUserPicture(this));
      if(response.status != 201){
        this.dialog.open(FailedUpdateDialogComponent)
        return
      }
    }

    if(this.newName != undefined){
      response = await firstValueFrom(this.conn.TryToUpdateUserName(this));
      if(response.status != 201){
        this.dialog.open(FailedUpdateDialogComponent)
        return
      }
    }
    this.buffer.userInfo = await firstValueFrom(this.conn.ReloadUser());
    this.dialog.closeAll();
  }
}
