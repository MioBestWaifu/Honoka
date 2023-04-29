import { Component, Input } from '@angular/core';
import { ServiceBundle } from 'src/serviceBundle';
import { ServiceInformation } from 'src/serviceInformation';

@Component({
  selector: 'app-service-card',
  templateUrl: './service-card.component.html',
  styleUrls: ['./service-card.component.css']
})
export class ServiceCardComponent {
  @Input() service:ServiceInformation
}
