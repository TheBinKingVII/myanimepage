/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.ControllerAnime;
import Controller.ControllerBookmark;
import Controller.ControllerUser;
import Model.Anime.ModelAnime;
import Model.Bookmark.ModelBookmark;
import Model.Users.ModelUser;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author BINTORO
 */
public class DashboardView extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard1
     */
    private JPanel animePanel;
    private JPanel bookmarkPanel;
   
    ControllerAnime controller;
    ControllerUser controllerUser; 
    ControllerBookmark controllerBookmark;
    ModelUser dataUser;

    public static int ID;
    int page;

    public DashboardView(int ID, int page) {
        initComponents();
        this.ID = ID;
        this.page = page;
        controller = new ControllerAnime(this);
        controllerUser = new ControllerUser(this);

        System.out.println("User ID " + this.ID);

        animePanel = new JPanel();
        animePanel.setLayout(new BoxLayout(animePanel, BoxLayout.Y_AXIS));
        animePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        animePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        bookmarkPanel = new JPanel();
        bookmarkPanel.setLayout(new BoxLayout(bookmarkPanel, BoxLayout.Y_AXIS));
        bookmarkPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bookmarkPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

// Atur scroll pane langsung
        jScrollPane1.setViewportView(animePanel);
        jScrollPane2.setViewportView(bookmarkPanel);
        controllerBookmark = new ControllerBookmark(this);
        controller.fetchAnime(this.page, this.ID);
        dataUser = controllerUser.fetchUser(this.ID);
        jLabel2.setText(dataUser.getUsername());
        
        
    }

    public void displayAnimeList(List<ModelAnime> animeList, List<ModelBookmark> bookmarkList) {
        animePanel.removeAll();
        bookmarkPanel.removeAll();
        

        for (ModelAnime anime : animeList) {
            JPanel itemPanel = new JPanel(new BorderLayout(10, 0));
            itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));

            // Gambar
            JLabel imageLabel = new JLabel();
            imageLabel.setPreferredSize(new Dimension(100, 140));
            try {
                ImageIcon icon = new ImageIcon(new java.net.URL(anime.getImageUrl()));
                Image scaledImage = icon.getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            } catch (Exception e) {
                imageLabel.setText("No Image");
            }

            // Info
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

            JLabel titleLabel = new JLabel("<html><b>" + anime.getTitle() + "</b></html>");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

            JLabel ratingLabel = new JLabel("Rating: " + anime.getRating());
            ratingLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

            String synopsis = anime.getSynopsis();
            if (synopsis != null && synopsis.length() > 250) {
                synopsis = synopsis.substring(0, 250) + "...";
            }
            JLabel synopsisLabel = new JLabel("<html><p style='width:600px;'>" + synopsis + "</p></html>");
            synopsisLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

            JButton bookmarkButton;
            boolean isBookmarked = false;

            for (ModelBookmark bookmark : bookmarkList) {
                if (anime.getId() == bookmark.getIdAnime()) {
                    isBookmarked = true;
                    break;
                }
            }

            if (isBookmarked) {
                bookmarkButton = new JButton("Remove Bookmark");
                bookmarkButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int input = JOptionPane.showConfirmDialog(
                                null,
                                "Remove Bookmark " + anime.getTitle() + "?",
                                "Hapus Mahasiswa",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (input == 0) {

                            controllerBookmark.hapusBookmark(ID, anime.getId());
                            controller.fetchAnime(page, ID);
//                          
//                            
//                            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");
//
//                            
//                            showAllDosen();
                        }
                    }

                });
            } else {

                bookmarkButton = new JButton("Bookmark");
                bookmarkButton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int userID = DashboardView.this.ID;
                        System.out.println("ID User " + userID);
                        PopUpView popUp = new PopUpView(anime, userID, DashboardView.this);
                        popUp.setVisible(true);

                    }

                });
            }

            infoPanel.add(titleLabel);
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(ratingLabel);
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(synopsisLabel);
            infoPanel.add(Box.createVerticalStrut(5));
            infoPanel.add(bookmarkButton);

            itemPanel.add(imageLabel, BorderLayout.WEST);
            itemPanel.add(infoPanel, BorderLayout.CENTER);
            itemPanel.setPreferredSize(new Dimension(1800, 160)); // atau nilai besar lainnya

            animePanel.add(itemPanel);
        }

        for (ModelBookmark bookmark : bookmarkList) {
            JPanel itemPanel2 = new JPanel(new BorderLayout(10, 0));
            itemPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            itemPanel2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));

            // Gambar
            JLabel imageLabel2 = new JLabel();
            imageLabel2.setPreferredSize(new Dimension(100, 140));
            try {
                ImageIcon icon2 = new ImageIcon(new java.net.URL(bookmark.getImgUrlAnime()));
                Image scaledImage = icon2.getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
                imageLabel2.setIcon(new ImageIcon(scaledImage));
            } catch (Exception e) {
                imageLabel2.setText("No Image");
            }

            // Info
            JPanel infoPanel2 = new JPanel();
            infoPanel2.setLayout(new BoxLayout(infoPanel2, BoxLayout.Y_AXIS));

            JLabel titleLabel = new JLabel("<html><b>" + bookmark.getAnimeTitle() + "</b></html>");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

