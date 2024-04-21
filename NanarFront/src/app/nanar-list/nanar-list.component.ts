import {Component} from '@angular/core';
/*import {NanarService} from "../Model/nanar.service";*/
import {Nanar} from "../Model/Nanar";
import {Router} from "@angular/router";


@Component({
  selector: 'app-nanar-list',
  templateUrl: './nanar-list.component.html',
  styleUrls: ['./nanar-list.component.css']
})
export class NanarListComponent {

  /*nanars: Nanar[]

  constructor(
    private nanarService: NanarService,
    private router: Router
  ) {
    this.nanars = []
    nanarService.get()
      .subscribe((nanars)=>this.nanars=nanars)
  }*/

}
