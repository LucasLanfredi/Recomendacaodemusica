
public class musicaTeste {
	public static void main(String args[]) {
		String nomeUsuario = ""; //recebe da interface
		String senha = ""; // recebe da interface

		String dbSenha = ConnectionFactory.pegarSenha(nomeUsuario); //recebe do banco

		if(senha.equals(dbSenha) == true) {
			usuarioclass usuario = new usuarioclass(nomeUsuario, senha);
			//proxima tela;
		}else {
			//erro de login;
		}
	}
}
