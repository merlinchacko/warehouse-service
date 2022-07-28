import {Component, OnInit} from '@angular/core';

import {Router} from "@angular/router";
import {Box} from "./warehouse/box";
import {BoxService} from "./warehouse/box-service.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.less']
})
export class AppComponent implements OnInit {
  boxes: Box[];
  search : String ="";
  columnsToDisplay = ['description', 'product', 'location'];

  constructor(private router: Router, private boxService: BoxService) {
  }

  getBoxes() {
    this.boxService.getBoxes().subscribe(data => {
      this.boxes = data;
    });
  }

  addBox(): void {
    this.router.navigate(['add-box'])
      .then((e) => {
        if (e) {
          console.log("Navigation is successful!");
        } else {
          console.log("Navigation has failed!");
        }
      });
  };

  ngOnInit(): void {
    this.router.events.subscribe(value => {
      this.getBoxes();
    });
  }

  onSearch() {
    this.boxService.searchBox(this.search)
      .subscribe(data => {
        //this.router.navigate(['list-boxes']);
        this.boxes = data;
      });
  }
}
