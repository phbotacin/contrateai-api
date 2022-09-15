package br.com.bruno2code.contrateai.persist;

import br.com.bruno2code.contrateai.api.LogApi;
import br.com.bruno2code.contrateai.model.Message;
import br.com.bruno2code.contrateai.model.MessagePaginated;
import br.com.bruno2code.contrateai.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class e010comDAO extends DAO {

    final String table = "e010com com";
    final Util util = new Util();

    public boolean checkPlaceIdExist(Connection c, String place_id) {

        boolean exist = false;
        try {
            String sql = "SELECT 1 FROM " + table + " WHERE codGoo = ?";
            PreparedStatement p = c.prepareStatement(sql);
            p.setString(1, place_id);
            exist = p.executeQuery().next();
        } catch (SQLException e) {
            new LogApi().sendLog("e010comDAO", "checkPlaceIdExist", e.toString());
        }
        return exist;
    }

    public Message saveFromGoogle(Object codUsu) {
        try {
            int qtdSaved = 0;
            ArrayList<Map<String, Object>> places = new e030plaDAO().getList();

            try (Connection c = getConexaoMySql()) {
                for (Map<String, Object> place : places) {
                    Map<String, Object> body = new HashMap();
                    qtdSaved++;

                    body.put("nomCom", place.get("name"));

                    if (!util.vazio(place.get("adr_address").toString())) {
                        body.put("paiEnd", util.spanValue(place.get("adr_address"), "country-name"));
                        body.put("ufsEnd", util.spanValue(place.get("adr_address"), "region"));
                        body.put("cidEnd", util.spanValue(place.get("adr_address"), "locality"));
                        body.put("baiEnd", util.spanValue(place.get("adr_address"), "extended-address"));
                        body.put("ruaEnd", util.spanValue(place.get("adr_address"), "street-address"));
                        body.put("cepEnd", util.spanValue(place.get("adr_address"), "postal-code"));
                    }
                    body.put("desCom", "");
                    body.put("fonFix", place.get("formatted_phone_number"));
                    body.put("zapFon", place.get("formatted_phone_number"));

                    if (place.get("website") != null) {
                        if (place.get("website").toString().contains("facebook")) {
                            body.put("facBok", place.get("website"));
                        } else {
                            body.put("facBok", "");
                        }

                        if (place.get("website").toString().contains("instagram")) {
                            body.put("insGrm", place.get("website"));
                        } else {
                            body.put("insGrm", "");
                        }
                    }

                    body.put("intNet", "");
                    body.put("fotUrl", place.get("icon"));
                    body.put("codCat", new e032typDAO().buscaCodCat(c, place.get("place_id")));
                    body.put("scoOri", place.get("scope"));
                    body.put("urlGoo", place.get("url"));
                    body.put("webSit", place.get("website"));
                    body.put("locLat", place.get("location_lat"));
                    body.put("locLgn", place.get("location_lng"));
                    body.put("codGoo", place.get("place_id"));
                    body.put("codUsu", 0);
                    body.put("catPrd", "");
                    body.put("sitCom", "A");
                    body.put("usuCad", codUsu);
                    save(c, body).toString();
                }
            }
            return new Message(false, qtdSaved + " Registros salvos");
        } catch (SQLException e) {
            new LogApi().sendLog("e010comDAO", "saveFromGoogle", e.toString());
            return new Message(false, e.toString());
        }
    }

    public Message saveFromApp(Map<String, Object> body) {
        try {
            try (Connection c = getConexaoMySql()) {
                return save(c, body);
            }
        } catch (SQLException e) {
            new LogApi().sendLog("e010comDAO", "saveFromApp", e.toString());
            return new Message(false, e.toString());
        }
    }

    public Message save(Connection c, Map<String, Object> body) {
        try {
            if (checkPlaceIdExist(c, body.get("codGoo").toString())) {
                return new Message(false, "Já existe um registor com esse place_id do Google!");
            }

            String sql = """
                    INSERT INTO e010com
                      (nomCom, paiEnd, ufsEnd, cidEnd, baiEnd, ruaEnd,
                         cepEnd, desCom, fonFix, zapFon, facBok, insGrm,
                         intNet, fotUrl, codCat, scoOri, urlGoo, webSit,
                         locLat, locLgn, codGoo, codUsu, catPrd,  sitCom, usuCad)
                         VALUES
                      (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)""";

            PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            p.setObject(1, body.get("nomCom"));
            p.setObject(2, body.get("paiEnd"));
            p.setObject(3, body.get("ufsEnd"));
            p.setObject(4, body.get("cidEnd"));
            p.setObject(5, body.get("baiEnd"));
            p.setObject(6, body.get("ruaEnd"));
            p.setObject(7, body.get("cepEnd"));
            p.setObject(8, body.get("desCom"));
            p.setObject(9, body.get("fonFix"));
            p.setObject(10, body.get("zapFon"));
            p.setObject(11, body.get("facBok"));
            p.setObject(12, body.get("insGrm"));
            p.setObject(13, body.get("intNet"));
            p.setObject(14, body.get("fotUrl"));
            p.setObject(15, body.get("codCat"));
            p.setObject(16, body.get("scoOri"));
            p.setObject(17, body.get("urlGoo"));
            p.setObject(18, body.get("webSit"));
            p.setObject(19, body.get("locLat"));
            p.setObject(20, body.get("locLgn"));
            p.setObject(21, body.get("codGoo"));
            p.setObject(22, body.get("codUsu"));
            p.setObject(23, body.get("catPrd"));
            p.setObject(24, body.get("sitCom"));
            p.setObject(25, body.get("usuCad"));

            if (p.executeUpdate() > 0) {
                ResultSet r = p.getGeneratedKeys();
                if (r.next()) {
                    body.put("codCom", r.getInt(1));
                }
                return new Message(true, "Registro salvo com sucesso!", body);
            } else {
                return new Message(false, "Registro não foi salvo!");
            }
        } catch (SQLException e) {
            new LogApi().sendLog("e010comDAO", "save", e.toString());
            return new Message(false, e.toString());
        }
    }

    public MessagePaginated list(int page, int per_page, int pCodCat, String pNomCom, double pLocLat, double pLocLgn) {

        ArrayList list = new ArrayList<>();
        int totReg = 0;

        try {
            final String pagination = " LIMIT " + per_page + " OFFSET " + (page * per_page);
            final String order = " ORDER BY com.nomCom ASC \n";
            String params = " WHERE com.sitcom = 'A' \n";

            if (!util.vazio(pNomCom)) {
                params += " AND UPPER(com.nomcom) LIKE UPPER('%" + pNomCom + "%')";
            }

            try (Connection c = getConexaoMySql()) {

                if (pCodCat > 1) {
                    if (new e020catDAO().exist(c, pCodCat)) {
                        params += " AND com.codcat = " + pCodCat + "    \n";
                    }
                } 

                params += " AND loclat BETWEEN (" + pLocLat + " - 0.10) AND (" + pLocLat + " + 0.10)\n"
                        + "    AND loclgn BETWEEN (" + pLocLgn + " - 0.10) AND (" + pLocLgn + " + 0.10) \n";

                totReg = count(c, params);
                String sql = """
                        SELECT
                            com.codCom,
                            com.nomCom,
                            com.paiEnd,
                            com.ufsEnd,
                            com.cidEnd,
                            com.baiEnd,
                            com.ruaEnd,
                            com.cepEnd,
                            com.desCom,
                            com.fonFix,
                            com.zapFon,
                            com.facBok,
                            com.insGrm,
                            com.intNet,
                            com.fotUrl,
                            com.codCat,
                            cat.nomCat,
                            com.scoOri,
                            com.urlGoo,
                            com.webSit,
                            com.locLat,
                            com.locLgn,
                            com.codGoo,
                            com.codUsu,
                            com.catPrd,
                            com.sitCom,
                            com.usuCad
                          FROM """ + " " + table
                        + "  LEFT JOIN e020cat cat\n"
                        + "     ON cat.codcat = com.codcat\n"
                        + params
                        + order
                        + pagination;

                PreparedStatement p = c.prepareStatement(sql);
                ResultSet r = p.executeQuery();

                System.out.println(sql);

                while (r.next()) {
                    Map<String, Object> line = new HashMap<>();
                    line.put("codCom", r.getInt("codCom"));
                    line.put("nomCom", r.getString("nomCom"));
                    line.put("paiEnd", r.getString("paiEnd"));
                    line.put("ufsEnd", r.getString("ufsEnd"));
                    line.put("cidEnd", r.getString("cidEnd"));
                    line.put("baiEnd", r.getString("baiEnd"));
                    line.put("ruaEnd", r.getString("ruaEnd"));
                    line.put("cepEnd", r.getString("cepEnd"));
                    line.put("desCom", r.getString("desCom"));
                    line.put("fonFix", r.getString("fonFix"));
                    line.put("zapFon", r.getString("zapFon"));
                    line.put("facBok", r.getString("facBok"));
                    line.put("insGrm", r.getString("insGrm"));
                    line.put("intNet", r.getString("intNet"));
                    line.put("fotUrl", r.getString("fotUrl"));
                    line.put("codCat", r.getInt("codCat"));
                    line.put("nomCat", r.getString("nomCat"));
                    line.put("scoOri", r.getString("scoOri"));
                    line.put("urlGoo", r.getString("urlGoo"));
                    line.put("webSit", r.getString("webSit"));
                    line.put("locLat", r.getDouble("locLat"));
                    line.put("locLgn", r.getDouble("locLgn"));
                    line.put("codGoo", r.getString("codGoo"));
                    line.put("codUsu", r.getInt("codUsu"));
                    line.put("catPrd", r.getString("catPrd"));
                    line.put("sitCom", r.getString("sitCom"));
                    line.put("usuCad", r.getInt("usuCad"));
                    list.add(line);
                }
            }
            return new MessagePaginated(true, page, totReg, per_page, list);
        } catch (SQLException e) {
            new LogApi().sendLog("e010comDAO", "list", e.toString());
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
            new LogApi().sendLog("e010comDAO", "count", e.toString());
        }
        return qtd;
    }
}
