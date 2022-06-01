package utiles;

import java.sql.Date;

public class seminario {
private int id;
private String titulo;
private Date fecha;
private int cupos;

    public seminario() {
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    @Override
    public String toString() {
        return "seminario{" + "id=" + id + ", titulo=" + titulo + ", fecha=" + fecha + ", cupos=" + cupos + '}';
    }
    


}
