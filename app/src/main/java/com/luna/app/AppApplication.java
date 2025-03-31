package com.luna.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.luna.app.entidades.Producto;
import com.luna.app.entidades.Ventas;
import com.luna.app.repositorio.ProductoRepositorio;
import com.luna.app.repositorio.VentaRepositorio;


@SpringBootApplication
@ComponentScan(basePackages = "com.luna.app")

public class AppApplication implements CommandLineRunner{

	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	@Autowired
	private VentaRepositorio	ventaRepositorio; 

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<Producto>producto=productoRepositorio.findAll();
		producto.forEach(System.out::println);

		List<Ventas>ventas=ventaRepositorio.findAll();
		ventas.forEach(System.out::println);
	}

}
