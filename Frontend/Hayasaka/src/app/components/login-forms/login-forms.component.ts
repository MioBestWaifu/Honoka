import { Component } from '@angular/core';
import { ServerConnectionService } from 'src/app/services/server-connection.service';

@Component({
  selector: 'app-login-forms',
  templateUrl: './login-forms.component.html',
  styleUrls: ['./login-forms.component.css']
})
export class LoginFormsComponent {
  username:string;
  password:string;
  constructor(private conn:ServerConnectionService){}

  Submit(){
    if (!this.username  || !this.password){
      return;
    }

    if(this.conn.TryToLogin(this.username,this.password)){
      this.username = "";
      this.password = ""
    }
  }
}