//            JLabel ratingLabel = new JLabel("Rating: " + anime.getRating());
//            ratingLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            String catatan = bookmark.getCatatan();
            if (catatan != null && catatan.length() > 250) {
                catatan = catatan.substring(0, 250) + "...";
            }
            JLabel catatanLabel = new JLabel("<html><p style='width:600px;'>" + catatan + "</p></html>");
            catatanLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

            JButton editButton = new JButton("Edit");
            JButton hapusButton = new JButton("Hapus");

            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int userID = DashboardView.this.ID;
                    System.out.println("ID User " + userID);
                    System.out.println("Ini di popup" + bookmark.getId());
                    PopUpView popUp = new PopUpView(bookmark, userID, DashboardView.this);
                    popUp.setVisible(true);
                }

            });

            hapusButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int input = JOptionPane.showConfirmDialog(
                            null,
                            "Remove Bookmark " + bookmark.getAnimeTitle() + "?",
                            "Hapus Mahasiswa",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (input == 0) {
                        

                        controllerBookmark.hapusBookmark(ID, bookmark.getIdAnime());
                        controller.fetchAnime(page, ID);

                        JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

                    }
                }

            });
//            boolean isBookmarked = false;
//
//            for (ModelBookmark bookmark : bookmarkList) {
//                if (anime.getId() == bookmark.getIdAnime()) {
//                    isBookmarked = true;
//                    break;
//                }
//            }
//
//            if (isBookmarked) {
//                bookmarkButton = new JButton("Remove Bookmark");
//                bookmarkButton.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        int input = JOptionPane.showConfirmDialog(
//                                null,
//                                "Remove Bookmark " + anime.getTitle() + "?",
//                                "Hapus Mahasiswa",
//                                JOptionPane.YES_NO_OPTION
//                        );
//
//                        if (input == 0) {
//                           
//                           controllerBookmark.hapusBookmark(ID, anime.getId());
            ////                              
////                            
////                            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");
////
////                            
////                            showAllDosen();
//                        }
//                    }
//
//                });
//            } else {
//                
//                bookmarkButton = new JButton("Bookmark");
//                bookmarkButton.addActionListener(new ActionListener() {
//                    
//                    
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        int userID = DashboardView.this.ID;
//                        System.out.println("ID User " + userID);
//                        PopUpView popUp = new PopUpView(anime, userID);
//                        popUp.setVisible(true);
//                    }
//                    
//
//                });
//            }

            infoPanel2.add(titleLabel);
//            infoPanel2.add(Box.createVerticalStrut(5));
//            infoPanel2.add(ratingLabel);
            infoPanel2.add(Box.createVerticalStrut(5));
            infoPanel2.add(catatanLabel);
//            infoPanel2.add(Box.createVerticalStrut(5));
//            infoPanel2.add(bookmarkButton);
            infoPanel2.add(Box.createVerticalStrut(5));
            infoPanel2.add(editButton);
            infoPanel2.add(Box.createVerticalStrut(5));
            infoPanel2.add(hapusButton);

            itemPanel2.add(imageLabel2, BorderLayout.WEST);
            itemPanel2.add(infoPanel2, BorderLayout.CENTER);
            itemPanel2.setPreferredSize(new Dimension(1800, 160)); // atau nilai besar lainnya

            bookmarkPanel.add(itemPanel2);
        }

        animePanel.revalidate();
        animePanel.repaint();
        bookmarkPanel.revalidate();
        bookmarkPanel.repaint();

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
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();
        searchInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1920, 1080));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                Maximize(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("jLabel2");

        jButton1.setText("LogOut");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Profile-PNG-File (2).png"))); // NOI18N
        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(204, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(20, 20, 20))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 161, -1, 617));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.addTab("Anime", jScrollPane1);
        jTabbedPane1.addTab("Bookmark", jScrollPane2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1190, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(312, 202, -1, -1));

        jPanel3.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MyAnimePage");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/vecteezy_three-girls-anime-faces_10385621-ezgif.com-resize.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 768, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(50, 50, 50))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 1508, -1));

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        getContentPane().add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 161, -1, -1));

        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });
        getContentPane().add(nextButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1433, 161, -1, -1));

        prevButton.setText("Prev");
        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });
        getContentPane().add(prevButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1343, 161, -1, -1));

        searchInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchInputActionPerformed(evt);
            }
        });
        getContentPane().add(searchInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 161, 352, 23));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
        this.page += 1;
        controller.fetchAnime(page, this.ID);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void prevButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        // TODO add your handling code here:
        this.page -= 1;
        controller.fetchAnime(page, this.ID);

    }//GEN-LAST:event_prevButtonActionPerformed


    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        controller.searchAnime(searchInput.getText(), this.ID);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void searchInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchInputActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int input = JOptionPane.showConfirmDialog(
                            null,
                            "Anda ingin Logout?",
                            "Log Out",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (input == 0) {
                        
                        LoginView login = new LoginView();
                        login.setVisible(true);
                        this.dispose();

                    }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Maximize(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_Maximize
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_Maximize
    public int getIdUser() {
        return this.ID;
    }

    public int getAnimePage() {
        return this.page;
    }
    

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
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            int ID = this.ID;
            int page = this.page;

            ;
            public void run() {
                new DashboardView(ID, page).setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton prevButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchInput;
    // End of variables declaration//GEN-END:variables
}
