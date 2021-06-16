/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatypeapp.vista;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Lucas
 */
public class App extends javax.swing.JFrame {

    public String sHora1 = "";
    public String sHora2 = "";
    public String sHora3 = "";
    public String sHora4 = "";
    public String sHora5 = "";
    String horaActual = "";

    public App() throws ParseException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("App CMD");
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
        Runnable runnable = new Runnable() {
        @Override
        public void run() {
        while(true){
                try {
                    Thread.sleep(500);
                    etiquetaReloj.setText(formateador.format(LocalDateTime.now()));
                    String horaActual = etiquetaReloj.getText();
                    if(horaActual.equals(sHora1) || horaActual.equals(sHora2) || horaActual.equals(sHora3) || horaActual.equals(sHora4) || horaActual.equals(sHora5) && jBotonActivacion.isSelected()){
                        try {
                            String comandos = taComandos.getText();
                            String[] lines = comandos.split("\\r?\\n|\\r");
                            if(!jHeadless.isSelected() && (!comandos.isEmpty())){
                                String cmd = "cmd /c start cmd.exe";
                                Runtime.getRuntime().exec(cmd);
                                Thread.sleep(1500);
                                for (int i = 0; i < lines.length; i++){
                                    escribir(lines[i]);
                                                presionarEnter();
                                                Thread.sleep(2000);
                                                // waits especificos
                                                if(lines[i].equals("gradlew.bat")){
                                                    Thread.sleep(18000);
                                                }
                                                if(lines[i].startsWith("start gradlew.bat AllTest")){
                                                    Thread.sleep(120000);
                                                }
                                                //-----------
                                }
                                // To kill a command prompt
                                comandos = "";
                                WindowsProcessKiller pKiller = new WindowsProcessKiller();
                                String processName = "cmd.exe";
                                boolean isRunning = pKiller.isProcessRunning(processName);
                                System.out.println("is " + processName + " running : " + isRunning);
                                if (isRunning) {
                                    pKiller.killProcess(processName);
                                }
                                else {
                                    System.out.println("Not able to find the process : "+processName);
                                }
                            }else if(jHeadless.isSelected() && !comandos.isEmpty()){
                                for (int i = 0; i < lines.length; i++){
                                    Runtime.getRuntime().exec(lines[i]);
                                    Thread.sleep(2000);
                                    // waits especificos
                                    if(lines[i].equals("gradlew.bat")){
                                        Thread.sleep(18000);
                                    }
                                    if(lines[i].startsWith("start gradlew.bat AllTest")){
                                        Thread.sleep(120000);
                                    }
                                }
                                // To kill a command prompt
                                comandos = "";
                                WindowsProcessKiller pKiller = new WindowsProcessKiller();
                                String processName = "cmd.exe";
                                boolean isRunning = pKiller.isProcessRunning(processName);
                                System.out.println("is " + processName + " running : " + isRunning);
                                if (isRunning) {
                                    pKiller.killProcess(processName);
                                }
                                else {
                                    System.out.println("Not able to find the process : "+processName);
                                }
                            }    

                        } catch (IOException ex) {
                            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (AWTException ex) {
                            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                        }                        
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        }
                    };
                    Thread hilo = new Thread(runnable);
                    hilo.start();
    }
    
    /*public void switchFocus() { 
        try { 
            Robot r = new Robot(); 
            r.keyPress(KeyEvent.VK_ALT); 
            r.keyPress(KeyEvent.VK_TAB);
            r.delay(10);
            r.keyRelease(KeyEvent.VK_ALT); 
            r.keyRelease(KeyEvent.VK_TAB); 
        } catch(AWTException e) { 
            e.printStackTrace();
        } 
    }*/ 
    
    protected void presionarEnter() throws InterruptedException, AWTException {
        Robot robot = new Robot();
        try { 			
            robot = new Robot(); 			
            robot.keyPress(KeyEvent.VK_ENTER); // confirm by pressing Enter in the end 			
            robot.keyRelease(KeyEvent.VK_ENTER); 		
        } catch (AWTException e) { 			
            e.printStackTrace(); 		
        }
            
    }
    
    protected void escribir(String string) throws InterruptedException, AWTException
    {
        //Instantiating robot
        Robot robot = new Robot();

        //Looping through every char
        for (int i = 0; i < string.length(); i++)
        {
            //Getting current char
            char c = string.charAt(i);

            //Pressing shift if it's uppercase ! " * ' / # \ $ & % ( )
            if (Character.isUpperCase(c))
            {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }
                if(c == '!' || c == '"' || c == '*' || c == '\'' || c == '/' || c == '#' || c == '$' || c == '&' 
                || c == '%' || c == '(' || c == ')' || c == '\\' || c == ':' || c == '_' || c == '-' || c == '.' 
                || c == ';' || c == '<' || c == '=' || c == '>' || c == '?' || c == '+' ) {
                switch (c){
                    case '!': 
                        pressUnicode(robot,33);
                    break;
                    case '"':
                        pressUnicode(robot,34);
                    break;
                    case '#':
                        pressUnicode(robot,35);
                    break;
                    case '$':
                        pressUnicode(robot,36);
                    break;
                    case '%':
                        pressUnicode(robot,37);
                    break;
                    case '&':
                        pressUnicode(robot,38);
                    break;
                    case '\\':
                        pressUnicode(robot,92);
                    break;
                    case '(':
                        pressUnicode(robot,40);
                    break;
                    case ')':
                        pressUnicode(robot,41);
                    break;
                    case '*':
                        pressUnicode(robot,42);
                    break;
                    case '/': 
                        pressUnicode(robot,47);
                    break;
                    case '\'': 
                        pressUnicode(robot,39);
                    break;
                    case ':': 
                        pressUnicode(robot,58);
                    break;
                    case '-': 
                        pressUnicode(robot,45);
                    break;
                    case '_': 
                        pressUnicode(robot,95);
                    break;
                    case '.': 
                        pressUnicode(robot,46);
                    break;
                    case ';': 
                        pressUnicode(robot,59);
                    break;
                    case '<': 
                        pressUnicode(robot,60);
                    break;
                    case '=': 
                        pressUnicode(robot,61);
                    break;
                    case '>': 
                        pressUnicode(robot,62);
                    break;
                    case '?': 
                        pressUnicode(robot,63);
                    break;
                    case '+': 
                        pressUnicode(robot,43);
                    break;
                }
                    robot.keyRelease(KeyEvent.VK_ALT);
            }else{
                //Actually pressing the key
                robot.keyPress(Character.toUpperCase(c));
                robot.keyRelease(Character.toUpperCase(c));
            }

            //Releasing shift if it's uppercase
            if (Character.isUpperCase(c))
            {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }

            //Optional delay to make it look like it's a human typing
            Thread.sleep(20 + new Random().nextInt(150));
        }
    }
    
    public static void pressUnicode(Robot r, int key_code)
{
    r.keyPress(KeyEvent.VK_ALT);

    for(int i = 3; i >= 0; --i)
    {
        // extracts a single decade of the key-code and adds
        // an offset to get the required VK_NUMPAD key-code
        int numpad_kc = key_code / (int) (Math.pow(10, i)) % 10 + KeyEvent.VK_NUMPAD0;

        r.keyPress(numpad_kc);
        r.keyRelease(numpad_kc);
    }

    r.keyRelease(KeyEvent.VK_ALT);
}
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jHeadless = new javax.swing.JCheckBox();
        lTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taComandos = new javax.swing.JTextArea();
        jTitulo2 = new javax.swing.JLabel();
        jTitulo3 = new javax.swing.JLabel();
        jBotonActivacion = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        etiquetaReloj = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        hora1 = new javax.swing.JFormattedTextField();
        hora2 = new javax.swing.JFormattedTextField();
        hora3 = new javax.swing.JFormattedTextField();
        hora4 = new javax.swing.JFormattedTextField();
        hora5 = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jBotonEjecutar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jHeadless.setText("Headless");
        jHeadless.setToolTipText("Si está seleccionado se ejecutará en segundo plano");
        jHeadless.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jHeadlessActionPerformed(evt);
            }
        });

        lTitulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lTitulo.setText("Aplicación de ejecución CMD");

        taComandos.setColumns(20);
        taComandos.setRows(5);
        taComandos.setToolTipText("Ingresar comandos separados por Enter");
        jScrollPane1.setViewportView(taComandos);

        jTitulo2.setText("Ingresar comandos a ejecutar separado por un ENTER:");

        jTitulo3.setText("Ingresar horarios de ejecución");

        jBotonActivacion.setText("Activar");
        jBotonActivacion.setToolTipText("Se activa el programador de tareas");
        jBotonActivacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonActivacionActionPerformed(evt);
            }
        });

        etiquetaReloj.setText("Hora actual");
        etiquetaReloj.setToolTipText("Hora del sistema");

        jLabel2.setText("Formato HH:MM:SS (hasta 5 horarios diarios):");

        try{
            MaskFormatter mascara = new MaskFormatter("##:##:##");
            mascara.setValidCharacters("0123456789");
            hora1 = new JFormattedTextField(mascara);
        }catch(Exception e){
            e.printStackTrace();
        }
        hora1.setEditable(true);
        hora1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        hora1.setToolTipText("HH:MM:SS");
        hora1.setDragEnabled(true);
        hora1.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        hora1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hora1ActionPerformed(evt);
            }
        });

        try{
            MaskFormatter mascara = new MaskFormatter("##:##:##");
            hora2 = new JFormattedTextField(mascara);
        }catch(Exception e){
            e.printStackTrace();
        }
        hora2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        hora2.setToolTipText("HH:MM:SS");
        hora2.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        try{
            MaskFormatter mascara = new MaskFormatter("##:##:##");
            hora3 = new JFormattedTextField(mascara);
        }catch(Exception e){
            e.printStackTrace();
        }
        hora3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        hora3.setToolTipText("HH:MM:SS");
        hora3.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        try{
            MaskFormatter mascara = new MaskFormatter("##:##:##");
            hora4 = new JFormattedTextField(mascara);
        }catch(Exception e){
            e.printStackTrace();
        }
        hora4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        hora4.setToolTipText("HH:MM:SS");
        hora4.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        try{
            MaskFormatter mascara = new MaskFormatter("##:##:##");
            hora5 = new JFormattedTextField(mascara);
        }catch(Exception e){
            e.printStackTrace();
        }
        hora5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        hora5.setToolTipText("HH:MM:SS");
        hora5.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel3.setText("Created By Lucas E. Yoris ");

        jBotonEjecutar.setText("Ejecutar");
        jBotonEjecutar.setToolTipText("Se ejecuta el comando inmediatamente");
        jBotonEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonEjecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(lTitulo)
                            .addGap(143, 143, 143))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(38, 38, 38)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTitulo2)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(hora1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jBotonActivacion, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jBotonEjecutar))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(hora2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(hora3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(hora4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(hora5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(36, 36, 36)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTitulo3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jHeadless))
                            .addComponent(etiquetaReloj))
                        .addGap(23, 23, 23))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBotonActivacion, jBotonEjecutar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lTitulo)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(etiquetaReloj))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTitulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jHeadless)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hora2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hora3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hora4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hora5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBotonEjecutar)
                            .addComponent(jBotonActivacion))
                        .addGap(30, 30, 30)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBotonActivacion, jBotonEjecutar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBotonActivacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonActivacionActionPerformed
        if(jBotonActivacion.isSelected()){
            sHora1 = hora1.getText();
            sHora2 = hora2.getText();
            sHora3 = hora3.getText();
            sHora4 = hora4.getText();
            sHora5 = hora5.getText();
            hora1.setText(sHora1);
            hora2.setText(sHora2);
            hora3.setText(sHora3);
            hora4.setText(sHora4);
            hora5.setText(sHora5);
            jBotonActivacion.setText("Desactivar");
        }
        if(!jBotonActivacion.isSelected()){
            sHora1 = "";
            sHora2 = "";
            sHora3 = "";
            sHora4 = "";
            sHora5 = "";
            jBotonActivacion.setText("Activar");
        }
        
    }//GEN-LAST:event_jBotonActivacionActionPerformed

    private void hora1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hora1ActionPerformed
        hora1.setText("");
    }//GEN-LAST:event_hora1ActionPerformed

    private void jHeadlessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jHeadlessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jHeadlessActionPerformed

    private void jBotonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonEjecutarActionPerformed
        try {
            String comandos = taComandos.getText();
            String[] lines = comandos.split("\\r?\\n|\\r");
            if(!jHeadless.isSelected() && (!comandos.isEmpty())){
                String cmd = "cmd /c start cmd.exe";
                Runtime.getRuntime().exec(cmd);
                Thread.sleep(1500);
                for (int i = 0; i < lines.length; i++){
                    escribir(lines[i]);
                                presionarEnter();
                                Thread.sleep(2000);
                                // waits especificos
                                if(lines[i].equals("gradlew.bat")){
                                    Thread.sleep(18000);
                                }
                                if(lines[i].startsWith("start gradlew.bat AllTest")){
                                    Thread.sleep(120000);
                                }
                                //-----------
                }
                // To kill a command prompt
                comandos = "";
                WindowsProcessKiller pKiller = new WindowsProcessKiller();
                String processName = "cmd.exe";
                boolean isRunning = pKiller.isProcessRunning(processName);
                System.out.println("is " + processName + " running : " + isRunning);
                if (isRunning) {
                    pKiller.killProcess(processName);
                }
                else {
                    System.out.println("Not able to find the process : "+processName);
                }
            }else if(jHeadless.isSelected() && !comandos.isEmpty()){
                for (int i = 0; i < lines.length; i++){
                    Runtime.getRuntime().exec(lines[i]);
                    Thread.sleep(2000);
                    // waits especificos
                    if(lines[i].equals("gradlew.bat")){
                        Thread.sleep(18000);
                    }
                    if(lines[i].startsWith("start gradlew.bat AllTest")){
                        Thread.sleep(120000);
                    }
                }
                // To kill a command prompt
                comandos = "";
                WindowsProcessKiller pKiller = new WindowsProcessKiller();
                String processName = "cmd.exe";
                boolean isRunning = pKiller.isProcessRunning(processName);
                System.out.println("is " + processName + " running : " + isRunning);
                if (isRunning) {
                    pKiller.killProcess(processName);
                }
                else {
                    System.out.println("Not able to find the process : "+processName);
                }
            }    
            
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AWTException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBotonEjecutarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ParseException {
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
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new App().setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel etiquetaReloj;
    public javax.swing.JFormattedTextField hora1;
    private javax.swing.JFormattedTextField hora2;
    private javax.swing.JFormattedTextField hora3;
    private javax.swing.JFormattedTextField hora4;
    private javax.swing.JFormattedTextField hora5;
    private javax.swing.JToggleButton jBotonActivacion;
    private javax.swing.JButton jBotonEjecutar;
    private javax.swing.JCheckBox jHeadless;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jTitulo2;
    private javax.swing.JLabel jTitulo3;
    private javax.swing.JLabel lTitulo;
    private javax.swing.JTextArea taComandos;
    // End of variables declaration//GEN-END:variables
}
