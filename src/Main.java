import java.util.ArrayList;
import java.util.Scanner;
import java.io.Console;

public class Main{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Conteudo> catalogo = new ArrayList<>();

        Administrador administrador = new Administrador(
                "admin",
                "admin@zetta.com",
                "123456"
        );

        Filme filme = new Filme(
                "Interestelar",
                "Ficção científica",
                2014,
                8.7,
                169
        );

        Serie serie = new Serie(
                "Stranger Things",
                "Ficção científica",
                2016,
                8.6,
                5
        );

        Musica musica = new Musica(
                "Blinding Lights",
                "Pop",
                2019,
                8.5,
                "The Weeknd",
                "After hours",
                200
        );

        catalogo.add(filme);
        catalogo.add(serie);
        catalogo.add(musica);

        int opcao = -1;

        while(opcao != 0){

            mostrarMenuInicial();

            System.out.print("Escolha uma opção: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:

                    criarConta(usuarios, sc);

                    break;

                case 2:
                    Usuario usuarioLogado = iniciarSessao(usuarios, sc);

                    if (usuarioLogado == null) {

                        break;
                    }

                        int opcaoUsuario = -1;
                        boolean contaExcluida = false;

                        while (opcaoUsuario != 6 && !contaExcluida){

                            mostrarMenuUsuario();

                            System.out.print("Escolha uma opção: ");
                            opcaoUsuario = sc.nextInt();

                            switch (opcaoUsuario){

                                case 1:

                                    System.out.println("\n========== FILTROS ==========");
                                    System.out.println("1 - Todos");
                                    System.out.println("2 - Filmes");
                                    System.out.println("3 - Séries");
                                    System.out.println("4 - Músicas");
                                    System.out.println("0 - Voltar");

                                    System.out.println("\nEscolha um filtro: ");
                                    int filtro = sc.nextInt();

                                    if (filtro == 0) {
                                        break;
                                    }

                                    if (filtro < 1 || filtro > 4) {
                                        System.out.println("\nOpção inválida!");
                                        break;
                                    }

                                    ArrayList<Conteudo> catalogoFiltrado = new ArrayList<>();

                                    for (Conteudo conteudo : catalogo) {

                                        if (filtro == 1) {
                                            catalogoFiltrado.add(conteudo);

                                        } else if (filtro == 2 && conteudo instanceof Filme) {
                                            catalogoFiltrado.add(conteudo);

                                        } else if (filtro == 3 && conteudo instanceof Serie) {
                                            catalogoFiltrado.add(conteudo);

                                        } else if (filtro == 4 && conteudo instanceof Musica) {
                                            catalogoFiltrado.add(conteudo);
                                        }
                                    }

                                    if (catalogoFiltrado.isEmpty()) {
                                        System.out.println("\nNenhum conteúdo encontrado para esse filtro!");
                                        break;
                                    }

                                    ArrayList<Conteudo> catalogoOrdenado = mostrarCatalogo(catalogoFiltrado);

                                    System.out.print("\nEscolha um conteúdo: ");
                                    int escolha = sc.nextInt();

                                    if (escolha >= 1 && escolha <= catalogoOrdenado.size()){

                                        Conteudo selecionado = catalogoOrdenado.get(escolha - 1);

                                        usuarioLogado.adicionarAoHistorico(selecionado);

                                        System.out.println("\n========== CONTEÚDO ==========");

                                        selecionado.exibirInformacoes();

                                        System.out.println("\n1 - Adicionar à minha lista");
                                        System.out.println("2 - Voltar");

                                        System.out.print("Escolha uma opção: ");
                                        int opcaoConteudo = sc.nextInt();

                                        if (opcaoConteudo == 1){

                                            usuarioLogado.adicionarNaLista(selecionado);

                                            System.out.println("\nConteúdo adicionado à sua lista!");

                                        }

                                    } else {
                                        System.out.println("\nConteúdo inválido!");
                                    }

                                    break;

                                case 2:

                                    mostrarMinhaLista(usuarioLogado);

                                    if (!usuarioLogado.getMinhaLista().isEmpty()) {

                                        System.out.println("\n0 - Voltar");
                                        System.out.print("Escolha um conteúdo: ");

                                        int escolhaLista = sc.nextInt();

                                        if (escolhaLista >= 1 && escolhaLista <= usuarioLogado.getMinhaLista().size()){

                                            Conteudo conteudoSelecionado = usuarioLogado.getMinhaLista().get(escolhaLista - 1);

                                            System.out.println("\n========== CONTEÚDO ==========");

                                            conteudoSelecionado.exibirInformacoes();

                                            System.out.println("===============================");

                                            System.out.println("\n1 - Remover da minha lista");
                                            System.out.println("0 - Voltar");

                                            System.out.print("Escolha uma opção:");
                                            int opcaoLista = sc.nextInt();

                                            if (opcaoLista == 1) {

                                                usuarioLogado.removerDaLista(conteudoSelecionado);

                                            } else if (opcaoLista != 0){

                                                System.out.println("Opção inválida!");
                                            }

                                        } else if (escolhaLista != 0) {

                                            System.out.println("Opção inválida!");
                                        }

                                    }

                                    System.out.println("=================================\n");

                                    break;

                                case 3:

                                    contaExcluida = mostrarPerfil(usuarioLogado, usuarios, sc);

                                    break;

                                case 4:

                                    sc.nextLine();

                                    pesquisarConteudo(catalogo, sc, usuarioLogado);

                                    break;

                                case 5:

                                    mostrarHistorico(usuarioLogado, sc);

                                    break;

                                case 6:

                                    System.out.println("\nSaindo da conta...");

                                    break;
                            }

                        }

                    break;

                case 3:

                    sc.nextLine();

                    System.out.println("========== ÁREA ADMINISTRATIVA ==========");

                    System.out.print("Email: ");
                    String emailAdmin = sc.nextLine();

                    System.out.print("Senha: ");
                    String senhaAdmin = sc.nextLine();

                    if (administrador.getEmail().equalsIgnoreCase(emailAdmin) && administrador.getSenha().equals(senhaAdmin)) {

                        System.out.println("\nLogin administrativa realizada com sucesso!");
                        System.out.println("Bem vindo, " + administrador.getNome() + "!");

                        int opcaoAdmin = -1;

                        while (opcaoAdmin != 0) {

                            mostrarMenuAdministrador();

                            System.out.print("Escolha uma opção: ");
                            opcaoAdmin = sc.nextInt();

                            switch (opcaoAdmin) {

                                case 1:

                                    adicionarFilme(catalogo, sc);

                                    break;

                                case 2:

                                    adicionarSerie(catalogo, sc);

                                    break;

                                case 3:

                                    adicionarMusica(catalogo, sc);

                                    break;

                                case 4:

                                    removerConteudo(catalogo, sc);

                                    break;

                                case 0:

                                    System.out.println("\nSaindo da área administrativa...");

                                    break;

                                default:

                                    System.out.println("\nOpção inválida!");
                            }
                        }

                    } else {

                        System.out.println("\nEmail ou senha incorretos!");
                    }

                    break;

                case 0:
                    System.out.println("Saindo do Zetta, até mais... ");
                    break;

                default:
                    System.out.println("\n[Opção inválida!]\n");
            }
        }
    }

    public static ArrayList<Conteudo> mostrarCatalogo(ArrayList<Conteudo> catalogo){

        System.out.println("\n========== CATÁLOGO ==========");

        ArrayList<Conteudo> catalogoOrdenado = new ArrayList<>(catalogo);

        catalogoOrdenado.sort((c1, c2) -> Double.compare(c2.getNota(), c1.getNota()));

        for (int i = 0; i < catalogoOrdenado.size(); i++){

            System.out.println((i + 1) + " - " + catalogoOrdenado.get(i).getTitulo() + " [" + catalogoOrdenado.get(i).getTipo().toUpperCase() + "]" + " - Nota: " + catalogoOrdenado.get(i).getNota());

        }
        return catalogoOrdenado;
    }

    public static void mostrarMinhaLista(Usuario usuarioLogado){

        System.out.println("\n========== MINHA LISTA ==========");

        if (usuarioLogado.getMinhaLista().isEmpty()){

            System.out.println("Sua lista está vazia!");

        } else {

            for (int i = 0; i < usuarioLogado.getMinhaLista().size(); i++){

                Conteudo conteudo = usuarioLogado.getMinhaLista().get(i);


                System.out.println((i + 1) + " - " + conteudo.getTitulo() + " [" + conteudo.getTipo().toUpperCase() + "]" + " - Nota: " + conteudo.getNota());

            }
        }
    }

    public static void pesquisarConteudo (ArrayList<Conteudo> catalogo, Scanner sc, Usuario usuarioLogado) {

        System.out.println("\n========== PESQUISAR ==========");

        System.out.println("1 - Pesquisar por título");
        System.out.println("2 - Pesquisar por gênero");
        System.out.println("3 - Pesquisar por ano");
        System.out.println("0 - Voltar");

        System.out.print("Escolha uma opção: ");
        int opcaoPesquisa = sc.nextInt();
        sc.nextLine();

        if (opcaoPesquisa == 0) {
            return;
        }

        if (opcaoPesquisa < 1 || opcaoPesquisa > 3) {
            System.out.println("Opção inválida!");
            return;
        }

        System.out.println("\n========== FILTRAR POR TIPO ==========");
        System.out.println("1 - Todos");
        System.out.println("2 - Filmes");
        System.out.println("3 - Séries");
        System.out.println("4 - Músicas");
        System.out.println("0 - Voltar");

        System.out.print("Escolha um filtro: ");
        int filtroTipo = sc.nextInt();
        sc.nextLine();

        if (filtroTipo == 0) {
            return;
        }

        if (filtroTipo < 1 || filtroTipo > 4) {
            System.out.println("\nOpção inválida!");
            return;
        }

        System.out.println("\nDigite o que deseja procurar: ");
        String pesquisa = sc.nextLine().trim().toLowerCase();

        if (pesquisa.isEmpty()) {
            System.out.println("\nA pesquisa não pode ficar vazia!");
            return;
        }

        ArrayList<Conteudo> resultados = new ArrayList<>();

        for (Conteudo conteudo : catalogo) {

            boolean corresponde = false;

            boolean tipoCorresponde =
                    filtroTipo == 1
                    || (filtroTipo == 2 && conteudo instanceof Filme)
                    || (filtroTipo == 3 && conteudo instanceof Serie)
                    || (filtroTipo == 4 && conteudo instanceof Musica);

            if (!tipoCorresponde) {
                continue;
            }

            if (opcaoPesquisa == 1){

                corresponde = conteudo.getTitulo().toLowerCase().contains(pesquisa);

            } else if (opcaoPesquisa == 2) {

                corresponde = conteudo.getGenero().toLowerCase().contains(pesquisa);

            } else if (opcaoPesquisa == 3) {

                corresponde = String.valueOf(conteudo.getAno()).equals(pesquisa);

            } else {

                System.out.println("Opção inválida!");
                return;
            }

            if (corresponde){

                resultados.add(conteudo);
            }
        }

        if (resultados.isEmpty()) {

            System.out.println("\nNenhum conteúdo encontrado!");
            return;
        }

        resultados.sort((c1, c2) -> Double.compare(c2.getNota(), c1.getNota()));

        System.out.println("\n========== RESULTADOS ==========");

        for (int i = 0; i < resultados.size(); i++){

            System.out.println((i + 1) + " - " + resultados.get(i).getTitulo() + " [" + resultados.get(i).getTipo().toUpperCase() + "]" + " - Nota: " + resultados.get(i).getNota());
        }

        System.out.println("0 - Voltar");

        System.out.print("\nEscolha um conteúdo: ");
        int escolha = sc.nextInt();

        if (escolha == 0) {
            return;
        }

        if (escolha < 1 || escolha > resultados.size()) {

            System.out.println("Opção inválida!");
            return;
        }

        Conteudo selecionado = resultados.get(escolha - 1);

        System.out.println("\n========== CONTEÚDO ==========");
        selecionado.exibirInformacoes();
        System.out.println("===============================");

        System.out.println("\n1 - Adiconar a minha lista");
        System.out.println("2 - Remover da minha lista");
        System.out.println("0 - Voltar");

        System.out.print("Escolha uma opção: ");
        int opcaoResultado = sc.nextInt();

        if (opcaoResultado == 1) {

            usuarioLogado.adicionarNaLista(selecionado);

        } else if (opcaoResultado == 2) {

            usuarioLogado.removerDaLista(selecionado);

        } else if (opcaoResultado != 0) {

            System.out.println("Opção inválida!");
        }
    }

    public static boolean mostrarPerfil (Usuario usuarioLogado, ArrayList<Usuario> usuarios, Scanner sc) {

        System.out.println("\n========== MEU PERFIL ==========");

        System.out.println("Nome: " + usuarioLogado.getNome());
        System.out.println("Email: " + usuarioLogado.getEmail());

        System.out.println("\n1 - Alterar nome");
        System.out.println("2 - Alterar email");
        System.out.println("3 - Alterar senha");
        System.out.println("4 - Excluir conta");
        System.out.println("0 - Voltar");

        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        if (opcao == 1) {

            System.out.print("\nNovo nome: ");
            String novoNome = sc.nextLine().trim();

            if (!novoNome.isEmpty()) {

                usuarioLogado.alterarNome(novoNome);

                System.out.println("\nNome alterado com sucesso!");

            } else {

                System.out.println("\nO nome não pode ficar vazio!");
            }
        } else if (opcao == 2) {

            System.out.print("\nNovo email: ");
            String novoEmail = sc.nextLine().trim();

            if (emailValido(novoEmail)) {

                usuarioLogado.alterarEmail(novoEmail);

                System.out.println("\nEmail cadastrado com sucesso!");

            } else {

                System.out.println("\nDigite um email válido!");
            }

        } else if (opcao == 3) {

            System.out.print("\nNova senha: ");
            String novaSenha = sc.nextLine().trim();

            if (novaSenha.length() >= 6) {

                usuarioLogado.alterarSenha(novaSenha);

                System.out.println("\nSenha alterada com sucesso!");

            } else {

                System.out.println("\nA senha deve ter pelo menos 6 caracteres!");
            }

        } else if (opcao == 4) {

            System.out.println("Tem certeza que deseja excluir sua conta?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            System.out.print("Escolha uma opção: ");
            int confirmacao = sc.nextInt();

            if (confirmacao == 1) {

                usuarios.remove(usuarioLogado);

                System.out.println("\nConta excluída com sucesso!");

                return true;

            } else if (confirmacao == 2) {

                System.out.println("\nOperação cancelada.");

            } else {

                System.out.println("\nOpção inválida!");
            }

        } else if (opcao != 0) {

            System.out.println("\nOpção inválida!");

        } else {

            System.out.println("\nOpção inválida!");
        }

        return false;
    }

    public static void mostrarHistorico(Usuario usuarioLogado, Scanner sc) {

        System.out.println("\n========== HISTÓRICO ==========");

        if (usuarioLogado.getHistorico().isEmpty()){

            System.out.println("Seu histórico está vazio.");

        } else {

            for (int i = 0; i < usuarioLogado.getHistorico().size(); i++){

                Conteudo conteudo = usuarioLogado.getHistorico().get(i);

                System.out.println((i + 1) + " - " + conteudo.getTitulo() + " [" + conteudo.getTitulo() + "]" + " - Nota: " + conteudo.getNota());
            }

            System.out.println("\n0 - Voltar");
            System.out.println("1 - Limpar histórico");
            System.out.println("2 - Ver detalhes de um conteúdo");

            System.out.print("Escolha uma opção: ");
            int opcaoHistorico = sc.nextInt();

            if (opcaoHistorico == 1) {

                System.out.println("Tem certeza que deseja limpar seu histórico?");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");

                System.out.println("Escolha uma opção: ");
                int confirmacao = sc.nextInt();

                if (confirmacao == 1) {

                    usuarioLogado.limparHistorico();

                } else if (confirmacao == 2){

                    System.out.println("\nOperação cancelada.");
                } else {

                    System.out.println("\nOpção inválida!");
                }

            } else if (opcaoHistorico == 2) {

                System.out.println("\nEscolha um conteúdo para ver os detalhes: ");
                int escolha = sc.nextInt();

                if (escolha >= 1 && escolha <= usuarioLogado.getHistorico().size()) {

                    Conteudo conteudoSelecionado = usuarioLogado.getHistorico().get(escolha - 1);

                    System.out.println("\n========== DETALHES ==========");

                    conteudoSelecionado.exibirInformacoes();

                    System.out.println("===============================");

                } else {

                    System.out.println("Opção inválida!");

                }

            } else if (opcaoHistorico != 0) {

                System.out.println("\nOpção inválida!");

            }
        }
    }

    public static void mostrarMenuUsuario() {

        System.out.println("\n==============================");
        System.out.println("          Z E T T A");
        System.out.println("==============================");
        System.out.println("1 - Ver catálogo");
        System.out.println("2 - Minha lista");
        System.out.println("3 - Perfil");
        System.out.println("4 - Pesquisa");
        System.out.println("5 - Histórico");
        System.out.println("6 - Sair da conta");
        System.out.println("==============================");
    }

    public static void criarConta(ArrayList<Usuario> usuarios, Scanner sc) {

        sc.nextLine();

        System.out.println("\n========== CRIAR CONTA ==========");

        System.out.print("\nNome: ");
        String nome = sc.nextLine().trim();

        System.out.print("\nEmail: ");
        String email = sc.nextLine().trim();

        System.out.print("\nSenha: ");
        String senha = sc.nextLine().trim();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {

            System.out.println("\nPreencha todos os campos!");

            return;
        }

        if (!emailValido(email)) {

            System.out.println("\nDigite um email válido!");

            return;
        }

        if (emailJaCadastrado(usuarios, email)) {

            System.out.println("\nEsse email já está cadastrado!");

        } else if (senha.length() < 6){

            System.out.println("\nA senha deve ter pelo menos 6 caracteres!");

            return;

        } else {

            Usuario novoUsuario = new Usuario(nome, email, senha);

            usuarios.add(novoUsuario);

            System.out.println("\nConta criada com sucesso!");
        }
    }

    public static Usuario iniciarSessao(ArrayList<Usuario> usuarios, Scanner sc) {

        sc.nextLine();

        System.out.println("\n========== INICIAR SESSÃO ==========");

        System.out.print("Email: ");
        String email = sc.nextLine();

        if (email.isEmpty()) {

            System.out.println("\nDigite um email!");

            return null;
        }

        Console console = System.console();

        String senha;

        if (console != null){

            senha = new String(console.readPassword("Senha: "));

        } else {

            System.out.println("Senha: ");
            senha = sc.nextLine();
        }

        if (senha.isEmpty()){

            System.out.println("\nDigite uma senha!");

            return null;
        }

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email) && usuario.getSenha().equals(senha)) {

                System.out.println("\nLogin realizado com sucesso!");
                System.out.println("Bem-vindo ao Zetta, " +
                        usuario.getNome() + "!");

                return usuario;
            }
        }

        System.out.println("\nEmail ou senha incorretos!");

        return null;
    }

    public static void mostrarMenuInicial() {

        System.out.println("==============================");
        System.out.println("          Z E T T A");
        System.out.println("==============================");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Iniciar sessão");
        System.out.println("3 - Área administrativa");
        System.out.println("0 - Sair");
        System.out.println("==============================");
    }

    public static boolean emailJaCadastrado(ArrayList<Usuario> usuarios, String email){

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {

                return true;
            }
        }

        return false;
    }

    public static boolean emailValido(String email) {

        return email.contains("@")
                && email.contains(".")
                && email.indexOf("@") < email.lastIndexOf(".");
    }

    public static void mostrarMenuAdministrador() {

        System.out.println("\n==============================");
        System.out.println("      ÁREA ADMINISTRATIVA");
        System.out.println("==============================");
        System.out.println("1 - Adicionar filme");
        System.out.println("2 - Adicionar série");
        System.out.println("3 - Adicionar música");
        System.out.println("4 - Remover conteúdo");
        System.out.println("0 - Sair");
        System.out.println("==============================");
    }

    public static void adicionarFilme(ArrayList<Conteudo> catalogo, Scanner sc) {

        sc.nextLine();

        System.out.println("\n========== ADICIONAR FILME ==========");

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Gênero: ");
        String genero = sc.nextLine();

        int ano;

        do {
            System.out.print("Ano: ");
            ano = sc.nextInt();
            if (ano <= 0) {
                System.out.println("O ano deve ser maior que 0!");
            }
        } while (ano <=0);

        double nota;

        do{
            System.out.print("Nota: ");
            nota = sc.nextDouble();

            if (nota < 0 || nota > 10) {
                System.out.println("A nota deve ser entre 0 e 10!");
            }

        } while (nota < 0 || nota > 10);

        int duracao;

        do {
            System.out.print("Duração em minutos: ");
            duracao = sc.nextInt();

            if (duracao <= 0) {
                System.out.println("A duração deve ser maior do que 0!");
            }

        } while (duracao <= 0);

        Filme novofilme = new Filme(
                titulo,
                genero,
                ano,
                nota,
                duracao
        );

        catalogo.add(novofilme);

        System.out.println("\nFilme adicionado com sucesso!");
    }

    public static void adicionarSerie(ArrayList<Conteudo> catalogo, Scanner sc) {

        sc.nextLine();

        System.out.println("\n========== ADICIONAR SÉRIE ==========");

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Gênero: ");
        String genero = sc.nextLine();

        int ano;

        do{
            System.out.print("Ano: ");
            ano = sc.nextInt();

            if (ano <= 0) {
                System.out.println("O ano deve ser maior que 0!");
            }

        } while (ano <= 0);

        double nota;

        do {
            System.out.print("Nota: ");
            nota = sc.nextDouble();

            if (nota < 0 || nota > 10) {
                System.out.println("A nota deve estar entre 0 e 10!");
            }

        } while (nota < 0 || nota > 10);

        int temporadas;

        do {
            System.out.print("Número de temporadas: ");
            temporadas = sc.nextInt();

            if (temporadas <= 0) {
                System.out.println("O número de temporadas deve ser maior que 0!");
            }

        } while (temporadas <= 0);

        Serie novaSerie = new Serie(
                titulo,
                genero,
                ano,
                nota,
                temporadas
        );

        catalogo.add(novaSerie);

        System.out.println("\nSérie adicionada com sucesso!");
    }

    public static void adicionarMusica(ArrayList<Conteudo> catalogo, Scanner sc) {

        sc.nextLine();

        System.out.println("\n========== ADICIONAR MÚSICA ==========");

        System.out.print("Título: ");
        String titulo = sc.nextLine();

        System.out.print("Gênero: ");
        String genero = sc.nextLine();

        int ano;

        do {
            System.out.print("Ano: ");
            ano = sc.nextInt();

            if (ano <= 0) {
                System.out.println("O ano dve ser maior que 0!");
            }
            
        } while (ano <= 0);

        double nota;

        do {
            System.out.print("Nota: ");
            nota = sc.nextDouble();

            if (nota < 0 || nota > 10) {
                System.out.println("A nota deve ser entre 0  e 10!");
            }

        } while (nota < 0 || nota > 10);

        sc.nextLine();

        System.out.print("Artista: ");
        String artista = sc.nextLine();

        System.out.print("Álbum: ");
        String album = sc.nextLine();

        int duracao;

        do {
            System.out.print("Duração em segundos: ");
            duracao = sc.nextInt();

            if (duracao <= 0) {
                System.out.println("A duração deve ser maior que 0!");
            }

        } while (duracao <= 0);

        Musica novaMusica = new Musica(
                titulo,
                genero,
                ano,
                nota,
                artista,
                album,
                duracao
        );

        catalogo.add(novaMusica);

        System.out.println("\nMúsica adicionada com sucesso!");
    }

    public static void removerConteudo(ArrayList<Conteudo> catalogo, Scanner sc) {

        System.out.println("\n========== REMOVER CONTEÚDO ==========");

        if (catalogo.isEmpty()) {
            System.out.println("O catálogo está vazio!");
            return;
        }

       ArrayList<Conteudo> catalogoOrdenado = mostrarCatalogo(catalogo);

        System.out.println("0 - Voltar");
        System.out.print("\nEscolha um conteúdo: ");

        int escolha = sc.nextInt();

        if (escolha == 0) {
            return;
        }

        if (escolha < 1 || escolha > catalogo.size()) {
            System.out.println("\nOpção inválida!");
            return;
        }

        Conteudo conteudoSelecionado = catalogoOrdenado.get(escolha - 1);

        System.out.println("\nConteúdo selecionado: " + conteudoSelecionado.getTitulo());

        System.out.println("\nTem certeza que deseja remover?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");

        System.out.print("Escolha uma opção: ");
        int confirmacao = sc.nextInt();

        if (confirmacao == 1) {

            catalogo.remove(conteudoSelecionado);

            System.out.println("\nConteúdo removido com sucesso!");

        } else if (confirmacao == 2) {

            System.out.println("\nOperação cancelada.");

        } else {

            System.out.println("\nOpção inválida!");
        }
    }
}