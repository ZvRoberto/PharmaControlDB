package dao;

import conexion.Conexion;
import modelo.ProductoFarmaceutico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ProductoFarmaceuticoDAO {

    // CREAR
    public boolean crear(ProductoFarmaceutico producto) {

        String sql = "INSERT INTO inventario.productosFarmaceuticos "
                + "(nombre, descripcion, laboratorio, precio, stock, fecha_vencimiento) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getLaboratorio());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getStock());
            ps.setDate(6, producto.getFechaVencimiento());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al crear producto: " + e.getMessage());
            return false;
        }
    }

    // LEER TODOS
    public List<ProductoFarmaceutico> leerTodo() {

        List<ProductoFarmaceutico> lista = new ArrayList<>();

        String sql = "SELECT * FROM inventario.productosFarmaceuticos "
                + "ORDER BY id_producto";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                ProductoFarmaceutico producto =
                        new ProductoFarmaceutico();

                producto.setIdProducto(
                        rs.getInt("id_producto"));

                producto.setNombre(
                        rs.getString("nombre"));

                producto.setDescripcion(
                        rs.getString("descripcion"));

                producto.setLaboratorio(
                        rs.getString("laboratorio"));

                producto.setPrecio(
                        rs.getDouble("precio"));

                producto.setStock(
                        rs.getInt("stock"));

                producto.setFechaVencimiento(
                        rs.getDate("fecha_vencimiento"));

                lista.add(producto);
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al leer productos: "
                    + e.getMessage());
        }

        return lista;
    }

    // FILTRAR
    public List<ProductoFarmaceutico> filtrar(String filtro) {

        List<ProductoFarmaceutico> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM inventario.productosFarmaceuticos "
                + "WHERE nombre LIKE ? "
                + "OR descripcion LIKE ? "
                + "OR laboratorio LIKE ? "
                + "ORDER BY id_producto";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            String buscar = "%" + filtro + "%";

            ps.setString(1, buscar);
            ps.setString(2, buscar);
            ps.setString(3, buscar);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    ProductoFarmaceutico producto =
                            new ProductoFarmaceutico();

                    producto.setIdProducto(
                            rs.getInt("id_producto"));

                    producto.setNombre(
                            rs.getString("nombre"));

                    producto.setDescripcion(
                            rs.getString("descripcion"));

                    producto.setLaboratorio(
                            rs.getString("laboratorio"));

                    producto.setPrecio(
                            rs.getDouble("precio"));

                    producto.setStock(
                            rs.getInt("stock"));

                    producto.setFechaVencimiento(
                            rs.getDate("fecha_vencimiento"));

                    lista.add(producto);
                }
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al filtrar productos: "
                    + e.getMessage());
        }

        return lista;
    }

    // ACTUALIZAR
    public boolean actualizar(
            ProductoFarmaceutico producto) {

        String sql =
                "UPDATE inventario.productosFarmaceuticos SET "
                + "nombre=?, "
                + "descripcion=?, "
                + "laboratorio=?, "
                + "precio=?, "
                + "stock=?, "
                + "fecha_vencimiento=? "
                + "WHERE id_producto=?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getLaboratorio());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getStock());
            ps.setDate(6, producto.getFechaVencimiento());
            ps.setInt(7, producto.getIdProducto());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(
                    "Error al actualizar producto: "
                    + e.getMessage());

            return false;
        }
    }

    // ELIMINAR
    public boolean eliminar(int idProducto) {

        String sql =
                "DELETE FROM inventario.productosFarmaceuticos "
                + "WHERE id_producto=?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idProducto);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(
                    "Error al eliminar producto: "
                    + e.getMessage());

            return false;
        }
    }
}