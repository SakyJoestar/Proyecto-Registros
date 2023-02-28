package modelo;

//import static modelo.Accesorio.setNumeroAccesorio;
import static modelo.Lugar.setNumeroLugar;
//import static modelo.RegistroInstalaciones.setNumeroRegistro;

import java.io.File;
import java.util.ArrayList;

import dao.AccesorioDao;
import dao.LugarDao;
import dao.RegistroDao;

public class FuncionesGlobales
{
    private final AccesorioDao accesorioDao;
    private final LugarDao lugarDao;
    private final RegistroDao registroDao;
    private final Conexion conexion;

    public FuncionesGlobales()
    {
        this.accesorioDao = new AccesorioDao();
        this.lugarDao = new LugarDao();
        this.registroDao = new RegistroDao();
        this.conexion = new Conexion(new File("java/com/example/registrosv1/ui/archivos"));
    }

    // /--------Accesorios-------/

    public boolean agregarAccesorio(Accesorio auxAccesorio)
    {
        return this.accesorioDao.crearAccesorio(auxAccesorio);
    }

    public boolean eliminarAccesorio(Accesorio auxAccesorio)
    {
        return this.accesorioDao.eliminarAccesorio(auxAccesorio);
    }

    public ArrayList<Accesorio> getAccesorios()
    {
        return this.accesorioDao.getAccesorios();
    }

    // /--------Lugares-------/

    public boolean agregarLugar(Lugar auxLugar)
    {
        return this.lugarDao.crearALugar(auxLugar);
    }

    public boolean eliminarLugar(Lugar auxLugar)
    {
        return this.lugarDao.eliminarLugar(auxLugar);
    }

    public ArrayList<Lugar> getLugares()
    {
        return this.lugarDao.getLugares();
    }

    // /--------Registros-------//

    public boolean agregarRegistro(RegistroInstalaciones auxRegistro)
    {
        return this.registroDao.crearRegistro(auxRegistro);
    }

    public ArrayList<RegistroInstalaciones> getRegistros()
    {
        return this.registroDao.getRegistros();
    }

    public ArrayList<RegistroInstalaciones> getRegistrosPlaca(String auxPlaca)
    {
        return this.registroDao.getRegistrosPlaca(auxPlaca);
    }

    public ArrayList<RegistroInstalaciones> getRegistrosSerie(String auxSerie)
    {
        return this.registroDao.getRegistrosSerie(auxSerie);
    }

    public ArrayList<RegistroInstalaciones> getRegistrosOrden(int auxOrden)
    {
        return this.registroDao.getRegistrosOrden(auxOrden);
    }

    public ArrayList<RegistroInstalaciones> getRegistrosAccesorio(String auxNombreAccesorio)
    {
        return this.registroDao.getRegistrosAccesorio(auxNombreAccesorio);
    }

    // /--------Conexion-------//

    public void escribirLugares()
    {
        ArrayList auxLugares;
        auxLugares = getLugares();
        conexion.setArchivo("lugares.bin");
        conexion.escribirDatosBinario(auxLugares);
    }

    public void escribirAccesorios()
    {
        ArrayList auxAccesorios;
        auxAccesorios = getAccesorios();
        conexion.setArchivo("accesorios.bin");
        conexion.escribirDatosBinario(auxAccesorios);
    }

    public void escribirRegistros()
    {
        ArrayList auxRegistros;
        auxRegistros = getRegistros();
        conexion.setArchivo("registros.bin");
        conexion.escribirDatosBinario(auxRegistros);
    }

    public boolean recuperarDatos()
    {
        boolean datosValidos;
        datosValidos = true;

        ArrayList<Object> auxDatos;
        conexion.setArchivo("accesorios.bin");
        auxDatos = conexion.leerDatosBinario();
        Accesorio auxAccesorio;
        for(Object objeto : auxDatos)
        {
            auxAccesorio = (Accesorio) objeto;
            agregarAccesorio(auxAccesorio);
            //setNumeroAccesorio(auxAccesorio.getId());
        }

        conexion.setArchivo("lugares.bin");
        auxDatos = conexion.leerDatosBinario();
        Lugar auxLugar;
        for(Object objeto : auxDatos)
        {
            auxLugar = (Lugar) objeto;
            agregarLugar(auxLugar);
            setNumeroLugar(auxLugar.getId());
        }

        conexion.setArchivo("registros.bin");
        auxDatos = conexion.leerDatosBinario();
        RegistroInstalaciones auxRegistro;
        for(Object objeto : auxDatos)
        {
            auxRegistro = (RegistroInstalaciones) objeto;
            agregarRegistro(auxRegistro);
            //setNumeroRegistro(auxRegistro.getId());
        }
        return datosValidos;
    }

}
