# Taller 02 - "La fonda del Papu"

Software hecho en Java para monitorear y editar entradas de alimentos consumidos de distintos usuarios 
---

## Integrantes
* **Nombre:** Agustín Muñoz | **RUT:** 22.387.587-4 | **Usuario GitHub:** Agustin-Munoz-Vargas-UCN (https://github.com/Agustin-Munoz-Vargas-UCN) | **Carrera:** Ingeniería Civil en Computación e Informática (ICCI)

---

## Clases presentes:

1.   **Persona**     *(String nombre)*
2.   **Comida**      *(Persona persona, String fecha)*
2.1. **Choripan**    *(Persona persona, String fecha, float largo, float ancho)*
2.2. **Terremoto**   *(Persona persona, String fecha, float litros)*
3.   **Fecha**       *(int año, int mes, int dia, float litros)*



---

## Estructuras de Datos y Decisiones Técnicas

El proyecto presente utilizo Programación Orientada a Objetos (POO) y arreglos fijos, manejándose de esta forma:

1. **Lista usuarios (Personas[] arregloPersonas):**
   * Maneja los integrantes que se encuentran en el archivo "Participantes.txt", se rellena automáticamente y no se modifica.
   * Tiene un máximo de 200 integrantes.
     
2. **Lista de entradas (Comida[] arregloComida):**
   * Maneja la lista de consumidos que se encuentran en el archivo "Consumido.txt", se puede modificar dentro del programa en conjunto con el archivo de origen, y se dedica a guardar entradas de dos clases hijas, "Coripan" y "Terremoto":
   * Tiene un máximo de 200 entradas.
     
---

## Estructura del Repositorio


```text
.
├── README.md
├── Agustin-Munoz-Vargas-UCN_POO_Taller01_Eclipse.zip
├── Agustin-Munoz-Vargas-UCN_POO_Taller01_Terminal.zip
└── src/
    ├── reportes/
    │   ├── //ReporteC1-VX (Cantidad variable de archivos de reporte C1)
    │   └── //ReporteC2-VX (Cantidad variable de archivos de reporte C2)
    ├── textos/
    │   ├── Alumnos.txt
    │   └── Solicitudes.txt
    ├── taller01/
    │   └── Main.java
    └── module-info.class
```


---

## Requisitos del Entorno

* Se requiere una computadora con Java instalado previamente. 

---

## Instrucciones de Ejecución

> **Importante:** 
* Como el directorio de trabajo varía entre si uno usa la terminal y el programa utilizado para crear los archivos, ***la versión presente en el archivo .zip fue creada con la intención de que funcione en la terminal.***
* Si se decide utilizar Eclipse, se recomienda que se ejecute como aplicación de java, dándole click derecho a la carpeta src/ y dándole a Run As... 

### Opción 1: Terminal.

- Descargue y descomprima (Click derecho/Extraer aquí) el
  "Agustin-Munoz-Vargas-UCN_POO_Taller01_Terminal.zip"

- Abra la carpeta raíz del archivo en la terminal

- Escriba "java Main.java"

- Y con eso el archivo tendría que estar ejecutándose la terminal.

### Opción 2: Eclipse IDE.

- Descargue y descomprima (Click derecho/Extraer aquí) el
  "Agustin-Munoz-Vargas-UCN_POO_Taller01_Eclipse.zip"

- Abra Eclipse y dele a "Importar"

- Seleccione "General/Archive File"

- Busque el archivo descargado "Agustin-Munoz-Vargas-UCN_POO_Taller01_Eclipce.zip" y selecciónelo y continue.

- Ya con el proyecto iniciado, dele a la carpeta raíz, click derecho, "Run As/Java aplication" 

- Y con eso el archivo tendría que estar ejecutándose en Eclipse.



## Manejo de Casos Borde y Validaciones

* **Control de Archivo:** Si no esta disponible, el programa tirara un aviso, pero sera capaz de funcionar mayormente sin la necesidad de los archivos, permitiendo generar guardados hechos puramente por el programa.
* **Fallas a la hora de ingresar inputs:** Si se presenta un argumento de formato incorrecto durante la navegación de los menus, el programa generara un reporte especial y te consultara si quieres hacer un guardado de la "lista de alumnos de la inscritos al curso" modificada antes de cerrar el programa (tanto si se genera un error como si se sale de forma intencionada), y en ciertos casos especiales, te pedirá ingresar un input forzado (como a la hora de poner un paralelo al inscribir a alguien al curso).
* **Alumnos Duplicados:** Se verifica si los alumnos están duplicados tanto al agregar a al grupo como en la lista de alumnos.
* **Limpieza de archivos de guardado:** Los espacios vacíos se manejan como "Srings nullos", pero si se elimina un integrante, este sera guardado con todas las entradas como "ELIMINADO", a la hora de guardar un archivo o agregar un nuevo integrante, los espacios nulos o los que dicen "ELIMINADO" serán rellenados o eliminados.
* **Máximo de integrantes del grupo de WhatsApp:** A la hora de intentar añadir mas de 100 integrantes tirara el aviso de "[!] No quedan cupos en el grupo.".
