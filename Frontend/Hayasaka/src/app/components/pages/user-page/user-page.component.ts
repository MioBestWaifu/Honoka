import { Component, OnInit } from '@angular/core';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { ActivatedRoute } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { UserInformation } from 'src/userInformation';
import { ServerConnectionService } from 'src/app/services/server-connection.service';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit{
  id:number;
  sub:any;
  info:UserInformation;
  constructor(public buffer:BufferserviceService, private router:ActivatedRoute, private conn:ServerConnectionService){}

  async ngOnInit(){
    if (this.buffer.userInfo == null){
      this.buffer.userInfo = await firstValueFrom(this.conn.ReloadUser());
    }
    this.sub = this.router.params.subscribe(params => {
      this.id = +params['id']; 
  
   })
   const x = await firstValueFrom (this.conn.SetLastPage("/user/"+this.id));
   this.info = await firstValueFrom(this.conn.GetUser(this.id.toString()));
  }

}
