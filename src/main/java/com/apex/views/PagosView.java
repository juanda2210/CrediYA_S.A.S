package com.apex.views;

import com.apex.dao.PagosDAO;
import com.apex.dao.PrestamoDAO;
import com.apex.models.Pago;
import com.apex.models.Prestamo;
import com.apex.util.ScannerUtils;

import java.sql.SQLException;
import java.util.List;


public class PagosView {

    public static final int REGISTRAR_ABONOS = 1;
    public static final int MOSTRAR_SALDO_PENDIENTE = 2;
    public static final int MOSTRAR_HISTORICO_PAGOS = 3;
    public static final int SALIR = 4;

    public static PagosDAO pagosDAO = new PagosDAO();
    public static PrestamoDAO prestamoDAO = new PrestamoDAO();

    public static void menu() {
        while (true) {

            int option = ScannerUtils.capturarNumero("""
            \n===== MENÚ DE PAGOS =====
            1. Registrar abonos
            2. Mostrar saldo pendiente
            3. Mostrar histórico de pagos
            4. Volver al menu principal""");

            switch (option) {

                case REGISTRAR_ABONOS -> {

                    try {
                        double monto = ScannerUtils.capturarDecimal("Monto");
                        pagosDAO.verificarMonto(monto);
                    } catch (IllegalArgumentException | SQLException e) {
                        System.err.println(e.getMessage());
                    }

                }

                case MOSTRAR_SALDO_PENDIENTE -> {
                    // Lógica para mostrar saldo pendiente
                    try {
                        int idPrestamo = ScannerUtils.capturarNumero("Id del prestamo");
                        List<Pago> pagosPorIdPrestamo = pagosDAO.pagosPorIdPrestamo(idPrestamo);

                        double sumaDePagos = pagosPorIdPrestamo.stream()
                                .mapToDouble(pago -> pago.getMonto())
                                .sum();

                        Prestamo prestamo = prestamoDAO.seleccionarPorId(idPrestamo);

                        double saldoPendiente = prestamo.getMonto() - sumaDePagos;

                        System.out.println("El saldo pendiente es " + saldoPendiente);
                    } catch (SQLException e) {
                        e.getMessage();
                    }
                }

                case MOSTRAR_HISTORICO_PAGOS -> {
                    // Lógica para mostrar histórico de pagos

                    try {
                        int idPrestamo = ScannerUtils.capturarNumero("Id del prestamo");
                        List<Pago> pagosPorIdPrestamo = pagosDAO.pagosPorIdPrestamo(idPrestamo);

                        pagosPorIdPrestamo.forEach(pago -> pago.mostrarPago());
                    } catch (RuntimeException e) {
                        throw new RuntimeException(e);
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
