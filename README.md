# lab03-CVDS

## CREAR PROYECTO CON MAVEN
Deben crear un proyecto maven con los siguientes parámetros:
Grupo: edu.eci.cvds 
Artefacto: Library 
Paquete: edu.eci.cvds.tdd 
archetypeArtifactId: maven-archetype-quickstart
creamos el proyecto con el siguiente comando:
mvn archetype:generate -DgroupId=edu.eci.cvds -DartifactId=Library -Dpackage=edu.eci.cvds.tdd -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

![imagenes](images/1.png)

Después de creado nuestro proyecto maven tenemos el siguiente arbol

![imagenes](images/2.png)

## AGREGAR DEPENDENCIA JUNIT5
Para esta parte se agregan las dependencias de JUNIT con los paquetes en las versiones adecuadas

![imagenes](images/3.png)

## AGREGAR LAS CLASES Y PAQUETES REQUERIDOS 

![imagenes](images/4.png)

## PRUEBAS UNITARIA Y TDD
para esta parte de las pruebas unitarias es necesario crear nuestro repositorio donde sobre este se van a estar desarrollando 2 ramas en las cuales trabajaremos y verificaremos nuestros test mediante pull request 

![imagenes](images/5.png)

## CREAR LA CLASE DE PRUEBA 

