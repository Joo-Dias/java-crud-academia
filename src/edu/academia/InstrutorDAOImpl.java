package edu.academia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class InstrutorDAOImpl implements  InstrutorDAO {

    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/academia?characterEncoding=latin1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "123456";
    private Connection con;

    public InstrutorDAOImpl() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(
                    JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void salvar(Instrutor i) {
        String sql = "INSERT INTO instrutor "
                + "(id, nome, sobrenome, sexo, area) VALUES "
                + "(?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, i.getId());
            stmt.setString(2, i.getNome());
            stmt.setString(3, i.getSobrenome());
            stmt.setString(4, i.getSexo());
            stmt.setString(5, i.getArea());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Instrutor> lerTodos() {
        return pesquisarNome("");
    }

    @Override
    public List<Instrutor> pesquisarNome(String nome) {
        List<Instrutor> lista = new ArrayList<>();
        String sql = "SELECT * FROM instrutor WHERE nome LIKE ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Instrutor i = new Instrutor();
                i.setId( rs.getLong("id") );
                i.setNome( rs.getString("nome") );
                i.setSobrenome( rs.getString("sobrenome") );
                i.setSexo( rs.getString("sexo") );
                i.setArea( rs.getString("area") );
                lista.add(i);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lista;
    }
}
