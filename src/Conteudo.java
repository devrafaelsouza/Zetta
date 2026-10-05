public abstract class Conteudo {

    protected String titulo;
    protected String genero;
    protected int ano;
    protected double nota;

    public Conteudo(String titulo, String genero, int ano, double nota){
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public int getAno() {
        return ano;
    }

    public double getNota() {
        return nota;
    }

    public abstract void exibirInformacoes();

    public String getTipo() {

        String tipo = getClass().getSimpleName();

        if (tipo.equals("Serie")) {
            return "Série";
        }

        if (tipo.equals("Musica")) {
            return "Música";
        }

        return tipo;
    }
}
