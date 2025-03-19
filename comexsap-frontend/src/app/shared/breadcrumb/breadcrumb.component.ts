import { Component, Input, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router, NavigationEnd, ActivatedRoute, Data } from '@angular/router';
import { MenuItem } from 'primeng/api/menuitem';
import { Breadcrumb } from 'primeng/breadcrumb';
import { Observable } from 'rxjs';
import { filter, map, mergeMap } from 'rxjs/operators';
import { BreadcrumbService } from '../breadcrumb.service';
import { Breadcrumb2Service } from '../breadcrumb2.service';

@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html'
})
export class BreadcrumbComponent implements OnInit {

  breadcrumbs$: Observable<MenuItem[]>;
  inicio: MenuItem;
  listaTabs: MenuItem[] = [];

  pageInfo: Data = Object.create(null);
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private titleService: Title,
    private readonly breadcrumbService: Breadcrumb2Service
  ) {
    this.breadcrumbs$ = breadcrumbService.breadcrumbs$;
    this.inicio = { label: 'Inicio', url: '#/', target: '' };
    this.breadcrumbs$.subscribe(val => this.listaTabs = val);
    // this.router.events
    //   .pipe(filter(event => event instanceof NavigationEnd))
    //   .pipe(map(() => this.activatedRoute))
    //   .pipe(
    //     map(route => {
    //       while (route.firstChild) {
    //         route = route.firstChild;
    //       }
    //       return route;
    //     })
    //   )
    //   .pipe(filter(route => route.outlet === 'primary'))
    //   .pipe(mergeMap(route => route.data))
    //   .subscribe(event => {
    //     this.titleService.setTitle(event['title']);
    //     this.pageInfo = event;
    //   });
  }
  ngOnInit() { }

}
