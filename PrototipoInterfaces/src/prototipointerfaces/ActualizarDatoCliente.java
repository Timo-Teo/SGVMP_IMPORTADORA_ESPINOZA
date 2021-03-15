/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipointerfaces;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author santiago
 */
public class ActualizarDatoCliente extends javax.swing.JFrame {

    String numDocumento = MóduloClientes.nuevoClienteA.numDoc;
    String tipoDocumento = MóduloClientes.nuevoClienteA.tipoDoc;
    String campo = MóduloClientes.campoActualizar;

    /**
     * Creates new form ActualizarDatoCliente
     */
    public ActualizarDatoCliente() {
        initComponents();
        setLocationRelativeTo(null);
        mostrarInfo(numDocumento);
    }

    void mostrarInfo(String numeroDoc) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Tipo de Documento");
        modelo.addColumn("Numero de Documento");
        modelo.addColumn("Telefono Celular");
        modelo.addColumn("Direccion");
        modelo.addColumn("Correo");
        modelo.addColumn("Estado");

        String sql = "SELECT * FROM clientes WHERE Numero_de_Documento LIKE ?";
        String datos[] = new String[8];

        Conexion con = new Conexion();
        Connection cn = (Connection) con.conexion();
        com.mysql.jdbc.PreparedStatement pst;
        ResultSet rs;

