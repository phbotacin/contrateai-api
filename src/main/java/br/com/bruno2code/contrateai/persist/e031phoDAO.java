package br.com.bruno2code.contrateai.persist;

import br.com.bruno2code.contrateai.api.LogApi;
import br.com.bruno2code.contrateai.model.places.Photo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class e031phoDAO extends DAO {

    public void save(Connection c, String place_id, Photo photo) {

        try {
            final String sql = """
                         INSERT INTO e031pho
                           (place_id, height, html_attributions, photo_reference, width)
                           VALUES
                           (?,?,?,?,?)""";

            final PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, place_id);
            p.setDouble(2, photo.getHeight());
            p.setString(3, photo.getHtml_attributions().toString());
            p.setString(4, photo.getPhoto_reference());
            p.setDouble(5, photo.getWidth());
            p.executeUpdate();
        } catch (SQLException e) {
            new LogApi().sendLog("e031phoDAO", "save", e.toString());
        }
    }

}
