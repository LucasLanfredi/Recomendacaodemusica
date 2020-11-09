import java.util.ArrayList;

public class usuarioclass {
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
	}
	
	public String getNome() {
		return nomeUsuario;
	}
	
	public ArrayList<musiclass> recomendar() {
		ArrayList<musiclass> recomendacoes = new ArrayList<>();
		for(int i = 0; i<= generosPreferidos.size(); i++) {
			recomendacoes.addAll(generosPreferidos.get(1).getMusicas());
		}
		return recomendacoes;
	}

}
