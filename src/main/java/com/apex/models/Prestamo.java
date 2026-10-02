package com.apex.models;

import java.sql.SQLException;
import java.time.LocalDate;

public class Prestamo {
    private int id;
    private int cliente_id;
    private int empleado_id;
    private double monto;
    private double interes;
    private int cuotas;
    private LocalDate fecha_inicio;
    private String estado;

    public Prestamo() {
    }

    public Prestamo(int id, int empleado_id, String estado, int cliente_id, double interes, double monto, int cuotas, LocalDate fecha_inicio) {
        this.id = id;
        this.empleado_id = empleado_id;
        this.estado = estado;
        this.cliente_id = cliente_id;
        this.interes = interes;
        this.monto = monto;
        this.cuotas = cuotas;
        this.fecha_inicio = fecha_inicio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(int empleado_id) {
        this.empleado_id = empleado_id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void mostrarPrestamo() throws SQLException {
        Cliente clienteDelPrestamo = Cliente.consultar(this.cliente_id);

        if (clienteDelPrestamo != null) {
            System.out.println("\nPrestamo fecha " + fecha_inicio.getDayOfMonth() +
                    "/" + fecha_inicio.getMonth() +
                    "/" + fecha_inicio.getYear());
            System.out.println("----------------------------------------------");
            System.out.println("Cliente: " + clienteDelPrestamo.getNombre());
            System.out.println("El restoooo");
        }
    }
}
