import { Component, Input } from '@angular/core';
import { ClientServiceInteraction } from 'src/clientServiceInteraction';

@Component({
  selector: 'app-instance-for-provider-card',
  templateUrl: './instance-for-provider-card.component.html',
  styleUrls: ['./instance-for-provider-card.component.css']
})
export class InstanceForProviderCardComponent {
  @Input() request:ClientServiceInteraction;
}
