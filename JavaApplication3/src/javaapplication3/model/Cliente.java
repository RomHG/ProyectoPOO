package javaapplication3.model;

import java.io.IOException;

public class Cliente extends Persona {

    private Integer idClient;

    private String codCliente;

    public Cliente(Integer idClient, String codCliente) {
        this.idClient = idClient;
        this.codCliente = codCliente;
    }

    public Cliente(Integer idClient, String codCliente, int idPersona, String nombre, String apellido, String tipoDocumento, String numDocumento, String telefono, String email) {
        super(idPersona, nombre, apellido, tipoDocumento, numDocumento, telefono, email);
        this.idClient = idClient;
        this.codCliente = codCliente;
    }

    public Cliente() {
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }
}
        