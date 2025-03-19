import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Approutes } from './routes';

@NgModule({
    imports: [
        RouterModule.forRoot(Approutes, { useHash: true })
    ],
    declarations: [],
    exports: [
        RouterModule
    ]
})

export class RoutesModule {
    constructor() {
    }
}
