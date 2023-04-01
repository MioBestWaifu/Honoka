import { Component } from '@angular/core';
import { ServerConnectionService } from 'src/app/services/server-connection.service';
import { Router } from '@angular/router';
import { LoginTemplate } from 'src/loginTemplate';
import { UserInformation } from 'src/userInformation';
import { catchError } from 'rxjs';
import { firstValueFrom } from 'rxjs';
import { BufferserviceService } from 'src/app/services/bufferservice.service';

@Component({
  selector: 'app-login-forms',
  templateUrl: './login-forms.component.html',
  styleUrls: ['./login-forms.component.css']
})
export class LoginFormsComponent {
  email:string;
  password:string;
  user:UserInformation;
  constructor(private conn:ServerConnectionService, private router:Router,private buffer:BufferserviceService){}

  async Submit(){
    if (!this.email  || !this.password){
      return;
    }
    
    let t:LoginTemplate = new LoginTemplate(this.email,this.password);

    const x = await firstValueFrom(this.conn.TryToLogin(t));
    this.user = x;
    if(this.user.Email != "NULL"){
      this.email = "";
      this.password = "";
      this.buffer.userInfo = this.user;
      this.router.navigateByUrl("/main");
    }
    }
}

