package br.com.bruno2code.contrateai.persist;

import br.com.bruno2code.contrateai.api.LogApi;
import br.com.bruno2code.contrateai.model.MessagePaginated;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CidadeDAO extends DAO {

    public MessagePaginated list(int page, int per_page) {

        ArrayList list = new ArrayList<>();
        int totReg = 0;

        try {
            final String pagination = " LIMIT " + per_page + " OFFSET " + (page * per_page);

            try ( Connection c = getConexaoMySql()) {

                totReg = count(c);
                String sql = "SELECT cidend, locLat, locLgn FROM view_cidades" + pagination;

                PreparedStatement p = c.prepareStatement(sql);
                ResultSet r = p.executeQuery();

                while (r.next()) {
                    Map<String, Object> line = new HashMap<>();
                    line.put("cidEnd", r.getString("cidEnd"));
                    line.put("locLat", r.getString("locLat"));
                    line.put("locLgn", r.getString("locLgn"));
                    list.add(line);
                }
            }
            return new MessagePaginated(true, page, totReg, per_page, list);
        } catch (SQLException e) {
            new LogApi().sendLog("e010comDAO", "list", e.toString());
            return new MessagePaginated(false, e.toString());
        }
    }

    public int count(Connection c) {

        int qtd = 0;

        try {
            ResultSet r = c.prepareStatement("SELECT COUNT(1) AS qtd FROM view_cidades").executeQuery();

            if (r.next()) {
                qtd = r.getInt("qtd");
            }
        } catch (SQLException e) {
            new LogApi().sendLog("CidadeDAO", "count", e.toString());
        }
        return qtd;
    }
}
