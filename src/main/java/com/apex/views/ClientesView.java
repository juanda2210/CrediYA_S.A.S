package com.apex.views;

import com.apex.util.ScannerUtils;

public class ClientesView {

    public static final int REGISTRAR_CLIENTES = 1;
    public static final int LISTAR_CLIENTES = 2;
    public static final int CONSULTAR_PRESTAMOS = 3;
    public static final int SALIR = 4;

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
                }

                case LISTAR_CLIENTES -> {
                    // Lógica para listar clientes
                }

                case CONSULTAR_PRESTAMOS -> {
                    // Lógica para consultar préstamos
                }

                case SALIR -> {
                    return;
                }

                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
