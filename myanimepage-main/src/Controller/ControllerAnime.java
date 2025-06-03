/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Anime.DAOAnime;
import Model.Anime.InterfaceDAOAnime;
import Model.Anime.ModelAnime;
import Model.Bookmark.DAOBookmark;
import Model.Bookmark.InterfaceDAOBookmark;
import Model.Bookmark.ModelBookmark;
import View.DashboardView;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author BINTORO
 */
public class ControllerAnime {

    InterfaceDAOAnime daoAnime;
    InterfaceDAOBookmark dAOBookmark;
    List<ModelAnime> listAnime;
    List<ModelBookmark> listBookmark;
    private final DashboardView halamanDashboard;

    public ControllerAnime(DashboardView halamanDashboard){
        this.halamanDashboard = halamanDashboard;
        this.daoAnime = new DAOAnime();
        this.dAOBookmark = new DAOBookmark();
    }

    public void fetchAnime(int page, int id){
        if (page < 1) {
            JOptionPane.showMessageDialog(halamanDashboard, "Tidak bisa ke halaman sebelumnya");
        }else{
            listBookmark = dAOBookmark.getBookmarksByUserId(id);
            listAnime = daoAnime.fetchTopAnime(page); // ambil page 1 misalnya
        // Kirim data ke view
        halamanDashboard.displayAnimeList(listAnime, listBookmark);
        }
        
        
    }
    
    public void searchAnime(String query, int id){
        listAnime = daoAnime.searchAnime(query);
        listBookmark = dAOBookmark.getBookmarksByUserId(id);
        
        halamanDashboard.displayAnimeList(listAnime, listBookmark);
    }
}

