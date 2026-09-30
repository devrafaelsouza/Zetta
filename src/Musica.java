public class Musica extends Conteudo {

    private String artista;
    private String album;
    private int duracao;

    public Musica(String titulo, String genero, int ano, double nota,
                  String artista, String album, int duracao) {

        super(titulo, genero, ano, nota);

        this.artista = artista;
        this.album = album;
        this.duracao = duracao;
    }

    @Override
    public void exibirInformacoes(){
        System.out.println("Título: " + titulo);
        System.out.println("Artista: " + artista);
        System.out.println("Álbum: " + album);
        System.out.println("Gênero: " + genero);
        System.out.println("Ano: " + ano);
        System.out.println("Nota: " + nota);
        System.out.println("Duração: " + duracao + "Segundos");
    }
}
