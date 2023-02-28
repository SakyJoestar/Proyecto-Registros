package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fecha implements Serializable
{
    private final LocalDate fecha;
    private static final long serialVersionUID = 1L;

    public Fecha(LocalDate auxFecha)
    {
        this.fecha = auxFecha;
        formatearFecha();
    }

    public void formatearFecha()
    {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        fecha.format(formato);
    }

    public LocalDate getFecha()
    {
        return fecha;
    }
}
