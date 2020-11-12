import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class usuarioclass {
	int Id;
	String nomeUsuario;
	String senha;
	ArrayList<generoMusical> generosPreferidos;
	
	//primerio Login
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
		//iniciar array list
	}
	
	public String getNome(){
		return nomeUsuario;
	}
	
	public ArrayList<musiclass> recomendar(){
		ArrayList<musiclass> recomendacoes = new ArrayList<>();
		for(int i = 0; i<= generosPreferidos.size(); i++) {
			recomendacoes.addAll(generosPreferidos.get(i).getMusicas());
		}
		recomendacoes.addAll(ConnectionFactory.pegarNaoAvaliadas(Id));
		//filtrar nao favoritas
		Set<musiclass> listaDeMusicas = new LinkedHashSet<>();
		listaDeMusicas.addAll(listaDeMusicas);
		recomendacoes.clear();
		recomendacoes.addAll(listaDeMusicas);
		
		return recomendacoes;
	}

}
