import { Component, OnInit } from '@angular/core';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { ActivatedRoute } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { UserInformation } from 'src/userInformation';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit{
  id:number;
  sub:any;
  info:UserInformation;
  constructor(public buffer:BufferserviceService, private router:ActivatedRoute){}

  async ngOnInit(){
    this.sub = this.router.params.subscribe(params => {
      this.id = +params['id']; 
      
   });
  }

}
