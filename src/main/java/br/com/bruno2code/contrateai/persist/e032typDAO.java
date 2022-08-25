package br.com.bruno2code.contrateai.persist;

import br.com.bruno2code.contrateai.api.LogApi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class e032typDAO extends DAO {

    public void save(Connection c, String place_id, String type) {

        try {
            if (!exist(c, place_id, type)) {
                final String sql = "INSERT INTO e032typ (place_id, type) VALUES (?,?)";

                final PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, place_id);
                p.setString(2, type);
                p.executeUpdate();
            }
        } catch (SQLException e) {
            new LogApi().sendLog("e032typeDAO", "save", e.toString());
        }

    }

    public boolean exist(Connection c, String place_id, String type) {

        boolean exist = false;

        try {
            final String sql = "SELECT 1 FROM e032typ WHERE place_id = ? AND type = ?";
            final PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, place_id);
            p.setString(2, type);
            final ResultSet r = p.executeQuery();

            exist = r.next();
        } catch (SQLException e) {
            new LogApi().sendLog("e032typeDAO", "exist", e.toString());
        }
        return exist;
    }

    public int preencheDesTyp(Connection c) {
        try {
            String sql = """
                         UPDATE e032typ typ
                           SET typ.destyp = (SELECT destyp 
                                               FROM e032typ 
                                               WHERE type = typ.type 
                                               LIMIT 1)
                           WHERE destyp IS NULL""";

            return c.prepareStatement(sql).executeUpdate();
        } catch (SQLException e) {
            new LogApi().sendLog("e032typeDAO", "preencheDesTyp", e.toString());

            return 0;
        }
    }

    public int buscaCodCat(Connection c, Object place_id) {

        int codCat = 0;

        try {
            String sql = """
                         SELECT codCat
                              FROM e032typ 
                              WHERE sitTyp = 'A'
                                  AND type = (SELECT type FROM e032typ WHERE place_id = ? LIMIT 1)
                              LIMIT 1""";

            final PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, place_id.toString());
            final ResultSet r = p.executeQuery();

            if (r.next()) {
                codCat = r.getInt("codCat");
            }
        } catch (SQLException e) {
            new LogApi().sendLog("e032typeDAO", "preencheDesTyp", e.toString());
        }
        return codCat;
    }

}
