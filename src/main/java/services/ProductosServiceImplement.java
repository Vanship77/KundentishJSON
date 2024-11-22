package services;

import modelos.Productos;

import java.util.Arrays;
import java.util.List;

public class ProductosServiceImplement implements ProductosService {

    @Override
    public List<Productos> listar() {
        return Arrays.asList(
                new Productos(1L, "iPhone 13", "Apple", "A2633", 999.99, "Smartphone"),
                new Productos(2L, "Galaxy S21", "Samsung", "SM-G991B", 799.99, "Smartphone"),
                new Productos(3L, "XPS 13", "Dell", "9310", 1249.99, "Laptop"),
                new Productos(4L, "MacBook Pro", "Apple", "M1", 1999.99, "Laptop"),
                new Productos(5L, "Surface Pro 7", "Microsoft", "VGY-00001", 749.99, "Tablet"),
                new Productos(6L, "iPad Air", "Apple", "A2316", 599.99, "Tablet"),
                new Productos(7L, "PlayStation 5", "Sony", "CFI-1015A", 499.99, "Consola"),
                new Productos(8L, "Xbox Series X", "Microsoft", "RRT-00001", 499.99, "Consola"),
                new Productos(9L, "Echo Dot", "Amazon", "B07FZ8S74R", 49.99, "Smart Speaker"),
                new Productos(10L, "Kindle Paperwhite", "Amazon", "B08KTZ4T7H", 139.99, "E-Reader")
        );
    }
}