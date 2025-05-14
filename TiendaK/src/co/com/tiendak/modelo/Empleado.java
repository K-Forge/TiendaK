package co.com.tiendak.modelo;

public class Empleado extends Usuario {

    private int idEmpleado;
    private TipoEmpleado tipoEmpleado;

    public Empleado(int idUsuario, String nombre, String apellido, Object tipoDocumento, int documento, String telefono, String correo, String direccion, String contraseña, int idEmpleado, TipoEmpleado tipoEmpleado) {
        super(idUsuario, nombre, apellido, tipoDocumento, documento, telefono, correo, direccion, contraseña);
        this.idEmpleado = idEmpleado;
        this.tipoEmpleado = tipoEmpleado;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    @Override
    public String toString() {
        return super.toString() + "Empleado{" + "idEmpleado=" + idEmpleado + ", tipoEmpleado=" + tipoEmpleado + '}';
    }

}
