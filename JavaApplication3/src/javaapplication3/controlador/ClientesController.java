/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication3.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javaapplication3.DAO.ClienteDAO;
import javaapplication3.model.Cliente;
import javaapplication3.vista.ClientView;
import javaapplication3.vista.TableClientView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ClientesController implements ActionListener{
    private ClienteDAO clienteDao;
    private TableClientView vistaTable;
    private ClientView vistaCliente;


    public ClientesController() throws ClassNotFoundException {
        this.clienteDao = new ClienteDAO();
        this.vistaTable = new TableClientView();
        this.vistaCliente = new ClientView();
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
        vistaTable.jTable1.setModel(model);
        vistaTable.btnBuscar.addActionListener(this);
        vistaCliente.btnDelete.addActionListener(this);
        vistaTable.setVisible(true);
    }
   

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vistaTable.btnBuscar)) {
            System.out.println("Action de buscar comenzando...");
            // Lógica para manejar el evento del botón aquí
            vistaTable.setVisible(false);
            vistaCliente.setVisible(true);
            vistaCliente.email.enable(false);
            vistaCliente.nom.enable(false);
            vistaCliente.numdoc.enable(false);
            vistaCliente.numtelf.enable(false);
            String num = vistaTable.numCli.getText();
            Cliente cliente = clienteDao.findByDni(num);
            vistaCliente.setVisible(true);
            //CAMBIAR VALORES
            vistaCliente.email.setText(cliente.getEmail());
            vistaCliente.nom.setText(cliente.getNombre()+cliente.getApellido());
            vistaCliente.numdoc.setText(cliente.getNumDocumento());
            vistaCliente.numtelf.setText(cliente.getTelefono());
            vistaCliente.id.setText(String.valueOf(cliente.getIdPersona()));
        }  

        
        if (e.getSource().equals(vistaCliente.btnDelete)) {
            // Lógica para manejar el eve vistaCliente.btnDeletnto del botón aquí
            Long id = Long.parseLong(vistaCliente.id.getText());
            clienteDao.delete(id);
            vistaCliente.setVisible(false);
            iniciar();
        }
        
    }
}
