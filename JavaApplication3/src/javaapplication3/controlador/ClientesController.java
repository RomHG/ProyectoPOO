/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javaapplication3.DAO.ClienteDAO;
import javaapplication3.DAO.CrudDAO;
import javaapplication3.model.Cliente;
import javaapplication3.vista.ClientsView;
import javax.swing.table.DefaultTableModel;


public class ClientesController implements ActionListener{
    private CrudDAO clienteDao;
    private ClientsView vista;

    public ClientesController() throws ClassNotFoundException {
        this.clienteDao = new ClienteDAO();
        this.vista = new ClientsView();
    }
    
    public void iniciar(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nombre"); 
        model.addColumn("Apellido"); 
        model.addColumn("DNI");
        model.addColumn("Telefono");
        model.addColumn("Email");
        
        
        List<Cliente> clientes = clienteDao.findAll();
        for ( Cliente cliente : clientes) {
            model.addRow(new Object[]{cliente.getNombre(),cliente.getApellido(), cliente.getNumDocumento(),cliente.getTelefono(),cliente.getEmail()});
        }
        vista.jTable1.setModel(model);
        vista.setVisible(true);
    }
   

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
