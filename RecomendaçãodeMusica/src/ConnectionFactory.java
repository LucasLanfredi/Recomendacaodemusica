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
			//3: Pr� compila o comando
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
	 * Tabela tb_usuarios(Id_usuario, Nome, Senha, FK_Id_generoPreferio)
	 * Tabel tb_musicas(Id_musica, nome_musica, Id_genero)
	 * tb_avaliacoes(Id_usuario, Id_musica, nota_musica) ----- select Id_musica, nota from a.tb_avaliacoes A LEFT JOIN where not I
	 * tb_genero(Id_genero, nome_genero)
	 * Query criar banco de dados = 
create database projeto;	 
use projeto;

CREATE TABLE tb_genero (
	Id_genero int not null auto_increment,
    nome_genero char(50),
    primary key (Id_genero)
);

CREATE TABLE tb_usuario (
	Id_usuario int not null auto_increment,
    Nome char(100),
    Senha char(16),
    primary key (Id_usuario),
	FK_Id_generoPreferio int ,
	foreign key (FK_Id_generoPreferio) references tb_genero (Id_genero)
);

CREATE TABLE tb_musicas (
	Id_musica int not null auto_increment,
    nome_musica char(100),
    Primary key (Id_musica),
    Id_genero int,
	foreign key (Id_genero) references tb_genero (Id_genero)
);

CREATE TABLE tb_avaliacoes (
	Id_usuario int,
	foreign key (Id_usuario) references tb_usuario (Id_usuario),
    Id_musica int,
    foreign key (Id_musica) references tb_musicas (Id_musica),
    nota_musica int
);

insert into tb_genero (nome_genero) values ("Rock");
insert into tb_genero (nome_genero) values ("Blues");
insert into tb_genero (nome_genero) values ("Jazz");

insert into tb_musicas (nome_musica,Id_genero) values ("All Blues", "5");
insert into tb_musicas (nome_musica,Id_genero) values ("ACDC HELL", "3");
insert into tb_musicas (nome_musica,Id_genero) values ("Blue River", "5");

insert into tb_usuario (Nome,Senha,FK_Id_generoPreferio) values ("Jo�ozinho", "12345","5");

insert into tb_avaliacoes (Id_usuario,Id_musica,nota_musica) values ("1", "1","5");
	 */

}