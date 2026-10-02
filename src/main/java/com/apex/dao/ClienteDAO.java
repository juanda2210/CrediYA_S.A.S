package com.apex.dao;

import com.apex.database.ConexionDB;
import com.apex.models.Cliente;
import com.apex.models.Prestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void crear(Cliente cliente) throws SQLException {

        String sql = """
                INSERT INTO clientes (nombre, documento, correo, telefono)
                VALUES (?, ?. ?, ?)
                """;

        try (Connection conn = ConexionDB.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDocumento());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getTelefono());

            ps.executeUpdate();
        }
    }


    public List<Cliente> listar() throws SQLException {

        String sql = """
                SELECT id, nombre, documento, correo, telefono
                FROM clientes
                """;

        List<Cliente> clientes = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDocumento(rs.getString("documento"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));

                clientes.add(cliente);
            }
        }
        return clientes;
    }


    private Cliente consultarClientePorNombre(String nombre) throws SQLException {
        List<Cliente> clientes = this.listar();

        return clientes.stream()
                .filter(cliente -> cliente.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    private int consultarIdCliente (String nombre) throws SQLException {
        Cliente clienteEncontrado = this.consultarClientePorNombre(nombre);

        if (clienteEncontrado != null) {
            return clienteEncontrado.getId();
        }

        throw new SQLException("El cliente que está tratando de buscar no se encuentra en la base de datos");
    }


    // Esta es una buena solucion, e incluso bastante logica pero no es lo más escalable profesionalmente hablando
    //Traer muchos datos puede causar un problema en nuestro codigo
    /*public void consultarPrestamo (String nombre) throws SQLException {

        int id = this.consultarIdCliente(nombre);

        PrestamoDAO prestamoDAO = new PrestamoDAO();
        List<Prestamo> prestamos = prestamoDAO.listar();

        Prestamo prestamoSeleccionado = prestamos.stream()
                .filter(prestamo -> prestamo.getCliente_id() == id)
                .findFirst()
                .orElse(null);

        if (prestamoSeleccionado != null) {
            prestamoSeleccionado.mostrarPrestamo();
        } else {
            throw new SQLException("No fue encontrado algun prestamo a nombre del cliente seleccionado");
        }
    }*/

    public Prestamo buscarPorCliente(String nombre) throws SQLException {

        int clienteId = this.consultarIdCliente(nombre);

        String sql = """
            SELECT id, cliente_id, empleado_id, monto, interes,
                   cuotas, fecha_inicio, estado
            FROM prestamos
            WHERE cliente_id = ?
            """;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, clienteId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Prestamo prestamo = new Prestamo();

                    prestamo.setId(rs.getInt("id"));
                    prestamo.setCliente_id(rs.getInt("cliente_id"));
                    prestamo.setEmpleado_id(rs.getInt("empleado_id"));
                    prestamo.setMonto(rs.getDouble("monto"));
                    prestamo.setInteres(rs.getDouble("interes"));
                    prestamo.setCuotas(rs.getInt("cuotas"));
                    prestamo.setFecha_inicio(rs.getDate("fecha_inicio").toLocalDate());
                    prestamo.setEstado(rs.getString("estado"));

                    return prestamo;
                }
            }
        }

        return null;
    }
}
