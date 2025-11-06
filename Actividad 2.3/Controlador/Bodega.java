package Controlador;

import java.util.ArrayList;

public class Bodega {
    // Clase 
    private class Producto { 
        String codigo; 
        String nombre; 
        int stock; 

        // Constructor para inicializar un producto 
        Producto(String codigo, String nombre, int stock) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.stock = stock;
        }

        // Método para añadir stock al producto
        void añadirStock(int cantidad) {
            this.stock += cantidad; // Incrementa el stock con la cantidad especificada
        }

        void restarStock(int cantidad) {
            if (stock - cantidad >= 0) { // Verifica si hay suficiente stock
                this.stock -= cantidad;    //RESTA
            } else {
                throw new IllegalArgumentException("No hay suficiente stock para vender " + cantidad);
            }
        }

        @Override
        public String toString() {
            return "Codigo : " + codigo + " | Nombre :  " + nombre + " | Stock: " + stock;
        }
    }

    
    private ArrayList<Producto> productos = new ArrayList<>(); 

    public void agregarProducto(String codigo, String nombre, int stock) {
        // Verificar si ya existe un producto con el mismo código
        for (Producto p : productos) {
            if (p.codigo.equals(codigo)) {
                throw new IllegalArgumentException("Ya existe un producto con el código " + codigo);
            }
        }
        productos.add(new Producto(codigo, nombre, stock));
    }

    public void Agregar(String codigo, int cantidad) {
        boolean encontrado = false;
        for (Producto p : productos) {
            if (p.codigo.equals(codigo)) {
                p.añadirStock(cantidad);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new IllegalArgumentException("No se encontró el producto con código " + codigo);
        }
    }

    public void restar(String codigo, int cantidad) {
        boolean encontrado = false;
        for (Producto p : productos) {
            if (p.codigo.equals(codigo)) {
                p.restarStock(cantidad);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            throw new IllegalArgumentException("No se encontró el producto con código " + codigo);
        }
    }

    
    public String listarProductos() {
        StringBuilder lista = new StringBuilder();
        lista.append("|||||| Inventario Almacen ||||||\n");
        for (Producto p : productos) {
            lista.append(p.toString()).append("\n");
        }
        return lista.toString();
    }
}