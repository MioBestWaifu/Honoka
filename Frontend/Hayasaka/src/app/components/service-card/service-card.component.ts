import { Component, Input, OnInit } from '@angular/core';
import { ServiceBundle } from 'src/serviceBundle';
import { ServiceInformation } from 'src/serviceInformation';

@Component({
  selector: 'app-service-card',
  templateUrl: './service-card.component.html',
  styleUrls: ['./service-card.component.css']
})
export class ServiceCardComponent implements OnInit{
  starWidth = "1.6vw"
  @Input() service:ServiceInformation

  ngOnInit(): void {
    //console.log("AVG"+this.service.AverageScore)
  }
}
