import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-search',
  templateUrl: './home-search.component.html',
  styleUrls: ['./home-search.component.css']
})
export class HomeSearchComponent implements OnInit {

  showList:boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  toggleList():void {
    this.showList = !this.showList;
  }

}
