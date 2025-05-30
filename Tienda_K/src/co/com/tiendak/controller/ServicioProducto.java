package co.com.tiendak.controller;

import co.com.tiendak.model.Producto;
import co.com.tiendak.model.Stock;
import co.com.tiendak.model.dao.ProductoDAO;
import co.com.tiendak.model.dao.StockDAO;
import co.com.tiendak.model.enums.Categoria;
import co.com.tiendak.vista.Ventana;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ServicioProducto {

    private final ProductoDAO productoDAO = new ProductoDAO();
    private final StockDAO stockDAO = new StockDAO();
    private final Ventana ventana = new Ventana();

    // Mostrar todos los productos
    public void mostrarCatalogo() {
        List<Producto> productos = productoDAO.listarTodos();

        if (productos.isEmpty()) {
            ventana.mostrarMensaje("No hay productos disponibles.");
            return;
        }

        ventana.mostrarMensaje("╔══════════════════════════════════════════════════════════════════════╗");
        ventana.mostrarMensaje("║                         CATÁLOGO DE PRODUCTOS                       ║");
        ventana.mostrarMensaje("╠════╦════════════════════════╦══════════╦════════════╦═══════════════╣");
        ventana.mostrarMensaje("║ ID ║ Nombre                 ║ Categoría║ Precio ($) ║ Stock Total   ║");
        ventana.mostrarMensaje("╠════╬════════════════════════╬══════════╬════════════╬═══════════════╣");

        for (Producto p : productos) {
            int stockTotal = obtenerStockTotal(p.getIdProducto());
            ventana.mostrarMensaje(String.format("║ %-2d ║ %-22s ║ %-9s ║ %-10.2f ║ %-13d ║",
                    p.getIdProducto(), p.getNombre(), p.getCategoria().name(),
                    p.getPrecioUnitario(), stockTotal));
        }

        ventana.mostrarMensaje("╚════╩════════════════════════╩══════════╩════════════╩═══════════════╝");
    }

    // Crear nuevo producto con stock
    public void crearProducto(Scanner sc) {
        ventana.mostrarMensaje("\n--- Registro de Producto ---");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Precio Unitario: ");
        double precio = Double.parseDouble(sc.nextLine());

        System.out.print("Categoría (ALIMENTO, PAPELERIA, TECNOLOGIA, HIGIENE): ");
        Categoria categoria = Categoria.valueOf(sc.nextLine().toUpperCase());

        Producto producto = new Producto(0, categoria, nombre, precio);
        productoDAO.crear(producto);

        // Obtener último ID insertado (puedes mejorar esto si tienes retorno de ID en tu DAO)
        List<Producto> todos = productoDAO.listarTodos();
        int nuevoId = todos.get(todos.size() - 1).getIdProducto();

        System.out.print("Cantidad en stock inicial: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        Stock stock = new Stock(0, new Date(), cantidad, nuevoId);
        stockDAO.crear(stock);

        ventana.mostrarMensaje("Producto registrado exitosamente.");
    }

    // Actualizar producto
    public void actualizarProducto(Scanner sc) {
        System.out.print("ID del producto a actualizar: ");
        int id = Integer.parseInt(sc.nextLine());

        Producto existente = productoDAO.buscarPorId(id);
        if (existente == null) {
            ventana.mostrarMensaje("Producto no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre (" + existente.getNombre() + "): ");
        String nombre = sc.nextLine();
        if (!nombre.isEmpty()) {
            existente.setNombre(nombre);
        }

        System.out.print("Nuevo precio (" + existente.getPrecioUnitario() + "): ");
        String precioStr = sc.nextLine();
        if (!precioStr.isEmpty()) {
            existente.setPrecioUnitario(Double.parseDouble(precioStr));
        }

        System.out.print("Nueva categoría (" + existente.getCategoria() + "): ");
        String categoriaStr = sc.nextLine();
        if (!categoriaStr.isEmpty()) {
            existente.setCategoria(Categoria.valueOf(categoriaStr.toUpperCase()));
        }

        productoDAO.actualizar(existente);
        ventana.mostrarMensaje("Producto actualizado.");
    }

    // Eliminar producto
    public void eliminarProducto(Scanner sc) {
        System.out.print("ID del producto a eliminar: ");
        int id = Integer.parseInt(sc.nextLine());

        Producto p = productoDAO.buscarPorId(id);
        if (p == null) {
            ventana.mostrarMensaje("Producto no encontrado.");
            return;
        }

        productoDAO.eliminar(id);
        ventana.mostrarMensaje("Producto eliminado.");
    }

    // Buscar por categoría
    public void buscarPorCategoria(Scanner sc) {
        System.out.print("Ingrese la categoría (ALIMENTO, PAPELERIA, TECNOLOGIA, HIGIENE): ");
        String cat = sc.nextLine().toUpperCase();

        List<Producto> lista = productoDAO.buscarPorCategoria(cat);
        if (lista.isEmpty()) {
            ventana.mostrarMensaje("No hay productos en esa categoría.");
        } else {
            for (Producto p : lista) {
                ventana.mostrarMensaje(p.toString());
            }
        }
    }

    private int obtenerStockTotal(int idProducto) {
        List<Stock> stockList = stockDAO.buscarPorProducto(idProducto);
        return stockList.stream().mapToInt(Stock::getStock).sum();
    }

    public void agregarStock(Scanner sc) {
        System.out.print("ID del producto para agregar stock: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        Producto producto = productoDAO.buscarPorId(idProducto);
        if (producto == null) {
            ventana.mostrarMensaje("Producto no encontrado.");
            return;
        }

        System.out.print("Cantidad a agregar: ");
        int cantidad = Integer.parseInt(sc.nextLine());

        Stock nuevoStock = new Stock(0, new Date(), cantidad, idProducto);
        stockDAO.crear(nuevoStock);

        ventana.mostrarMensaje("Stock agregado correctamente.");
    }

    public void eliminarStock(Scanner sc) {
        System.out.print("ID del stock a eliminar: ");
        int idStock = Integer.parseInt(sc.nextLine());

        Stock s = stockDAO.buscarPorId(idStock);
        if (s == null) {
            ventana.mostrarMensaje("Registro de stock no encontrado.");
            return;
        }

        stockDAO.eliminar(idStock);
        ventana.mostrarMensaje("🗑️ Registro de stock eliminado.");
    }

    public void verRegistrosDeStock(Scanner sc) {
        System.out.print("ID del producto para ver su historial de stock: ");
        int idProducto = Integer.parseInt(sc.nextLine());

        List<Stock> registros = stockDAO.buscarPorProducto(idProducto);
        if (registros.isEmpty()) {
            ventana.mostrarMensaje("No hay registros de stock para este producto.");
            return;
        }

        ventana.mostrarMensaje(" Historial de stock:");
        ventana.mostrarMensaje("╔════╦══════════════════════╦════════════╗");
        ventana.mostrarMensaje("║ ID ║ Fecha Ingreso        ║ Cantidad   ║");
        ventana.mostrarMensaje("╠════╬══════════════════════╬════════════╣");

        for (Stock s : registros) {
            ventana.mostrarMensaje(String.format("║ %-2d ║ %-20s ║ %-10d ║",
                    s.getIdStock(), s.getFechaIngreso(), s.getStock()));
        }

        ventana.mostrarMensaje("╚════╩══════════════════════╩════════════╝");
    }

    public void buscarProducto(Scanner sc) {
        ventana.mostrarMensaje("\n🔎 Buscar producto en el inventario");
        String palabraClave = ventana.pedirString("Ingrese el nombre o parte del nombre del producto a buscar: ").toLowerCase();

        List<Producto> resultados = new ArrayList<>();
        for (Producto p : productoDAO.listarTodos()) {
            if (p.getNombre().toLowerCase().contains(palabraClave)) {
                resultados.add(p);
            }
        }

        if (resultados.isEmpty()) {
            ventana.mostrarMensaje("No se encontraron productos con esa descripción.");
        } else {
            ventana.mostrarMensaje("Resultados encontrados:");
            for (Producto p : resultados) {
                int stockTotal = stockDAO.buscarPorProducto(p.getIdProducto())
                        .stream()
                        .mapToInt(Stock::getStock)
                        .sum();
                ventana.mostrarMensaje(p.toString() + " | Stock actual: " + stockTotal);
            }
        }
    }
}
