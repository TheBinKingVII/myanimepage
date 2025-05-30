/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Anime.ModelAnime;
import Model.Bookmark.DAOBookmark;
import Model.Bookmark.InterfaceDAOBookmark;
import Model.Bookmark.ModelBookmark;
import View.DashboardView;
import View.PopUpView;
import java.util.List;

/**
 *
 * @author BINTORO
 */
public class ControllerBookmark {
    
    InterfaceDAOBookmark daoBookmark;
    List<ModelBookmark> listBookmark;
    PopUpView halamanPopUp;
    DashboardView halamanDashboard;
    ModelBookmark bookmark;
    ModelAnime anime;
    
    
    public ControllerBookmark(PopUpView halamanPopUp) {
        
        this.halamanPopUp = halamanPopUp;
        this.daoBookmark = new DAOBookmark();
        anime = halamanPopUp.getAnime();
        bookmark = new ModelBookmark();
        bookmark.setIdUser(halamanPopUp.getUserID());
        bookmark.setIdAnime(anime.getId());
        bookmark.setAnimeTitle(anime.getTitle());
        bookmark.setImgUrlAnime(anime.getImageUrl());
        bookmark.setCatatan(halamanPopUp.getCatatan());
        
        
    }
    
    public ControllerBookmark(DashboardView halamanDashboard) {
        this.halamanDashboard = halamanDashboard;
        this.daoBookmark = new DAOBookmark();
        anime = halamanPopUp.getAnime();
        bookmark = new ModelBookmark();
        bookmark.setIdUser(halamanPopUp.getUserID());
        bookmark.setIdAnime(anime.getId());
        bookmark.setAnimeTitle(anime.getTitle());
        bookmark.setImgUrlAnime(anime.getImageUrl());
        bookmark.setCatatan(halamanPopUp.getCatatan());
        
        
    }
    
    public void tambahBookmark(){
        
        daoBookmark.insertBookmark(bookmark);
        
        
        
    }
    
    public void hapusBookmark(){
        
        daoBookmark.deleteBookmark(bookmark.getIdUser(), bookmark.getIdAnime());
    }
    
    
}
