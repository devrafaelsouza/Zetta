public class Filme extends Conteudo{
    private int duracao;

    public Filme(String titulo, String genero, int ano, double nota, int duracao){
        super(titulo, genero, ano, nota);
        this.duracao = duracao;
    }
    @Override
    public void exibirInformacoes(){
        System.out.println("Titulo: " + titulo);
        System.out.println("Gênero: " + genero);
        System.out.println("Ano: " + ano);
        System.out.println("Nota: " + nota);
        System.out.println("Duração: " + duracao + "Minutos");
    }

}


