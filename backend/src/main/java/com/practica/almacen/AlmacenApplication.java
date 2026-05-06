package com.practica.almacen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot.
 *
 * @SpringBootApplication combina tres anotaciones:
 * - @Configuration: indica que esta clase contiene configuración
 * - @EnableAutoConfiguration: Spring configura automáticamente los beans necesarios
 * - @ComponentScan: busca componentes (@Controller, @Service, @Repository) en este paquete y sub-paquetes
 */
@SpringBootApplication
public class AlmacenApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlmacenApplication.class, args);
    }
}
