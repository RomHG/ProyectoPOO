public class Habitacion {

    private int IDhabitacion;

    private String numero;

    private String piso;

    private String descripcion;

    private Double precioXdia;

    private String estado;

    private String tipo_habitacion;

    public Habitacion(int IDhabitacion, String numero, String piso,
            String descripcion, Double precioXdia, String estado, String tipo_habitacion) {
        this.IDhabitacion = IDhabitacion;
        this.numero = numero;
        this.piso = piso;
        this.descripcion = descripcion;
        this.precioXdia = precioXdia;
        this.estado = estado;
        this.tipo_habitacion = tipo_habitacion;
    }

    public int getIDhabitacion() {
        return IDhabitacion;
    }

    public String getNumero() {
        return numero;
    }

    public String getPiso() {
        return piso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Double getPrecioXdia() {
        return precioXdia;
    }

    public String getEstado() {
        return estado;
    }

    public String getTipo_habitacion() {
        return tipo_habitacion;
    }
    
    
}
