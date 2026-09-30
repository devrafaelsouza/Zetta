public class Serie extends Conteudo {
    private int temporadas;

    public Serie(String titulo, String genero, int ano, double nota, int temporadas){
        super(titulo, genero, ano, nota);
        this.temporadas = temporadas;
    }

    @Override
    public void exibirInformacoes(){
        System.out.println("Título: " + titulo);
        System.out.println("Gênero: " + genero);
        System.out.println("Ano: " + ano);
        System.out.println("Nota: " + nota);
        System.out.println("Temporadas: " + temporadas);
    }
}
