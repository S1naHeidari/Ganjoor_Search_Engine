package raycatcher.search_engine;


import java.awt.ComponentOrientation;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jbibtex.ParseException;


public class searchPanel extends javax.swing.JFrame {
    static ArrayList<String> filePaths;

    public searchPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonSearch = new javax.swing.JButton();
        textSearch = new javax.swing.JTextField();
        labelSetabrDescribe = new javax.swing.JLabel();
        lebelSetabr = new javax.swing.JLabel();
        labelIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        buttonSearch.setBackground(new java.awt.Color(102, 102, 102));
        buttonSearch.setFont(new java.awt.Font("B Koodak", 0, 18)); // NOI18N
        buttonSearch.setText("بیاب");
        buttonSearch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonSearch.setContentAreaFilled(false);
        buttonSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        textSearch.setFont(new java.awt.Font("B Koodak", 0, 18)); // NOI18N
        textSearch.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textSearch.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "جستجو", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("XB Kayhan", 2, 18))); // NOI18N

        labelSetabrDescribe.setFont(new java.awt.Font("XB Kayhan", 1, 18)); // NOI18N
        labelSetabrDescribe.setText("موتورِ جستجوی پرشتاب");

        lebelSetabr.setFont(new java.awt.Font("XB Kayhan", 1, 24)); // NOI18N
        lebelSetabr.setText("سِتَبر");

        labelIcon.setIcon(new javax.swing.ImageIcon("/home/raycatcher/NetBeansProjects/search_engine/3cdf49ae-de8d-42d8-8b18-abe82d38a296_200x200.png")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(416, 416, 416)
                        .addComponent(lebelSetabr))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(336, 336, 336)
                        .addComponent(labelIcon)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addComponent(labelSetabrDescribe))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(349, 349, 349)
                        .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lebelSetabr)
                .addGap(18, 18, 18)
                .addComponent(labelSetabrDescribe, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(labelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchActionPerformed
        ShowResults sr = new ShowResults();
        sr.setVisible(true);
        SearchPoems sp = new SearchPoems(sr.areaPoem);
        
        try {
            sp.search(textSearch.getText());
        } catch (org.apache.lucene.queryparser.classic.ParseException ex) {
            Logger.getLogger(searchPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonSearchActionPerformed

    public static void main(String args[]) throws FileNotFoundException, ParseException, IOException, org.apache.lucene.queryparser.classic.ParseException {
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
            java.util.logging.Logger.getLogger(searchPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(searchPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(searchPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(searchPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new searchPanel().setVisible(true);
            }
        });
//        List<String> poems = readPoems();
//        System.out.println(poems);
        //Indexer indexer = new Indexer();
        //indexer.doIndexing();
        //SearchPoems searchPoem = new SearchPoems();
        //Constants c = new Constants();
        //String query = readLineByLineJava8(c.queryPath); 
        //searchPoem.search(query);
        
    }
    public static String readLineByLineJava8(String filePath) 
        {
            StringBuilder contentBuilder = new StringBuilder();

            try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) 
            {
                stream.forEach(s -> contentBuilder.append(s).append("\n"));
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }

            return contentBuilder.toString();
        }
    public static String readLineByLineJava16(String filePath) 
        {
            StringBuilder contentBuilder = new StringBuilder();

            try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_16)) 
            {
                stream.forEach(s -> contentBuilder.append(s).append("\n"));
            }
            catch (IOException e) 
            {
                e.printStackTrace();
            }

            return contentBuilder.toString();
        }
    public static List<String> readPoems() throws IOException{
        Path path = Paths.get("/home/raycatcher/NetBeansProjects/search_engine/Poems-final");
        String cleanData = "";
        try(Stream<Path> subpaths = Files.walk(path)){
            filePaths = (ArrayList<String>) subpaths.filter(Files::isRegularFile)
                    .map(Objects::toString)
                    .collect(Collectors.toList());
            for(String root : filePaths){
                String poem = readLineByLineJava8(root);
                String pattern = "[\\u0600-\\u06ff]|[\\u0750-\\u077f]|[\\ufb50-\\ufbc1]|"
                    + "[\\ufbd3-\\ufd3f]|[\\ufd50-\\ufd8f]|[\\ufd92-\\ufdc7]|[\\ufe70-\\ufefc]|[\\uFDF0-\\uFDFD]|[\\s]";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(poem);
                String tempData = "";
                while(m.find()){
                    tempData += m.group();
                }
                cleanData += tempData.trim();
                cleanData += "sep1234";
                String[] new_file_split = root.split("/");
                String newFile = new_file_split[new_file_split.length-1];
                BufferedWriter bw = new BufferedWriter(new FileWriter("/home/raycatcher/NetBeansProjects/search_engine/new/"
                        + newFile + ".txt"));
                bw.write(tempData.trim());
                bw.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        var documents = Arrays.asList(cleanData.split("sep1234"));
        return documents;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelIcon;
    private javax.swing.JLabel labelSetabrDescribe;
    private javax.swing.JLabel lebelSetabr;
    private javax.swing.JTextField textSearch;
    // End of variables declaration//GEN-END:variables
}
