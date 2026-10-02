package com.apex.views;

import com.apex.dao.ClienteDAO;
import com.apex.models.Cliente;
import com.apex.models.Prestamo;
import com.apex.util.ScannerUtils;

import java.sql.SQLException;
import java.util.List;

public class ClientesView {

    public static final int REGISTRAR_CLIENTES = 1;
    public static final int LISTAR_CLIENTES = 2;
    public static final int CONSULTAR_PRESTAMOS = 3;
    public static final int SALIR = 4;

    public static ClienteDAO clienteDAO = new ClienteDAO();

    public static void menu() {
        while (true) {

            int option = ScannerUtils.capturarNumero("""
            \n===== MENÚ DE CLIENTES =====
            1. Registrar clientes
            2. Listar clientes
            3. Consultar préstamos
            4. Volver al menu principal""");

            switch (option) {

                case REGISTRAR_CLIENTES -> {
                    // Lógica para registrar clientes
                    try {
                        System.out.println("\n");
                        String nombre = ScannerUtils.capturarTexto("Nombre");
                        String documento = ScannerUtils.capturarTexto("Documento");
                        String correo = ScannerUtils.capturarTexto("Correo");
                        String telefono = ScannerUtils.capturarTexto("Telefono");
                        Cliente cliente = new Cliente();

                        clienteDAO.crear(cliente);
                    } catch (SQLException e) {
                        System.err.println("Fue imposible crear el cliente");
                    }
                }

                case LISTAR_CLIENTES -> {
                    // Lógica para listar clientes

                    try {
                        List<Cliente> clientes = clienteDAO.listar();
                        clientes.forEach(cliente -> cliente.mostrarCliente());
                    } catch (SQLException e) {
                        System.err.println("Fue imposible listar los clientes");
                    }
                }

                case CONSULTAR_PRESTAMOS -> {
                    // Lógica para consultar préstamos

                    String nombre = ScannerUtils.capturarTexto("Nombre");
                    try {
                        Prestamo prestamo = clienteDAO.buscarPorCliente(nombre);
                        prestamo.mostrarPrestamo();
                    } catch (SQLException e) {
                        e.getMessage();
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
