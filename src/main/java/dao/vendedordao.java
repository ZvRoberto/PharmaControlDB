/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author robertomedrano
 */

import conexion.Conexion;
import modelo.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class vendedordao{

    // CREAR
    public boolean crear(Vendedor vendedor) {

        String sql = "INSERT INTO ventas.vendedores "
                + "(nombre, apellido, telefono, correo, direccion) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, vendedor.getNombre());
            ps.setString(2, vendedor.getApellido());
            ps.setString(3, vendedor.getTelefono());
            ps.setString(4, vendedor.getCorreo());
            ps.setString(5, vendedor.getDireccion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al crear vendedor: " + e.getMessage());
            return false;
        }
    }

    // LEER TODO
    public List<Vendedor> leerTodo() {

        List<Vendedor> lista = new ArrayList<>();

        String sql = "SELECT * FROM ventas.vendedores "
                   + "ORDER BY id_vendedor";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Vendedor v = new Vendedor();

                v.setIdVendedor(rs.getInt("id_vendedor"));
                v.setNombre(rs.getString("nombre"));
                v.setApellido(rs.getString("apellido"));
                v.setTelefono(rs.getString("telefono"));
                v.setCorreo(rs.getString("correo"));
                v.setDireccion(rs.getString("direccion"));;

                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error al leer vendedores: " + e.getMessage());
        }

        return lista;
    }

    // LEER POR FILTRO
    public List<Vendedor> filtrar(String filtro) {

    List<Vendedor> lista = new ArrayList<>();

    String sql = "SELECT * FROM ventas.vendedores "
            + "WHERE nombre LIKE ? "
            + "OR apellido LIKE ? "
            + "OR telefono LIKE ? "
            + "OR correo LIKE ? "
            + "OR direccion LIKE ? "
            + "ORDER BY id_vendedor";

    try (Connection cn = Conexion.conectar();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        String buscar = "%" + filtro + "%";

        ps.setString(1, buscar);
        ps.setString(2, buscar);
        ps.setString(3, buscar);
        ps.setString(4, buscar);
        ps.setString(5, buscar);

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Vendedor v = new Vendedor();

                v.setIdVendedor(rs.getInt("id_vendedor"));
                v.setNombre(rs.getString("nombre"));
                v.setApellido(rs.getString("apellido"));
                v.setTelefono(rs.getString("telefono"));
                v.setCorreo(rs.getString("correo"));
                v.setDireccion(rs.getString("direccion"));

                lista.add(v);
            }
        }

    } catch (SQLException e) {
        System.out.println("Error al filtrar: " + e.getMessage());
    }

    return lista;
}
    // ACTUALIZAR
    public boolean actualizar(Vendedor vendedor) {

    String sql = "UPDATE ventas.vendedores SET "
            + "nombre=?, apellido=?, telefono=?, "
            + "correo=?, direccion=? "
            + "WHERE id_vendedor=?";

    try (Connection cn = Conexion.conectar();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setString(1, vendedor.getNombre());
        ps.setString(2, vendedor.getApellido());
        ps.setString(3, vendedor.getTelefono());
        ps.setString(4, vendedor.getCorreo());
        ps.setString(5, vendedor.getDireccion());
        ps.setInt(6, vendedor.getIdVendedor());

        return ps.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al actualizar: " + e.getMessage());
        return false;
    }
}

    // ELIMINAR
    public boolean eliminar(int idVendedor) {

        String sql = "DELETE FROM ventas.vendedores "
                   + "WHERE id_vendedor=?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idVendedor);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
}
