/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.Anime;

import Model.Users.ModelUser;
import java.util.List;

/**
 *
 * @author BINTORO
 */
public interface InterfaceDAOAnime {
    public List<ModelAnime> fetchTopAnime(int page);
    public ModelAnime fetchAnimeById(int Id);
    public List<ModelAnime> searchAnime(String anime);
    
}
