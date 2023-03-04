package modelo;

public class RegistroInstalaciones
{
    private String fecha;
    private String modeloVehiculo;
    private String placa;
    private String serie;
    private String ordenDeTrabajo;
    private String accesorio;
    private int valor;
    private String lugar;
    private boolean estado;

    private  int id;

    //Constructor con Placa
    /*
    public RegistroInstalaciones(String auxModeloVehiculo, String auxPlaca, String auxOrden, String auxAccesorio,  String auxLugar, int auxValor, String auxFecha)
    {
        this.modeloVehiculo = auxModeloVehiculo;
        this.placa = auxPlaca;
        this.ordenDeTrabajo = auxOrden;
        this.accesorio = auxAccesorio;
        this.valor = auxValor;
        this.lugar = auxLugar;
        this.fecha = auxFecha;
        this.estado = false;
        this.serie = "";
    }

     */

    //Constructor con Serie
    /*
    public RegistroInstalaciones(String auxModeloVehiculo, String auxOrden, String auxAccesorio, String auxLugar, String auxSerie, int auxValor, String auxFecha)
    {
        this.modeloVehiculo = auxModeloVehiculo;
        this.serie = auxSerie;
        this.ordenDeTrabajo = auxOrden;
        this.accesorio = auxAccesorio;
        this.valor = auxValor;
        this.lugar = auxLugar;
        this.fecha = auxFecha;
        this.estado = false;
        this.placa = "";
    }

     */

    //Constructor con ambos
    public RegistroInstalaciones(String auxModeloVehiculo, String auxPlaca, String auxSerie, String auxOrden, String auxAccesorio, String auxLugar, int auxValor, String auxFecha)
    {
        this.fecha = auxFecha;
        this.modeloVehiculo = auxModeloVehiculo;
        this.placa = auxPlaca;
        this.serie = auxSerie;
        this.ordenDeTrabajo = auxOrden;
        this.accesorio = auxAccesorio;
        this.valor = auxValor;
        this.lugar = auxLugar;
        this.estado = false;
    }

    public RegistroInstalaciones()
    {

    }

    public int getId()
    {
        return id;
    }
    public void setId(int auxid){this.id = auxid;}
    public String getFecha()
    {
        return fecha;
    }

    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }

    public String getModeloVehiculo()
    {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo)
    {
        this.modeloVehiculo = modeloVehiculo;
    }

    public String getPlaca()
    {
        return placa;
    }

    public void setPlaca(String placa)
    {
        this.placa = placa;
    }

    public String getSerie()
    {
        return serie;
    }

    public void setSerie(String serie)
    {
        this.serie = serie;
    }

    public String getOrdenDeTrabajo()
    {
        return ordenDeTrabajo;
    }

    public void setOrdenDeTrabajo(String ordenDeTrabajo)
    {
        this.ordenDeTrabajo = ordenDeTrabajo;
    }

    public String getAccesorio() {
        return accesorio;
    }

    public void setAccesorio(String accesorio)
    {
        this.accesorio = accesorio;
    }

    public int getValor()
    {
        return valor;
    }

    public void setValor(int valor)
    {
        this.valor = valor;
    }

    public String getLugar()
    {
        return lugar;
    }

    public void setLugar(String lugar)
    {
        this.lugar = lugar;
    }

    public boolean getEstado()
    {
        return estado;
    }

    public void setEstado(boolean estado)
    {
        this.estado = estado;
    }
}
