package com.apex.dao;

import com.apex.database.ConexionDB;
import com.apex.models.Prestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public List<Prestamo> listar() throws SQLException {

        String sql = """
                SELECT id, cliente_id, empleado_id, monto, interes, cuotas, fecha_inicio, estado
                FROM clientes
                """;

        List<Prestamo> prestamos = new ArrayList<>();

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Prestamo prestamo = new Prestamo();

                prestamo.setId(rs.getInt("id"));
                prestamo.setCliente_id(rs.getInt("cliente_id"));
                prestamo.setEmpleado_id(rs.getInt("empleado_id"));
                prestamo.setMonto(rs.getDouble("monto"));
                prestamo.setInteres(rs.getDouble("intereses"));
                prestamo.setCuotas(rs.getInt("cuotas"));
                prestamo.setFecha_inicio(rs.getDate("fecha_inicio").toLocalDate());
                prestamo.setEstado(rs.getString("estado"));

                prestamos.add(prestamo);
            }
        }
        return prestamos;
    }
}
