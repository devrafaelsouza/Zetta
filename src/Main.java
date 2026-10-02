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

                        while (opcaoUsuario != 6){

                            mostrarMenuUsuario();

                            System.out.print("Escolha uma opção: ");
                            opcaoUsuario = sc.nextInt();

                            switch (opcaoUsuario){

                                case 1:

                                    mostrarCatalogo(catalogo);

                                    System.out.print("\nEscolha um conteúdo: ");
                                    int escolha = sc.nextInt();

                                    if (escolha >= 1 && escolha <= catalogo.size()){

                                        Conteudo selecionado = catalogo.get(escolha - 1);

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

                                    mostrarPerfil(usuarioLogado, sc);

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

                                    System.out.println("\nAdicionar filme");

                                    break;

                                case 2:

                                    System.out.println("\nAdiconar série");

                                    break;

                                case 3:

                                    System.out.println("\nAdicionar música");

                                    break;

                                case 4:

                                    System.out.println("\nRemover conteúdo");

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

    public static void mostrarCatalogo(ArrayList<Conteudo> catalogo){

        System.out.println("\n========== CATÁLOGO ==========");

        for (int i = 0; i < catalogo.size(); i++){

            System.out.println((i + 1) + " - " + catalogo.get(i).getTitulo());

        }
    }

    public static void mostrarMinhaLista(Usuario usuarioLogado){

        System.out.println("\n========== MINHA LISTA ==========");

        if (usuarioLogado.getMinhaLista().isEmpty()){

            System.out.println("Sua lista está vazia!");

        } else {

            for (int i = 0; i < usuarioLogado.getMinhaLista().size(); i++){

                System.out.println((i + 1) + " - " + usuarioLogado.getMinhaLista().get(i).getTitulo());

            }
        }
    }

    public static void pesquisarConteudo (ArrayList<Conteudo> catalogo, Scanner sc, Usuario usuarioLogado) {

        System.out.println("\n========== PESQUISAR ==========");

        System.out.println("1 - Pesquisar por título");
        System.out.println("2 - Pesquisar por gênero");
        System.out.println("3 - Pesquisar por ano");

        System.out.print("Escolha uma opção: ");
        int opcaoPesquisa = sc.nextInt();
        sc.nextLine();

        System.out.println("\nDigite o que deseja procurar: ");
        String pesquisa = sc.nextLine().toLowerCase();

        ArrayList<Conteudo> resultados = new ArrayList<>();

        for (Conteudo conteudo : catalogo) {

            boolean corresponde = false;

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

        System.out.println("\n========== RESULTADOS ==========");

        for (int i = 0; i < resultados.size(); i++){

            System.out.println((i + 1) + " - " + resultados.get(i).getTitulo());
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

    public static void mostrarPerfil (Usuario usuarioLogado, Scanner sc) {

        System.out.println("\n========== MEU PERFIL ==========");

        System.out.println("Nome: " + usuarioLogado.getNome());
        System.out.println("Email: " + usuarioLogado.getEmail());

        System.out.println("\n1 - Alterar nome");
        System.out.println("2 - Alterar email");
        System.out.println("3 - Alterar senha");
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

        } else if (opcao != 0) {
            System.out.println("\nOpção inválida!");
        }
    }

    public static void mostrarHistorico(Usuario usuarioLogado, Scanner sc) {

        System.out.println("\n========== HISTÓRICO ==========");

        if (usuarioLogado.getHistorico().isEmpty()){

            System.out.println("Seu histórico está vazio.");

        } else {

            for (int i = 0; i < usuarioLogado.getHistorico().size(); i++){

                System.out.println((i + 1) + " - " + usuarioLogado.getHistorico().get(i).getTitulo());
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

        System.out.print("Ano: ");
        int ano = sc.nextInt();

        System.out.print("Nota: ");
        double nota = sc.nextDouble();

        System.out.print("Duração em minutos: ");
        int duracao = sc.nextInt();

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

        System.out.print("Ano: ");
        int ano = sc.nextInt();

        System.out.print("Nota: ");
        double nota = sc.nextDouble();

        System.out.print("Número de temporadas: ");
        int temporadas = sc.nextInt();

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

        System.out.print("Ano: ");
        int ano = sc.nextInt();

        System.out.print("Nota: ");
        double nota = sc.nextDouble();

        sc.nextLine();

        System.out.print("Artista: ");
        String artista = sc.nextLine();

        System.out.print("Álbum: ");
        String album = sc.nextLine();

        System.out.print("Duração em segundos: ");
        int duracao = sc.nextInt();

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

        mostrarCatalogo(catalogo);

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

        Conteudo conteudoSelecionado = catalogo.get(escolha - 1);

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