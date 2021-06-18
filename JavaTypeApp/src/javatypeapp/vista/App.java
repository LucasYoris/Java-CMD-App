/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatypeapp.vista;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Lucas
 */
public class App extends javax.swing.JFrame {

    public static String sHora1;
    public static String sHora2;
    public static String sHora3;
    public static String sHora4;
    public static String sHora5;
    String horaActual = "";
    public static String commands;
    public static String seconds;

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
                            commands = comandos;
                            String[] lines = comandos.split("\\r?\\n|\\r");
                            if(!comandos.isEmpty()){
                                String cmd = "cmd /c start cmd.exe";
                                Runtime.getRuntime().exec(cmd);
                                Thread.sleep(1000);
                                for (int i = 0; i < lines.length; i++){
                                    escribir(lines[i]);
                                    presionarEnter();
                                    Thread.sleep(2000);
                                    // waits especificos
                                    if(lines[i].equals("gradlew.bat")){
                                        TimeUnit.SECONDS.sleep(18);
                                    }
                                    if(lines[i].startsWith("start gradlew.bat AllTest")){
                                        TimeUnit.SECONDS.sleep(125);
                                    }
                                    //-----------
                                }
                                seconds = segundos.getText();
                                if(segundos.getText() != null ){
                                    TimeUnit.SECONDS.sleep(5);
                                }else{
                                    TimeUnit.SECONDS.sleep(Integer.parseInt(segundos.getText()));
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
                            }else{ 
                            //modo headless
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
            Thread.sleep(20);
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
        jLabel4 = new javax.swing.JLabel();
        segundos = new javax.swing.JFormattedTextField();
        jLabelMensaje = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenuItemGuardar = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lTitulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lTitulo.setText("Aplicación de ejecución CMD");

        taComandos.setColumns(20);
        taComandos.setRows(5);
        taComandos.setToolTipText("<html>\n-Ingresar comandos separados por ENTER<br>\n-No se admiten ESPACIOS en los nombres de archivos<br>\n-No se admiten COMAS en los nombres de archivos<br>\n");
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

        jLabel4.setText("<html>\nTiempo de<br>\ncierre de CMD<br>\n(segundos)<br>");

        try{
            MaskFormatter mascara = new MaskFormatter("########");
            mascara.setValidCharacters("0123456789");
            segundos = new JFormattedTextField(mascara);
        }catch(Exception e){
            e.printStackTrace();
        }
        segundos.setEditable(true);
        segundos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        segundos.setToolTipText("<html>\nTiempo en el que se cerrarán<br>\nlos procesos cmd.exe<br>\nluego de ejecutar los comandos");
        segundos.setDragEnabled(true);
        segundos.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        segundos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundosActionPerformed(evt);
            }
        });

        jLabelMensaje.setBackground(new java.awt.Color(255, 102, 102));
        jLabelMensaje.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelMensaje.setToolTipText("");

        jMenuArchivo.setText("Archivo");

        jMenuItemGuardar.setText("Guardar");
        jMenuItemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGuardarActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemGuardar);

        jMenuItem1.setText("Cargar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItem1);

        jMenuBar1.add(jMenuArchivo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTitulo3)
                        .addComponent(jLabel2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lTitulo)
                                .addGap(143, 143, 143))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(etiquetaReloj)
                                        .addGap(17, 17, 17))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTitulo2)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(segundos, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(8, 8, 8)
                                                    .addComponent(hora1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(hora2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(hora3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(hora4, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(jBotonActivacion, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(jBotonEjecutar)
                                                            .addGap(26, 26, 26)))))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(hora5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel5)
                                                    .addGap(72, 72, 72))))))
                                .addGap(19, 19, 19))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabelMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jBotonActivacion, jBotonEjecutar});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {hora1, hora2, hora3, hora4, hora5});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lTitulo)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel1)
                        .addGap(141, 141, 141))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etiquetaReloj)
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTitulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(segundos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jTitulo3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hora1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hora2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hora3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hora4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hora5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBotonEjecutar)
                    .addComponent(jBotonActivacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jBotonActivacion, jBotonEjecutar});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {hora1, hora2, hora3, hora4, hora5});

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
    }//GEN-LAST:event_hora1ActionPerformed

    private void jBotonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonEjecutarActionPerformed
        try {
            String comandos = taComandos.getText();
            commands = comandos;
            String[] lines = comandos.split("\\r?\\n|\\r");
            if(!comandos.isEmpty()){
                String cmd = "cmd /c start cmd.exe";
                Process process = Runtime.getRuntime().exec(cmd);
                Thread.sleep(1000);
                for (int i = 0; i < lines.length; i++){
                    escribir(lines[i]);
                    presionarEnter();
                    Thread.sleep(500);
                    // waits especificos
                    if(lines[i].equals("gradlew.bat")){
                        TimeUnit.SECONDS.sleep(18);
                    }
                    if(lines[i].startsWith("start gradlew.bat AllTest")){
                        TimeUnit.SECONDS.sleep(125);
                    }
                    //-----------
                }
                seconds = segundos.getText();
                if(segundos.getText() != null ){
                    TimeUnit.SECONDS.sleep(5);
                }else{
                    TimeUnit.SECONDS.sleep(Integer.parseInt(segundos.getText()));
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
            }else{
                //modo headless
            }    
            
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AWTException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBotonEjecutarActionPerformed

    private void jMenuItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuardarActionPerformed
        sHora1 = hora1.getText();
        sHora2 = hora2.getText();
        sHora3 = hora3.getText();
        sHora4 = hora4.getText();
        sHora5 = hora5.getText();
        commands = taComandos.getText();
        seconds = segundos.getText();
        Guardar guardar = new Guardar();
        guardar.setVisible(true);
    }//GEN-LAST:event_jMenuItemGuardarActionPerformed

    private void segundosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_segundosActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Cargar cargar = new Cargar();
        cargar.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

   
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
    public static javax.swing.JFormattedTextField hora1;
    public static javax.swing.JFormattedTextField hora2;
    public static javax.swing.JFormattedTextField hora3;
    public static javax.swing.JFormattedTextField hora4;
    public static javax.swing.JFormattedTextField hora5;
    private javax.swing.JToggleButton jBotonActivacion;
    private javax.swing.JButton jBotonEjecutar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabelMensaje;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemGuardar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jTitulo2;
    private javax.swing.JLabel jTitulo3;
    private javax.swing.JLabel lTitulo;
    public static javax.swing.JFormattedTextField segundos;
    public static javax.swing.JTextArea taComandos;
    // End of variables declaration//GEN-END:variables
}
