import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgbModule, NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { NgSelectModule } from '@ng-select/ng-select';
import { NotifierModule } from 'angular-notifier';
import { NgApexchartsModule } from 'ng-apexcharts';
import { ChartistModule } from 'ng-chartist';
import { NgMultiSelectDropDownModule } from 'ng-multiselect-dropdown';
import { ChartsModule } from 'ng2-charts';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { AccordionModule } from 'primeng/accordion';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { CalendarModule } from 'primeng/calendar';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DropdownModule } from 'primeng/dropdown';
import { DialogService, DynamicDialogModule } from 'primeng/dynamicdialog';
import { FileUploadModule } from 'primeng/fileupload';
import { MessageModule } from 'primeng/message';
import { MessagesModule } from 'primeng/messages';
import { MultiSelectModule } from 'primeng/multiselect';
import { PanelModule } from 'primeng/panel';
import { RadioButtonModule } from 'primeng/radiobutton';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { StepsModule } from 'primeng/steps';
import { TableModule } from 'primeng/table';
import { TabMenuModule } from 'primeng/tabmenu';
import { TabViewModule } from 'primeng/tabview';
import { ToastModule } from 'primeng/toast';
import { ComponentsModule } from 'src/app/component/component.module';
import { ConfiguracionModule } from '../configuracion/configuracion.module';
import { DocumentoAereoRoutes } from './documentoaereo.routing';
import { MessageService, ConfirmationService } from 'primeng/api';
import { AuthInterceptor } from 'src/app/core/interceptors/auth.interceptor';
import { TokenInterceptor } from 'src/app/core/interceptors/token.interceptor';
import { ConfiguracionService } from '../configuracion/configuracion.sevice';
import { DocumentoAereoService } from './documentoaereo.service';
import { ListarDocumentoAereoComponent } from './listar/listar.component';
import { CrearDocumentoAereoComponent } from './crear/crear.component';
import { EditarDocumentoAereoComponent } from './editar/editar.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(DocumentoAereoRoutes),
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
    PdfViewerModule,
    ComponentsModule,
    ConfiguracionModule
  ],
  declarations: [
    ListarDocumentoAereoComponent,
    CrearDocumentoAereoComponent,
    EditarDocumentoAereoComponent
  ],
  providers: [
    DatePipe,
    MessageService,
    DialogService,
    ConfirmationService,
    ConfiguracionService,
    DocumentoAereoService,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  exports: [
    RouterModule
  ]
})
export class DocumentoAereoModule { }
