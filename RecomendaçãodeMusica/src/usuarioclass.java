import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class usuarioclass {
	int Id;
	String nomeUsuario;
	String senha;
	ArrayList<generoMusical> generosPreferidos;
	
	//Cadastro
	public usuarioclass(String nomeUsuario, String senha, ArrayList<generoMusical> generosPreferidos){
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		this.generosPreferidos = generosPreferidos;
		//armazenar no banco de dados
	}
	//proximos logins
	public usuarioclass(String nomeUsuario, String senha){
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
		//pegar generos do banco e id
	}
	
	public String getNome(){
		return nomeUsuario;
	}
	
	public ArrayList<musiclass> recomendar(){
		ArrayList<musiclass> recomendacoes = new ArrayList<>();
		for(int i = 0; i<= generosPreferidos.size(); i++) {
			recomendacoes.addAll(ConnectionFactory.pegarNaoAvaliadas(Id, generosPreferidos.get(i).getId()));
		}
		
		return recomendacoes;
	}

}
