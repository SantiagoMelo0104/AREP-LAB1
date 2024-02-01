# LABORATORIO 1 AREP - APLICACIONES DISTRIBUIDAS 

Una aplicación web que permite consultar la información de las películas mediante el nombre del filme. La app web levanta un servidor de manera local y utiliza como fuente de información a la API externa [OMDb](https://www.omdbapi.com/) para extraer la informacíon de la película buscada por el usuario. Es eficiente, pues implementa un caché que evita realizar consultas repetidas al API externa, permitiendo asi una busqueda más rápida.

# Iniciando 
A continuación se indican una serie de instruciones para bajar y ejecutar el proyecto de manera exitosa:

Es **importante**❗tener instalado: 
- [MAVEN](https://maven.apache.org) : Manejo de las dependecias. 
- [GIT](https://git-scm.com) : Control de versiones.
- [JAVA](https://www.java.com/es/) : Lenguaje de programación (JDK 20). 

# Instalación ⬇️
Los siguiente comando le permitira clonar el repositorio de manera local:
~~~
git clone https://github.com/SantiagoMelo0104/AREP-LAB1.git
~~~

# Ejecución🪄

Para este ejemplo usaremos el IDE de Intelij:
Una vez clonado, abrimos el proyecto en en IDE y ubicamos la siguiente clase **HttpServer**.
![image](https://github.com/SantiagoMelo0104/AREP-LAB1/assets/123812833/edd1bec1-7f6a-4e44-8667-9f70cd2aacec)

Para ejecutar el proyecto podemos hacerlo presionando cualquiera de los recuadros a continuación
![image](https://github.com/SantiagoMelo0104/AREP-LAB1/assets/123812833/3c18a32b-6e3e-43f6-8479-f43524a67e5e)

A continuación dirijase al navegador de su preferencia y vaya a la siguiente dirección [http://localhost:35000/Pelicula](http://localhost:35000/Pelicula), aquí encontrara la aplicación lista para su funcionamiento.
![image](https://github.com/SantiagoMelo0104/AREP-LAB1/assets/123812833/d80e8096-e95f-42c0-bff0-94378fb140f2)

Luego de hacer una respectiva busqueda como por ejemplo "Avatar" obtendremos algo del siguiente estilo:
![image](https://github.com/SantiagoMelo0104/AREP-LAB1/assets/123812833/10a47646-a4b8-4a46-b01a-d0be886ce523)

# Descripción 📄
La aplicacion web actua como un servidor fachada que consulta al API de [OMDB](https://www.omdbapi.com). esta"fachada" implementa un caché para evitar consultas repetidas, almacenando las respuestas como cadenas y comparándolas. Si la búsqueda no está en el caché, el servidor fachada la realiza en el API de [OMDB](https://www.omdbapi.com).

La aplicación cuenta con dos clases:
- HttpServer
- HttpConnection
# Pruebas
Para correr la pruebas nos dirigimos al IDE y localizamos una carpeta llamada **test**
![image](https://github.com/SantiagoMelo0104/AREP-LAB1/assets/123812833/623aec5a-dcfe-47e2-ac61-8002812d55c8)
del mismo modo que ejecutamos el servidor lo haremos con la clase de pruebas:
![image](https://github.com/SantiagoMelo0104/AREP-LAB1/assets/123812833/a5374740-ca62-45fb-8267-d183745670c8)
Resultado✅
![image](https://github.com/SantiagoMelo0104/AREP-LAB1/assets/123812833/fea159b0-3cc5-4c5a-927a-1099d7ad90bf)

# Extensibilidad

Podemos extender la conexión es decir podemos extender la clase HttpConnection para asi dejarla como una interfaz lo cula permitiria poder tener otros servicios externos que complementen la aplicación web.



# Autor 
Santiago Naranjo Melo [SantiagoMelo0104](https://github.com/SantiagoMelo0104)
