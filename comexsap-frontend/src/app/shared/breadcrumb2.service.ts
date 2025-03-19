import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Data, NavigationEnd, Router } from "@angular/router";
import { MenuItem } from "primeng/api";
import { Breadcrumb } from "primeng/breadcrumb";
import { BehaviorSubject } from "rxjs";
import { filter } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class Breadcrumb2Service {

  // Subject emitting the breadcrumb hierarchy
  private readonly _breadcrumbs$ = new BehaviorSubject<MenuItem[]>([]);

  // Observable exposing the breadcrumb hierarchy
  readonly breadcrumbs$ = this._breadcrumbs$.asObservable();

  constructor(private router: Router) {
    // this.router.events
    //   .pipe(
    //     // Filter the NavigationEnd events as the breadcrumb is updated only when the route reaches its end
    //     filter((event) => event instanceof NavigationEnd)
    //   )
    //   .subscribe((event) => {
    //     // Construct the breadcrumb hierarchy
    //     const root = this.router.routerState.snapshot.root;
    //     const breadcrumbs: MenuItem[] = [];
    //     this.addBreadcrumb(root, [], breadcrumbs);

    //     // Emit the new hierarchy
    //     this._breadcrumbs$.next(breadcrumbs);
    //   });
  }

  public addBreadcrumbs(breadcrumbs: MenuItem[]){
    if(breadcrumbs === null){
      breadcrumbs = [];
    }
    this._breadcrumbs$.next(breadcrumbs);
  }

}
