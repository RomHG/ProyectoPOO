/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javaapplication3.conexion.Connect;
import javaapplication3.model.Cliente;
import javaapplication3.model.Empleado;

public class EmpleadoDAO implements CrudDAO<Empleado, Integer>{
    private Connection connection;
    private long id;
    
    public EmpleadoDAO() throws ClassNotFoundException {
        this.connection = new Connect().conectar() ;
    }
    public Cliente findByName(Integer id) {
        return null;
    }
    
    public Empleado findByUser(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Empleado findById(Integer id) {
        Empleado empleado = null;
       String query = "SELECT * FROM personas INNER JOIN empleados ON personas.id_persona = empleados.persona_id WHERE empleados.login = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                empleado = new Empleado();
                empleado.setIdPersona(resultSet.getInt("persona_id"));
                empleado.setCodEmpleado(resultSet.getString("login"));
                empleado.setNombre(resultSet.getString("nombres"));
                empleado.setApellido(resultSet.getString("apellidos"));
                empleado.setNumDocumento(resultSet.getString("DNI"));
                empleado.setTelefono(resultSet.getString("telefono"));
                empleado.setEmail(resultSet.getString("email"));
                // Obtener otros campos de la tabla personas si es necesario
                System.out.println("Se realizo la busqueda correctamente del cliente: "+ empleado.getNombre());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return empleado;
    }

    @Override
    public List<Empleado> findAll() {
       List<Empleado> empleados = new ArrayList<>();
        String query = "SELECT * FROM personas INNER JOIN empleados ON personas.id_persona = empleados.persona_id";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            
            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdPersona(resultSet.getInt("persona_id"));
                empleado.setCodEmpleado(resultSet.getString("login"));
                empleado.setNombre(resultSet.getString("nombres"));
                empleado.setApellido(resultSet.getString("apellidos"));
                empleado.setNumDocumento(resultSet.getString("DNI"));
                empleado.setTelefono(resultSet.getString("telefono"));
                empleado.setEmail(resultSet.getString("email"));
                // Obtener otros campos de la tabla personas si es necesario
                empleados.add(empleado);
               
            }
             
             System.out.println("Atrayendo todas los clientes: "+ empleados.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return empleados;
    }

    @Override
    public void save(Empleado entity) {
        String personaQuery = "INSERT INTO personas (nombre,apellidos,DNI, telefono,email) VALUES (?,?,?,?,?)";
        String clienteQuery = "INSERT INTO empleados (persona_id,login) VALUES ((select id_persona from personas order by id_persona desc limit 1), ?)";
        
        try {
            connection.setAutoCommit(false);
            
            // Insertar en la tabla personas
            PreparedStatement insertStatement = connection.prepareStatement(personaQuery, Statement.RETURN_GENERATED_KEYS);
            
            insertStatement.setString(1,entity.getNombre());
            insertStatement.setString(2,entity.getApellido());
            insertStatement.setString(3,entity.getNumDocumento());
            insertStatement.setString(4,entity.getTelefono());
            insertStatement.setString(5,entity.getEmail());
            // Establecer los valores correspondientes en el insertStatement
            insertStatement.executeUpdate();
            
            ResultSet generatedKeys = insertStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                long personaId = generatedKeys.getLong(1);
                
                // Insertar en la tabla cliente
                PreparedStatement clienteStatement = connection.prepareStatement(clienteQuery);
                
                clienteStatement.setString(1, entity.getCodEmpleado());
                
                clienteStatement.executeUpdate();
                
                connection.commit();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
            }
        }

    }

    @Override
    public void update(Empleado entity) {
        String deleteQuery = "DELETE FROM empleados WHERE persona_id = ?";
        String deleteQuery2 = "DELETE FROM personas WHERE id_persona = ?";
        
        try  {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            PreparedStatement statement2 = connection.prepareStatement(deleteQuery2);
            
            statement.setLong(1, id);
            statement2.setLong(1, id);
            
            int n = statement.executeUpdate();
             int n2 = statement2.executeUpdate();
            
            if(n!=0 && n2!=0){
                System.out.println("Exito al eliminar "+id );
            }else{
                System.out.println("Error al eliminar "+id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    
    }

    @Override
    public void delete(Long entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
