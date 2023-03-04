package modelo;

public class Lugar
{
    private String nombre;
    private int valorAgregado;
    private  int id;

    public Lugar(String auxNombre, int auxValorAgregado)
    {
        this.nombre = auxNombre;
        this.valorAgregado = auxValorAgregado;
    }

    public Lugar()
    {

    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public int getValorAgregado()
    {
        return valorAgregado;
    }

    public void setValorAgregado(int valorAgregado)
    {
        this.valorAgregado = valorAgregado;
    }

    public int getId()
    {
        return id;
    }
    public void setId(int auxId)
    {
        this.id = auxId;
    }

    public String toString()
    {
        String datos =nombre;
        return datos;
    }
}
