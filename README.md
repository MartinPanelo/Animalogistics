# Universidad de la Punta

## Trabajo final Laboratorio 3

### Sistema de gestión de refugios

**Alumno:** Panelo Rodrigo Martin
**Fecha:** 07/11/2023
**Docentes:** Mariano Luzza, Luis Mercado.

## Tabla de contenido

1. [Introducción](#introducción)
2. [Justificación](#justificación)
3. [Objetivo general del proyecto](#objetivo-general-del-proyecto)
4. [Objetivos específicos del proyecto](#objetivos-específicos-del-proyecto)
5. [Objetivo general del sistema](#objetivo-general-del-sistema)
6. [Límite](#límite)
7. [Alcance](#alcance)
8. [No Contemplado](#no-contemplado)
9. [Tecnologías](#tecnologías)
10. [Competencia](#competencia)
11. [Listado de Requerimientos funcionales](#listado-de-requerimientos-funcionales)
12. [Listado de Requerimientos no funcionales](#listado-de-requerimientos-no-funcionales)
13. [Desarrollo del Prototipo](#desarrollo-del-prototipo)
14. [Análisis y Diseño](#análisis-y-diseño)
15. [Diagrama de Casos de Uso más relevantes](#diagrama-de-casos-de-uso-más-relevantes)
16. [Diagrama de Base de datos](#diagrama-de-base-de-datos)
17. [Interfaz Gráfica](#interfaz-gráfica)
18. [Bibliografía](#bibliografía)

## Introducción

Este proyecto se enfoca en el desarrollo de una aplicación destinada a abordar la problemática de la gestión de refugios de animales, la ubicación de animales perdidos y la promoción de la adopción de animales sin dueño. La aplicación se diseñará con el propósito de ofrecer una solución tecnológica para la comunidad que se preocupa por el bienestar de los animales.

### ANTECEDENTES

La gestión de refugios de animales, la ubicación de animales perdidos y la promoción de la adopción de animales sin dueño han sido desafíos significativos en muchas comunidades. A lo largo del tiempo, se han realizado avances notables en la forma en que la sociedad aborda estas cuestiones:

- **Refugios Tradicionales:** La gestión de refugios de animales se basa en métodos tradicionales y en la operación de instalaciones físicas. A menudo, estos refugios tienden a tener dificultades para gestionar eficazmente los recursos y coordinar la atención de los animales.
- **Organizaciones de Rescate:** Con el tiempo, surgen organizaciones de rescate de animales que se dedican a brindar refugio y cuidado a animales abandonados o maltratados. Estas organizaciones juegan un papel crucial en la atención de animales necesitados, pero la coordinación entre ellas y la comunidad en general es limitada.
- **Uso de Tecnología:** En la última década, se ha observado un notable aumento en el desarrollo de aplicaciones y plataformas web que se dedican a promover la adopción de mascotas. No obstante, a pesar de estos avances, persisten desafíos en cuanto a la eficaz gestión de refugios de animales.

### DESCRIPCIÓN DEL AREA PROBLEMÁTICA

La problemática de los perros callejeros en Argentina afecta a todo el país, tanto en entornos urbanos como rurales, y se ha convertido en una realidad cotidiana que impacta la vida de las personas. Factores involucrados incluyen la falta de esterilización de mascotas, la falta de regulación en la venta de animales y el abandono de mascotas, lo que ha llevado al crecimiento descontrolado de la población canina en situación de calle. En las últimas décadas, ha surgido una creciente conciencia pública sobre el bienestar animal y una mayor demanda de soluciones efectivas para abordar esta problemática.

### FORMULACIÓN DE LA PROBLEMÁTICA

La pregunta central que este proyecto busca abordar es: ¿Cómo podemos proporcionar una plataforma efectiva que facilite la gestión de refugios de animales y la ubicación de animales perdidos, unificando los esfuerzos de la comunidad para mejorar el bienestar de los animales en situación de vulnerabilidad?

## Justificación

Este proyecto se justifica en base a varias razones que resaltan su importancia y relevancia:

1. **Necesidades Comunitarias:** La gestión de refugios de animales y la ubicación de animales perdidos son necesidades comunes en muchas comunidades. La falta de una plataforma eficiente para abordar estas necesidades ha generado desafíos para quienes desean ayudar a los animales en situación de vulnerabilidad. Este proyecto busca satisfacer estas necesidades al proporcionar una solución tecnológica que simplifica la colaboración y la gestión de recursos.
2. **Impacto en el Bienestar Animal:** La aplicación tiene un impacto directo en el bienestar de los animales. Facilita la rápida ubicación de animales perdidos, lo que aumenta sus posibilidades de ser reunidos con sus dueños. Además, agiliza la gestión de refugios, lo que contribuye a mejorar las condiciones y el cuidado de los animales alojados en estos lugares.
3. **Relevancia para la Sociedad:** La sociedad actual muestra una creciente sensibilidad hacia el cuidado y la protección de los animales. Esta aplicación se alinea con esta tendencia, permitiendo a los miembros de la comunidad participar activamente en la ayuda a los animales necesitados. Esto promueve valores de empatía y responsabilidad hacia los seres vivos, lo que contribuye a una sociedad más consciente y compasiva.

## Objetivo general del proyecto

Desarrollar un sistema de información que aborde la problemática de los animales callejeros en Argentina, facilitando la gestión de refugios, la ubicación de animales perdidos y promoviendo la adopción responsable, con el fin de reducir la población de animales en situación de calle y mejorar su bienestar.

## Objetivos específicos del proyecto

- Diseñar un sistema de gestión de refugios de animales que permita llevar un registro eficiente de los animales alojados, su estado de salud y sus necesidades específicas.
- Desarrollar una funcionalidad de ubicación de animales perdidos que facilite la rápida reunión de mascotas con sus dueños mediante geolocalización.
- Crear una plataforma de promoción de la adopción responsable de animales sin dueño, conectando a potenciales adoptantes con refugios y organizaciones de rescate.
- Establecer una interfaz intuitiva y amigable para los usuarios finales, incluyendo dueños de mascotas, refugios y personas interesadas en la adopción.

## Objetivo general del sistema

Optimizar la gestión de refugios de animales y promover la adopción de mascotas, contribuyendo a reducir la población de perros callejeros en Argentina y mejorar su bienestar.

## Límite

Desde que un animal es reportado hasta que se le encuentra un hogar.

## Alcance

Los procesos de negocio incluidos en el alcance del sistema abarcan:

- Registrar un nuevo animal en el sistema.
- Buscar y ubicar animales perdidos.
- Gestionar la disponibilidad de animales para adopción.
- Realizar solicitudes de adopción.
- Registro y gestión de voluntarios.
- Gestión de refugios de animales.

## Tecnologías

- **Base de Datos:** MySQL se empleará como sistema de gestión de bases de datos para almacenar la información relacionada con los animales, refugios, voluntarios y usuarios del sistema.
- **Servidor/API:** Se desarrollará una API utilizando ASP.NET para la gestión de datos y lógica del sistema. ASP.NET es una plataforma sólida y escalable que facilitará la creación de endpoints para la comunicación con la aplicación cliente.
- **Cliente:** La aplicación móvil para Android se desarrollará utilizando Java, que es un lenguaje de programación ampliamente utilizado para aplicaciones móviles. Android Studio será la herramienta principal para el desarrollo de la interfaz de usuario y la funcionalidad de la aplicación.

Estas tecnologías se seleccionaron en función de su idoneidad para los objetivos del proyecto y su compatibilidad entre sí.

## Competencia

Algunas de las aplicaciones con características similares:

| Característica | Petfinder | DogHero | Wag! |
| --- | --- | --- | --- |
| Descripción | Aplicación móvil y web que conecta a personas que buscan adoptar mascotas con refugios y organizaciones de rescate. | Aplicación móvil que conecta a personas que buscan cuidadores para sus mascotas con dueños que necesitan ayuda. También permite encontrar mascotas en adopción. | Aplicación móvil que conecta a personas que buscan cuidadores para sus mascotas con dueños que necesitan ayuda. También permite encontrar mascotas en adopción. |
| Plataformas | Móvil (Android, iOS) y web | Móvil (Android, iOS) | Móvil (Android, iOS) |
| Costo | Gratis | Gratis | Gratis |
| Países disponibles | Estados Unidos, Canadá, Reino Unido, Australia, Irlanda, Nueva Zelanda | Argentina, Brasil, Chile, Colombia, México, Perú, Uruguay | Estados Unidos, Canadá, Reino Unido, Australia, Irlanda, Nueva Zelanda, Alemania, Italia, Francia, España |
| Tipos de mascotas | Perros, gatos, caballos, conejos, otros animales pequeños | Perros, gatos, otros animales domésticos | Perros, gatos, otros animales domésticos |
| Información de las mascotas | Fotos, videos, descripción | Fotos, videos, descripción, historia | Fotos, videos, descripción, historia, necesidades especiales |
| Proceso de adopción | En línea o en persona | En línea o en persona | En línea o en persona |

## Listado de Requerimientos funcionales

- Registrar un nuevo animal en el sistema.
- Actualizar la información de un animal (estado de salud, fotos, descripción, etc.).
- Eliminar un animal del sistema cuando se complete la adopción.
- Asociar un animal a un refugio o una organización de rescate.
- Establecer la disponibilidad de adopción para un animal.
- Permitir a los usuarios reportar animales perdidos.
- Geolocalizar animales perdidos en un mapa.
- Ofrecer una lista de animales disponibles para adopción.
- Permitir a los usuarios solicitar la adopción de un animal.
- Facilitar la comunicación entre adoptantes y refugios/organizaciones de rescate.
- Permitir a los usuarios registrarse como voluntarios.
- Permitir la inscripción de refugios y organizaciones de rescate en el sistema.
- Gestionar la información de contacto y ubicación de los refugios.
- Asociar animales a refugios u organizaciones.
- Generar informes sobre la actividad del sistema, como adopciones, animales perdidos, voluntariado.


## Listado de Requerimientos no funcionales

- **Usabilidad:**
  - Interfaz de usuario amigable: La interfaz debe ser intuitiva y fácil de usar para personas con diversos niveles de habilidades técnicas.
- **Confiabilidad:**
  - Disponibilidad: El sistema debe estar disponible las 24 horas, los 7 días de la semana, ya que la gestión de refugios es continua.
- **Seguridad de datos:**
  - Garantizar la protección de la información sensible, como datos de salud de animales y datos personales de adoptantes.

## Desarrollo del Prototipo

### Análisis y Diseño

[Plantee el análisis que sea necesario de acuerdo al prototipo propuesto y a la lista de requerimientos. En caso de ser software deberá utilizar UML].

### Diagrama de Casos de Uso más relevantes

| Nro. UC | Nombre del Caso de Uso | Prioridad | Complejidad |
| --- | --- | --- | --- |
| UC-01 | Registrar Usuario | Alta | Media |
| UC-02	 | Editar Perfil Usuario | Media | Baja |
| UC-03	 | Ver Perfil | Media | Baja |
| UC-04	| Listar Noticias | Baja | Media |
| UC-05	| Editar Noticia | media | Media |
| UC-06	| Crear Noticia | Media | Media |
| UC-07	| Eliminar Noticia | Baja | Baja |
| UC-08	| Registrar Animal | Alta | Alta |
| UC-09	| Listar Animales Registrados | Alta | Media |
| UC-10	| Listar Animales Asociados a un Refugio | Alta | Media |
| UC-11	| Editar Registro de Animal | Alta | Alta |
| UC-12	| Asociar Animal a Refugio | Alta | Media |
| UC-13	| Eliminar Registro de Animal | Alta | Media |
| UC-14	| Geolocalizar Animales | Alta | Media |
| UC-15	| Registrar Refugio | Alta | Media |
| UC-16	| Editar Refugio | Media | Media |
| UC-17	| Crear Tarea | Media | Media |
| UC-18	| Editar Tarea | Media | Media |
| UC-19	| Listar Tareas | Media | Baja |
| UC-20	| Asociarse a Tarea | Media | Baja |
| UC-21	| Desasociarse de Tarea | Media | Baja |


### Diagrama de la base de datos

![image](https://github.com/user-attachments/assets/fe78aff8-ca57-4d60-b4b9-0473621c2095)



## Interfaz Gráfica

### Pantalla de Login
**Descripción : ** Formulario para que usuarios registrados puedan ingresar a la aplicacion.
**Elementos visuales : ** Campos de correo y contraseña. ademas del boton de cambiar idioma.

### Pantalla de Registro
**Descripción : ** Formulario para que nuevos usuarios se registren.
**Elementos visuales : ** Campos de correo, contraseña, nombre, apellido, telefono, DNI, imagen de perfil, boton de registro.

### Pantalla Principal
**Descripción : ** Interfaz para explorar refugios, ver noticias y registrar animales perdidos y menus para navegar en la aplicacion.
**Elementos visuales : ** Menu inferior con las opciones de: noticias, registrar animales perdidos, explorar refugios.
Menu lateral con las opciones : editar perfil, agregar refugio, lista de refugios de los que soy dueño, lista de refugios en los que soy voluntario, boton de mis registros de animales, boton de cerrar sesion y boton de opciones de idioma.

### Pantalla de listar todas las noticias
**Descripción : ** Se listan todas las noticias publicadas en la plataforma.
**Elementos visuales : ** Lista de noticias con título, fecha de publicación y breve descripción.

### Pantalla de Leer Noticia
**Descripción : ** Visualización detallada de una noticia específica.
**Elementos visuales : ** Título, contenido completo de la noticia, imágen.

### Pantalla de Registro de Animal
**Descripción:** Formulario para registrar un nuevo animal en el sistema.
**Elementos visuales:** Campos para detalles del animal : nombre, tipo de animal, edad, tamaño, collar, genero, detalles, posicion GPS, foto.

### Pantalla de vista de refugios en mapa
**Descripción:** Mapa interactivo con marcadores de refugios detallando informacion y ramgo de accion.
**Elementos visuales:** Mapa con marcadores.

### Pantalla de Perfil del Refugio
**Descripción:** Información detallada sobre el refugio, sus actividades y animales bajo su cuidado.
**Elementos visuales:** Detalles del refugio como telefono, direccion, y una descripción, boton para llamar,
carousel con las siguientes opciones : noticas, voluntariados y adopciones.

### Pantalla de Detalles del Animal
**Descripción:** Información detallada de un animal específico de un refugio.
**Elementos visuales:** nombre, tipo de animal, edad, tamaño, collar, genero, detalles y foto.

### Pantalla de Voluntariado
**Descripción:** Lista de voluntariados disponibles en un refugio.
**Elementos visuales:** selector de tareas y su respectiva descripción y un boton para inscribirse.

### Pantalla de Perfil de Usuario
**Descripción:** En esta interfaz, los usuarios pueden ver y editar su información personal.
**Elementos visuales:** Campos de correo, contraseña, nombre, apellido, telefono, DNI, imagen de perfil, boton de actualizar datos.

### Pantalla de Registro de Refugio
**Descripción:** Formulario para que los refugios se registren en la plataforma.
**Elementos visuales:** Campos para nombre del refugio, direccion, telefono, descripción, foto, posicion GPS, rango de accion del refugio.

### Pantalla de Gestión de animales por usuario
**Descripción:** Se listan los animales registrados por el usuario que no están asociados a un refugio, donde se puede actualizar su información.
**Elementos visuales:** Lista de animales del usuario, botón para editar, botón para borrar.

### Pantalla de Gestión de Refugio
**Descripción:** Herramienta para que el refugio administre los animales registrados, actualice su información, agregue tareas, y gestione las noticas del mismo.
**Elementos visuales:** Lista de animales del refugio, opciones de edición y disponibilidad.

### Pantalla de Gestión de Animales del Refugio
**Descripción:** Herramienta para que el refugio administre los animales registrados, actualice su información
**Elementos visuales:** Lista de animales del refugio, opciones de edición y boton de eliminar.

### Pantalla de Gestión de noticias del Refugio
**Descripción:** Herramienta para que el refugio administre las noticas publicadas, actualice su información
**Elementos visuales:** Lista de noticas del refugio, opciones de edición y boton de eliminar.

### Pantalla de Gestión de taras del Refugio
**Descripción:** Herramienta para que el refugio administre las tareas, actualice su información o borre.
**Elementos visuales:** Lista de tareas del refugio, opciones de edición y boton de eliminar.

### Pantalla para Agregar Animal
**Descripción:** se muestran los animales registrados por usuarios y que no esten asociados a un refugio.
**Elementos visuales:** se muestren marcadores de los animales registrados y un marcados para el refugio.

### Pantalla para Agregar notica
**Descripción:** se muestran un formulario para que se agrege una noticia.
**Elementos visuales:** se muestren un formulario para agregar una noticia, que consta de una foto, categoria, titulo y contenido.

### Pantalla para Agregar una tarea
**Descripción:** se muestran un formulario para que se agrege una tarea.
**Elementos visuales:** se muestren un formulario para agregar una tarea, que consta de una activadad y detalles de la misma.


## Repositorios

- Enlace al repositorio con la App Móvil : https://github.com/MartinPanelo/Animalogistics
- Enlace al repositorio con la Web API : https://github.com/MartinPanelo/API_Animalogistics

## Bibliografía

- Muñoz Testón, Tomás. (Madrid, Julio 2019). "Trabajo fin de master: Plataforma web para la adopción y gestión de animales procedentes de protectoras". [Enlace](https://oa.upm.es/55859/1/TESIS_MASTER_TOMAS_MUNOZ_TESTON.pdf)
- Refugio Animal Argentina. "Perros Callejeros en Argentina: Una Problemática Nacional.". [Enlace](https://refugioanimal-argentina.com/perros-callejeros-en-argentina/)
- Mori, Luciano. (2019). "Solución web para encontrar, adoptar y devolver mascotas perdidas". [Enlace](https://repositorio.21.edu.ar/bitstream/handle/ues21/18252/Luciano%20Mori.pdf?sequence=1&isAllowed=y)
- ASTESANA, D.M.; KABARADJIAN, S.; BLANCHE, G.P.; ROMERO SCHARPEN, A.; SOTO, L.P.; BLAJMAN, E.; DALLASANTINA, R.; MARTI, E. (2012). "Percepción de la problemática de perros vagabundos por parte de la población de Esperanza". En XIII Jornadas de Divulgación Técnico Científicas 2012. [Enlace](https://www.conicet.gov.ar/new_scp/detalle.php?keywords=&id=38460&congresos=yes&detalles=yes&congr_id=5752487)
