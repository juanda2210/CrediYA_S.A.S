package com.apex.views;

import com.apex.util.ScannerUtils;

public class EmpleadosView {

    public static final int REGISTRAR_EMPLEADOS = 1;
    public static final int CONSULTAR_EMPLEADOS = 2;
    public static final int SALIR = 3;

    public static void menu() {
        while (true) {

            int option = ScannerUtils.capturarNumero("""
            \n===== MENÚ DE EMPLEADOS =====
            1. Registrar empleados
            2. Consultar empleados
            3. Volver al menu principal""");

            switch (option) {

                case REGISTRAR_EMPLEADOS -> {
                    // Lógica para registrar empleados
                }

                case CONSULTAR_EMPLEADOS -> {
                    // Lógica para consultar empleados
                }

                case SALIR -> {
                    return;
                }

                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
