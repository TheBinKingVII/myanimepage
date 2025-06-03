/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Bookmark;

import Model.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author BINTORO
 */
public class DAOBookmark implements InterfaceDAOBookmark{
    public void insertBookmark(ModelBookmark bookmark) {
        try {
            String query = "INSERT INTO bookmark (user_id, anime_id, anime_title, anime_image_url, catatan) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, bookmark.getIdUser());
            statement.setInt(2, bookmark.getIdAnime());
            statement.setString(3, bookmark.getAnimeTitle());
            statement.setString(4, bookmark.getImgUrlAnime());
            statement.setString(5, bookmark.getCatatan());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Insert bookmark failed: " + e.getLocalizedMessage());
        }
    }
    
    public void updateBookmark(ModelBookmark bookmark) {
        try {
            String query = "UPDATE bookmark SET catatan = ? WHERE user_id = ? AND anime_id = ?";
            PreparedStatement statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, bookmark.getCatatan());
            statement.setInt(2, bookmark.getIdUser());
            statement.setInt(3, bookmark.getIdAnime());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Update bookmark failed: " + e.getLocalizedMessage());
        }
    }
    
    public void deleteBookmark(int userId, int animeId) {
        try {
            String query = "DELETE FROM bookmark WHERE user_id = ? AND anime_id = ?";
            PreparedStatement statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, userId);
            statement.setInt(2, animeId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Delete bookmark failed: " + e.getLocalizedMessage());
        }
    }
    
    public List<ModelBookmark> getBookmarksByUserId(int userId) {
        List<ModelBookmark> bookmarks = new ArrayList<>();
        try {
            String query = "SELECT * FROM bookmark WHERE user_id = ?";
            PreparedStatement statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                ModelBookmark bookmark = new ModelBookmark();
                bookmark.setId(rs.getInt("id"));
                bookmark.setIdUser(rs.getInt("user_id"));
                bookmark.setIdAnime(rs.getInt("anime_id"));
                bookmark.setAnimeTitle(rs.getString("anime_title"));
                bookmark.setImgUrlAnime(rs.getString("anime_image_url"));
                bookmark.setCatatan(rs.getString("catatan"));
                

                bookmarks.add(bookmark);
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Get bookmarks failed: " + e.getLocalizedMessage());
        }

        return bookmarks;
    }

    
}
