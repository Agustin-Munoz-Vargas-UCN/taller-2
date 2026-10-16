# Taller 02 - "La fonda del Papu"

Software hecho en Java para monitorear y editar entradas de alimentos consumidos de distintos usuarios, monitoreando la cantidad y los KCAL de cada uno, permitiendo editar y añadir entradas, guardando estas en un archivo .txt para conservarse después de apagado el programa.
---

---

## Integrantes
* **Nombre:** Agustín Muñoz | **RUT:** 22.387.587-4 | **Usuario GitHub:** Agustin-Munoz-Vargas-UCN (https://github.com/Agustin-Munoz-Vargas-UCN) | **Carrera:** Ingeniería Civil en Computación e Informática (ICCI)

---

## Estructura del Repositorio


```text
.
├── README.md
├── Agustin-Munoz-Vargas-UCN_POO_Taller02_Eclipse.zip
└── taller02/
    ├──.classpath
    ├──.proyect
    └── src/
        ├── module-info.java
        ├── textos/
        │   ├── Consumido.txt
        │   └── Participantes.txt
        └── taller02/
            ├── Choripan.java
            ├── Comida.java
            ├── Fecha.java
            ├── Main.java **
            ├── Persona.java
            └── Terremoto.java
```

---

## Clases presentes:

1.   **Persona**     *(String nombre)*
2.   **Comida**      *(Persona persona, String fecha)*
3.   **Choripan**    *(Persona persona, String fecha, float largo, float ancho)* (hijo de Comida)
4.   **Terremoto**   *(Persona persona, String fecha, float litros)* (hijo de Comida)
5.   **Fecha**       *(int año, int mes, int dia, float litros)*

## Más detalle de las clases:
1.   **Persona**
     * Cada persona guarda individualmente una serie de datos de cada uno de los intregrantes, siendo: el numero de choripanes (int numeroChoripan), numero de terremotos (int numeroTerremoto), KCAL totales (float numeroKCAL) y KCAL de choripane (float numeroKCALChoripan), litros totales consumidos (float numeroLitros) y un arreglo que contiene cada fecha en la cual el usuario consumió algo (Fecha[] registroDias).

2.   **Comida**
     * Funciona como base para sus clases hija, permitiendo-las guardarse en un mismo arreglo, y guardando la persona que consumió la comida (Persona persona) y la fecha en la que lo comió (String fecha).

3.   **Choripan y Terremoto**
     * Clases hijas de "Comida", "Choripan" guarda el largo (float largo) y ancho de la comida (float ancho), y "Terremoto" guarda unicamente los litros (float litros), cada uno calculando su cantidad de KCAL de forma diferente.

4.   **Fecha**
     * Guarda el año, mes y día (int año, int mes, int dia) en el cual se tomo un "Terremoto", y guarda los litros de "Terremoto" consumidos (float litros), permitiendo acumular lo consumido de distintas entradas que sean de la misma persona y día.
       
---

## Opciones disponibles:

```text
  [1] Ver registros
     [1] Choripanes
     [2] Terremotos
     [3] Volver al menú principal
  [2] Actualizar registros
     [1] Agregar
     [2] Editar
     [3] Eliminar
     [4] Volver al menú principal
  [3] Calcular métricas
     [1] Choripanes comidos en total
     [2] Terremotos tomados en total
     [3] Choripanes comidos por usuario
     [4] Terremotos tomados por usuario
     [5] KCal consumidas por usuario
     [6] Días ebrio por usuario
     [7] Volver al menú principal
  [4] Salir
```

---

## Estructuras de Datos y Decisiones Técnicas

El proyecto presente utilizo Programación Orientada a Objetos (POO) y arreglos fijos, manejándose de esta forma:

1. **Lista usuarios (Personas[] arregloPersonas):**
   * Maneja los integrantes que se encuentran en el archivo "Participantes.txt", se rellena automáticamente y no se modifica.
   * Tiene un máximo de 200 integrantes.
     
2. **Lista de entradas (Comida[] arregloComida):**
   * Maneja la lista de consumidos que se encuentran en el archivo "Consumido.txt", se puede modificar dentro del programa en conjunto con el archivo de origen, y se dedica a guardar entradas de dos clases hijas, "Choripan" y "Terremoto":
   * Tiene un máximo de 200 entradas.
     
---

## Requisitos del Entorno

* Se requiere una computadora con Java instalado previamente.
* Es preferible el uso de alguna versión de "Eclipse IDE" para ejecutarlo

---

## Instrucciones de Ejecución

### Eclipse IDE.

- Descargue el archivo "Agustin-Munoz-Vargas-UCN_POO_Taller02_Eclipse.zip"

- Abra Eclipse y dele a "Importar"

- Seleccione "General/Archive File"

- Busque el archivo descargado "Agustin-Munoz-Vargas-UCN_POO_Taller02_Eclipce.zip" y selecciónelo y continué.

- Ya con el proyecto iniciado, dele a la carpeta raíz, clic derecho, "Run As/Java aplication" 

- Y con eso el archivo tendría que estar ejecutándose en Eclipse.
  
```text
Si después de terminar un ciclo el programa empieza a dar vueltas deforma infinita, el problema surge por el "scan.close();" de la linea 644 de "Main.java", todo esto se comenta de forma preventiva en caso de haber remplazado el archivo .zip con el fallo de forma incorrecta, si no presenta este error, solo ignore esta linea... por favor.
```

## Manejo de Casos Borde y Validaciones

* **Control de Archivo:** Si no esta disponible, el programa tirara un aviso, y no sera capaz de funcionar por su cuenta ya que no van a haber participantes disponibles y el programa no es capaz de funcionar por su cuenta, en caso de que unicamente falte "Consumido.txt", sera capaz de generarlo de forma independiente.
* **Fallas a la hora de ingresar inputs:** Si se presenta un argumento de formato incorrecto durante la navegación de los menús, el programa reiniciara el ciclo, retornandolo al menú principal sin haber guardado los últimos cambios. 
* **Fallas al alcanzar los limites de los arreglos:** La lectura de archivos se detiene en la linea 200 del archivo de texto, y en caso de intentar generar una entrada 201 a través del programa, no sera permitido.
* **Guardado de archivos:** Unicamente se guardan los cambios si "arregloComida" es modificado. Usando el "arregloComida" modificado, el programa sobrescribe este en el .txt, aplicando los cambios de la misma forma en el archivo y en el arreglo sin necesidad de leerlo después de iniciado el programa.
