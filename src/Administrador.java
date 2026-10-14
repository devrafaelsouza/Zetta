import java.io.Serializable;

public class Administrador implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;
    private String senha;

    public Administrador(String nome, String email, String senha) {

        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
