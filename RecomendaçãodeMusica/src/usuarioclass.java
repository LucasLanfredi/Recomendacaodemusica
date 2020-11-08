public class usuarioclass {
	String nomeUsuario;
	String senha;
	String[] generosPreferidos;
	
	//primerio Login
	public usuarioclass(String nomeUsuario, String senha, String[] generosPreferidos){
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
	
	//login de usuario cadastrado
	public boolean logar(){
		String dbSenha = ConnectionFactory.pegarSenha(); //recebe do banco
		
		if(senha.equals(dbSenha) == true) {
			return true;
		}else {
			return false;
		}
		
	}

}
