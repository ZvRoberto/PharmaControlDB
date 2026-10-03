/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author robertomedrano
 */

import dao.vendedordao;
import modelo.Vendedor;
import java.util.List;

public class VendedorController {

    private final vendedordao vendedordao;

    public VendedorController() {
        vendedordao = new vendedordao();
    }

    // CREAR
    public boolean crearVendedor(
            String nombre,
            String apellido,
            String telefono,
            String correo,
            String direccion) {

        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        if (apellido == null || apellido.trim().isEmpty()) {
            return false;
        }


        Vendedor vendedor = new Vendedor();

        vendedor.setNombre(nombre.trim());
        vendedor.setApellido(apellido.trim());
        vendedor.setTelefono(telefono.trim());
        vendedor.setCorreo(correo.trim());
        vendedor.setDireccion(direccion.trim());

        return vendedordao.crear(vendedor);
    }

    // LEER TODO
    public List<Vendedor> leerTodos() {
        return vendedordao.leerTodo();
    }

    // LEER POR FILTRO
    public List<Vendedor> buscar(String filtro) {

        if (filtro == null) {
            filtro = "";
        }

        return vendedordao.filtrar(filtro.trim());
    }

    // ACTUALIZAR
    public boolean actualizarVendedor(
            int idVendedor,
            String nombre,
            String apellido,
            String telefono,
            String correo,
            String direccion) {

        if (idVendedor <= 0) {
            return false;
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        if (apellido == null || apellido.trim().isEmpty()) {
            return false;
        }

        

        Vendedor vendedor = new Vendedor();

        vendedor.setIdVendedor(idVendedor);
        vendedor.setNombre(nombre.trim());
        vendedor.setApellido(apellido.trim());
        vendedor.setTelefono(telefono.trim());
        vendedor.setCorreo(correo.trim());
        vendedor.setDireccion(direccion.trim());

        return vendedordao.actualizar(vendedor);
    }

    // ELIMINAR
    public boolean eliminarVendedor(int idVendedor) {

        if (idVendedor <= 0) {
            return false;
        }

        return vendedordao.eliminar(idVendedor);
    }
}
