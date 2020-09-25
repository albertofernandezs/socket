# socket

## INTEGRANTES
- Luis Pérez
- Carlos Ruiz Díaz
- Alberto fFernandez
- Marcos Riveros


## Requerimientos de Instalación
- Java 8
- JDK 1.8
- Maven

## Base de Datos
- No se utiliza

## Documnetación API de servicios del servidor

#### public void ejecutar() throws IOException:
   - Se encarga de crear el socket en el puerto 4444
  
#### public void registro(String origen, String destino, Socket socket):
   - _Metodo que se encarga de guardar en un archivo txt las llamadas realizadas con los siguientes datos:_
      * Origen
      * Destino
      * Fecha/Hora
      * Puerto
      
      Ej formato de registro: "<nombre_origen> <nombre_destino>  yyyy/mm/dd hh:mm:ss  <nro_puerto>
      
   - Parámetros:
      * _String:_ Origen
      * _Sring:_ Destino
      * _Socket:_ socket
      
## Instruciones de Uso:

### Servidor
- Al abrir el proyecto _"servidor"_ en el IDE elegido , selecciona el archivo en la dirección _"socket\servidor\src\main\java\py\una\server\tcp\TCPMultiserver.java"_, le da click derecho y lo ejectuta como _Java Apolication_. Si la ejecución es exitosa, muestra el mensaje "Puerto abierto 4444"

### Cliente 
- Al abrir el proyecto _"cliente"_ en el IDE elegido , selecciona el archivo en la dirección _"socket\cliente\src\main\java\py\una\server\tcp\TCPCliente.java"_, le da click derecho y lo ejectuta como _Java Apolication_. Si la ejecución es exitosa, muestra un mensaje con las opciones posibles. A fin de Emular correctamente el envio de mensajes entre dos clientes, ´puede repetir el proceso y asi tener dos clientes en funcionamiento que se comunican a traves del servidor.

