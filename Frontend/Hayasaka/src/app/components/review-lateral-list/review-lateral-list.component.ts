import { Component, Input } from '@angular/core';
import { ReviewInformation } from 'src/reviewInformation';

@Component({
  selector: 'app-review-lateral-list',
  templateUrl: './review-lateral-list.component.html',
  styleUrls: ['./review-lateral-list.component.css']
})
export class ReviewLateralListComponent {
  starWidth = "1.4vw";
  @Input() reviews:ReviewInformation[];
}
