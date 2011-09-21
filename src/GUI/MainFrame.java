package GUI;

import Model.Algorithm;
import Model.ClusterPoint;
import Model.FileReader;
import Model.Input;
import Model.Output;
import java.awt.*;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

/**
 * 
 * @author  OGO 1.2 groep 1
 *          Carl van Dueren den Hollander
 *          Ferry
 *          Nicky Advokaat
 *          Roby Visser
 *          Tim v Dalen
 */
public class MainFrame extends javax.swing.JFrame {
    private InputPanel inputPanel;   // left panel
    private OutputPanel outputPanel; // right panel
    private Input input;
    private Output output;
    private FileReader filereader;
    private Algorithm algorithm;
    private static int size = 50; // size of point drawn at screen
    private static int relSize = 90;
    private JFileChooser fileChooser = new JFileChooser();
    
    private static int right = 0;
    private static int down = 0;
    
    

    /** Creates new form MainFrame */
    public MainFrame() {
        filereader = new FileReader();
        algorithm = new Algorithm();
        
        input = new Input( 0, 0, new ArrayList< Point >() );
        output = algorithm.Algorithm1(input);
                
        FileChooserSetup();
        
        initComponents();
        initAddedComponents();
    }
    
    /**
     * initializes "open file" menu
     */
    private void FileChooserSetup(){
        fileChooser.setCurrentDirectory(new java.io.File("C:/Users/nicky/Documents/NetBeansProjects/OGO2"));
        fileChooser.setCurrentDirectory(new java.io.File("D:/My Documents/Ogo/2.1/Clusterfinder/ClusterFinder"));
        fileChooser.setDialogTitle("open een puzzeltje");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setAcceptAllFileFilterUsed(false);

        // filter .txt files only
        javax.swing.filechooser.FileFilter filter = (javax.swing.filechooser.FileFilter) new txtFileFilter();
        fileChooser.addChoosableFileFilter((javax.swing.filechooser.FileFilter) filter);
    }
    
    // manually added methods
    // initialize and place all added components
    private void initAddedComponents() {        
        inputPanel = new InputPanel();
        inputPanel.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        jPanel1.add(inputPanel);
        
        outputPanel = new OutputPanel();
        outputPanel.setSize(jPanel2.getWidth(), jPanel2.getHeight());
        jPanel2.add(outputPanel);
    }
    
    private class InputPanel extends JPanel  {

        public InputPanel(){
            this.setBackground(Color.white);
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for( Point p : input.points ){;
                g.setColor(Color.blue);
                int circleX = ( p.x + right ) * size;
                int circleY = ( p.y + down ) * size;
                int circleSize = size * relSize / 100 ;    
                g.fillOval(circleX, circleY, circleSize, circleSize);
            }
            g.setColor(Color.red);
            g.drawString("Clusters: " + input.nrOfClusters + "    Points: " + input.nrOfPoints, 0, 10);
        }

    }
    
    private class OutputPanel extends JPanel  {

        public OutputPanel(){
            this.setBackground(Color.white);
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for( ClusterPoint p : output.clusterPoints ){
                g.setColor( getColor(p.cluster) );
                int circleX = ( p.point.x + right ) * size;
                int circleY = ( p.point.y + down ) * size;
                int circleSize = size * relSize / 100 ;    
                g.fillOval(circleX, circleY, circleSize, circleSize);
            }
            g.setColor(Color.red);
            g.drawString("Clusters: " + output.nrOfClusters + "    Points: " + output.nrOfPoints, 0, 10);
        }
        
