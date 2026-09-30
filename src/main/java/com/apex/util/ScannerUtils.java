package com.apex.util;

import java.util.Scanner;

public class ScannerUtils {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static String capturarTexto(String mensaje) {
        String dato;

        while (true) {
            System.out.println(mensaje + ": ");
            dato = SCANNER.nextLine();

            if (!dato.isBlank()) {
                return dato;
            }

            System.err.println("Error: el dato ingresado no puede estar vacio");
        }
    }

    public static int capturarNumero(String mensaje) {
        System.out.println(mensaje + ": ");

        while (!SCANNER.hasNextInt()) {
            System.out.println("Dato no aceptado " + mensaje + ": ");
            SCANNER.next();
        }

        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return dato;
    }

    public static double capturarDecimal(String mensaje) {
        System.out.println(mensaje + ": ");

        while (!SCANNER.hasNextDouble()) {
            System.out.println("Dato no aceptado " + mensaje + ": ");
            SCANNER.next();
        }

        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }
}
