import { DAYS_OF_WEEK } from "calendar-utils";
import * as moment from "moment";
import { DAY_FORMAT } from "./dayformat";

export class Utils {
  public static dateTimeToStringDate(datetime: string) {
    if(datetime == null || datetime == '') return null;
    var date = datetime.split('T')[0];
    return date;
  }

  public static stringToDatetime(datetime: string) {//Input format: "2023-05-21T04:30:00.000+00:00"
    if(datetime == null || datetime == '') return null;
    var date = datetime.substring(0,16);
    return date;
  }

  public static stringToDate(strDate: string) {//Input format: "2023-05-21T04:30:00.000+00:00"
    if(strDate == null || strDate == '' || strDate.trim() == '') return null;
    const date = strDate.substring(0, 10);
    const [year, month, day] = date.split("-");
    return new Date(parseInt(year), parseInt(month) - 1, parseInt(day));
  }

  public static isNullOrEmpty(value) {
    return (value == undefined || !value || value == "" || value.length == 0);
  }

  public static isNullOrUndefined(value) {
    return (value == undefined || value == null);
  }

  public static isEmail(email: string) {
    const expRegEmail=/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/;
    return email.match(expRegEmail) != null;
  }

  public static esEntero(valor) {
    if (isNaN(valor)){
      return false;
    } else {
      if (valor % 1 == 0) {
        return true;
      } else {
        return false;
      }
    }
  }

  public static quitarDuplicados(value, index, self) {
    return self.indexOf(value) === index;
  }

  public static round(value, decimals) {
    if(value == null) return "0";
    return parseFloat("" + Math.round(value * Math.pow(10, decimals)) / Math.pow(10, decimals)).toFixed(decimals);
  }

  public static dateYMD(date) {
    var sValue;
    if (date instanceof Date) {
      sValue = String(date.getFullYear()) + this.padStart(String(date.getMonth() + 1), "0", 2) + this.padStart(String(date.getDate()), "0", 2);
    } else {//Input Format: yyyy-MM-dd ** Output Format: yyyyMMdd
      var string = String(date);
      if(string == null) return "";
      const strDate = string.split("T");
      sValue = this.replaceAll(strDate[0], "-", "");
    }
    return sValue;
  }

  public static padStart(sFrom, sValue, sSize) {
    var string = String(sFrom);
    while (string.length < sSize) {
    	string = sValue + string;
    }
    return string;
  }

  public static toString(sValue) {
    if (sValue === null || sValue === "" || sValue === undefined) {
      sValue = "";
    }
    return sValue;
  }

  public static toNumber(sValue) {
    if (sValue === null || sValue === "" || sValue === undefined) {
      sValue = 0;
    } else {
      if (isNaN(sValue)) {
        sValue = 0;
      }
    }

    return sValue;
  }

  public static replaceAll (string, search, replace) {
    return string.split(search).join(replace);
  }

  public static isValidCodePer(value: string) {
    if(value == null) return false;
    const exp = /^PER\.[0-9]+\.[0-9]+/;
    if(value.trim().match(exp)) return true;
    return false;
  }

  public static toCodeMaterial(codigoMaterial: string) {
    const code = codigoMaterial.padStart(18, '0');
    return code;
  }

  public static removeLeftZeros(value: string) {
    if(value == undefined || value == null) return "";
    return value.replace(/^(0+)/g, '');
  }

  public static isCodeSapCustomer(codigoSap: string) {
    if(codigoSap==null) return false;
    const exp = /^99/;
    return !exp.test(codigoSap);
  }

  public static getCurrentTimeId() {
    return moment(new Date()).format('YYYYMMDDhhmmss').toString();
  }

  public static getFirstDateCurrentMonth() {
    var date = new Date();
    var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
    return firstDay;
  }

  public static getLastDateCurrentMonth() {
    var date = new Date();
    var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
    return lastDay;
  }

  public static dateToShortFormat(date: string) {//FORMAT: YYYY-MM-DDT00:00:000
    if(date == null || date == '') return '';
    const arrDate = date.substring(0,10).split("-");
    const strDate = [arrDate[2], arrDate[1], arrDate[0] ].join('/');
    return strDate;
  }

  public static formatDate(date: Date) {
    var d = new Date(date),
    month = '' + (d.getMonth() + 1),
    day = '' + d.getDate(),
    year = d.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;

    return [year, month, day].join('-');
  }

  public static formatYearMonth(date: Date) {
    var d = new Date(date),
    month = '' + (d.getMonth() + 1),
    year = d.getFullYear();

    if (month.length < 2)
      month = '0' + month;

    return [year, month].join('-');
  }

  public static addDaysToDate(strDate: string, days: number) {//FORMAT: YYYY-MM-DDT00:00:000
    if(strDate == null || strDate == '') return null;

    if(strDate.length > 10) {
      strDate = strDate.substring(0, 10);
    }

    var d = new Date(strDate + " 00:00");
    d.setDate(d.getDate() + days);

    return d;
  }

