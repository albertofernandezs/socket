# socket

## INTEGRANTES
- Luis Pérez
- Carlos Ruiz Díaz
- Alberto fFernandez
- Marcos Riveros


## Requerimientos de Instalacion
- Java 8
- JDK 1.8
- Maven

## Base de Datos
- No se utiliza

## Documnetacion API de servivios del servidor

#### public void ejecutar() throws IOException:
   - Se encarga de crear el socket en el puerto 4444
  
#### public void registro(String origen, String destino, Socket socket):
   - Metodo que se encarga de guardar en un archivo txt las llamadas realizadas con los siguintes datos:
      * Origen
      * Destino
      * Fecha/Hora
      * Puerto
      Ej formato de registro: "<nombre_origen> <nombre_destino>  yyyy/mm/dd hh:mm:ss  <nro_puerto>
   - Parametros
      * _String:_ Origen
      * _Sring:_ Destino
      * _Socket:_ socket
      

