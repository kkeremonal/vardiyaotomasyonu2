
package UiDesing;

import DataBase.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Vardiyalar extends javax.swing.JFrame {
Hashtable<String,String> personelTcno=new Hashtable<>();
    
    public Vardiyalar() {
        initComponents();
        nobetListe();
    }
    Connection conn = null;
    ResultSet rs,rs1;
    Statement statement;
     public void tableBosalt(javax.swing.JTable tb) {
        DefaultTableModel dm = (DefaultTableModel) tb.getModel();
        int rowCount = dm.getRowCount();

        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }
     public void nobetListe()
    {
        SimpleDateFormat sekil = new SimpleDateFormat("MM.yyyy");
                 Date   tarih = new Date();
        tableBosalt(tb_nobet);
        DefaultTableModel model = (DefaultTableModel) tb_nobet.getModel();
        
        try {
            conn = db.java_db();
            System.out.println(sekil.format(tarih));
            rs = conn.prepareStatement("SELECT *FROM nobettime WHERE NobetTarihi LIKE '%"+sekil.format(tarih)+"%' ").executeQuery();
            while (rs.next()) {
                String tc=String.valueOf(rs.getString("PERSONELTC"));
                String adSoyad ="";
                String nTarihi = String.valueOf(rs.getString("NobetTarihi"));
                String nSaati = String.valueOf(rs.getString("NobetSaati"));
                String vardiya=String.valueOf(rs.getString("VardiyaSistemi"));
                rs1=conn.prepareStatement("SELECT * FROM personeller WHERE tcNo='"+tc+"'").executeQuery();
                String gorev="";
                while(rs1.next()){
                    
                    gorev=String.valueOf(rs1.getString("kadroGorev"));
                    String ad= String.valueOf(rs1.getString("adi"));
                    String soyad= String.valueOf(rs1.getString("soyadi"));
                    
                    adSoyad=ad+" "+soyad;
                    personelTcno.put(adSoyad, String.valueOf(rs1.getString("tcNo")));
                }
                if(!adSoyad.equals("") && !gorev.equals("") && !nTarihi.equals("")&& !nSaati.equals("")){
                    Object[] eklenecek = {adSoyad,gorev,vardiya,nTarihi,nSaati};
                model.addRow(eklenecek);
                }
                
            }
            
            
            
        } catch (Exception e) {
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }    
   
    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tb_nobet = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aylık Personel Nöbet Listesi");

        tb_nobet.setFont(new java.awt.Font("sansserif", 3, 12)); // NOI18N
        tb_nobet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Personel Ad Soyad", "Görev", "Vardiya Sistemi", "Tarih", "Saat"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_nobet.setRowHeight(26);
        jScrollPane1.setViewportView(tb_nobet);
        if (tb_nobet.getColumnModel().getColumnCount() > 0) {
            tb_nobet.getColumnModel().getColumn(0).setResizable(false);
            tb_nobet.getColumnModel().getColumn(1).setResizable(false);
            tb_nobet.getColumnModel().getColumn(2).setResizable(false);
            tb_nobet.getColumnModel().getColumn(3).setResizable(false);
            tb_nobet.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); 
        jLabel1.setText("Aylık Nöbet Listesi");

        jButton1.setFont(new java.awt.Font("sansserif", 1, 18)); 
        jButton1.setText("Vardiya Ekle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("sansserif", 1, 18)); 
        jButton2.setText("Anasayfaya Dön");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("sansserif", 1, 18)); 
        jButton3.setText("Vardiya Düzenle");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        VardiyaEkle frame=new VardiyaEkle();
        frame.setVisible(true);
        this.setVisible(false);
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        YoneticiAnaSayfa frame=new YoneticiAnaSayfa();
        frame.setVisible(true);
        this.setVisible(false);
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
         int seciliRow = tb_nobet.getSelectedRow();
         DefaultTableModel dm = (DefaultTableModel) tb_nobet.getModel();
        if (seciliRow == -1) {
            if (tb_nobet.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Vardiyalar Tablosu Boş");
            } else {
                JOptionPane.showMessageDialog(null, "Vardiya Bilgileri Güncellenecek Personeli Seçiniz");
            }
        } else {
                String isimSoyisim = "",personelTc="",vardiyaSistemi="",vardiyaSaati="",vardiyaGunu="";
            try {
                
                conn=db.java_db();
                
                isimSoyisim=String.valueOf(dm.getValueAt(seciliRow, 0)).trim();
                String tc=personelTcno.get(isimSoyisim);
                
                String sql="SELECT * FROM personelvardiya.nobettime WHERE PERSONELTC='"+tc+"'; ";
                rs=conn.prepareStatement(sql).executeQuery();
                
                while(rs.next()){
                    personelTc=tc;
                    vardiyaSistemi=String.valueOf(rs.getString("VardiyaSistemi"));
                    vardiyaGunu=String.valueOf(rs.getString("NobetTarihi"));
                    vardiyaSaati=String.valueOf(rs.getString("NobetSaati"));
                        
                    
                
                }
                
            } catch (Exception e) {
            }finally{
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
            VardiyaDuzenle frame=new VardiyaDuzenle(isimSoyisim,personelTc,vardiyaSistemi,vardiyaGunu,vardiyaSaati);
            frame.setVisible(true);
            this.setVisible(false);
        }
    }

   
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vardiyalar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vardiyalar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vardiyalar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vardiyalar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vardiyalar().setVisible(true);
            }
        });
    }

   
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_nobet;
  
}
