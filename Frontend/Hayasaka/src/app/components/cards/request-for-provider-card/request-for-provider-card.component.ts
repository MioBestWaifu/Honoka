import { Component, Input } from '@angular/core';
import { ClientServiceInteraction } from 'src/clientServiceInteraction';
import {Utils} from 'src/utils';
@Component({
  selector: 'app-request-for-provider-card',
  templateUrl: './request-for-provider-card.component.html',
  styleUrls: ['./request-for-provider-card.component.css']
})
export class RequestForProviderCardComponent {
  @Input() request:ClientServiceInteraction;
}
