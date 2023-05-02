import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { EditUserDialogComponent } from '../dialogs/edit-user-dialog/edit-user-dialog.component';

@Component({
  selector: 'app-user-basic-info',
  templateUrl: './user-basic-info.component.html',
  styleUrls: ['./user-basic-info.component.css']
})
export class UserBasicInfoComponent {
  constructor(public buffer:BufferserviceService, private dialog:MatDialog){}

  edit(){
    this.dialog.open(EditUserDialogComponent)
  }
}
