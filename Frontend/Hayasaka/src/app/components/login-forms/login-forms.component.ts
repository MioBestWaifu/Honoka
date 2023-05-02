import { Component } from '@angular/core';
import { ServerConnectionService } from 'src/app/services/server-connection.service';
import { Router } from '@angular/router';
import { LoginTemplate } from 'src/loginTemplate';
import { UserInformation } from 'src/userInformation';
import { catchError } from 'rxjs';
import { firstValueFrom } from 'rxjs';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { MatDialog } from '@angular/material/dialog';
import { RegisterFormsComponent } from '../register-forms/register-forms.component';
import { MissingInfoDialogComponent } from '../dialogs/missing-info-dialog/missing-info-dialog.component';
import { WrongCredentialsDialogComponent } from '../dialogs/wrong-credentials-dialog/wrong-credentials-dialog.component';


@Component({
  selector: 'app-login-forms',
  templateUrl: './login-forms.component.html',
  styleUrls: ['./login-forms.component.css']
})
export class LoginFormsComponent {
  email:string;
  password:string;
  user:UserInformation;
  constructor(private conn:ServerConnectionService, private router:Router,private buffer:BufferserviceService,private dialog:MatDialog){}

  async Submit(){
    if (!this.email  || !this.password){
      this.dialog.open(MissingInfoDialogComponent)
      return;
    }
    
    let t:LoginTemplate = new LoginTemplate(this.email,this.password);
    try{
    const x = await firstValueFrom(this.conn.TryToLogin(t));
    this.user = x;
    console.log(x);
    x.ServiceRecs.forEach(z => console.log(z.ServiceInfos[0].ServiceName));
    } catch (error){
      console.log(error)
    }
    if(this.user.Email != "NULL"){
      this.email = "";
      this.password = "";
      this.buffer.userInfo = this.user;
      this.router.navigateByUrl("/main");
    } else {
      this.dialog.open(WrongCredentialsDialogComponent)
    }
    }
}

