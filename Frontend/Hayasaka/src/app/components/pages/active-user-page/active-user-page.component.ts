import { Component } from '@angular/core';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { EditUserDialogComponent } from '../../dialogs/edit-user-dialog/edit-user-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-active-user-page',
  templateUrl: './active-user-page.component.html',
  styleUrls: ['./active-user-page.component.css']
})
export class ActiveUserPageComponent {

  constructor(public buffer:BufferserviceService,private dialog:MatDialog){
    
  }

  edit(){
    const x = this.dialog.open(EditUserDialogComponent)
    x.updateSize("40vw","60vh")
   }

}
