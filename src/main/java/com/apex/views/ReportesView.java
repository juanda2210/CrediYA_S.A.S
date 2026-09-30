package com.apex.views;

import com.apex.util.ScannerUtils;

public class ReportesView {

    public static final int CONSULTAR_PRESTAMOS_ACTIVOS = 1;
    public static final int CONSULTAR_PRESTAMOS_VENCIDOS = 2;
    public static final int CONSULTAR_CLIENTES_MOROSOS = 3;
    public static final int SALIR = 4;

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
                }

                case CONSULTAR_PRESTAMOS_VENCIDOS -> {
                    // Lógica para consultar préstamos vencidos
                }

                case CONSULTAR_CLIENTES_MOROSOS -> {
                    // Lógica para consultar clientes morosos
                }

                case SALIR -> {
                    return;
                }

                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
