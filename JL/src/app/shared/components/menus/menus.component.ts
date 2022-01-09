import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menus.component.html',
  styleUrls: ['./menus.component.css']
})
export class MenusComponent implements OnInit {
  events: string[] = [];
  opened: boolean = false;
  menus: string[] = ['a', 'b'];
  fillerNav = Array.from({length: 50}, (_, i) => `Nav Item ${i + 1}`);
  shouldRun = /(^|.)(stackblitz|webcontainer).(io|com)$/.test(window.location.host);
  constructor() { }

  ngOnInit(): void {
  }

}
