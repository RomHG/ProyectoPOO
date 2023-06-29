/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3.model;

/**
 *
 * @author Piero
 */
public class Empleado extends Persona{
    
    private int idEmpleado;

    private String codEmpleado;

    private String pass;

    public Empleado() {
    }

    public Empleado(int idEmpleado, String codEmpleado, String pass) {
        this.idEmpleado = idEmpleado;
        this.codEmpleado = codEmpleado;
        this.pass = pass;
    }

    public Empleado(int idEmpleado, String codEmpleado, String pass, int idPersona, String nombre, String apellido, String tipoDocumento, String numDocumento, String telefono, String email) {
        super(idPersona, nombre, apellido, tipoDocumento, numDocumento, telefono, email);
        this.idEmpleado = idEmpleado;
        this.codEmpleado = codEmpleado;
        this.pass = pass;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(String codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
    
    
}