        private Color getColor(int cluster){
            switch(cluster){
                case 1: return Color.RED; 
                case 2: return Color.BLUE;    
                case 3: return Color.GREEN;
                case 4: return Color.YELLOW;
                case 5: return Color.PINK;
                case 6: return Color.ORANGE;
                case 7: return Color.MAGENTA;  
                case 8: return new Color(100,200,150);    
                case 9: return new Color(150,100,150);   
                case 10: return new Color(48,128,47);
                case 11: return new Color(13,170,85);
                case 12: return new Color(142,69,3);
                case 13: return new Color(84,89,206);
                case 14: return new Color(79,66,94);
                case 15: return new Color(238,3,194);
                case 16: return new Color(53,71,96);
                case 17: return new Color(46,186,148);
                case 18: return new Color(102,173,200);
                case 19: return new Color(159,184,218);
                case 20: return new Color(222,171,243);
                case 21: return new Color(41,159,24);
                case 22: return new Color(229,51,115);
                case 23: return new Color(154,52,26);
                case 24: return new Color(85,179,86);
                case 25: return new Color(158,129,150);
                case 26: return new Color(70,129,167);
                case 27: return new Color(180,213,49);
                case 28: return new Color(90,119,122);
                case 29: return new Color(48,185,154);
                case 30: return new Color(46,45,155);
                case 31: return new Color(186,61,235);
                case 32: return new Color(49,73,254);
                case 33: return new Color(87,167,39);
                case 34: return new Color(240,59,65);
                case 35: return new Color(137,35,17);
                case 36: return new Color(85,174,192);
                case 37: return new Color(77,224,17);
                case 38: return new Color(51,75,145);
                case 39: return new Color(136,144,209);
                case 40: return new Color(184,182,7);
                case 41: return new Color(178,67,173);
                case 42: return new Color(138,158,134);
                case 43: return new Color(144,59,172);
                case 44: return new Color(183,35,249);
                case 45: return new Color(125,252,20);
                case 46: return new Color(27,13,67);
                case 47: return new Color(182,102,140);
                case 48: return new Color(107,120,126);
                case 49: return new Color(145,12,54);
                case 50: return new Color(44,244,164);
                case 51: return new Color(247,118,146);
                case 52: return new Color(11,40,252);
                case 53: return new Color(241,250,171);
                case 54: return new Color(210,117,184);
                case 55: return new Color(144,4,108);
                case 56: return new Color(71,34,226);
                case 57: return new Color(199,4,104);
                case 58: return new Color(166,158,73);
                case 59: return new Color(233,252,29);
                case 60: return new Color(124,162,9);
                case 61: return new Color(127,227,172);
                case 62: return new Color(162,15,210);
                case 63: return new Color(16,215,251);
                case 64: return new Color(247,159,92);
                case 65: return new Color(243,15,96);
                case 66: return new Color(11,41,226);
                case 67: return new Color(90,157,52);
                case 68: return new Color(44,28,140);
                case 69: return new Color(255,240,92);
                case 70: return new Color(66,86,246);
                case 71: return new Color(97,8,82);
                case 72: return new Color(49,210,133);
                case 73: return new Color(30,87,161);
                case 74: return new Color(71,247,137);
                case 75: return new Color(108,121,89);
                case 76: return new Color(101,201,225);
                case 77: return new Color(122,253,62);
                case 78: return new Color(95,66,249);
                case 79: return new Color(132,33,171);
                case 80: return new Color(121,24,117);
                case 81: return new Color(129,140,119);
                case 82: return new Color(97,242,150);
                case 83: return new Color(195,254,68);
                case 84: return new Color(154,188,91);
                case 85: return new Color(75,52,8);
                case 86: return new Color(184,238,236);
                case 87: return new Color(239,5,39);
                case 88: return new Color(213,116,235);
                case 89: return new Color(82,98,147);
                case 90: return new Color(13,191,25);
                case 91: return new Color(77,217,132);
                case 92: return new Color(43,155,26);
                case 93: return new Color(136,188,21);
                case 94: return new Color(51,176,151);
                case 95: return new Color(114,77,197);
                case 96: return new Color(10,145,197);
                case 97: return new Color(126,215,189);
                case 98: return new Color(170,130,156);
                case 99: return new Color(77,172,248);
                default: return Color.gray;
     
            }
        }

    }
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSlider1 = new javax.swing.JSlider();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jSlider2 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cluster Finder");
        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 678, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 597, Short.MAX_VALUE)
        );

        jSlider1.setMinimum(2);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 597, Short.MAX_VALUE)
        );

        jButton1.setText("Open File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        jLabel2.setText("Algorithm:");

        jLabel3.setText("Size:");

        jButton2.setText("Run");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.setText("No File Selected");
        jTextField1.setEnabled(false);

        jSlider2.setMaximum(300);
        jSlider2.setMinimum(20);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jLabel1.setText("Rel point size:");

        jButton3.setText("^");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("<");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("-");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText(">");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton11.setText("R");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton7.setText("Focus First Point");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel3))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                                        .addComponent(jSlider2, 0, 0, Short.MAX_VALUE)
                                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton3)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton4)
                                    .addGap(10, 10, 10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton6)
                            .addGap(92, 92, 92)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(105, 105, 105)))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addGap(26, 26, 26)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton11)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addGap(91, 91, 91))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * slider changes size of drawn clusters 
     */
    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        // TODO add your handling code here:
        size = jSlider1.getValue();
        jPanel1.repaint();
        jPanel2.repaint();
    }//GEN-LAST:event_jSlider1StateChanged

    /**
     * takes care "open file" can only open .txt files
     */
    class txtFileFilter extends javax.swing.filechooser.FileFilter {
        public boolean accept(File f) {
            return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt") || f.getName().toLowerCase().endsWith(".in");
        }

        public String getDescription() {
            return ".txt files";
        }
    }
    
    /** 
     * Open File button 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int retval = fileChooser.showOpenDialog(null);  // open menu op locatie null
        if (retval == JFileChooser.APPROVE_OPTION) {            
            File file = fileChooser.getSelectedFile();
            input = filereader.readFromFile(file.getName());    // read new file   
            jTextField1.setText(file.getName());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * run button
     * @param evt 
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Run();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * relative size button
     * @param evt 
     */
    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged
        relSize = jSlider2.getValue();
        repaint();
    }//GEN-LAST:event_jSlider2StateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        right+=100;
        repaint();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        down-=100;
        repaint();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        down+=100;
        repaint();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        right-=100;
        repaint();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        right = 0;
        down = 0;
        repaint();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        right = -input.points.get(0).x;
        down = -input.points.get(0).y;
        repaint();
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * calculate algorithm and repaint
     */
    
    private void Run(){
        switch(Integer.parseInt(jComboBox1.getSelectedItem().toString())){
                case 1: output = algorithm.Algorithm1(input); break;
                case 2: output = algorithm.Algorithm2(input); break;
                case 3: output = algorithm.Algorithm3(input); break;
                case 4: output = algorithm.Algorithm4(input); break;    
            }            
            initAddedComponents();
            repaint();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
