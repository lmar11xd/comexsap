# COMEXSAP

## Configuración
* Colocar los endpoints del backend que serán usadas de acuerdo al ambiente en:
```
src/environments/environment.ts
src/environments/environment.dev.ts
src/environments/environment.qas.ts
src/environments/environment.prd.ts
```
## Requisitos
* Node: 16.14.2
* Angular: 12.0.2

## Compilación
* Abrir terminal en la carpeta "COMEXSAP"
* Ejecutar según el ambiente a desplegar:
```
npm run build-web-dev
npm run build-web-qas
npm run build-web-prd
```
* Abrir terminal en la subcarpeta "COMEXSAP"
* Ejecutar:
```
cd COMEXSAP
mvn clean package -Pdev -DskipTests
mvn clean package -Pqas -DskipTests
mvn clean package -Pprd -DskipTests
```
* El compilado a desplegar es el archivo: \COMEXSAP\target\COMEXSAP.war.original
* Antes de desplegar se debe renombrar por "COMEXSAP.war"
