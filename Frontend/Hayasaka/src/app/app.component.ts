import { Component, OnDestroy, OnInit } from '@angular/core';
import { ServerConnectionService } from './services/server-connection.service';
import { firstValueFrom } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy{
  title = 'Hayasaka';
  constructor (private conn:ServerConnectionService, private router:Router){}
  async ngOnInit(){
    const y = await firstValueFrom(this.conn.EstablishConnection());
    const x = await firstValueFrom(this.conn.GetTargetPage());
    this.router.navigateByUrl(x);
  }

  async ngOnDestroy(){
      
  }
}
