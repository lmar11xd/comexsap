// This file can be replaced during build by using the `fileReplacements` array.
// `ng build ---prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiUrlComex: 'http://localhost:9095/comexsap-exportaciones-backend/',
  //apiUrlComex: 'https://jas1q.aasa.com.pe/comexsap-exportaciones-backend-QAS/',
  apiUrlSeguridad: 'https://jas1q.aasa.com.pe/SEGURSAP2-soa-QAS/',
  appcode: '205',
  clientSecret: '123456'
};

/*
 * In development mode, to ignore zone related error stack frames such as
 * `zone.run`, `zoneDelegate.invokeTask` for easier debugging, you can
 * import the following file, but please comment it out in production mode
 * because it will have performance impact when throw error
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
