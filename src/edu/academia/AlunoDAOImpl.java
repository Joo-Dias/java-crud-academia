package edu.academia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAOImpl implements AlunoDAO {
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/academia?characterEncoding=latin1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "123456";
    private Connection con;
    public AlunoDAOImpl() {
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
    public void salvar(Aluno a) {
        String sql = "INSERT INTO aluno "
                + "(id, nome, sobrenome, sexo, esporte, dataRegistro) VALUES "
                + "(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, a.getId());
            stmt.setString(2, a.getNome());
            stmt.setString(3, a.getSobrenome());
            stmt.setString(4, a.getSexo());
            stmt.setString(5, a.getEsporte());
            stmt.setDate(6, Date.valueOf(a.getDataRegistro()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    public List<Aluno> lerTodos() {
        return pesquisarNome("");
    }

    @Override
    public List<Aluno> pesquisarNome(String nome) {
        // TODO Auto-generated method stub
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM aluno WHERE nome LIKE ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Aluno a = new Aluno();
                a.setId( rs.getLong("id") );
                a.setNome( rs.getString("nome") );
                a.setSobrenome( rs.getString("sobrenome") );
                a.setSexo( rs.getString("sexo") );
                a.setEsporte( rs.getString("esporte") );
                a.setDataRegistro( rs.getDate("dataRegistro").toLocalDate() );
                lista.add(a);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return lista;
    }
}
