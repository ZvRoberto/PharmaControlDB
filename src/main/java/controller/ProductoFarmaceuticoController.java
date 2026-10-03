package controller;

import dao.ProductoFarmaceuticoDAO;
import modelo.ProductoFarmaceutico;

import java.sql.Date;
import java.util.List;

public class ProductoFarmaceuticoController {

    private final ProductoFarmaceuticoDAO productoDAO;

    public ProductoFarmaceuticoController() {
        productoDAO = new ProductoFarmaceuticoDAO();
    }

    // CREAR
    public boolean crearProducto(
            String nombre,
            String descripcion,
            String laboratorio,
            double precio,
            int stock,
            Date fechaVencimiento) {

        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        if (laboratorio == null || laboratorio.trim().isEmpty()) {
            return false;
        }

        if (precio < 0 || stock < 0) {
            return false;
        }

        if (fechaVencimiento == null) {
            return false;
        }

        ProductoFarmaceutico producto =
                new ProductoFarmaceutico();

        producto.setNombre(nombre.trim());
        producto.setDescripcion(descripcion.trim());
        producto.setLaboratorio(laboratorio.trim());
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setFechaVencimiento(fechaVencimiento);

        return productoDAO.crear(producto);
    }

    // LEER TODOS
    public List<ProductoFarmaceutico> leerTodos() {
        return productoDAO.leerTodo();
    }

    // FILTRAR
    public List<ProductoFarmaceutico> buscar(String filtro) {

        if (filtro == null) {
            filtro = "";
        }

        return productoDAO.filtrar(filtro.trim());
    }

    // ACTUALIZAR
    public boolean actualizarProducto(
            int idProducto,
            String nombre,
            String descripcion,
            String laboratorio,
            double precio,
            int stock,
            Date fechaVencimiento) {

        if (idProducto <= 0) {
            return false;
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        if (laboratorio == null || laboratorio.trim().isEmpty()) {
            return false;
        }

        if (precio < 0 || stock < 0) {
            return false;
        }

        if (fechaVencimiento == null) {
            return false;
        }

        ProductoFarmaceutico producto =
                new ProductoFarmaceutico();

        producto.setIdProducto(idProducto);
        producto.setNombre(nombre.trim());
        producto.setDescripcion(descripcion.trim());
        producto.setLaboratorio(laboratorio.trim());
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setFechaVencimiento(fechaVencimiento);

        return productoDAO.actualizar(producto);
    }

    // ELIMINAR
    public boolean eliminarProducto(int idProducto) {

        if (idProducto <= 0) {
            return false;
        }

        return productoDAO.eliminar(idProducto);
    }
}