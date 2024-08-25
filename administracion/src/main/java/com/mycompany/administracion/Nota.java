package com.mycompany.administracion;

/**
 *
 * @author kenny
 */
public class Nota {
    private int id;
    private int alumnoId;
    private int cursoId;
    private double valor;

    public Nota(int id, int alumnoId, int cursoId, double valor) {
        this.id = id;
        this.alumnoId = alumnoId;
        this.cursoId = cursoId;
        this.valor = valor;
    }

   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAlumnoId() { return alumnoId; }
    public void setAlumnoId(int alumnoId) { this.alumnoId = alumnoId; }

    public int getCursoId() { return cursoId; }
    public void setCursoId(int cursoId) { this.cursoId = cursoId; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
}

