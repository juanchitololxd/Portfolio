import { Component, OnInit } from '@angular/core';
import { JConfig } from './../../models/jConfig';
import { Option} from './../../models/options';

@Component({
  selector: 'app-dash-board',
  templateUrl: './dash-board.component.html',
  styleUrls: ['./dash-board.component.css']
})
export class DashBoardComponent implements OnInit {
  jconfig: JConfig = JSON.parse('{ "title": "DashBoard", "options": [ { "name": "estudio", "enabled": "true" }, { "name": "finanzas", "enabled": "true" }, { "name": "tareas", "enabled": "true" } ] }');
  opened: boolean = false;
  menus: string[] = [];

  shouldRun = /(^|.)(stackblitz|webcontainer).(io|com)$/.test(window.location.host);
  constructor() { }

  ngOnInit(): void {
    console.log(this.jconfig);
    this.jconfig["options"].forEach(element => {
      this.menus.push(element.name);
    });
  }

}
