package com.mycompany.administracion;

import java.sql.*;

public class DatabaseManager {
    private Connection connection;

    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/administracion";
        String user = "root";
        String password = "1234";
        connection = DriverManager.getConnection(url, user, password);
    }

    public void desconectar() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void agregarAlumno(Alumno alumno) throws SQLException {
        String query = "INSERT INTO Alumno (nombre, apellido, curso_id) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, alumno.getNombre());
        stmt.setString(2, alumno.getApellido());
        stmt.setInt(3, alumno.getCursoId());
        stmt.executeUpdate();
    }

    public Alumno obtenerAlumno(int id) throws SQLException {
        String query = "SELECT * FROM Alumno WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Alumno(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("curso_id"));
        } else {
            return null;
        }
    }

    public void actualizarAlumno(Alumno alumno) throws SQLException {
        String query = "UPDATE Alumno SET nombre = ?, apellido = ?, curso_id = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, alumno.getNombre());
        stmt.setString(2, alumno.getApellido());
        stmt.setInt(3, alumno.getCursoId());
        stmt.setInt(4, alumno.getId());
        stmt.executeUpdate();
    }

    public void eliminarAlumno(int id) throws SQLException {
        String query = "DELETE FROM Alumno WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    // Métodos para Profesor
    public void agregarProfesor(Profesor profesor) throws SQLException {
        String query = "INSERT INTO Profesor (nombre, apellido) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, profesor.getNombre());
        stmt.setString(2, profesor.getApellido());
        stmt.executeUpdate();
    }

    public Profesor obtenerProfesor(int id) throws SQLException {
        String query = "SELECT * FROM Profesor WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Profesor(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"));
        } else {
            return null;
        }
    }

    public void actualizarProfesor(Profesor profesor) throws SQLException {
        String query = "UPDATE Profesor SET nombre = ?, apellido = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, profesor.getNombre());
        stmt.setString(2, profesor.getApellido());
        stmt.setInt(3, profesor.getId());
        stmt.executeUpdate();
    }

    public void eliminarProfesor(int id) throws SQLException {
        String query = "DELETE FROM Profesor WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    // Métodos para Curso
    public void agregarCurso(Curso curso) throws SQLException {
        String query = "INSERT INTO Curso (descripcion) VALUES (?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, curso.getDescripcion());
        stmt.executeUpdate();
    }

    public Curso obtenerCurso(int id) throws SQLException {
        String query = "SELECT * FROM Curso WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Curso(rs.getInt("id"), rs.getString("descripcion"));
        } else {
            return null;
        }
    }

    public void actualizarCurso(Curso curso) throws SQLException {
        String query = "UPDATE Curso SET descripcion = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, curso.getDescripcion());
        stmt.setInt(2, curso.getId());
        stmt.executeUpdate();
    }

    public void eliminarCurso(int id) throws SQLException {
        String query = "DELETE FROM Curso WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    // Métodos para Nota
    public void agregarNota(Nota nota) throws SQLException {
        String query = "INSERT INTO Nota (alumno_id, curso_id, valor) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, nota.getAlumnoId());
        stmt.setInt(2, nota.getCursoId());
        stmt.setDouble(3, nota.getValor());
        stmt.executeUpdate();
    }

    public Nota obtenerNota(int id) throws SQLException {
        String query = "SELECT * FROM Nota WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Nota(rs.getInt("id"), rs.getInt("alumno_id"), rs.getInt("curso_id"), rs.getDouble("valor"));
        } else {
            return null;
        }
    }

    public void actualizarNota(Nota nota) throws SQLException {
        String query = "UPDATE Nota SET alumno_id = ?, curso_id = ?, valor = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, nota.getAlumnoId());
        stmt.setInt(2, nota.getCursoId());
        stmt.setDouble(3, nota.getValor());
        stmt.setInt(4, nota.getId());
        stmt.executeUpdate();
    }

    public void eliminarNota(int id) throws SQLException {
        String query = "DELETE FROM Nota WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
