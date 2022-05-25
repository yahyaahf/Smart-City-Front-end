/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package newpackage;

import com.formdev.flatlaf.FlatLightLaf;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import model.*;
import model.SmartBuilding;
import model.Ville;
import model.WorkSpace;
import service.EtageHttp;
import service.SmartBuildingHtttp;
import service.VilleHttp;
import service.WorkSpaceHttp;
import com.formdev.flatlaf.FlatLightLaf;
import java.util.Date;
import model.Client;
import model.ClientEntreprise;
import model.ClientIndividu;
import service.ClientHttp;
import service.LocationHttp;
import java.text.*;
import java.util.ArrayList;
import model.Location;
import service.ReservationHttp;

/**
 *
 * @author Lenovo
 */
public  class Main extends javax.swing.JFrame {
 DefaultTableModel d1= new DefaultTableModel();
    DefaultTableModel d2= new DefaultTableModel();
    DefaultTableModel d3= new DefaultTableModel();
    DefaultTableModel d4= new DefaultTableModel();
    DefaultTableModel d5= new DefaultTableModel();
    DefaultTableModel d6= new DefaultTableModel();
    
    
    
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        init2();
        
        String[] table1 =new String[]{"numero","nombreEtage","surface","adress"};
        d1.setColumnIdentifiers(table1);
        jTable1.setModel(d1);
        String[] table2 =new String[]{"numero","workSpace","surface"};
        d2.setColumnIdentifiers(table2);
        jTable2.setModel(d2);
        EtageHttp eh=new EtageHttp();
        List<Etage> etages=eh.getEtageFromServer(1);
        
        for(Etage e:etages){
            remplirEtage.addItem(e);
        }
        String[] table3 =new String[]{"Numero","Surface","Type","Louer"};
        
        d3.setColumnIdentifiers(table3);
        remplirWork.setModel(d3);
        String[] table4 =new String[]{"formejuridique","pattente","email","téléphone","adresse"};
        
        d4.setColumnIdentifiers(table4);
        jTable4.setModel(d4);
        
