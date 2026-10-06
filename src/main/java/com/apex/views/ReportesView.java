package com.apex.views;

import com.apex.dao.ClienteDAO;
import com.apex.dao.PrestamoDAO;
import com.apex.models.Cliente;
import com.apex.models.Prestamo;
import com.apex.util.ScannerUtils;

import java.sql.SQLException;
import java.util.List;

public class ReportesView {

    public static final int CONSULTAR_PRESTAMOS_ACTIVOS = 1;
    public static final int CONSULTAR_PRESTAMOS_VENCIDOS = 2;
    public static final int CONSULTAR_CLIENTES_MOROSOS = 3;
    public static final int SALIR = 4;

    public static PrestamoDAO prestamoDAO = new PrestamoDAO();
    public static ClienteDAO clienteDAO = new ClienteDAO();

    public static void menu() {
        while (true) {

            int option = ScannerUtils.capturarNumero("""
            \n===== MENÚ DE REPORTES =====
            1. Consultar préstamos activos
            2. Consultar préstamos vencidos
            3. Consultar clientes morosos
            4. Volver al menu principal""");

            switch (option) {

                case CONSULTAR_PRESTAMOS_ACTIVOS -> {
                    // Lógica para consultar préstamos activos
                    try {
                        List<Prestamo> prestamosActivos = prestamoDAO.prestamosPendientes();
                        prestamosActivos.forEach(Prestamo::mostrarPrestamo);
                    } catch (SQLException e) {
                        System.err.println("Error en base de datos consultando prestamos activos");
                    }
                }

                case CONSULTAR_PRESTAMOS_VENCIDOS -> {
                    // Lógica para consultar préstamos vencidos
                    try {
                        List<Prestamo> prestamosVencidos = prestamoDAO.prestamosVencidos();
                        prestamosVencidos.forEach(Prestamo::mostrarPrestamo);
                    } catch (SQLException e) {
                        System.err.println("Error en base de datos consultando prestamos vencidos");
                    }
                }

                case CONSULTAR_CLIENTES_MOROSOS -> {
                    // Lógica para consultar clientes morosos
                    try {
                        List<Cliente> clientesMorosos = clienteDAO.clientesMorosos();
                        clientesMorosos.forEach(Cliente::mostrarCliente);
                    } catch (SQLException e) {
                        System.err.println("Error en base de datos consultando clientes morosos");
                    }
                }

                case SALIR -> {
                    return;
                }

                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
