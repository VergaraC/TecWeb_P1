package br.edu.insper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DAO {
	private Connection connection = null;
	
	public DAO() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projeto1?serverTimezone=UTC",
		//connection = DriverManager.getConnection("jdbc:mysql://localhost/projeto1?serverTimezone=UTC",
				"teste", "senha");
	}
	
	public List<Tarefas> getTarefas() throws SQLException {
		List<Tarefas> tarefas = new ArrayList<Tarefas>();
		
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Tarefas ORDER BY data");
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			Tarefas tarefa = new Tarefas();
			tarefa.setId(rs.getInt("id"));
			tarefa.setNome(rs.getNString("nome"));
			tarefa.setTarefa(rs.getNString("tarefa"));
			Calendar data = Calendar.getInstance();
            data.setTime(rs.getDate("data"));
            tarefa.setData(data);
            tarefa.setCategoria(rs.getNString("categoria"));
			tarefas.add(tarefa);
		}
		rs.close();
		stmt.close();
		
		return tarefas;
	}
	
	public void addTodo(Tarefas tarefa) throws SQLException{
		String sql = "INSERT INTO Tarefas (tarefa,nome,data,categoria) VALUES (?,?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, tarefa.getTarefa());
		stmt.setString(2, tarefa.getNome());
		stmt.setDate(3, new java.sql.Date(tarefa.getData().getTimeInMillis()));
		stmt.setString(4, tarefa.getCategoria());

		stmt.execute();
		stmt.close();
	}
	
	public void updateTodo(Tarefas tarefa) throws SQLException{
		String sql = "UPDATE Tarefas SET tarefa=?,nome=?,data=?,categoria=? WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, tarefa.getTarefa());
		stmt.setString(2, tarefa.getNome());
		stmt.setDate(3, new java.sql.Date(tarefa.getData().getTimeInMillis()));
		stmt.setString(4, tarefa.getCategoria());
		stmt.setInt(5, tarefa.getId());
		stmt.execute();
		stmt.close();
	}
	
	public void removeTodo(Integer id) throws SQLException{
		String sql = "DELETE FROM Tarefas WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
	}
	
	public void close() throws SQLException{
		connection.close();
	}
}
