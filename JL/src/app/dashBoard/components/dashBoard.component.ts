import { Component, OnInit } from '@angular/core';
import { JConfig } from './../../models/JConfig';

@Component({
  selector: 'app-dash-board',
  templateUrl: './dash-board.component.html',
  styleUrls: ['./dash-board.component.css']
})
export class DashBoardComponent implements OnInit {
  jconfig: JConfig = JSON.parse('{ "title": "DashBoard", "options": [ { "name": "estudio", "enabled": "false", "depth": 0, "iconName": "help", "route": "dash", "children": [ { "name": "estudio2", "enabled": "false", "depth": 1, "iconName": "help", "route": ".", "children": [] } ] }, { "name": "Finanzas", "enabled": "false", "depth": 0, "iconName": "help", "route": "dash", "children": [] } ] }');
  opened: boolean = false;
  menus: any[] = [];

  shouldRun = /(^|.)(stackblitz|webcontainer).(io|com)$/.test(window.location.host);
  constructor() { }

  ngOnInit(): void {
    this.jconfig["options"].forEach(element => {
      this.menus.push(element);

    });
  }

}
