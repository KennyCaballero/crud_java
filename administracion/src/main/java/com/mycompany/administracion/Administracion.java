package com.mycompany.administracion;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author kenny
 */
public class Administracion {

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        try {
            dbManager.conectar();

            String[] opciones = {"Gestionar Alumnos", "Gestionar Profesores", "Gestionar Cursos", "Gestionar Notas"};
            String seleccion = (String) JOptionPane.showInputDialog(null, "Selecciona una entidad para gestionar",
                    "Gestión de Escuela", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case "Gestionar Alumnos" -> gestionarAlumnos(dbManager);

                case "Gestionar Profesores" -> gestionarProfesores(dbManager);

                case "Gestionar Cursos" -> gestionarCursos(dbManager);

                case "Gestionar Notas" -> gestionarNotas(dbManager);

                default -> {
                }
            }

            dbManager.desconectar();
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private static void gestionarAlumnos(DatabaseManager dbManager) throws SQLException {
        String[] opcionesAlumno = {"Agregar Alumno", "Actualizar Alumno", "Eliminar Alumno", "Consultar Alumno"};
        String seleccionAlumno = (String) JOptionPane.showInputDialog(null, "Selecciona una acción",
                "Gestión de Alumnos", JOptionPane.QUESTION_MESSAGE, null, opcionesAlumno, opcionesAlumno[0]);

        switch (seleccionAlumno) {
            case "Agregar Alumno" -> {
                String nombre = JOptionPane.showInputDialog("Introduce el nombre del alumno:");
                String apellido = JOptionPane.showInputDialog("Introduce el apellido del alumno:");
                int cursoId = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID del curso:"));
                Alumno nuevoAlumno = new Alumno(0, nombre, apellido, cursoId);
                dbManager.agregarAlumno(nuevoAlumno);
                JOptionPane.showMessageDialog(null, "Alumno agregado correctamente.");
            }

            case "Actualizar Alumno" -> {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID del alumno:"));
                Alumno alumnoExistente = dbManager.obtenerAlumno(id);
                String nombre;
                int cursoId;
                String apellido;
                if (alumnoExistente != null) {
                    nombre = JOptionPane.showInputDialog("Introduce el nuevo nombre del alumno:", alumnoExistente.getNombre());
                    apellido = JOptionPane.showInputDialog("Introduce el nuevo apellido del alumno:", alumnoExistente.getApellido());
                    cursoId = Integer.parseInt(JOptionPane.showInputDialog("Introduce el nuevo ID del curso:", alumnoExistente.getCursoId()));
                    alumnoExistente.setNombre(nombre);
                    alumnoExistente.setApellido(apellido);
                    alumnoExistente.setCursoId(cursoId);
                    dbManager.actualizarAlumno(alumnoExistente);
                    JOptionPane.showMessageDialog(null, "Alumno actualizado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Alumno no encontrado.");
                }
            }

            case "Eliminar Alumno" -> {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID del alumno:"));
                dbManager.eliminarAlumno(id);
                JOptionPane.showMessageDialog(null, "Alumno eliminado correctamente.");
            }

            case "Consultar Alumno" -> {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID del alumno:"));
                Alumno alumno = dbManager.obtenerAlumno(id);
                if (alumno != null) {
                    JOptionPane.showMessageDialog(null, "ID: " + alumno.getId() + "\nNombre: " + alumno.getNombre() + "\nApellido: " + alumno.getApellido() + "\nCurso ID: " + alumno.getCursoId());
                } else {
                    JOptionPane.showMessageDialog(null, "Alumno no encontrado.");
                }
            }

            default -> {
            }
        }
    }

    private static void gestionarProfesores(DatabaseManager dbManager) throws SQLException {
        String[] opcionesProfesor = {"Agregar Profesor", "Actualizar Profesor", "Eliminar Profesor", "Consultar Profesor"};
        String seleccionProfesor = (String) JOptionPane.showInputDialog(null, "Selecciona una acción",
                "Gestión de Profesores", JOptionPane.QUESTION_MESSAGE, null, opcionesProfesor, opcionesProfesor[0]);

        switch (seleccionProfesor) {
            case "Agregar Profesor" -> {
                String nombre = JOptionPane.showInputDialog("Introduce el nombre del profesor:");
                String apellido = JOptionPane.showInputDialog("Introduce el apellido del profesor:");
                Profesor nuevoProfesor = new Profesor(0, nombre, apellido);
                dbManager.agregarProfesor(nuevoProfesor);
                JOptionPane.showMessageDialog(null, "Profesor agregado correctamente.");
            }

            case "Actualizar Profesor" -> {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID del profesor:"));
                Profesor profesorExistente = dbManager.obtenerProfesor(id);
                String nombre;
                String apellido;
                if (profesorExistente != null) {
                    nombre = JOptionPane.showInputDialog("Introduce el nuevo nombre del profesor:", profesorExistente.getNombre());
                    apellido = JOptionPane.showInputDialog("Introduce el nuevo apellido del profesor:", profesorExistente.getApellido());
                    profesorExistente.setNombre(nombre);
                    profesorExistente.setApellido(apellido);
                    dbManager.actualizarProfesor(profesorExistente);
                    JOptionPane.showMessageDialog(null, "Profesor actualizado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Profesor no encontrado.");
                }
            }

            case "Eliminar Profesor" -> {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID del profesor:"));
                dbManager.eliminarProfesor(id);
                JOptionPane.showMessageDialog(null, "Profesor eliminado correctamente.");
            }

            case "Consultar Profesor" -> {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID del profesor:"));
                Profesor profesor = dbManager.obtenerProfesor(id);
                if (profesor != null) {
                    JOptionPane.showMessageDialog(null, "ID: " + profesor.getId() + "\nNombre: " + profesor.getNombre() + "\nApellido: " + profesor.getApellido());
                } else {
                    JOptionPane.showMessageDialog(null, "Profesor no encontrado.");
                }
            }

            default -> {
            }
        }
    }

    private static void gestionarCursos(DatabaseManager dbManager) throws SQLException {
        String[] opcionesCurso = {"Agregar Curso", "Actualizar Curso", "Eliminar Curso", "Consultar Curso"};
        String seleccionCurso = (String) JOptionPane.showInputDialog(null, "Selecciona una acción",
                "Gestión de Cursos", JOptionPane.QUESTION_MESSAGE, null, opcionesCurso, opcionesCurso[0]);

        switch (seleccionCurso) {
            case "Agregar Curso" -> {
                String descripcion = JOptionPane.showInputDialog("Introduce la descripción del curso:");
                Curso nuevoCurso = new Curso(0, descripcion);
                dbManager.agregarCurso(nuevoCurso);
                JOptionPane.showMessageDialog(null, "Curso agregado correctamente.");
            }

            case "Actualizar Curso" -> {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID del curso:"));
                Curso cursoExistente = dbManager.obtenerCurso(id);
                String descripcion;
                if (cursoExistente != null) {
                    descripcion = JOptionPane.showInputDialog("Introduce la nueva descripción del curso:", cursoExistente.getDescripcion());
                    cursoExistente.setDescripcion(descripcion);
                    dbManager.actualizarCurso(cursoExistente);
                    JOptionPane.showMessageDialog(null, "Curso actualizado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Curso no encontrado.");
                }
            }

            case "Eliminar Curso" -> {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID del curso:"));
                dbManager.eliminarCurso(id);
                JOptionPane.showMessageDialog(null, "Curso eliminado correctamente.");
            }

            case "Consultar Curso" -> {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID del curso:"));
                Curso curso = dbManager.obtenerCurso(id);
                if (curso != null) {
                    JOptionPane.showMessageDialog(null, "ID: " + curso.getId() + "\nDescripción: " + curso.getDescripcion());
                } else {
                    JOptionPane.showMessageDialog(null, "Curso no encontrado.");
                }
            }

            default -> {
            }
        }
    }

    private static void gestionarNotas(DatabaseManager dbManager) throws SQLException {
        String[] opcionesNota = {"Agregar Nota", "Actualizar Nota", "Eliminar Nota", "Consultar Nota"};
        String seleccionNota = (String) JOptionPane.showInputDialog(null, "Selecciona una acción",
                "Gestión de Notas", JOptionPane.QUESTION_MESSAGE, null, opcionesNota, opcionesNota[0]);

        switch (seleccionNota) {
            case "Agregar Nota" -> {
                int alumnoId = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID del alumno:"));
                int cursoId = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID del curso:"));
                double valor = Double.parseDouble(JOptionPane.showInputDialog("Introduce el valor de la nota:"));
                Nota nuevaNota = new Nota(0, alumnoId, cursoId, valor);
                dbManager.agregarNota(nuevaNota);
                JOptionPane.showMessageDialog(null, "Nota agregada correctamente.");
            }

            case "Actualizar Nota" -> {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID de la nota:"));
                Nota notaExistente = dbManager.obtenerNota(id);
                int cursoId;
                int alumnoId;
                double valor;
                if (notaExistente != null) {
                    alumnoId = Integer.parseInt(JOptionPane.showInputDialog("Introduce el nuevo ID del alumno:", notaExistente.getAlumnoId()));
                    cursoId = Integer.parseInt(JOptionPane.showInputDialog("Introduce el nuevo ID del curso:", notaExistente.getCursoId()));
                    valor = Double.parseDouble(JOptionPane.showInputDialog("Introduce el nuevo valor de la nota:", notaExistente.getValor()));
                    notaExistente.setAlumnoId(alumnoId);
                    notaExistente.setCursoId(cursoId);
                    notaExistente.setValor(valor);
                    dbManager.actualizarNota(notaExistente);
                    JOptionPane.showMessageDialog(null, "Nota actualizada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Nota no encontrada.");
                }
            }

            case "Eliminar Nota" -> {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID de la nota:"));
                dbManager.eliminarNota(id);
                JOptionPane.showMessageDialog(null, "Nota eliminada correctamente.");
            }

            case "Consultar Nota" -> {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Introduce el ID de la nota:"));
                Nota nota = dbManager.obtenerNota(id);
                if (nota != null) {
                    JOptionPane.showMessageDialog(null, "ID: " + nota.getId() + "\nAlumno ID: " + nota.getAlumnoId() + "\nCurso ID: " + nota.getCursoId() + "\nValor: " + nota.getValor());
                } else {
                    JOptionPane.showMessageDialog(null, "Nota no encontrada.");
                }
            }

            default -> {
            }
        }
    }
}
