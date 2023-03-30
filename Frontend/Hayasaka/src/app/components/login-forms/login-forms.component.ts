import { Component } from '@angular/core';
import { ServerConnectionService } from 'src/app/services/server-connection.service';
import { Router } from '@angular/router';
import { LoginTemplate } from 'src/loginTemplate';

@Component({
  selector: 'app-login-forms',
  templateUrl: './login-forms.component.html',
  styleUrls: ['./login-forms.component.css']
})
export class LoginFormsComponent {
  email:string;
  password:string;
  constructor(private conn:ServerConnectionService, private router:Router){}

  Submit(){
    if (!this.email  || !this.password){
      return;
    }
    
    let t:LoginTemplate = new LoginTemplate(this.email,this.password);

    if(this.conn.TryToLogin(t)){
      this.email = "";
      this.password = "";
      this.router.navigateByUrl("/main");
    }
  }
}
