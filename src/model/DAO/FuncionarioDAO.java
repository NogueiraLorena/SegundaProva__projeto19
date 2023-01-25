package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import model.Funcionario;
import util.Conexao;
		

public class FuncionarioDAO {

  public static Funcionario inserir(String nome,double salario) {
	Funcionario funcionario = null;
	
	Conexao conexao = new Conexao(
			"jdbc:mysql://localhost:3306/lorenaav2?useTimezone=true&serverTimezone=UTC",
			"com.mysql.cj.jdbc.Driver", "root", "raposa");
	

	Connection conec = conexao.obterConexao();

	String sql = "insert into funcionario(nome,salario) values(?,?)";

	try {
		PreparedStatement comando = conec.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
	
	comando.setString(1, nome);
	comando.setDouble(2, salario);

	if(comando.executeUpdate()>0) {
		ResultSet rs = comando.getGeneratedKeys();
		if(rs.next()) {
			int id;
			id = rs.getInt(1);
			funcionario = new Funcionario(id,nome,salario);
		}
		rs.close();
	}
	comando.close();
	conec.close();

} catch (SQLException e) {
	System.out.println("Erro ao inserir no Banco de Dados.");
	System.out.println("Verifique sua instrução SQL.");
    System.out.println("Mensagem: "+e.getMessage());

}

return funcionario;

}

public static List<Funcionario>buscarATodos(){
List<Funcionario> funcionarios = new LinkedList<Funcionario>();

Conexao conexao = new Conexao(
		"jdbc:mysql://localhost:3306/lorenaav2?useTimezone=true&serverTimezone=UTC",
		"com.mysql.cj.jdbc.Driver",
		"root",
		"raposa");
Connection conec = conexao.obterConexao();

String sql = "select * from funcionario";

try {
	Statement comando = conec.createStatement();
	
	ResultSet rs = comando.executeQuery(sql);
	
	while(rs.next()) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(rs.getInt("id"));
		funcionario.setNome(rs.getString("nome"));
		funcionario.setSalario(rs.getDouble("salario"));
		
		funcionarios.add(funcionario);
	}
	rs.close();
	comando.close();
	conec.close();
	
} catch (SQLException e) {
	System.out.println("Erro ao buscar no Banco de Dados.");
	System.out.println("Verifique sua instrução SQL.");
	System.out.println("Mensagem: "+e.getMessage());
}
return funcionarios;

}

public static boolean excluir(int id) {
boolean ok = false;

Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/lorenaav2?useTimezone=true&serverTimezone=UTC",
		"com.mysql.cj.jdbc.Driver",
		"root",
		"raposa");
Connection con = conexao.obterConexao();

String sql = "delete from funcionario where id=?";

try {
	PreparedStatement comando = con.prepareStatement(sql);
	comando.setInt(1, id);
	
	ok = comando.executeUpdate() > 0;
	
	comando.close();
	con.close();
	
} catch (SQLException e) {
	System.out.println("Erro ao excluir do Banco de Dados.");
	System.out.println("Verifique sua instrução SQL.");
	System.out.println("Mensagem: "+e.getMessage());
}
return ok;
}

public static boolean atualizar(int id, String nome,double salario) {
boolean ok = false;

Conexao conexao = new Conexao("jdbc:mysql://localhost:3306/lorenaav2?useTimezone=true&serverTimezone=UTC",
		"com.mysql.cj.jdbc.Driver",
		"root",
		"raposa");
Connection conec = conexao.obterConexao();

String sql = "update funcionario set nome=?, salario=? where id=?";

try {
	PreparedStatement comando = conec.prepareStatement(sql);
	
	comando.setString(1, nome);
	comando.setDouble(2, salario);
	comando.setInt(3, id);

	
	ok = comando.executeUpdate() > 0;
	
	comando.close();
	conec.close();
	
} catch (SQLException e) {
	System.out.println("Erro ao atualizar no Banco de Dados.");
	System.out.println("Verifique sua instrução SQL.");
	System.out.println("Mensagem: "+e.getMessage());
}
return ok;
}



}

//De tudo o que foi feito, essa foi a classe mais dificil de fazer
//na minha opinião














































































