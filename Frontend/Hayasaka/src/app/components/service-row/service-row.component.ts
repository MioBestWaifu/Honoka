import { Component, Input } from '@angular/core';
import { ServiceBundle } from 'src/serviceBundle';

@Component({
  selector: 'app-service-row',
  templateUrl: './service-row.component.html',
  styleUrls: ['./service-row.component.css']
})
export class ServiceRowComponent {
  @Input() bundle:ServiceBundle
}