        String[] table5 =new String[]{"nom","prenom","sexe","email","telephone","adresse"};
        d5.setColumnIdentifiers(table5);
        jTable3.setModel(d5);
        String[] table6 =new String[]{"Aile Gauche","Aile Droite"};
        d6.setColumnIdentifiers(table6);
        planEtage.setModel(d6);
        
    }

    private void init2(){
        
        VilleHttp vh=new VilleHttp();
        List<Ville> villes=vh.getVillesFromServer();
        //jComboBox1.setSelectedItem(null);
        for(Ville v:villes){
            String nom=v.getNom();
            jComboBox1.addItem(nom);
            deleteVille.addItem(nom);
            locationVille.addItem(nom);
            updateVille.addItem(nom);
            update.addItem(nom);
            ajoutBuild.addItem(nom);
             ajoutVilleWork.addItem(nom);
            
       }
        
    }
    void remplirTable1(){
        
        
            String nomVille=(String) jComboBox1.getSelectedItem();
        
        VilleHttp vil=new VilleHttp();
        Ville ville=vil.getVille(nomVille);
        long numeroVille=ville.getNumero();
        SmartBuildingHtttp hb=new SmartBuildingHtttp();
        List<SmartBuilding> buildings=hb.getSmartBuildingFromServer(numeroVille);
        String numero = null,numEtage = null,surface = null,adress = null;
        for(SmartBuilding sb:buildings){
            numero=String.valueOf(sb.getNumero());
            numEtage=String.valueOf(sb.getNombreEtages());
            surface=String.valueOf(sb.getSurface());
            adress=String.valueOf(sb.getAdress());
            d1.addRow(new Object[]{
                numero,numEtage,surface,adress
            });
        }
        
        
        
    }
    
    void remplirTable2(){
       
        //d2= new DefaultTableModel();
        //d6= new DefaultTableModel();
        Etage etage=(Etage) jComboBox2.getSelectedItem();
        System.out.println(etage.getId());
        long numeroetage=etage.getNumEtage();
        WorkSpaceHttp eh=new WorkSpaceHttp();
        List<WorkSpace> workSpaces=eh.getWorkSpaceFromServer(numeroetage);
        int size=workSpaces.size();
        System.out.println(size);
        //int a=size/2;
        List<Long> droite=new ArrayList<>();
        List<Long> gauche=new ArrayList<>();
        for(int i=1;i<=(size/2);i++){
            //int b=i-1;
            
            if(i%2==0){
                
                //long numero=workSpaces.get(i-1).getNumero();
                droite.add(workSpaces.get(i-1).getNumero());
            }
            else if(i%2!=0){
                //long numero=workSpaces.get(i-1).getNumero();
                gauche.add(workSpaces.get(i-1).getNumero());
            }
            
        }
     for (Long droite1 : droite) {
         for (Long gauche1 : gauche) {
             d6.addRow(new Object[]{
                 gauche1,droite1
             });
         }
     }
        
        String numero = null, workspace = null,surface = null;
        for(WorkSpace e:workSpaces){
            numero=String.valueOf(e.getNumero());
            workspace=String.valueOf(e.getType());
            surface=String.valueOf(e.getSurface());
            d2.addRow(new Object[]{
                numero,workspace,surface
            });
            
            
           
            
        }
        
        System.out.println(workSpaces);
    }
    void remplirTable3(){
        
        Etage etage=(Etage) remplirEtage.getSelectedItem();
        
        long numeroetage=etage.getNumEtage();
        WorkSpaceHttp eh=new WorkSpaceHttp();
        List<WorkSpace> workSpaces=eh.getWorkSpaceFromServer(numeroetage);
        
        String numero = null, workspace = null,surface = null;
        Boolean louer = null;
        for(WorkSpace e:workSpaces){
            numero=String.valueOf(e.getNumero());
            workspace=String.valueOf(e.getType());
            surface=String.valueOf(e.getSurface());
            louer=false;
            
           
            
        }
        d3.addRow(new Object[]{
                numero,workspace,surface,louer
            });
        System.out.println(workSpaces);
        remplirWork.setColumnSelectionAllowed(true);
        remplirWork.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        if (remplirWork.getColumnModel().getColumnCount() > 0) {
            remplirWork.getColumnModel().getColumn(3).setPreferredWidth(20);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jButton40 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        planEtage = new javax.swing.JTable();
        jPanel37 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        radioIndividu = new javax.swing.JRadioButton();
        radioEntreprise = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        female = new javax.swing.JRadioButton();
        male = new javax.swing.JRadioButton();
        ajoutNomFormejuridique = new javax.swing.JTextField();
        ajoutTelephoneClient = new javax.swing.JTextField();
        ajoutEmailClient = new javax.swing.JTextField();
        ajoutPrenomPattente = new javax.swing.JTextField();
        ajoutAdressClient = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        addLantitudeVille = new javax.swing.JTextField();
        addLongitudeVille = new javax.swing.JTextField();
        addNomVille = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        deleteVille = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        updateVille = new javax.swing.JComboBox<>();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel26 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        update = new javax.swing.JComboBox<>();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jButton27 = new javax.swing.JButton();
        jTextField27 = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jButton28 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        ajoutBuild = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ajoutNombreEtage = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        ajoutlongitude = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        ajoutSurfaceBuild = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        ajoutAdressBuild = new javax.swing.JTextField();
        jButton17 = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        ajoutlantitude = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel30 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        ajoutVilleWork = new javax.swing.JComboBox<>();
        jComboBox12 = new javax.swing.JComboBox<>();
        jComboBox13 = new javax.swing.JComboBox<>();
        jComboBox14 = new javax.swing.JComboBox<>();
        jButton32 = new javax.swing.JButton();
        jPanel31 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jComboBox17 = new javax.swing.JComboBox<>();
        jComboBox18 = new javax.swing.JComboBox<>();
        jComboBox19 = new javax.swing.JComboBox<>();
        jComboBox20 = new javax.swing.JComboBox<>();
        jButton37 = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        jComboBox21 = new javax.swing.JComboBox<>();
        jButton38 = new javax.swing.JButton();
        jPanel29 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        jTabbedPane9 = new javax.swing.JTabbedPane();
        jTabbedPane10 = new javax.swing.JTabbedPane();
        jPanel34 = new javax.swing.JPanel();
        jComboBox15 = new javax.swing.JComboBox<>();
        jButton35 = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        jButton36 = new javax.swing.JButton();
        jComboBox16 = new javax.swing.JComboBox<>();
        jPanel14 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        locationVille = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        locationBuilding = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        locationEtage = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        locationWorkSpace = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        montant = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel59 = new javax.swing.JLabel();
        dateFin = new com.toedter.calendar.JDateChooser();
        dateDebut = new com.toedter.calendar.JDateChooser();
        dateCreation = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        remplirWork = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        remplirEtage = new javax.swing.JComboBox<>();
        jPanel36 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jButton39 = new javax.swing.JButton();
        reserveWork = new javax.swing.JComboBox<>();
        dateFinReservation = new com.toedter.calendar.JDateChooser();
        dateDebutReservation = new com.toedter.calendar.JDateChooser();
        heureFin = new javax.swing.JTextField();
        heureDebut = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setBackground(new java.awt.Color(236, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Inscription");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 120, -1));

        jButton3.setBackground(new java.awt.Color(236, 255, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Ville");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 120, -1));

        jButton4.setBackground(new java.awt.Color(236, 255, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Location");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 120, -1));

        jButton13.setBackground(new java.awt.Color(236, 255, 255));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton13.setText("Gerer");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 120, -1));

        jButton18.setBackground(new java.awt.Color(236, 255, 255));
        jButton18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton18.setText("Se connecter");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        jButton20.setBackground(new java.awt.Color(236, 255, 255));
        jButton20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton20.setText("Client");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 120, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ihm/icon/LOGO (2).PNG"))); // NOI18N
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 100));

        jButton40.setBackground(new java.awt.Color(236, 255, 255));
        jButton40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton40.setText("Réservation");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 120, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 570));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 172, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Building", "nombre d'etages", "surface", "adress"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, 240));

        jTabbedPane1.addTab("tab1", jPanel3);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox2MouseClicked(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 220, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Numero", "Type", "surface"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 340, 230));

        planEtage.setBackground(new java.awt.Color(158, 231, 249));
        planEtage.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        planEtage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        planEtage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                " Rayon Gauche", " Rayon Droit"
            }
        ));
        jScrollPane7.setViewportView(planEtage);
        if (planEtage.getColumnModel().getColumnCount() > 0) {
            planEtage.getColumnModel().getColumn(0).setResizable(false);
        }

        jPanel4.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 330, 120));

        jPanel37.setLayout(new java.awt.GridLayout());
        jPanel4.add(jPanel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 330, 340, 250));

        jTabbedPane1.addTab("tab2", jPanel4);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup1.add(radioIndividu);
        radioIndividu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        radioIndividu.setText("Client Physique ");
        radioIndividu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioIndividuActionPerformed(evt);
            }
        });
        jPanel5.add(radioIndividu, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, -1));

        buttonGroup1.add(radioEntreprise);
        radioEntreprise.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        radioEntreprise.setText("Client Moral");
        radioEntreprise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioEntrepriseActionPerformed(evt);
            }
        });
        jPanel5.add(radioEntreprise, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, -1, -1));

        jLabel1.setText("Adress:");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, -1, -1));

        jLabel2.setText("Prénom:");
        jPanel5.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        jLabel3.setText("Email:");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, -1, -1));

        jLabel4.setText("Sexe:");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, -1, -1));

        jLabel5.setText("Nom:");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, -1, -1));

        jLabel6.setText("Téléphone");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, -1, -1));

        buttonGroup2.add(female);
        female.setText("Female");
        jPanel5.add(female, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, -1, -1));

        buttonGroup2.add(male);
        male.setText("Male\n");
        male.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleActionPerformed(evt);
            }
        });
        jPanel5.add(male, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, -1, -1));

        ajoutNomFormejuridique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutNomFormejuridiqueActionPerformed(evt);
            }
        });
        jPanel5.add(ajoutNomFormejuridique, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, 100, -1));
        jPanel5.add(ajoutTelephoneClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 250, 120, -1));
        jPanel5.add(ajoutEmailClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, 130, -1));
        jPanel5.add(ajoutPrenomPattente, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 120, -1));

        ajoutAdressClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutAdressClientActionPerformed(evt);
            }
        });
        jPanel5.add(ajoutAdressClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 120, -1));

        jButton19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton19.setText("S'inscrire");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 360, -1, 30));

        jTabbedPane1.addTab("tab3", jPanel5);

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setForeground(new java.awt.Color(204, 204, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setBackground(new java.awt.Color(227, 226, 226));
        jButton5.setText("Client");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        jButton6.setBackground(new java.awt.Color(227, 226, 226));
        jButton6.setText("Ville");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, 20));

        jButton7.setBackground(new java.awt.Color(227, 226, 226));
        jButton7.setText("Building");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 19, -1, -1));

        jButton8.setBackground(new java.awt.Color(227, 226, 226));
        jButton8.setText("WorkSpace");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, -1));

        jPanel11.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 60));

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(153, 153, 153));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton9.setText("Supprimer");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 100, -1));

        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton10.setText("Modifier");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 90, -1));

        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton11.setText("Ajouter");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 90, -1));

        jPanel13.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 700));

        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setText("Longitude:");
        jPanel17.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, -1, -1));

        jLabel9.setText("Nom:");
        jPanel17.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, -1, -1));

        jLabel10.setText("Lantitude:");
        jPanel17.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, -1, -1));

        addLantitudeVille.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLantitudeVilleActionPerformed(evt);
            }
        });
        jPanel17.add(addLantitudeVille, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 130, 20));

        addLongitudeVille.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLongitudeVilleActionPerformed(evt);
            }
        });
        jPanel17.add(addLongitudeVille, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 130, 20));

        addNomVille.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNomVilleActionPerformed(evt);
            }
        });
        jPanel17.add(addNomVille, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 130, 20));

        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton12.setText("Ajouter");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel17.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 90, 30));

        jTabbedPane3.addTab("tab1", jPanel17);

        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.add(deleteVille, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 170, -1));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Supprimer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel18.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, 140, 30));

        jTabbedPane3.addTab("tab2", jPanel18);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setText("Nom :");
        jPanel6.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 43, -1));

        jLabel29.setText("Longitude :");
        jPanel6.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 67, -1));

        jLabel30.setText("Latitude :");
        jPanel6.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 57, -1));

        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 146, -1));
        jPanel6.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 146, -1));
        jPanel6.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 146, -1));

        jButton26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton26.setText("Confirmer modification");
        jPanel6.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 241, -1, 30));

        jPanel6.add(updateVille, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 30, 140, -1));

        jTabbedPane3.addTab("tab3", jPanel6);

        jPanel13.add(jTabbedPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 700, 440));

        jTabbedPane2.addTab("tab1", jPanel13);

        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel21.setBackground(new java.awt.Color(153, 153, 153));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton23.setText("Ajouter");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, -1));

        jButton24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton24.setText("Modifier");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 100, -1));

        jButton25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton25.setText("Supprimer");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel21.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 100, -1));

        jPanel20.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 520));

        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setText("Ville :");
        jPanel26.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 66, 43, -1));

        jLabel33.setText("lantitude");
        jPanel26.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 90, -1));

        jLabel34.setText("Nombre d'étages :");
        jPanel26.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 100, -1));

        jLabel35.setText("Surface :");
        jPanel26.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 50, -1));

        jLabel36.setText("Adresse :");
        jPanel26.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 60, -1));

        jPanel26.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));
        jPanel26.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 90, -1));
        jPanel26.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 90, -1));
        jPanel26.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 90, -1));
        jPanel26.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 163, -1));

        jButton27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton27.setText("Confirmer Modification");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel26.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 312, 200, 40));
        jPanel26.add(jTextField27, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 90, -1));

        jTabbedPane4.addTab("tab2", jPanel26);

        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel27.add(jComboBox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 145, -1));

        jButton28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton28.setText("Supprimer");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel27.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 180, 100, 33));

        jTabbedPane4.addTab("tab3", jPanel27);

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ajoutBuild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutBuildActionPerformed(evt);
            }
        });
        jPanel8.add(ajoutBuild, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        jLabel13.setText("Ville :");
        jPanel8.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 43, -1));

        jLabel12.setText("lantitude");
        jPanel8.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        ajoutNombreEtage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutNombreEtageActionPerformed(evt);
            }
        });
        jPanel8.add(ajoutNombreEtage, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 80, -1));

        jLabel14.setText("Nombre d'étages :");
        jPanel8.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));
        jPanel8.add(ajoutlongitude, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 80, -1));

        jLabel15.setText("Surface :");
        jPanel8.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, -1));

        ajoutSurfaceBuild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajoutSurfaceBuildActionPerformed(evt);
            }
        });
        jPanel8.add(ajoutSurfaceBuild, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 80, -1));

        jLabel31.setText("Adresse :");
        jPanel8.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, -1, -1));
        jPanel8.add(ajoutAdressBuild, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 160, 20));

        jButton17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton17.setText("Ajouter");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, 100, 30));

        jLabel57.setText("longitude");
        jPanel8.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));
        jPanel8.add(ajoutlantitude, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 80, -1));

        jTabbedPane4.addTab("tab1", jPanel8);

        jPanel20.add(jTabbedPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, -40, 710, 480));

        jTabbedPane2.addTab("tab2", jPanel20);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel28.setBackground(new java.awt.Color(153, 153, 153));
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton29.setText("Ajouter");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel28.add(jButton29, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 90, 104, -1));

        jButton30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton30.setText("Modifier");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel28.add(jButton30, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 170, 96, -1));

        jButton31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton31.setText("Supprimer");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel28.add(jButton31, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 264, -1, -1));
        jPanel28.add(jTabbedPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 0, 100, 100));

        jPanel1.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 146, 510));

        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setText("Ville :");
        jPanel30.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 47, 43, -1));

        jLabel38.setText("Numéro :");
        jPanel30.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 60, -1));

        jLabel39.setText("Building :");
        jPanel30.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        jLabel40.setText("Surface :");
        jPanel30.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, -1, -1));

        jLabel41.setText("Type :");
        jPanel30.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, -1));

        jLabel42.setText("Etage :");
        jPanel30.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, -1, -1));

        jLabel43.setText("Position :");
        jPanel30.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));
        jPanel30.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 100, -1));
        jPanel30.add(jTextField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 100, -1));
        jPanel30.add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 180, -1));

        jPanel30.add(ajoutVilleWork, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 100, -1));

        jPanel30.add(jComboBox12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 100, -1));

        jPanel30.add(jComboBox13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 100, -1));

        jPanel30.add(jComboBox14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 100, -1));

        jButton32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton32.setText("Ajouter");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        jPanel30.add(jButton32, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 292, 100, 40));

        jTabbedPane6.addTab("tab1", jPanel30);

        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setText("Ville :");
        jPanel31.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 43, -1));

        jLabel45.setText("Building :");
        jPanel31.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        jLabel46.setText("Numéro :");
        jPanel31.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, -1, -1));

        jLabel47.setText("Surface :");
        jPanel31.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        jLabel48.setText("Type :");
        jPanel31.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        jLabel49.setText("Etage :");
        jPanel31.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, -1, -1));

        jLabel50.setText("Position :");
        jPanel31.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, -1, 20));
        jPanel31.add(jTextField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 100, -1));
        jPanel31.add(jTextField23, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 100, -1));

        jTextField24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField24ActionPerformed(evt);
            }
        });
        jPanel31.add(jTextField24, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, 140, 40));

        jPanel31.add(jComboBox17, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 100, -1));

        jPanel31.add(jComboBox18, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 100, -1));

        jPanel31.add(jComboBox19, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 100, -1));

        jPanel31.add(jComboBox20, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 100, -1));

        jButton37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton37.setText("Confimer Modification");
        jPanel31.add(jButton37, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 312, 180, 40));

        jTabbedPane6.addTab("tab2", jPanel31);

        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel32.add(jComboBox21, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 62, 158, 32));

        jButton38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton38.setText("Supprimer");
        jPanel32.add(jButton38, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 219, 123, 33));

        jTabbedPane6.addTab("tab3", jPanel32);

        jPanel1.add(jTabbedPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, -29, 700, 460));

        jTabbedPane2.addTab("tab3", jPanel1);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("tab5", jPanel29);

        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel33.setBackground(new java.awt.Color(153, 153, 153));
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton33.setText("Client Physique");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jPanel33.add(jButton33, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 25, 140, 30));

        jButton34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton34.setText("Client Moral");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        jPanel33.add(jButton34, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 25, 120, 30));

        jPanel22.add(jPanel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 844, 81));
        jPanel22.add(jTabbedPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));
        jPanel22.add(jTabbedPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));
        jPanel22.add(jTabbedPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 80, 40, 220));

        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox15.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel34.add(jComboBox15, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 43, 210, 30));

        jButton35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton35.setText("Supprimer");
        jPanel34.add(jButton35, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 162, 110, 30));

        jTabbedPane10.addTab("tab1", jPanel34);

        jPanel35.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton36.setText("Supprimer");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jPanel35.add(jButton36, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 112, 34));

        jComboBox16.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel35.add(jComboBox16, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 40, 200, 30));

        jTabbedPane10.addTab("tab2", jPanel35);

        jPanel22.add(jTabbedPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 840, 360));

        jTabbedPane2.addTab("tab4", jPanel22);

        jPanel11.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 850, 510));

        jTabbedPane1.addTab("tab4", jPanel11);

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText(" Ville :");
        jPanel14.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 83, 37, -1));

        locationVille.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationVilleActionPerformed(evt);
            }
        });
        jPanel14.add(locationVille, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 80, -1, -1));

        jLabel16.setText("Smart Building :");
        jPanel14.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 123, 94, -1));

        locationBuilding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationBuildingActionPerformed(evt);
            }
        });
        jPanel14.add(locationBuilding, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 120, -1, -1));

        jLabel17.setText("Etage :");
        jPanel14.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 166, 43, -1));

        locationEtage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationEtageActionPerformed(evt);
            }
        });
        jPanel14.add(locationEtage, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 160, -1, -1));

        jLabel18.setText("WorkSpace : ");
        jPanel14.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 203, -1, -1));

        locationWorkSpace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationWorkSpaceActionPerformed(evt);
            }
        });
        jPanel14.add(locationWorkSpace, new org.netbeans.lib.awtextra.AbsoluteConstraints(232, 200, 200, -1));

        jLabel19.setText("Date de création:");
        jPanel14.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, -1, -1));

        jLabel21.setText("Date fin :");
        jPanel14.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, -1, -1));

        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton14.setText("Confirmer");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 420, 120, 40));

        jLabel22.setText("Montant :");
        jPanel14.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, -1, -1));

        montant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                montantActionPerformed(evt);
            }
        });
        jPanel14.add(montant, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 120, -1));

        jLabel58.setText("Etage entier :");
        jPanel14.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, -1, -1));
        jPanel14.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, -1));

        jLabel59.setText("Date début  :");
        jPanel14.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, -1, -1));
        jPanel14.add(dateFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 150, 20));
        jPanel14.add(dateDebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 150, 20));
        jPanel14.add(dateCreation, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 150, 20));

        jTabbedPane1.addTab("tab5", jPanel14);

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setText("Nom de l'utilisateur :");
        jPanel10.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 48, -1, -1));
        jPanel10.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(297, 45, 76, -1));

        jTextField7.setText("Entrer votre nom");
        jPanel10.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 45, 122, -1));

        jLabel26.setText("Mot de passe :");
        jPanel10.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 82, 87, -1));

        jPasswordField1.setText("jPasswordField1");
        jPanel10.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(169, 79, -1, -1));

        jButton15.setText("Se connecter");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, -1, 35));

        jButton16.setText("Mot de passe oublié !");
        jPanel10.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 180, -1));

        jPanel7.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 390, 200));

        jTabbedPane1.addTab("tab6", jPanel7);

        jPanel9.setPreferredSize(new java.awt.Dimension(100, 527));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Consulter Clients :");
        jLabel20.setAlignmentY(2.0F);
        jPanel9.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 160, 50));

        jButton21.setText("Clients Physiques");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton21, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 130, 40));

        jButton22.setText("Clients Morals");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton22, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, 120, 40));

        jTabbedPane1.addTab("tab7", jPanel9);

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nom", "Prénom", "Sexe", "Email", "Téléphone", "Adresse"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jPanel15.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, -1, 221));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel25.setText("Clients Physiques");
        jPanel15.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 200, 30));

        jTabbedPane1.addTab("tab8", jPanel15);

        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Forme Juridique", "Pattente", "Email", "Téléphone", "Adresse"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jPanel19.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 150, 510, 218));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel27.setText("Clients Morals");
        jPanel19.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 190, 30));

        jTabbedPane1.addTab("tab9", jPanel19);

        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        remplirWork.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Numero", "Surface", "Type", "Louer"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        remplirWork.setColumnSelectionAllowed(true);
        jScrollPane5.setViewportView(remplirWork);
        remplirWork.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (remplirWork.getColumnModel().getColumnCount() > 0) {
            remplirWork.getColumnModel().getColumn(3).setPreferredWidth(20);
        }

        jPanel23.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 420, 250));

        jTable6.setBackground(new java.awt.Color(158, 231, 249));
        jTable6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"             1", "               3"},
                {"              2", "                4"},
                {"               5", "                 6"}
            },
            new String [] {
                " Rayon Gauche", " Rayon Droit"
            }
        ));
        jTable6.setRowHeight(50);
        jScrollPane6.setViewportView(jTable6);

        jPanel23.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 220, 180));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel23.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 220, 30));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel23.add(jPanel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 170, -1, 20));

        remplirEtage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remplirEtageActionPerformed(evt);
            }
        });
        jPanel23.add(remplirEtage, new org.netbeans.lib.awtextra.AbsoluteConstraints(132, 60, 170, -1));

        jTabbedPane1.addTab("tab10", jPanel23);

        jPanel36.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel51.setText("WorkSpace :");
        jPanel36.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 75, -1));

        jLabel52.setText("Date Début :");
        jPanel36.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 75, -1));

        jLabel53.setText("Date fin :");
        jPanel36.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 87, 20));

        jLabel54.setText("Heure  début :");
        jPanel36.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 87, -1));

        jLabel55.setText("Heure fin :");
        jPanel36.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 70, -1));

        jButton39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton39.setText("Réserver");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        jPanel36.add(jButton39, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 422, 100, 40));

        jPanel36.add(reserveWork, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 120, -1));
        jPanel36.add(dateFinReservation, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 120, 20));
        jPanel36.add(dateDebutReservation, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 120, 20));

        heureFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heureFinActionPerformed(evt);
            }
        });
        jPanel36.add(heureFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 290, 110, -1));

        heureDebut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heureDebutActionPerformed(evt);
            }
        });
        jPanel36.add(heureDebut, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, 110, -1));

        jLabel56.setText("écrire l'heure sous forme \"hh:mm\"");
        jPanel36.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 246, -1, 10));

        jTabbedPane1.addTab("tab11", jPanel36);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, -40, 890, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radioIndividuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioIndividuActionPerformed
                // TODO add your handling code here:
                jLabel5.setText("Nom");
                jLabel2.setText("Prénom");
                jLabel4.setText("Sexe");
                jLabel3.setText("Email");
                jLabel1.setText("Adresse");
                jLabel6.setText("Téléphone");
                jLabel5.setVisible(true);
                jLabel2.setVisible(true);
                jLabel4.setVisible(true);
                male.setVisible(true);
                female.setVisible(true);

    }//GEN-LAST:event_radioIndividuActionPerformed

    private void maleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maleActionPerformed

    private void ajoutNomFormejuridiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutNomFormejuridiqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ajoutNomFormejuridiqueActionPerformed

    private void ajoutAdressClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutAdressClientActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ajoutAdressClientActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
                jTabbedPane1.setSelectedIndex(0);

        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
      
        jTabbedPane1.setSelectedIndex(1);
        int i=jTable1.getSelectedRow();
        TableModel tm=jTable1.getModel();
        long numeroBuilding=Long.parseLong(tm.getValueAt(i, 0).toString());
        System.out.println(numeroBuilding);
        EtageHttp eh=new EtageHttp();
        List<Etage> etages=eh.getEtageFromServer(numeroBuilding);
        System.out.println(etages);
        for(Etage e:etages){
            jComboBox2.addItem(e);
            System.out.println(e.toString());
        }
        
        
    
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        while(d1.getRowCount() > 0){
            d1.removeRow(0);
        }
        remplirTable1();
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void radioEntrepriseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioEntrepriseActionPerformed
       // TODO add your handling code here:
                jLabel5.setText("Forme Juridique");
                jLabel2.setText("Pattente");
                jLabel4.setText("Sexe");
                jLabel3.setText("Email");
                jLabel1.setText("Adresse");
                jLabel6.setText("Téléphone");
                jLabel5.setVisible(true);
                jLabel2.setVisible(true);
                male.setVisible(false);
                female.setVisible(false);
                jLabel4.setVisible(false);
    }//GEN-LAST:event_radioEntrepriseActionPerformed

    private void montantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_montantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_montantActionPerformed

    private void locationWorkSpaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationWorkSpaceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_locationWorkSpaceActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
                jTabbedPane1.setSelectedIndex(5);

    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:
                                jTabbedPane1.setSelectedIndex(6);


    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:
                        jTabbedPane1.setSelectedIndex(8);
               ClientHttp client=new ClientHttp();
               List<ClientEntreprise> clientMorals=client.getClientEntreprise();
               //String formeJuridique=null, pattente=null,email=null,téléphone=null,adresse=null;
               for(ClientEntreprise ce:clientMorals){
                   String formeJuridique=String.valueOf(ce.getFormeJuridique());
                   String pattente=String.valueOf(ce.getPatente());
                   String email=String.valueOf(ce.getEmail());
                   String téléphone=String.valueOf(ce.getTelephone());
                   String adresse=String.valueOf(ce.getAdress());
                   d4.addRow(new Object[]{
                formeJuridique,pattente,email,téléphone,adresse
            });
               }
               
               

    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:
                        jTabbedPane1.setSelectedIndex(7);
                        ClientHttp client=new ClientHttp();
               List<ClientIndividu> clientIndividu=client.getClientIndividuel();
               String nom=null, prenom=null,sexe=null,email=null,telephone=null,adresse=null;
               for(ClientIndividu ci:clientIndividu){
                   nom=String.valueOf(ci.getNom());
                   prenom=String.valueOf(ci.getPrenom());
                   sexe=String.valueOf(ci.getSexe());
                   email=String.valueOf(ci.getEmail());
                   telephone=String.valueOf(ci.getTelephone());
                   adresse=String.valueOf(ci.getAdress());
               }
               d5.addRow(new Object[]{
                nom,prenom,sexe,email,telephone,adresse
            });

    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(4);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowOpened

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:
        jTabbedPane10.setSelectedIndex(1);
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:
        jTabbedPane6.setSelectedIndex(0);
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        String nomVille=(String) ajoutBuild.getSelectedItem();
        VilleHttp vil=new VilleHttp();
        Ville ville=vil.getVille(nomVille);
        
        Long numeroVille=ville.getNumero();
        System.out.println(numeroVille);
        //Integer numBuild=Integer.parseInt(numeroBuild.getSelectedText());
        Integer nombreEtage=Integer.parseInt(ajoutNombreEtage.getText());
        System.out.println(nombreEtage);
        Integer surfaceBuild=Integer.parseInt(ajoutSurfaceBuild.getText());
         System.out.println(surfaceBuild);
        String adressString=ajoutAdressBuild.getText();
         System.out.println(adressString);
        Double lantitudeBuild=Double.parseDouble(ajoutlantitude.getText());
        Double longitudeBuild=Double.parseDouble(ajoutlongitude.getText());
        System.out.println(lantitudeBuild);
        System.out.println(longitudeBuild);
       
        SmartBuildingHtttp sbh = new SmartBuildingHtttp();
        SmartBuilding sb=new SmartBuilding(nombreEtage, surfaceBuild, longitudeBuild, lantitudeBuild, adressString);
        sbh.addSmartBuilding(sb, numeroVille);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void ajoutSurfaceBuildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutSurfaceBuildActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ajoutSurfaceBuildActionPerformed

    private void ajoutNombreEtageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutNombreEtageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ajoutNombreEtageActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:
        jTabbedPane4.setSelectedIndex(2);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:

        String nm=addNomVille.getText();
                System.out.println(nm);
	    	double la=Double.parseDouble(addLantitudeVille.getText());
	    	double lo=Double.parseDouble(addLongitudeVille.getText());
	    	Ville ville=new Ville( nm, la, lo);
	    	
	    	VilleHttp vil= new  VilleHttp();
	    	vil.addVille(ville);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void addNomVilleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNomVilleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addNomVilleActionPerformed

    private void addLongitudeVilleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLongitudeVilleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addLongitudeVilleActionPerformed

    private void addLantitudeVilleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLantitudeVilleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addLantitudeVilleActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String nomVille=(String) deleteVille.getSelectedItem();
        VilleHttp vh=new VilleHttp();
        Ville ville=vh.getVille(nomVille);
        long numeroVille=vh.getNumeroVille(nomVille);
        vh.deleteVille(numeroVille);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        jTabbedPane3.setSelectedIndex(2);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        jTabbedPane3.setSelectedIndex(1);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        jTabbedPane3.setSelectedIndex(0);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:
        jTabbedPane6.setSelectedIndex(1);
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:
        jTabbedPane4.setSelectedIndex(0);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:
        jTabbedPane4.setSelectedIndex(1);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:
        jTabbedPane6.setSelectedIndex(2);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:
        jTabbedPane10.setSelectedIndex(0);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        Client client = null;
        ClientHttp clthttp=new ClientHttp();
        String nom=ajoutNomFormejuridique.getText();
        String prenom=ajoutPrenomPattente.getText();
        String email=ajoutEmailClient.getText();
        String telephone=ajoutTelephoneClient.getText();
        String adress=ajoutAdressClient.getText();
        if(radioIndividu.isSelected()){
            if(male.isSelected()){
                client= new ClientIndividu(email, telephone, adress, nom, prenom,"male");
               
            }
            if(female.isSelected()){
                client= new ClientIndividu(email, telephone, adress, nom, prenom,"female");
               
            }
        }
        if(radioIndividu.isSelected()){
            client=new ClientEntreprise(email, telephone, adress, adress, prenom);
           
        }
         clthttp.addClient(client);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(10);
        
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jTable1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable1AncestorAdded
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTable1AncestorAdded

    private void remplirEtageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remplirEtageActionPerformed
        // TODO add your handling code here:
        remplirTable3();
    }//GEN-LAST:event_remplirEtageActionPerformed

    private void locationVilleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationVilleActionPerformed
        // TODO add your handling code here:
         String nomVille=(String) locationVille.getSelectedItem();
        VilleHttp vil=new VilleHttp();
        Ville ville=vil.getVille(nomVille);
        Long numeroVille=vil.getNumeroVille(nomVille);
        SmartBuildingHtttp hb=new SmartBuildingHtttp();
        List<SmartBuilding> buildings=hb.getSmartBuildingFromServer(numeroVille);
        Long numeroBuilding=null;
        for(SmartBuilding sb:buildings){
             numeroBuilding=sb.getNumero();
        }
        locationBuilding.addItem(numeroBuilding);
    }//GEN-LAST:event_locationVilleActionPerformed

    private void locationBuildingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationBuildingActionPerformed
        // TODO add your handling code here:
        Long numeroBuilding=(Long)locationBuilding.getSelectedItem();
        EtageHttp eh=new EtageHttp();
        List<Etage> etages=eh.getEtageFromServer(numeroBuilding);
        Long numeroEtage = null;
        for(Etage et:etages){
            numeroEtage=et.getNumEtage();
        }
        locationEtage.addItem(numeroEtage);
    }//GEN-LAST:event_locationBuildingActionPerformed

    private void locationEtageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationEtageActionPerformed
        // TODO add your handling code here:
        locationEtage.removeAll();
        Long numeroEtage=(Long)locationEtage.getSelectedItem();
        WorkSpaceHttp wh=new WorkSpaceHttp();
        List<WorkSpace> workSpaces=wh.getWorkSpaceFromServer(numeroEtage);
        Long numeroWorkSpace = null;
        for(WorkSpace ws:workSpaces){
            
            locationWorkSpace.addItem(ws);
        }
        
    }//GEN-LAST:event_locationEtageActionPerformed

    private void ajoutBuildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajoutBuildActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ajoutBuildActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        LocationHttp locaht= new LocationHttp();
        SimpleDateFormat dated=new SimpleDateFormat("yyyy-mm-dd");
        String datedebut=dated.format(dateDebut.getDate());
        System.out.println(datedebut);
        String datefin=dated.format(dateFin.getDate());
        String datecreation=dated.format(dateCreation.getDate());
        Location location=new Location(datecreation, datedebut, datefin);
        WorkSpace workSpace=(WorkSpace)locationWorkSpace.getSelectedItem();
        Long idWork=workSpace.getId();
        System.out.println(idWork);
        locaht.addLocation(location,15,9);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void dateFinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateFinMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_dateFinMouseClicked

    private void dateFinAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_dateFinAncestorAdded
        // TODO add your handling code here:
         montant.setText("200");
    }//GEN-LAST:event_dateFinAncestorAdded

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
        ReservationHttp reserht=new ReservationHttp();
         SimpleDateFormat dated=new SimpleDateFormat("yyyy-mm-dd");
         String datedebut=dated.format(dateDebutReservation.getDate());
         String datefin=dated.format(dateFinReservation.getDate());
         String heuredebut=heureDebut.getText();
         String heurefin=heureFin.getText();
         Reservation reservation= new Reservation(datedebut, datefin, heuredebut, heurefin);
         reserht.addReservation(reservation, 9, 1);
         
         
    }//GEN-LAST:event_jButton39ActionPerformed

    private void heureFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heureFinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_heureFinActionPerformed

    private void heureDebutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heureDebutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_heureDebutActionPerformed

    private void jComboBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseClicked
        // TODO add your handling code here:
        while(d2.getRowCount() > 0){
            d2.removeRow(0);
        }
        while(d6.getRowCount() > 0){
            d6.removeRow(0);
        }
        
        
        remplirTable2();
    }//GEN-LAST:event_jComboBox2MouseClicked
       
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       /* try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>*/

        try{
            UIManager.setLookAndFeel(new FlatLightLaf());
        }catch(Exception ex){
            
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
           
                new Main().setVisible(true);
           
            
        });
        try {
    UIManager.setLookAndFeel( new FlatLightLaf() );
} catch( Exception ex ) {
    System.err.println( "Failed to initialize LaF" );
} 
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addLantitudeVille;
    private javax.swing.JTextField addLongitudeVille;
    private javax.swing.JTextField addNomVille;
    private javax.swing.JTextField ajoutAdressBuild;
    private javax.swing.JTextField ajoutAdressClient;
    private javax.swing.JComboBox<String> ajoutBuild;
    private javax.swing.JTextField ajoutEmailClient;
    private javax.swing.JTextField ajoutNomFormejuridique;
    private javax.swing.JTextField ajoutNombreEtage;
    private javax.swing.JTextField ajoutPrenomPattente;
    private javax.swing.JTextField ajoutSurfaceBuild;
    private javax.swing.JTextField ajoutTelephoneClient;
    private javax.swing.JComboBox<String> ajoutVilleWork;
    private javax.swing.JTextField ajoutlantitude;
    private javax.swing.JTextField ajoutlongitude;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser dateCreation;
    private com.toedter.calendar.JDateChooser dateDebut;
    private com.toedter.calendar.JDateChooser dateDebutReservation;
    private com.toedter.calendar.JDateChooser dateFin;
    private com.toedter.calendar.JDateChooser dateFinReservation;
    private javax.swing.JComboBox<String> deleteVille;
    private javax.swing.JRadioButton female;
    private javax.swing.JTextField heureDebut;
    private javax.swing.JTextField heureFin;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox15;
    private javax.swing.JComboBox<String> jComboBox16;
    private javax.swing.JComboBox<String> jComboBox17;
    private javax.swing.JComboBox<String> jComboBox18;
    private javax.swing.JComboBox<String> jComboBox19;
    private javax.swing.JComboBox<Etage> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox20;
    private javax.swing.JComboBox<String> jComboBox21;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane10;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTabbedPane jTabbedPane9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JComboBox<Long> locationBuilding;
    private javax.swing.JComboBox<Long> locationEtage;
    private javax.swing.JComboBox<String> locationVille;
    private javax.swing.JComboBox<WorkSpace> locationWorkSpace;
    private javax.swing.JRadioButton male;
    private javax.swing.JTextField montant;
    private javax.swing.JTable planEtage;
    private javax.swing.JRadioButton radioEntreprise;
    private javax.swing.JRadioButton radioIndividu;
    private javax.swing.JComboBox<Etage> remplirEtage;
    private javax.swing.JTable remplirWork;
    private javax.swing.JComboBox<String> reserveWork;
    private javax.swing.JComboBox<String> update;
    private javax.swing.JComboBox<String> updateVille;
    // End of variables declaration//GEN-END:variables

    


        
    



}

