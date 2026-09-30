package com.apex.views;

import com.apex.util.ScannerUtils;

public class PagosView {

    public static final int REGISTRAR_ABONOS = 1;
    public static final int MOSTRAR_SALDO_PENDIENTE = 2;
    public static final int MOSTRAR_HISTORICO_PAGOS = 3;
    public static final int SALIR = 4;

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
                    // Lógica para registrar abonos
                }

                case MOSTRAR_SALDO_PENDIENTE -> {
                    // Lógica para mostrar saldo pendiente
                }

                case MOSTRAR_HISTORICO_PAGOS -> {
                    // Lógica para mostrar histórico de pagos
                }

                case SALIR -> {
                    return;
                }

                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
