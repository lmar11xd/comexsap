import { NgModule, Optional, SkipSelf } from '@angular/core';

import { SettingsService } from './settings/settings.service';

import { throwIfAlreadyLoaded } from './module-import-guard';
import { SesionWebService } from './seguridad/sesion-web.service';
import { SesionLocalService } from './seguridad/sesion-local.service';
import { SesionCloudService } from './seguridad/sesion-cloud.service';
import { DbService } from './db/db.service';
import { SesionDbService } from './db/sesion-db.service';
import { AuthGuardService } from './seguridad/auth-guard.service';
import { WindowRef } from './db/WindowRef';
import { MessageService } from 'primeng/api';

@NgModule({
    imports: [
    ],
    providers: [
      SettingsService,
      SesionWebService,
      SesionLocalService,
      SesionCloudService,
      DbService,
      SesionDbService,
      WindowRef,
      AuthGuardService,
      MessageService
    ],
    declarations: [
    ],
    exports: [
    ]
})
export class CoreModule {
    constructor( @Optional() @SkipSelf() parentModule: CoreModule) {
        throwIfAlreadyLoaded(parentModule, 'CoreModule');
    }
}
