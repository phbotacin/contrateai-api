package br.com.bruno2code.contrateai.persist;

import br.com.bruno2code.contrateai.api.LogApi;
import br.com.bruno2code.contrateai.model.Message;
import br.com.bruno2code.contrateai.security.Credencial;
import br.com.bruno2code.contrateai.security.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.codec.digest.DigestUtils;

public class UsuarioDAO extends DAO {

    public User getLogin(Credencial c) {

        try {
            try ( Connection con = getConexaoMySql()) {
                String sql = """
                             SELECT
                                  pes.codUsu,
                                  pes.nomUsu,
                                  pes.intNet,
                                  pes.fonCel,
                                  pes.senUsu,
                                  pes.datNas,
                                  pes.tipPes,
                                  tip.destip,
                                  pes.sitPes
                               FROM e001pes pes
                               LEFT JOIN e001tip tip
                                 ON tip.codtip = pes.tippes
                               WHERE pes.intNet = ?
                                 AND pes.senUsu = ?""";

                PreparedStatement p = con.prepareStatement(sql);
                p.setString(1, c.getLogin().trim());
                p.setString(2, DigestUtils.sha512Hex(c.getSenha().trim()));
                ResultSet r = p.executeQuery();

                if (r.next()) {
                    User u = new User();
                    u.setCodUsu(r.getInt("codUsu"));
                    u.setNomUsu(r.getString("nomUsu"));
                    u.setIntNet(r.getString("intNet"));
                    u.setFonCel(r.getString("fonCel"));
                    u.setDatNas(r.getDate("datNas"));
                    u.setTipPes(r.getInt("tipPes"));
                    u.setDesTip(r.getString("destip"));
                    u.setSitPes(r.getString("sitPes"));
                    return u;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            new LogApi().sendLog("UsuarioDAO", "getLogin", e.getMessage());
        }
        return null;
    }

    public Message saveUser(User u) {

        u.setTipPes(0);
        u.setSitPes("I");

        try {
            try ( Connection c = getConexaoMySql()) {
                String sql = """
                             INSERT INTO e001pes
                                  (nomUsu, intNet, fonCel, senUsu, datNas, tipPes, sitPes)
                                  VALUES
                                  (?, ?, ?, ?, ?, ?, ?)""";

                PreparedStatement p = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                p.setString(1, u.getNomUsu());
                p.setString(2, u.getIntNet());
                p.setString(3, u.getFonCel());
                p.setString(4, DigestUtils.sha512Hex(u.getSenUsu()));
                p.setDate(5, (Date) u.getDatNas());
                p.setInt(6, u.getTipPes());
                p.setString(7, u.getSitPes());

                if (p.executeUpdate() == 0) {
                    return new Message(false, "Registro não foi salvo, Nenhuma linha foi alterada.");
                }

                ResultSet r = p.getGeneratedKeys();

                if (r.next()) {
                    u.setCodUsu(r.getInt(1));
                    return new Message(true, "Registro salvo com sucesso!", u);
                } else {
                    return new Message(false, "Registro não foi salvo.");
                }
            }
        } catch (SQLException e) {
            new LogApi().sendLog("UsuarioDAO", "saveUser", e.getMessage());

            return new Message(false, e.getMessage());
        }
    }

    public void registraUltLog(String pIntNet) {

        try {
            try ( Connection c = getConexaoMySql()) {
                String sql = "UPDATE e001pes SET ultlog = SYSDATE() WHERE intnet = ?";
                PreparedStatement p = c.prepareStatement(sql);
                p.setString(1, pIntNet);
                p.executeUpdate();
            }
        } catch (SQLException e) {
            new LogApi().sendLog("UsuarioDAO", "registraUltLog", e.getMessage());

        }
    }
}
