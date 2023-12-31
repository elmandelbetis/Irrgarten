package controller;

import irrgarten.Directions;

public class Cursors extends javax.swing.JDialog {
    
    // Dirección de movimiento
    private Directions direction;
    
    /**
     * Creates new form Cursors
     */
    public Cursors(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonUP = new javax.swing.JButton();
        buttonRIGHT = new javax.swing.JButton();
        buttonLEFT = new javax.swing.JButton();
        buttonDOWN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        buttonUP.setText("ARRIBA");
        buttonUP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUPActionPerformed(evt);
            }
        });

        buttonRIGHT.setText("DERECHA");
        buttonRIGHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRIGHTActionPerformed(evt);
            }
        });

        buttonLEFT.setText("IZQUIERDA");
        buttonLEFT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLEFTActionPerformed(evt);
            }
        });

        buttonDOWN.setText("ABAJO");
        buttonDOWN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDOWNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(buttonLEFT, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRIGHT)
                .addGap(68, 68, 68))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(buttonUP))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(buttonDOWN, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(buttonUP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRIGHT)
                    .addComponent(buttonLEFT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonDOWN)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Cursor arriba
    private void buttonUPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUPActionPerformed
        direction = Directions.UP;
        dispose();
    }//GEN-LAST:event_buttonUPActionPerformed
    
    // Cursor izquierdo
    private void buttonLEFTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLEFTActionPerformed
        direction = Directions.LEFT;
        dispose();
    }//GEN-LAST:event_buttonLEFTActionPerformed
    
    // Cursor derecho
    private void buttonRIGHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRIGHTActionPerformed
        direction = Directions.RIGHT;
        dispose();
    }//GEN-LAST:event_buttonRIGHTActionPerformed

    // Cursor abajo
    private void buttonDOWNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDOWNActionPerformed
        direction = Directions.DOWN;
        dispose();
    }//GEN-LAST:event_buttonDOWNActionPerformed
    
    // Método para devolver la dirección de movimiento de manera pública para la UI
    public Directions getDirection(){
        setVisible(true);
        return direction;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDOWN;
    private javax.swing.JButton buttonLEFT;
    private javax.swing.JButton buttonRIGHT;
    private javax.swing.JButton buttonUP;
    // End of variables declaration//GEN-END:variables
}
