import { Component, OnDestroy, OnInit } from '@angular/core';
import { BufferserviceService } from 'src/app/services/bufferservice.service';
import { ServerConnectionService } from 'src/app/services/server-connection.service';
import {firstValueFrom} from 'rxjs';

@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styleUrls: ['./search-page.component.css']
})
export class SearchPageComponent implements OnInit{
  constructor(public buffer:BufferserviceService, private conn:ServerConnectionService){}

  async ngOnInit(){
    const x = await firstValueFrom(this.conn.SetLastPage("/search"));
  }
}
