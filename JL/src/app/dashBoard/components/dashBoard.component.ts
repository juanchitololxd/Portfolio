import { Component, OnInit } from '@angular/core';
import { JConfig } from './../../models/JConfig';
import { Option } from './../../models/Options'
import { MatTreeNestedDataSource } from '@angular/material/tree';
import { NestedTreeControl } from '@angular/cdk/tree';
import { Router } from '@angular/router';
@Component({
  selector: 'app-dash-board',
  templateUrl: './dash-board.component.html',
  styleUrls: ['./dash-board.component.css']
})
export class DashBoardComponent implements OnInit {
  jconfig: JConfig = JSON.parse('{ "title": "DashBoard", "options": [ { "id": 1, "name": "dashBoard", "iconName": "book", "enabled": true, "children": [] }, { "id": 2, "name": "estudio", "iconName": "book", "enabled": true, "children": [ { "id": 21, "name": "materias", "iconName": "collections_bookmark", "enabled": true, "children": [] }, { "id": 22, "name": "libros", "iconName": "asdf", "enabled": true, "children": [] } ] }, { "id": 3, "name": "finanzas", "iconName": "help", "enabled": true, "children": [ { "id": 31, "name": "whishList", "iconName": "asdf", "enabled": true, "children": [] } ] } ] }');
  opened: boolean = true;
  menus: any[] = [];
  treeControl = new NestedTreeControl<Option>(node => node.children);
  dataSource = new MatTreeNestedDataSource<Option>();

  hasChild = (_: number, node: Option) => !!node.children && node.children.length > 0;
  

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.dataSource.data = this.jconfig["options"];
  }

  navigate(nodo: Option){
    //
    let dir: string = nodo.id + "";
    let URL: string = './dash';
    let indx = +dir[0]-1;
    URL = this.generatePath(this.jconfig["options"][indx], URL, dir.substring(1));
    this.router.navigate([URL]);
    this.closeMenus(nodo);
  }

  closeMenus(nodo: Option) {
    this.jconfig["options"].forEach(option => {
      if(option.id != nodo.id) this.treeControl.collapse(option);
    })
    
  }
  generatePath(option: Option, baseURL: string, name: string): string {
    baseURL += '/'+ option.name;
    if(name == "") return baseURL;
    
    let indx = +name[0]-1;
    if(option["children"]) return this.generatePath(option["children"][indx], baseURL, name.substring(1));
    return '';
  }
}
