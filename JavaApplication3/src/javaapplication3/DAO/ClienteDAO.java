/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3.DAO;

import java.util.List;
import javaapplication3.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javaapplication3.conexion.Connect;

public class ClienteDAO implements CrudDAO<Cliente, Integer> {
    private Connection connection;

    public ClienteDAO() throws ClassNotFoundException {
        this.connection = new Connect().conectar() ;
    }
    
    public Cliente findByName(Integer id) {
        return null;
    }
    
    @Override
    public Cliente findById(Integer id) {
       Cliente cliente = null;
       String query = "SELECT * FROM personas INNER JOIN clientes ON personas.id_persona = clientes.persona_id WHERE clientes.cod_cliente = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setIdPersona(resultSet.getInt("persona_id"));
                cliente.setCodCliente(resultSet.getString("cod_cliente"));
                cliente.setNombre(resultSet.getString("nombres"));
                cliente.setApellido(resultSet.getString("apellidos"));
                cliente.setNumDocumento(resultSet.getString("DNI"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setEmail(resultSet.getString("email"));
                // Obtener otros campos de la tabla personas si es necesario
                System.out.println("Se realizo la busqueda correctamente del cliente: "+ cliente.getNombre());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cliente;
    }

    @Override
    public List<Cliente> findAll() {
        
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM personas INNER JOIN clientes ON personas.id_persona = clientes.persona_id";
        
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdPersona(resultSet.getInt("persona_id"));
                cliente.setCodCliente(resultSet.getString("cod_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellidos"));
                cliente.setNumDocumento(resultSet.getString("DNI"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setEmail(resultSet.getString("email"));
                // Obtener otros campos de la tabla personas si es necesario
                clientes.add(cliente);
               
            }
             
             System.out.println("Atrayendo todas los clientes: "+ clientes.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return clientes;
    }

    @Override
    public void save(Cliente entity) {
        String personaQuery = "INSERT INTO personas (nombre,apellidos,DNI, telefono,email) VALUES (?,?,?,?,?)";
        String clienteQuery = "INSERT INTO clientes (persona_id,cod_cliente) VALUES ((select id_persona from personas order by id_persona desc limit 1), ?)";
        
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
                
                clienteStatement.setString(1, entity.getCodCliente());
                
                clienteStatement.executeUpdate();
                
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void update(Cliente entity) {
        /*String updateQuery = "UPDATE cliente SET codigo = ? WHERE id = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, entity.getCodCliente());
            statement.setLong(2, entity.getId());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }   */ 
    }

    @Override
    public void delete(Cliente entity) {
      /* String deleteQuery = "DELETE FROM cliente WHERE id = ?";*/
    }
    
}
