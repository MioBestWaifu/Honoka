import { ServiceInformation } from 'src/serviceInformation';
import { UserInformation } from 'src/userInformation';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-lateral-bar',
  templateUrl: './lateral-bar.component.html',
  styleUrls: ['./lateral-bar.component.css']
})
export class LateralBarComponent {
  @Input() user:UserInformation;
}
