package br.com.bruno2code.contrateai.persist;

import br.com.bruno2code.contrateai.api.LogApi;
import br.com.bruno2code.contrateai.model.places.PlaceDetails;
import br.com.bruno2code.contrateai.model.places.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class e030detDAO extends DAO {

    public boolean save(PlaceDetails placeDetails) {

        boolean ok = false;

        try {
            final Result obj = placeDetails.getResult();

            try ( Connection c = getConexaoMySql()) {
                if (!exist(c, obj.getPlace_id())) {
                    String sql = """
                                 INSERT INTO e030det
                                   (place_id, adr_address, formatted_address, formatted_phone_number, international_phone_number,
                                     name, url, user_ratings_total, utc_offset, website)
                                   VALUES
                                   (?,?,?,?,?,?,?,?,?,?)""";

                    PreparedStatement p = c.prepareStatement(sql);
                    p.setString(1, obj.getPlace_id());
                    p.setString(2, obj.getAdr_address());
                    p.setString(3, obj.getFormatted_address());
                    p.setString(4, obj.getFormatted_phone_number());
                    p.setString(5, obj.getInternational_phone_number());
                    p.setString(6, obj.getName());
                    p.setString(7, obj.getUrl());
                    p.setInt(8, obj.getUser_ratings_total());
                    p.setInt(9, obj.getUtc_offset());
                    p.setString(10, obj.getWebsite());
                    ok = p.executeUpdate() > 0;
                }
            }
        } catch (SQLException e) {
            new LogApi().sendLog("e030detDAO", "save", e.toString() + ": " + placeDetails.toString());
        }

        return ok;
    }

    public boolean exist(Connection c, String place_id) {

        boolean exist = false;

        try {
            String sql = "SELECT 1 FROM e030det WHERE place_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, place_id);
            ResultSet r = p.executeQuery();
            exist = r.next();
        } catch (SQLException e) {
            new LogApi().sendLog("e030detDAO", "exist", e.toString());
        }
        return exist;
    }

}
