import { Component,OnInit } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { ServerConnectionService } from 'src/app/services/server-connection.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit{

  constructor (private conn:ServerConnectionService){}
  async ngOnInit(){
    const x = await firstValueFrom (this.conn.SetLastPage("/pages/register"));
  }
}
