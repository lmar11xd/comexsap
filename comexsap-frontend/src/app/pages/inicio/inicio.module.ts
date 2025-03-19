import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { NgbModalModule, NgbModule, NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { NotifierModule, NotifierOptions } from 'angular-notifier';
import { ChartsModule } from 'ng2-charts';
import { ChartistModule } from 'ng-chartist';
import { NgApexchartsModule } from "ng-apexcharts";
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { DropdownModule } from 'primeng/dropdown';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { FileUploadModule } from 'primeng/fileupload';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MessageService } from 'primeng/api';
import { MultiSelectModule } from 'primeng/multiselect';
import { TableModule } from 'primeng/table';
import { DialogService, DynamicDialogModule } from 'primeng/dynamicdialog';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { AccordionModule } from 'primeng/accordion';
import { RadioButtonModule } from 'primeng/radiobutton';
import { CalendarModule } from 'primeng/calendar';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { ToastModule } from 'primeng/toast';
import { NgSelectModule } from '@ng-select/ng-select';
import { TabViewModule } from 'primeng/tabview';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { TabMenuModule } from 'primeng/tabmenu';
import { StepsModule } from 'primeng/steps';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { PanelModule } from 'primeng/panel';
import { AuthInterceptor } from '../../core/interceptors/auth.interceptor';
import { TokenInterceptor } from '../../core/interceptors/token.interceptor';
import { ConfiguracionService } from '../configuracion/configuracion.sevice';
import { InicioComexComponent } from './inicio-comex/inicio-comex.component';
import { InicioRoutes } from './inicio.routing';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { CircularExportacionComponent } from './graficos/circular/circular-exportacion.component';
import { BarrasExportacionComponent } from './graficos/barras/barras-exportacion.component';
import { TarjetasExportacionComponent } from './graficos/tarjetas/tarjetas-exportacion.component';
import { InicioService } from './inicio.service';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(InicioRoutes),
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    NotifierModule,
    NgbModalModule,
    ChartsModule,
    ChartistModule,
    NgApexchartsModule,
    PerfectScrollbarModule,
    DropdownModule,
    NgMultiSelectDropDownModule,
    FileUploadModule,
    HttpClientModule,
    MultiSelectModule,
    TableModule,
    DynamicDialogModule,
    ScrollPanelModule,
    AccordionModule,
    RadioButtonModule,
    CalendarModule,
    MessagesModule,
    MessageModule,
    ToastModule,
    NgSelectModule,
    // InputNumberModule
    TabViewModule,
    ConfirmDialogModule,
    TabMenuModule,
    StepsModule,
    BreadcrumbModule,
    PanelModule,
    NgxChartsModule
  ],
  declarations: [
    InicioComexComponent,
    CircularExportacionComponent,
    BarrasExportacionComponent,
    TarjetasExportacionComponent
  ],
  providers: [
      DatePipe,
      MessageService,
      DialogService,
      ConfirmationService,
      ConfiguracionService,
      InicioService,
      { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
      { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  exports: [
      RouterModule
  ]
})
export class InicioModule {}
