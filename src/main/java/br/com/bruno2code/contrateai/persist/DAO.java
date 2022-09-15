package br.com.bruno2code.contrateai.persist;

import br.com.bruno2code.contrateai.api.LogApi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    public static Connection getConexaoMySql() {

        Connection conn = null;
        String endDat = "localhost",
                porDat = "3306",
                datDat = "contrateai",
                usuDat = "contrateai",
                senDat = "h#Ki60bBE4HiS#&#MjOOSHYdQnHtvugk",
                claDat = "org.mariadb.jdbc.Driver",
                urlDat = "jdbc:mariadb://",
                conDat = urlDat + endDat + ":" + porDat + "/" + datDat + "?useSSL=false";
        try {
            Class.forName(claDat);
            conn = DriverManager.getConnection(conDat, usuDat, senDat);
        } catch (ClassNotFoundException | SQLException e) {
            new LogApi().sendLog("DAO", "getConexaoMySql", e.toString());
        }
        return conn;
    }

}
