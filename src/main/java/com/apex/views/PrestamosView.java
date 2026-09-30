package com.apex.views;

import com.apex.util.ScannerUtils;

public class PrestamosView {

    public static final int CREAR_PRESTAMOS = 1;
    public static final int SIMULACION_PRESTAMO = 2;
    public static final int CAMBIAR_ESTADO = 3;
    public static final int SALIR = 4;

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
                }

                case SIMULACION_PRESTAMO -> {
                    // Lógica para simular préstamo
                }

                case CAMBIAR_ESTADO -> {
                    // Lógica para cambiar estado
                }

                case SALIR -> {
                    return;
                }

                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
