package com.apex.views;

import com.apex.dao.ClienteDAO;
import com.apex.dao.EmpleadoDAO;
import com.apex.dao.PrestamoDAO;
import com.apex.models.Prestamo;
import com.apex.models.Simulacion;
import com.apex.util.ScannerUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PrestamosView {

    public static final int CREAR_PRESTAMOS = 1;
    public static final int SIMULACION_PRESTAMO = 2;
    public static final int CAMBIAR_ESTADO = 3;
    public static final int SALIR = 4;

    public static PrestamoDAO prestamoDAO = new PrestamoDAO();

    public static void menu() {
        while (true) {

            int option = ScannerUtils.capturarNumero("""
            \n===== MENÚ DE PRÉSTAMOS =====
            1. Crear préstamo
            2. Simulación de préstamo
            3. Cambiar estado
            4. Volver al menu principal""");

            switch (option) {

                case CREAR_PRESTAMOS -> {
                    // Lógica para crear préstamos

                    try {
                        String nombreCliente = ScannerUtils.capturarTexto("Nombre del cliente");
                        ClienteDAO clienteDAO = new ClienteDAO();
                        int idCliente = clienteDAO.consultarIdCliente(nombreCliente);

                        String nombreEmpleado = ScannerUtils.capturarTexto("Nombre del empleado");
                        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
                        int idEmpleado = empleadoDAO.consultarIdEmpleado(nombreEmpleado);

                        double monto = ScannerUtils.capturarDecimal("Monto total");
                        int periodoDeTiempo = ScannerUtils.capturarNumero("Periodo (meses)");
                        Simulacion simulacion = new Simulacion(monto, periodoDeTiempo);

                        Prestamo prestamo = new Prestamo(idCliente, idEmpleado, simulacion.getMonto(), simulacion.getTasaInteresAnual(), simulacion.getPeriodoDeMeses(), LocalDate.now());
                        prestamoDAO.crear(prestamo);

                        System.out.println("Su credito ha sido generado de la siguiente manera: ");
                        simulacion.mostrarSimulacion();
                    } catch (SQLException e) {
                        System.err.println("Su prestamo no se ha podido generar");
                    }
                }

                case SIMULACION_PRESTAMO -> {
                    // Lógica para simular préstamo

                    Prestamo prestamo = new Prestamo();
                    prestamo.calucularSimulacion();
                }

                case CAMBIAR_ESTADO -> {
                    // Lógica para cambiar estado

                    try {
                        int clienteId = ScannerUtils.capturarNumero("Id del cliente");
                        List<Prestamo> prestamosDelCliente = prestamoDAO.prestamosPorCliente(clienteId);
                        prestamosDelCliente.forEach(prestamo -> {
                            try {
                                prestamo.mostrarPrestamo();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        });

                        int prestamoId = ScannerUtils.capturarNumero("Id del prestamo");
                        Prestamo prestamo = prestamoDAO.seleccionarPorId(prestamoId);
                        prestamo.cambiarEstado();

                        System.out.println("Estado del prestamo actualizado satisfactoriamente");
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
