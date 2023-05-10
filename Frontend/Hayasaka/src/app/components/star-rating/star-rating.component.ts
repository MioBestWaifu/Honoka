import { Component, Input, OnInit } from '@angular/core';
import { ServerConnectionService } from 'src/app/services/server-connection.service';

@Component({
  selector: 'app-star-rating',
  templateUrl: './star-rating.component.html',
  styleUrls: ['./star-rating.component.css']
})
export class StarRatingComponent implements OnInit{
  @Input() rating:number;
  @Input() isEnabled:boolean;
  @Input() imgWidth:string;
  fullstar:string;
  halfstar:string;
  emptystar:string;
  sources:string[];

  constructor(private conn:ServerConnectionService){
    this.fullstar = this.conn.requestsUrl + "images/app/fullstar.png"
    this.halfstar = this.conn.requestsUrl + "images/app/halfstar.png"
    this.emptystar = this.conn.requestsUrl + "images/app/emptystar.png"
  }
  ngOnInit(): void {
    var x = this.rating;
    //console.log("PRIM" + x)
    x *= 10; 
    x = Math.round(x / 5) * 5;
    x /= 10;
    console.log(x)
    this.sources = new Array<string>(5).fill(this.emptystar)
    //console.log(this.sources)
    var i = 0.5;
    for (i; i<x;i++){
      this.sources[i-0.5] = this.fullstar
    }
    console.log(x.toString().charAt(2))
    if (x.toString().charAt(2) == '5'){
      this.sources[i-0.5] = this.halfstar
    }

    const y = document.getElementsByClassName("starImg")  as HTMLCollectionOf<HTMLElement>;
    
    console.log("IMAGE WIDTH");
    console.log(y.length);
    for (let a = 0; a < y.length; a++) {
      if (y[a].style.width == "1.6vw"){
        y[a].style.width = this.imgWidth
      }
    }
  }
}
