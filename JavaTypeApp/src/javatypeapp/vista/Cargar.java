/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatypeapp.vista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class Cargar extends javax.swing.JFrame {

    /**
     * Creates new form Guardar
     */
    public Cargar() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Cargar");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldPathCarga = new javax.swing.JTextField();
        jButtonCargar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTextFieldPathCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPathCargaActionPerformed(evt);
            }
        });

        jButtonCargar.setText("Cargar");
        jButtonCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCargarActionPerformed(evt);
            }
        });

        jLabel1.setText("Path:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonCargar)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPathCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldPathCarga, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCargar)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldPathCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPathCargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPathCargaActionPerformed

    private void jButtonCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCargarActionPerformed
        File f = new File(jTextFieldPathCarga.getText());
        BufferedReader entrada = null;
        try {
            entrada = new BufferedReader(new FileReader(f));
            String linea;
            String horarios = "Horarios:";
            int flag = 1;
            String commands="";
            while (entrada.ready()) {
                linea = entrada.readLine();
                if (!linea.equals(horarios)) {
                    if (flag != 0 && !linea.equals("")) {
                        commands=commands+linea+"\n";
                    } else if (flag == 0) {
                        for (int i = 0; i < 6; i++) {
                            linea = entrada.readLine();
                            switch (i) {
                                case 0:
                                    App.hora1.setText(linea);
                                    break;
                                case 1:
                                    App.hora2.setText(linea);
                                    break;
                                case 2:
                                    App.hora3.setText(linea);
                                    break;
                                case 3:
                                    App.hora4.setText(linea);
                                    break;
                                case 4:
                                    App.hora5.setText(linea);
                                    break;
                                case 5:
                                    String x;
                                    String y;
                                    y = linea;
                                    x = y.replace(" ", "");
                                    App.segundos.setText(x);
                                    break;
                                default:
                                // code block
                            }
                        }
                    }

                } else {
                    flag = 0;
                    App.taComandos.setText(commands);

                }
                dispose();
                //TIMER CON HILOS
                App.jLabelMensaje.setText("Carga exitosa");
                Timer timer = new Timer();
                TimerTask tarea = new TimerTask() {
                    @Override
                    public void run() {
                        App.jLabelMensaje.setText("");
                    }
                };
                timer.schedule(tarea, 2000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                entrada.close();
            } catch (IOException e1) {
            }
        }
    }//GEN-LAST:event_jButtonCargarActionPerformed

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
            java.util.logging.Logger.getLogger(Cargar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cargar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cargar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cargar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cargar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCargar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextFieldPathCarga;
    // End of variables declaration//GEN-END:variables
}