  public static getPreviousYear() {
    var date = new Date();
    date.setMonth(date.getMonth() - 12);
    return date;
  }

  public static getPreviousMonth() {
    var date = new Date();
    var month = date.getMonth();
    var year = date.getFullYear();
    var day = date.getDate();
    var lastDate = new Date(year, month, 0);
    if (month === 0) {
      month = 11;
      year = year - 1;
    } else {
      month = month - 1;
    }
    if (day > lastDate.getDate()) {
      day = lastDate.getDate();
    }
    var fecha = new Date(year, month, day);
    return fecha;
  }

  public static getNextBusinnesDay(strDate: string) {
    if(strDate == null || strDate == '') return '';

    if(strDate.length > 10) {
      strDate = strDate.substring(0, 10);
    }

    var d = new Date(strDate + " 00:00");

    if(d.getDay() == DAYS_OF_WEEK.FRIDAY) {
      d.setDate(d.getDate() + 3);
    } else if(d.getDay() === DAYS_OF_WEEK.SATURDAY) {
      d.setDate(d.getDate() + 2)
    } else {
      d.setDate(d.getDate() + 1)
    }

    return this.dateToStringFormat(d, DAY_FORMAT.DDMMYYYY_SLASH);
  }

  public static dateToStringFormat(date: Date, format: DAY_FORMAT) {
    if(date == null) return '';

    var year = date.getFullYear(),
    month = '' + (date.getMonth() + 1),
    day = '' + date.getDate();

    if (month.length < 2)
      month = '0' + month;

    if (day.length < 2)
      day = '0' + day;

    if(format == DAY_FORMAT.DDMMYYYY_SLASH) {
      return [day, month, year].join('/');
    } else if (format == DAY_FORMAT.YYYYMMDD_GUION) {
      return [year, month, day].join('-');
    } else {
      return '';
    }
  }

  public static workingDays(dateFrom: string, dateTo: string) {//format: dd/MM/yyyy
    if(dateFrom == null || dateFrom == '' || dateTo == null || dateTo == '') return 0;

    var from = moment(dateFrom, 'DD/MM/YYY'),
      to = moment(dateTo, 'DD/MM/YYY'),
      days = 0;

    while (!from.isAfter(to)) {
      // Si no es sabado ni domingo
      if (from.isoWeekday() !== 6 && from.isoWeekday() !== 7) {
        days++;
      }
      from.add(1, 'days');
    }
    return days;
  }

  public static getNameMonth(month: number) {
    let nameMonth = "";
    if(month == 1) {
      nameMonth = "Enero";
    } else if (month == 2) {
      nameMonth = "Febrero";
    } else if (month == 3) {
      nameMonth = "Marzo";
    } else if (month == 4) {
      nameMonth = "Abril";
    } else if (month == 5) {
      nameMonth = "Mayo";
    } else if (month == 6) {
      nameMonth = "Junio";
    } else if (month == 7) {
      nameMonth = "Julio";
    } else if (month == 8) {
      nameMonth = "Agosto";
    } else if (month == 9) {
      nameMonth = "Septiembre";
    } else if (month == 10) {
      nameMonth = "Octubre";
    } else if (month == 11) {
      nameMonth = "Noviembre";
    } else if (month == 12) {
      nameMonth = "Diciembre";
    }
    return nameMonth;
  }

  public static getDaysFromDates(strDate1: string, strDate2: string) {//format: 'yyyy/MM/dd'
    if(strDate1 == null || strDate2 == null || strDate1 == '' || strDate2 == '') return null;
    var fecha1 = moment(strDate1);
    var fecha2 = moment(strDate2);
    return fecha2.diff(fecha1, 'days');
  }

  public static formatearCantidad(value: number) {
    const cantidad = this.round(value, 2);
    const exp = /(\d)(?=(\d{3})+(?!\d))/g;
    const rep = '$1,';
    let arr = cantidad.toString().split('.');
    arr[0] = arr[0].replace(exp,rep);
    return arr[1] ? arr.join('.'): arr[0];
  }

  public static formatearImporte(value: number) {//FORMAT: YYYY-MM-DDT00:00:000
    if(value == null || value == 0) return '';
    let formatValue = value.toLocaleString("es-PE");
    if(Number.isInteger(value)) {
      formatValue = formatValue + '.00';
    }
    return formatValue;
  }

  public static haySaltoLinea(data: string) {
    if(data == null || data == '' || data.trim() == '') return false;
    const isMatch = data.match(/[\r\n]+/gm);
    return isMatch?.length > 0;
  }

  public static hayDobleSaltoLinea(data: string) {
    if(data == null || data == '' || data.trim() == '') return false;
    const isMatch = data.match(/[\r\n]+/gm);
    return isMatch?.length > 1;
  }
}
