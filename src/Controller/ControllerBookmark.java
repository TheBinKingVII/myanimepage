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
    List<ModelAnime> listAnime;
    PopUpView halamanPopUp;
    DashboardView halamanDashboard;
    ModelBookmark bookmark;
    ModelAnime anime;
    ControllerAnime controllerAnime;
    
    
    public ControllerBookmark(PopUpView halamanPopUp, DashboardView halamaDashboard) {
        
        this.halamanPopUp = halamanPopUp;
        this.daoBookmark = new DAOBookmark();
        anime = halamanPopUp.getAnime();
        bookmark = new ModelBookmark();
        bookmark.setIdUser(halamanPopUp.getUserID());
        bookmark.setIdAnime(anime.getId());
        bookmark.setAnimeTitle(anime.getTitle());
        bookmark.setImgUrlAnime(anime.getImageUrl());
        bookmark.setCatatan(halamanPopUp.getCatatan());
        this.halamanDashboard = halamaDashboard; 
        controllerAnime = new ControllerAnime(this.halamanDashboard);
        
    }
    
    public ControllerBookmark(DashboardView halamanDashboard) {
        this.halamanDashboard = halamanDashboard;
        this.daoBookmark = new DAOBookmark();
    }
    
    public void tambahBookmark(){
        
        daoBookmark.insertBookmark(bookmark);
        controllerAnime.fetchAnime(halamanDashboard.getAnimePage(), halamanDashboard.getIdUser());
        
        
    }
    
    public void hapusBookmark(int ID, int IdAnime){
        daoBookmark.deleteBookmark(ID, IdAnime);
    }
    
    
    
    
}
