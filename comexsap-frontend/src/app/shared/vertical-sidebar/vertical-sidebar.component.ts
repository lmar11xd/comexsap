import { Component, EventEmitter, Input, Output } from '@angular/core';
//import { RouteInfo } from './vertical-sidebar.metadata';
import { VerticalSidebarService } from './vertical-sidebar.service';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SettingsService } from 'src/app/core/settings/settings.service';
declare var $: any;

@Component({
  selector: 'app-vertical-sidebar',
  templateUrl: './vertical-sidebar.component.html'
})
export class VerticalSidebarComponent {
  showMenu = '';
  showSubMenu = '';
  public sidebarnavItems: any[] = [];
  path = '';
  nombreAplicacion = '';
  urlLogoAplicacion = '';

  @Input() showClass: boolean = false;
  @Output() notify: EventEmitter<boolean> = new EventEmitter<boolean>();


  handleNotify() {
    this.notify.emit(!this.showClass);
  }

  constructor(private menuServise: VerticalSidebarService, private router: Router, private settings: SettingsService) {
    this.nombreAplicacion = settings.getAppSetting('description');
    this.urlLogoAplicacion = settings.getAppSetting('logo');
    this.sidebarnavItems = menuServise.getMenuItems();
    // Active menu
    this.sidebarnavItems.filter(m => m.submenu.filter(
      (s) => {
        if (s.path === this.router.url) {
          this.path = m.title;
        }
      }
    ));
    this.addExpandClass(this.path);
    /*this.menuServise.items.subscribe(menuItems => {
      this.sidebarnavItems = menuItems;

      // Active menu
      this.sidebarnavItems.filter(m => m.submenu.filter(
        (s) => {
          if (s.path === this.router.url) {
            this.path = m.title;
          }
        }
      ));
      this.addExpandClass(this.path);
    });*/
  }

  addExpandClass(element: any) {
    if (element === this.showMenu) {
      this.showMenu = '0';
    } else {
      this.showMenu = element;
    }
  }

  addActiveClass(element: any) {
    if (element === this.showSubMenu) {
      this.showSubMenu = '0';
    } else {
      this.showSubMenu = element;
    }
    window.scroll({
      top: 0,
      left: 0,
      behavior: 'smooth'
    });
  }

  getVersion(){
    return this.settings.getAppSetting('VERSION');
  }

}
