import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { ServerConnectionService } from 'src/app/services/server-connection.service';
import { RegisterTemplate } from 'src/registerTemplate';

@Component({
  selector: 'app-register-forms',
  templateUrl: './register-forms.component.html',
  styleUrls: ['./register-forms.component.css']
})
export class RegisterFormsComponent {
  email:string;
  password:string;
  username:string;
  birthday:Date;

  constructor(private conn:ServerConnectionService, private router:Router){}

  async Submit(){
    if (!this.email  || !this.password || !this.username || !this.birthday){
      return;
    }
    let registerTemplate = new RegisterTemplate(this.email,this.password,this.username,this.birthday);
    const response = await firstValueFrom(this.conn.TryToRegister(registerTemplate));
    if (response == "DONE"){
      this.email = "";
      this.password = "";
      this.username = "";
      this.birthday = new Date();
      this.router.navigateByUrl("/");
    } else {
      
    }
  }
}
