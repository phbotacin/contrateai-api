package br.com.bruno2code.contrateai.persist;

import br.com.bruno2code.contrateai.api.LogApi;
import br.com.bruno2code.contrateai.model.places.Place;
import static br.com.bruno2code.contrateai.persist.DAO.getConexaoMySql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class e030plaDAO extends DAO {

    public boolean save(Connection c, Place place) {

        boolean success = false;

        try {
            if (!exist(c, place.getPlace_id())) {
                String sql = """
                             INSERT INTO
                                  e030pla
                               (place_id, business_status, icon, icon_background_color, icon_mask_base_uri, 
                                 name, rating, reference, scope, user_ratings_total, 
                                 vicinity, compound_code, global_code, location_lat, location_lng,
                                 northeast_lat, northeast_lng, southwest_lat, southwest_lng)
                               VALUES
                               (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)""";

                final PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, place.getPlace_id());
                p.setString(2, place.getBusiness_status());
                p.setString(3, place.getIcon());
                p.setString(4, place.getIcon_background_color());
                p.setString(5, place.getIcon_mask_base_uri());
                p.setString(6, place.getName());
                p.setDouble(7, place.getRating());
                p.setString(8, place.getReference());
                p.setString(9, place.getScope());
                p.setDouble(10, place.getUser_ratings_total());
                p.setString(11, place.getVicinity());
                p.setString(12, place.getPlus_code().getCompound_code());
                p.setString(13, place.getPlus_code().getGlobal_code());
                p.setDouble(14, place.getGeometry().getLocation().getLat());
                p.setDouble(15, place.getGeometry().getLocation().getLng());
                p.setDouble(16, place.getGeometry().getViewport().getNortheast().getLat());
                p.setDouble(17, place.getGeometry().getViewport().getNortheast().getLng());
                p.setDouble(18, place.getGeometry().getViewport().getSouthwest().getLat());
                p.setDouble(19, place.getGeometry().getViewport().getSouthwest().getLng());
                success = p.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            new LogApi().sendLog("e030plaDAO", "save", e.toString());
        }

        return success;

    }

    public boolean exist(Connection c, String place_id) {

        boolean exist = false;

        try {
            String sql = "SELECT 1 FROM e030pla WHERE place_id = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, place_id);
            ResultSet r = p.executeQuery();

            exist = r.next();

        } catch (SQLException e) {
            new LogApi().sendLog("e030plaDAO", "exist", e.toString());
        }
        return exist;
    }

    public List<String> getListPlaceId() {

        List<String> list = new ArrayList();

        try {
            Connection c = getConexaoMySql();
            String sql = """
                         SELECT 
                                  pla.place_id
                              FROM e030pla pla
                              WHERE pla.place_id NOT IN (SELECT place_id FROM e030det WHERE place_id = pla.place_id)
                              GROUP BY pla.place_id""";
            PreparedStatement p = c.prepareStatement(sql);
            ResultSet r = p.executeQuery();

            while (r.next()) {
                list.add(r.getString("place_id"));
            }

        } catch (SQLException e) {
            new LogApi().sendLog("e030detDAO", "getListPlaceId", e.toString());

        }
        return list;
    }

    public ArrayList<Map<String, Object>> getList() {

        ArrayList<Map<String, Object>> list = new ArrayList();

        try {
            try ( Connection c = getConexaoMySql()) {
                String sql = """
                             SELECT 
                                 pla.place_id,
                                 pla.business_status,
                                 pla.icon,
                                 pla.icon_background_color,
                                 pla.name,
                                 pla.rating,
                                 pla.reference,
                                 pla.scope,
                                 pla.user_ratings_total,
                                 pla.vicinity,
                                 pla.compound_code,
                                 pla.global_code,
                                 pla.location_lat,
                                 pla.location_lng,
                                 pla.northeast_lat,
                                 pla.northeast_lng,
                                 pla.southwest_lat,
                                 pla.southwest_lng,
                                 pla.sitPla,
                                 det.adr_address,
                                 det.formatted_address,
                                 det.formatted_phone_number,
                                 det.international_phone_number,
                                 det.name AS name2,
                                 det.url,
                                 det.user_ratings_total,
                                 det.utc_offset,
                                 det.website
                               FROM e030pla pla
                               INNER JOIN e030det det
                                 ON det.place_id = pla.place_id
                             """;

                final ResultSet r = c.prepareStatement(sql).executeQuery();

                while (r.next()) {
                    Map<String, Object> line = new HashMap();
                    line.put("place_id", r.getString("place_id"));
                    line.put("business_status", r.getString("business_status"));
                    line.put("icon", r.getString("icon"));
                    line.put("icon_background_color", r.getString("icon_background_color"));
                    line.put("name", r.getString("name"));
                    line.put("rating", r.getDouble("rating"));
                    line.put("reference", r.getString("reference"));
                    line.put("scope", r.getString("scope"));
                    line.put("user_ratings_total", r.getInt("user_ratings_total"));
                    line.put("vicinity", r.getString("vicinity"));
                    line.put("compound_code", r.getString("compound_code"));
                    line.put("global_code", r.getString("global_code"));
                    line.put("location_lat", r.getDouble("location_lat"));
                    line.put("location_lng", r.getDouble("location_lng"));
                    line.put("northeast_lat", r.getDouble("northeast_lat"));
                    line.put("northeast_lng", r.getDouble("northeast_lng"));
                    line.put("southwest_lat", r.getDouble("southwest_lat"));
                    line.put("southwest_lng", r.getDouble("southwest_lng"));
                    line.put("sitPla", r.getString("sitPla"));
                    line.put("adr_address", r.getString("adr_address"));
                    line.put("formatted_address", r.getString("formatted_address"));
                    line.put("formatted_phone_number", r.getString("formatted_phone_number"));
                    line.put("international_phone_number", r.getString("international_phone_number"));
                    line.put("name2", r.getString("name2"));
                    line.put("url", r.getString("url"));
                    line.put("user_ratings_total", r.getInt("user_ratings_total"));
                    line.put("utc_offset", r.getInt("utc_offset"));
                    line.put("website", r.getString("website"));
                    list.add(line);
                }

            }
        } catch (SQLException e) {
            new LogApi().sendLog("e030detDAO", "list", e.toString());
        }

        return list;
    }
}
