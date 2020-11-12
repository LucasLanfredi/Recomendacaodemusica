import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class ConnectionFactory {
	private String usuario = "root";
	private String senha = "";
	private String host = "localhost";
	private String porta = "3306";
	private String bd = "";

	public Connection obterConexao (){
		try{
			Connection c = DriverManager.getConnection(
					"jdbc:mysql://" + host + ":" + porta + "/" + bd,
					usuario,
					senha
					);
			return c;
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static String pegarSenha (String nomeUsuario){
		String sql = "SELECT senha FROM tb_usuarios WHERE nome = ?";
		
		String senhaUsuario = "";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obterConexao()){
			//3: Pré compila o comando
			PreparedStatement ps = c.prepareStatement(sql);
			//4: Executa o comando e guarda
			//o resultado em um ResultSet
			ps.setString(1, nomeUsuario);
			ResultSet rs = ps.executeQuery();
			senhaUsuario = rs.getString(1);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return senhaUsuario;
	}

}