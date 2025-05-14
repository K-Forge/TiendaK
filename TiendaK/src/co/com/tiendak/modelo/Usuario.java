package co.com.tiendak.modelo;

public class Usuario {

    protected int idUsuario;
    protected String nombre;
    protected String apellido;
    protected TipoDocumento tipoDocumento;
    protected int documento; //TODO: Cambia a string
    protected String telefono;
    protected String correo;
    protected String direccion;
    protected String contraseña;

    public Usuario(int idUsuario, String nombre, String apellido, TipoDocumento tipoDocumento, int documento, String telefono, String correo, String direccion, String contraseña) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.contraseña = contraseña;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", tipoDocumento=" + tipoDocumento + ", documento=" + documento + ", telefono=" + telefono + ", correo=" + correo + ", direccion=" + direccion + ", contrase\u00f1a=" + contraseña + '}';
    }

}
