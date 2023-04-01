import { Component } from '@angular/core';
import { ServerConnectionService } from 'src/app/services/server-connection.service';
import { Router } from '@angular/router';
import { LoginTemplate } from 'src/loginTemplate';
import { UserInformation } from 'src/userInformation';
import { catchError } from 'rxjs';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-login-forms',
  templateUrl: './login-forms.component.html',
  styleUrls: ['./login-forms.component.css']
})
export class LoginFormsComponent {
  email:string;
  password:string;
  piroca:UserInformation;
  constructor(private conn:ServerConnectionService, private router:Router){}

  async Submit(){
    if (!this.email  || !this.password){
      return;
    }
    
    let t:LoginTemplate = new LoginTemplate(this.email,this.password);

    const x = await firstValueFrom(this.conn.TryToLogin(t));
    this.piroca = x;
    this.Caralho();
    }
  

  Caralho(){
    if(this.piroca.email == "NULL"){
      this.email = "";
      this.password = "";
      this.router.navigateByUrl("/main");
    }
  }
}

