package br.com.bruno2code.contrateai.persist;

import br.com.bruno2code.contrateai.api.LogApi;
import br.com.bruno2code.contrateai.model.MessagePaginated;
import br.com.bruno2code.contrateai.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class e020catDAO extends DAO {

    Util util = new Util();
    final String table = "e020cat";

    public MessagePaginated list(int page, int per_page, String pSitCat, String pNomCat) {

        ArrayList list = new ArrayList<>();
        int totReg = 0;

        try {
            final String pagination = " LIMIT " + per_page + " OFFSET " + (page * per_page);
            String params = " WHERE 1 = 1 \n";
            final String order = " ORDER BY codcat \n";

            if (!util.vazio(pSitCat)) {
                params += " AND sitcat = '" + pSitCat + "' \n";
            }

            if (!util.vazio(pNomCat)) {
                params += " AND UPPER(nomcat) LIKE UPPER('%" + pNomCat + "%') \n";
            }

            try ( Connection c = getConexaoMySql()) {
                totReg = count(c, params);
                String sql = """
                             SELECT 
                                 codcat, 
                                 nomcat, 
                                 endass 
                               FROM e020cat
                             """
                        + params
                        + order
                        + pagination;

                PreparedStatement p = c.prepareStatement(sql);
                ResultSet r = p.executeQuery();

                while (r.next()) {
                    Map<String, Object> line = new HashMap<>();
                    line.put("codCat", r.getInt("codcat"));
                    line.put("nomCat", r.getString("nomcat"));
                    line.put("endAss", r.getString("endass"));
                    list.add(line);
                }
            }
            return new MessagePaginated(true, page, totReg, per_page, list);
        } catch (SQLException e) {
            new LogApi().sendLog("e020catDAO", "list", e.toString());
            return new MessagePaginated(false, e.toString());
        }
    }

    public int count(Connection c, String params) {

        int qtd = 0;

        try {
            ResultSet r = c.prepareStatement("SELECT COUNT(1) AS qtd FROM " + table + params).executeQuery();

            if (r.next()) {
                qtd = r.getInt("qtd");
            }
        } catch (SQLException e) {
            new LogApi().sendLog("e020catDAO", "count", e.toString());
        }
        return qtd;
    }

    public boolean exist(Connection c, int pCodCat) {

        try {
            String sql = "SELECT 1 FROM e020cat WHERE codcat = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setInt(1, pCodCat);
            ResultSet r = p.executeQuery();

            return r.next();

        } catch (SQLException e) {
            new LogApi().sendLog("e020catDAO", "exist", e.toString());
            return false;
        }
    }
}
