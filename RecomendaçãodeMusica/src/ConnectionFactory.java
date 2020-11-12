import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	
	public static ArrayList<musiclass> pegarNaoAvaliadas(int id_usuario, int id_genero){
		ArrayList<musiclass> musicasNaoAvaliadas = new ArrayList<>();
		//pegar nao avaliadas, nao sei se a query esta certa
		String query = "SELECT id_musica, nome, FROM tb_musicas A LEFT JOIN tb_Avaliacoes B on a.Id_musica = b.Id_musica WHERE Id_usuario != ? AND Id_genero = ?";
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection c = factory.obterConexao()){
			PreparedStatement ps = c.prepareStatement(query);
			
			ps.setInt(1, id_usuario);
			ps.setInt(2, id_genero);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int Id = rs.getInt("id_musica");
				String nome = rs.getString("nome");
				musiclass musica = new musiclass(Id, nome);
				musicasNaoAvaliadas.add(musica);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return musicasNaoAvaliadas;

	}
	/*Banco:
	 * Tabela tb_usuarios(Id, nome, senha)
	 * Tabel tb_musicas(Id, nome, Id_genero)
	 * tb_avaliacoes(Id_usuario, Id_musica, Id_genero, nota)
	 * tb_genero(Id_genero, nome)
	 */

}