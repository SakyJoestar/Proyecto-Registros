package modelo;

public class Lugar
{
    private String nombre;
    private int valorAgregado;
    private static int numero;
    private final int id;

    public Lugar(String auxNombre, int auxValorAgregado)
    {
        numero++;
        this.id = numero;
        this.nombre = auxNombre;
        this.valorAgregado = auxValorAgregado;
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

    public static void setNumeroLugar(int auxNumero)
    {
        Lugar.numero = auxNumero;
    }

    public String toString()
    {
        String datos = nombre + " - " + valorAgregado;
        return datos;
    }
}
