import java.util.ArrayList;

public class Usuario {

    private String nome;
    private String email;
    private String senha;

    private ArrayList<Conteudo> historico;
    private ArrayList<Conteudo> minhaLista;

    public Usuario(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;

        minhaLista = new ArrayList<>();
        historico = new ArrayList<>();
    }

    public ArrayList<Conteudo> getHistorico() {
        return historico;
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

    public ArrayList<Conteudo> getMinhaLista() {
        return minhaLista;
    }

    public void alterarNome(String novoNome) {
        this.nome = novoNome;
    }

    public void alterarEmail(String novoEmail) {
        this.email = novoEmail;
    }

    public void alterarSenha(String novaSenha){
        this.senha = novaSenha;
    }

    public void adicionarNaLista(Conteudo conteudo){

        if (!estaNaMinhaLista(conteudo)){

            minhaLista.add(conteudo);

            System.out.println("Conteúdo adicionado à sua lista!");

        } else {
            System.out.println("Esse conteúdo já está na sua lista!");
        }
    }

    public void removerDaLista (Conteudo conteudo){

        if (removerConteudoDaLista(conteudo)){

            System.out.println("Conteúdo removido da sua lista!");

        } else {

            System.out.println("Esse conteúdo não está na sua lista!");

        }
    }

    public void adicionarAoHistorico(Conteudo conteudo) {

        if (!historico.contains(conteudo)){

            historico.add(conteudo);
        }
    }

    public void limparHistorico() {

        historico.clear();

        System.out.println("Histórico limpo com sucesso!");
    }

    public boolean estaNaMinhaLista(Conteudo conteudo) {

        return minhaLista.contains(conteudo);
    }

    public boolean removerConteudoDaLista(Conteudo conteudo) {

        if (minhaLista.contains(conteudo)) {

            minhaLista.remove(conteudo);

            return true;
        }

        return false;
    }
}