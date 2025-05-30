/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.Bookmark;

import java.util.List;

/**
 *
 * @author BINTORO
 */
public interface InterfaceDAOBookmark {

    void insertBookmark(ModelBookmark bookmark);

    void deleteBookmark(int userId, int animeId);

    void updateBookmark(ModelBookmark bookmark);

    List<ModelBookmark> getBookmarksByUserId(int userId);
}
