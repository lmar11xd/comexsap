function unidades(num) {
  switch (num) {
    case 1:
      return 'Un';
    case 2:
      return 'Dos';
    case 3:
      return 'Tres';
    case 4:
      return 'Cuatro';
    case 5:
      return 'Cinco';
    case 6:
      return 'Seis';
    case 7:
      return 'Siete';
    case 8:
      return 'Ocho';
    case 9:
      return 'Nueve';
    default:
      return '';
  }
}

function decenasY(strSin, numUnidades) {
  if (numUnidades > 0) {
    return strSin + ' y ' + unidades(numUnidades);
  }

  return strSin;
}

function decenas(num) {
  var numDecena = Math.floor(num / 10);
  var numUnidad = num - numDecena * 10;

  switch (numDecena) {
    case 1:
      switch (numUnidad) {
        case 0:
          return 'Diez';
        case 1:
          return 'Once';
        case 2:
          return 'Doce';
        case 3:
          return 'Trece';
        case 4:
          return 'Catorce';
        case 5:
          return 'Quince';
        default:
          return 'Dieci' + unidades(numUnidad).toLowerCase();
      }
    case 2:
      switch (numUnidad) {
        case 0:
          return 'Veinte';
        default:
          return 'Veinti' + unidades(numUnidad).toLowerCase();
      }
    case 3:
      return decenasY('Treinta', numUnidad);
    case 4:
      return decenasY('Cuarenta', numUnidad);
    case 5:
      return decenasY('Cincuenta', numUnidad);
    case 6:
      return decenasY('Sesenta', numUnidad);
    case 7:
      return decenasY('Setenta', numUnidad);
    case 8:
      return decenasY('Ochenta', numUnidad);
    case 9:
      return decenasY('Noventa', numUnidad);
    case 0:
      return unidades(numUnidad);
    default:
      return '';
  }
}

function centenas(num) {
  var numCentenas = Math.floor(num / 100);
  var numDecenas = num - numCentenas * 100;

  switch (numCentenas) {
    case 1:
      if (numDecenas > 0) {
        return 'Ciento ' + decenas(numDecenas);
      }
      return 'Cien';
    case 2:
      return 'Doscientos ' + decenas(numDecenas);
    case 3:
      return 'Trescientos ' + decenas(numDecenas);
    case 4:
      return 'Cuatrocientos ' + decenas(numDecenas);
    case 5:
      return 'Quinientos ' + decenas(numDecenas);
    case 6:
      return 'Seiscientos ' + decenas(numDecenas);
    case 7:
      return 'Setecientos ' + decenas(numDecenas);
    case 8:
      return 'Ochocientos ' + decenas(numDecenas);
    case 9:
      return 'Novecientos ' + decenas(numDecenas);
    default:
      return decenas(numDecenas);
  }
}

function seccion(num, divisor, strSingular, strPlural) {
  var numCientos = Math.floor(num / divisor);
  var numResto = num - numCientos * divisor;

  var letras = '';

  if (numCientos > 0) {
    if (numCientos > 1) {
      letras = centenas(numCientos) + ' ' + strPlural;
    } else {
      letras = strSingular;
    }
  }

  if (numResto > 0) {
    letras += '';
  }

  return letras;
}

function miles(num) {
  var divisor = 1000;
  var numCientos = Math.floor(num / divisor);
  var numResto = num - numCientos * divisor;
  var strMiles = seccion(num, divisor, 'Un Mil', 'Mil');
  var strCentenas = centenas(numResto);

  if (strMiles === '') {
    return strCentenas;
  }

  return (strMiles + ' ' + strCentenas).trim();
}

function millones(num) {
  var divisor = 1000000;
  var numCientos = Math.floor(num / divisor);
  var numResto = num - numCientos * divisor;
  var strMillones = seccion(num, divisor, 'Un Millón', 'Millones');
  var strMiles = miles(numResto);

  if (strMillones === '') {
    return strMiles;
  }

  return (strMillones + ' ' + strMiles).trim();
}

export function NumerosALetras(num) {
  const opts = {
    letrasMonedaPlural: 'Dólares Americanos',
    letrasMonedaSingular: 'Dólar Americano',
    letrasMonedaCentavoPlural: '/100 Centavos',
    letrasMonedaCentavoSingular: '/100 Centavos'
  }

  var data = {
    numero: num,
    enteros: Math.floor(num),
    centavos: Math.round(num * 100) - Math.floor(num) * 100,
    letrasCentavos: '',
    letrasMonedaPlural: 'Pesos',
    letrasMonedaSingular: 'Peso',
    letrasMonedaCentavoPlural: '/100 M.N.',
    letrasMonedaCentavoSingular: '/100 M.N.'
  };

  if(opts != null) {
    data.letrasMonedaPlural = opts.letrasMonedaPlural;
    data.letrasMonedaSingular = opts.letrasMonedaSingular;
    data.letrasMonedaCentavoPlural = opts.letrasMonedaCentavoPlural;
    data.letrasMonedaCentavoSingular = opts.letrasMonedaCentavoSingular
  }

  if (data.centavos >= 0) {
    data.letrasCentavos = function () {
      if (data.centavos >= 1 && data.centavos <= 9) {
        return '0' + data.centavos + data.letrasMonedaCentavoSingular;
      }

      if (data.centavos === 0) {
        return '00' + data.letrasMonedaCentavoSingular;
      }

      return data.centavos + data.letrasMonedaCentavoPlural;
    }();
  }

  if (data.enteros === 0) {
    return ('Cero ' + data.letrasMonedaPlural + ' ' + data.letrasCentavos).trim();
  }

  if (data.enteros === 1) {
    return (millones(data.enteros) + ' ' + data.letrasMonedaSingular + ' ' + data.letrasCentavos).trim();
  }

  return (millones(data.enteros) + ' ' + data.letrasMonedaPlural + ' ' + data.letrasCentavos).trim();
}