        try {
            pst = (com.mysql.jdbc.PreparedStatement) cn.prepareStatement(sql);
            pst.setString(1, numeroDoc);
            rs = pst.executeQuery();
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                modelo.addRow(datos);
            }
            switch (campo) {
                case "Nombre":
                    txtActual.setText(datos[0]);
                    break;
                case "Apellido":
                    txtActual.setText(datos[1]);
                    break;
                case "Telefono_Celular":
                    txtActual.setText(datos[4]);
                    break;
                case "Direccion":
                    txtActual.setText(datos[5]);
                    break;
                case "Correo_Electronico":
                    txtActual.setText(datos[6]);
                    break;
                case "Estado":
                    txtActual.setText(datos[7]);
                    break;
            }
        } catch (SQLException e) {

        }
    }

    void actualizarInfo(String nuevoDato) {
        Conexion con = new Conexion();
        Connection cn = (Connection) con.conexion();
        com.mysql.jdbc.PreparedStatement pstm;
        try {
            switch (campo) {
                /*
                case "Nombre":
                    pstm = (com.mysql.jdbc.PreparedStatement) cn.prepareStatement("update clientes set Nombre = ? WHERE Numero_de_Documento LIKE ?");
                    pstm.setString(1, nuevoDato);
                    pstm.setString(2, numDocumento);
                    pstm.executeUpdate();
                    JOptionPane.showMessageDialog(null, "El campo "+campo+" ha sido actualizado exitosamente");
                    break;
                case "Apellido":
                    pstm = (com.mysql.jdbc.PreparedStatement) cn.prepareStatement("update clientes set Apellido = ? WHERE Numero_de_Documento LIKE ?");
                    pstm.setString(1, nuevoDato);
                    pstm.setString(2, numDocumento);
                    pstm.executeUpdate();
                    JOptionPane.showMessageDialog(null, "El campo "+campo+" ha sido actualizado exitosamente");
                    break;
                 */
                case "Telefono_Celular":
                    if (!MóduloClientes.nuevoClienteA.esTelefonoValido(nuevoDato)) {
                        JOptionPane.showMessageDialog(null, "Nuevo Teléfono Celular Incorrecto - Corrija");
                        txtNuevo.setText("");
                    } else {
                        pstm = (com.mysql.jdbc.PreparedStatement) cn.prepareStatement("update clientes set Telefono_Celular = ? WHERE Numero_de_Documento LIKE ?");
                        pstm.setString(1, nuevoDato);
                        pstm.setString(2, numDocumento);
                        pstm.executeUpdate();
                        JOptionPane.showMessageDialog(null, "¡Teléfono Celular del Cliente Actualzado Exitosamente!");
                        //------------------------------------------------------
                        MóduloClientes newMod = new MóduloClientes();
                        newMod.setVisible(true);
                        dispose();
                        //------------------------------------------------------
                    }
                    break;
                case "Direccion":
                    if (!MóduloClientes.nuevoClienteA.esDireccionValido(nuevoDato)) {
                        JOptionPane.showMessageDialog(null, "Nueva Dirección Incorrecta - Corrija");
                        txtNuevo.setText("");
                    } else {
                        pstm = (com.mysql.jdbc.PreparedStatement) cn.prepareStatement("update clientes set Direccion = ? WHERE Numero_de_Documento LIKE ?");
                        pstm.setString(1, nuevoDato);
                        pstm.setString(2, numDocumento);
                        pstm.executeUpdate();
                        JOptionPane.showMessageDialog(null, "¡Dirección del Cliente Actualzada Exitosamente!");
                        MóduloClientes newMod = new MóduloClientes();
                        newMod.setVisible(true);
                        dispose();
                        //------------------------------------------------------
                    }
                    break;
                case "Correo_Electronico":
                    if (!MóduloClientes.nuevoClienteA.esCorreoValido(nuevoDato)) {
                        JOptionPane.showMessageDialog(null, "Nuevo Correo Electrónico Incorrecto - Corrija");
                        txtNuevo.setText("");
                    } else {
                        pstm = (com.mysql.jdbc.PreparedStatement) cn.prepareStatement("update clientes set Correo_Electronico = ? WHERE Numero_de_Documento LIKE ?");
                        pstm.setString(1, nuevoDato);
                        pstm.setString(2, numDocumento);
                        pstm.executeUpdate();
                        JOptionPane.showMessageDialog(null, "¡Correo Electrónico del Cliente Actualzado Exitosamente!");
                        MóduloClientes newMod = new MóduloClientes();
                        newMod.setVisible(true);
                        dispose();
                        //------------------------------------------------------
                    }
                    break;
                case "Estado":
                    if (!nuevoDato.equals("Activo") || !nuevoDato.equals("Inactivo")) {
                        JOptionPane.showMessageDialog(null, "Nuevo Estado Incorrecto - Corrija");
                        txtNuevo.setText("");
                    } else {
                        pstm = (com.mysql.jdbc.PreparedStatement) cn.prepareStatement("update clientes set Estado = ? WHERE Numero_de_Documento LIKE ?");
                        pstm.setString(1, nuevoDato);
                        pstm.setString(2, numDocumento);
                        pstm.executeUpdate();
                        JOptionPane.showMessageDialog(null, "¡Estado del Cliente Actualzado Exitosamente!");
                        MóduloClientes newMod = new MóduloClientes();
                        newMod.setVisible(true);
                        dispose();
                        //------------------------------------------------------
                    }
                    break;
            }

        } catch (Exception e) {

        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtActual = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNuevo = new javax.swing.JTextField();
        btnActualizarCliente = new javax.swing.JButton();
        btnAtrasAC2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Ingresar"));

        jLabel1.setText("Actual");

        txtActual.setEditable(false);
        txtActual.setDragEnabled(true);
        txtActual.setEnabled(false);

        jLabel2.setText("Nuevo");

        btnActualizarCliente.setText("Actualizar Cliente");
        btnActualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarClienteActionPerformed(evt);
            }
        });

        btnAtrasAC2.setText("Atrás");
        btnAtrasAC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasAC2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtActual, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAtrasAC2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(btnActualizarCliente)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtActual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnActualizarCliente)
                .addGap(18, 18, 18)
                .addComponent(btnAtrasAC2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasAC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasAC2ActionPerformed
        // TODO add your handling code here:
        MóduloClientes nuevoModCli = new MóduloClientes();
        nuevoModCli.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasAC2ActionPerformed

    private void btnActualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarClienteActionPerformed
        // TODO add your handling code here:
        String nuevoDato = txtNuevo.getText();
        actualizarInfo(nuevoDato);
    }//GEN-LAST:event_btnActualizarClienteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ActualizarDatoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarDatoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarDatoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarDatoCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActualizarDatoCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarCliente;
    private javax.swing.JButton btnAtrasAC2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtActual;
    private javax.swing.JTextField txtNuevo;
    // End of variables declaration//GEN-END:variables
}
