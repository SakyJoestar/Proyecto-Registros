package modelo;

public class RegistroInstalaciones
{
    private Fecha fecha;
    private String modeloVehiculo;
    private String placa;
    private String serie;
    private int ordenDeTrabajo;
    private Accesorio accesorio;
    private double valor;
    private Lugar lugar;

    private  int id;

    public RegistroInstalaciones(Fecha auxFecha, String auxModeloVehiculo, String auxPlaca, int auxOrden, Accesorio auxAccesorio, double auxValor, Lugar auxLugar)
    {
        this.fecha = auxFecha;
        this.modeloVehiculo = auxModeloVehiculo;
        this.placa = auxPlaca;
        this.ordenDeTrabajo = auxOrden;
        this.accesorio = auxAccesorio;
        this.valor = auxValor;
        this.lugar = auxLugar;
    }

    public RegistroInstalaciones(Fecha auxFecha, String auxModeloVehiculo,  int auxOrden, Accesorio auxAccesorio, double auxValor, Lugar auxLugar, String auxSerie)
    {

        this.fecha = auxFecha;
        this.modeloVehiculo = auxModeloVehiculo;
        this.serie = auxSerie;
        this.ordenDeTrabajo = auxOrden;
        this.accesorio = auxAccesorio;
        this.valor = auxValor;
        this.lugar = auxLugar;
    }


    public RegistroInstalaciones(Fecha auxFecha, String auxModeloVehiculo, String auxPlaca, String auxSerie, int auxOrden, Accesorio auxAccesorio, double auxValor, Lugar auxLugar)
    {
        this.fecha = auxFecha;
        this.modeloVehiculo = auxModeloVehiculo;
        this.placa = auxPlaca;
        this.serie = auxSerie;
        this.ordenDeTrabajo = auxOrden;
        this.accesorio = auxAccesorio;
        this.valor = auxValor;
        this.lugar = auxLugar;
    }

    public int getId()
    {
        return id;
    }
    public void setId(int auxid){this.id = auxid;}
    public Fecha getFecha()
    {
        return fecha;
    }

    public void setFecha(Fecha fecha)
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

    public int getOrdenDeTrabajo()
    {
        return ordenDeTrabajo;
    }

    public void setOrdenDeTrabajo(int ordenDeTrabajo)
    {
        this.ordenDeTrabajo = ordenDeTrabajo;
    }

    public Accesorio getAccesorio() {
        return accesorio;
    }

    public void setAccesorio(Accesorio accesorio)
    {
        this.accesorio = accesorio;
    }

    public double getValor()
    {
        return valor;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

    public Lugar getLugar()
    {
        return lugar;
    }

    public void setLugar(Lugar lugar)
    {
        this.lugar = lugar;
    }
}
