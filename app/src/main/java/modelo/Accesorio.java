package modelo;

public class Accesorio
{
    private String nombre;
    private int valor;
    //private static int numero;
    private  int id;

    public Accesorio(String auxNombre, int auxValor)
    {
        //numero++;
        //this.id = numero;
        this.nombre = auxNombre;
        this.valor = auxValor;
    }

    public Accesorio()
    {
        //numero++;
        //this.id = numero;

    }

    public int getId()
    {
        return id;
    }
    public void setId(int auxId)
    {
        this.id = auxId;
    }

    /* public static void setNumeroAccesorio(int auxNumero)
    {
        Accesorio.numero = auxNumero;
    }

     */
    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public double getValor()
    {
        return valor;
    }

    public void setValor(int valor)
    {
        this.valor = valor;
    }

    public String toString()
    {
        String datos = id + " - " + nombre + " - " + valor;
        return datos;
    }
}
