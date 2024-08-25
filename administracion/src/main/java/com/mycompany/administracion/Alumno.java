
package com.mycompany.administracion;

/**
 *
 * @author kenny
 */
public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private int cursoId;

    public Alumno(int id, String nombre, String apellido, int cursoId) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cursoId = cursoId;
    }

   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getCursoId() { return cursoId; }
    public void setCursoId(int cursoId) { this.cursoId = cursoId; }
}

