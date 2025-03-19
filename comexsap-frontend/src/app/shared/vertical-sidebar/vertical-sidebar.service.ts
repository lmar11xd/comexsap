import { Injectable } from '@angular/core';
//import { BehaviorSubject } from 'rxjs';
//import { RouteInfo } from './vertical-sidebar.metadata';
//import { ROUTES } from './vertical-menu-items';


@Injectable({
    providedIn: 'root'
})
export class VerticalSidebarService {

    public screenWidth: any;
    public collapseSidebar: boolean = false;
    public fullScreen: boolean = false;

    MENUITEMS: any[] = [];//ROUTES;

    public items = [];//new BehaviorSubject<any[]>(this.MENUITEMS);

    constructor() {
    }

    setMenuItems(menuItems: any[]){
        for(let i=0;i < menuItems.length;i++){
            let item = menuItems[i];
            item.class = '';
            item.ddclass = '';
            item.label = '';
            item.labelClass = '';
            item.extralink = false;
            if(item.submenu == null){
                item.submenu = [];
            }else{
                let submenu = item.submenu;
                for(let j=0;j < submenu.length;j++){
                    let subitem = submenu[j];
                    subitem.class = '';
                    subitem.ddclass = '';
                    subitem.label = '';
                    subitem.labelClass = '';
                    subitem.extralink = false;
                    if(subitem.submenu == null){
                        subitem.submenu = [];
                    }
                }
            }
        }
        this.items = menuItems;
    }

    getMenuItems(){
        return this.items;
    }

}
