import { Injectable } from '@angular/core';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';

const PDF_EXTENSION = '.pdf';

@Injectable()
export class PdfDownloadHelper {

  constructor() { }

  public export(columns: any[], data: any[], pdfFileName: string): void {

    const doc = new jsPDF();

    autoTable(doc, {
      head: columns,
      body: data,
      didDrawPage: (dataArg) => {
        doc.text('', dataArg.settings.margin.left, 10);
      }
    });

    doc.save(pdfFileName + PDF_EXTENSION);

  }
}