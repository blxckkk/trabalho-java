import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static SistemaBiblioteca sistema = new SistemaBiblioteca();
    private static Scanner entrada;
    
    public static void main(String[] args) {
        configurarEncoding();
        entrada = new Scanner(System.in, StandardCharsets.UTF_8.name());
        
        sistema.carregarDados();
        
        boolean executando = true;
        while (executando) {
            exibirMenuPrincipal();
            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    realizarEmprestimo();
                    break;
                case 2:
                    realizarDevolucao();
                    break;
                case 3:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void realizarEmprestimo() {
        System.out.println("\n--- REALIZAR EMPRÉSTIMO ---");
        
        System.out.print("CPF do usuário: ");
        String cpf = entrada.nextLine().trim();
        
        System.out.print("ISBN do livro: ");
        String isbn = entrada.nextLine().trim();
        
        if (sistema.emprestarLivro(cpf, isbn)) {
            System.out.println("Empréstimo realizado com sucesso!");
        } else {
            System.out.println("Erro: Usuário ou livro não encontrado, ou livro indisponível!");
        }
    }
    
    private static void realizarDevolucao() {
        System.out.println("\n--- REALIZAR DEVOLUÇÃO ---");
        
        System.out.print("CPF do usuário: ");
        String cpf = entrada.nextLine().trim();
        
        System.out.print("ISBN do livro: ");
        String isbn = entrada.nextLine().trim();
        
        if (sistema.devolverLivro(cpf, isbn)) {
            System.out.println("Devolução realizada com sucesso!");
        } else {
            System.out.println("Erro: Usuário ou livro não encontrado, ou livro não estava emprestado!");
        }
    }
    
    private static int lerOpcao() {
        try {
            int valor = Integer.parseInt(entrada.nextLine().trim());
            return valor;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
            
            switch (opcao) {
                case 1:
                    menuAutores();
                    break;
                case 2:
                    menuLivros();
                    break;
                case 3:
                    menuUsuarios();
                    break;
                case 4:
                    menuEmprestimos();
                    break;
                case 5:
                    sistema.salvarDados();
                    System.out.println("\nDados salvos com sucesso!");
                    break;
                case 6:
                    sistema.salvarDados();
                    System.out.println("\nEncerrando o sistema...");
                    executando = false;
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
        }
        
        entrada.close();
    }
    
    private static void configurarEncoding() {
        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.err.println("Erro ao configurar encoding UTF-8");
        }
    }
    
    private static void exibirMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GERENCIAMENTO BIBLIOTECA  ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("1. Gerenciar Autores");
        System.out.println("2. Gerenciar Livros");
        System.out.println("3. Gerenciar Usuários");
        System.out.println("4. Empréstimos e Devoluções");
        System.out.println("5. Salvar Dados");
        System.out.println("6. Sair");
        System.out.print("\nEscolha uma opção: ");
    }
    
    private static void menuAutores() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- MENU AUTORES ---");
            System.out.println("1. Cadastrar Autor");
            System.out.println("2. Listar Autores");
            System.out.println("3. Pesquisar Autor");
            System.out.println("4. Voltar");
            System.out.print("Opção: ");
            
            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    cadastrarAutor();
                    break;
                case 2:
                    sistema.listarAutores();
                    break;
                case 3:
                    pesquisarAutor();
                    break;
                case 4:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void cadastrarAutor() {
        System.out.println("\n--- CADASTRO DE AUTOR ---");
        
        System.out.print("Código do autor: ");
        String codigo = entrada.nextLine().trim();
        
        System.out.print("Nome: ");
        String nome = entrada.nextLine().trim();
        
        System.out.print("Nacionalidade: ");
        String nacionalidade = entrada.nextLine().trim();
        
        Autor autor = new Autor(codigo, nome, nacionalidade);
        
        if (sistema.cadastrarAutor(autor)) {
            System.out.println("Autor cadastrado com sucesso!");
        } else {
            System.out.println("Erro: Já existe um autor com este código!");
        }
    }
    
    private static void pesquisarAutor() {
        System.out.print("\nDigite o código do autor: ");
        String codigo = entrada.nextLine().trim();
        
        Autor autor = sistema.pesquisarAutor(codigo);
        if (autor != null) {
            System.out.println(autor.detalhes());
        } else {
            System.out.println("Autor não encontrado!");
        }
    }
    
    private static void menuLivros() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- MENU LIVROS ---");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Listar Livros");
            System.out.println("3. Pesquisar Livro por ISBN");
            System.out.println("4. Pesquisar Livro por Título");
            System.out.println("5. Voltar");
            System.out.print("Opção: ");
            
            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    sistema.listarLivros();
                    break;
                case 3:
                    pesquisarLivroPorIsbn();
                    break;
                case 4:
                    pesquisarLivroPorTitulo();
                    break;
                case 5:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void cadastrarLivro() {
        System.out.println("\n--- CADASTRO DE LIVRO ---");
        
        if (sistema.getAutores().isEmpty()) {
            System.out.println("Não há autores cadastrados! Cadastre um autor primeiro.");
            return;
        }
        
        System.out.print("ISBN: ");
        String isbn = entrada.nextLine().trim();
        
        System.out.print("Título: ");
        String titulo = entrada.nextLine().trim();
        
        System.out.print("Código do autor: ");
        String codigoAutor = entrada.nextLine().trim();
        
        System.out.print("Ano de publicação: ");
        int ano = lerOpcao();
        
        System.out.print("Categoria: ");
        String categoria = entrada.nextLine().trim();
        
        Livro livro = new Livro(isbn, titulo, codigoAutor, ano, categoria);
        
        if (sistema.cadastrarLivro(livro)) {
            System.out.println("Livro cadastrado com sucesso!");
        } else {
            System.out.println("Erro: ISBN já cadastrado ou autor não encontrado!");
        }
    }
    
    private static void pesquisarLivroPorIsbn() {
        System.out.print("\nDigite o ISBN: ");
        String isbn = entrada.nextLine().trim();
        
        Livro livro = sistema.pesquisarLivro(isbn);
        if (livro != null) {
            System.out.println(livro.detalhes());
        } else {
            System.out.println("Livro não encontrado!");
        }
    }
    
    private static void pesquisarLivroPorTitulo() {
        System.out.print("\nDigite parte do título: ");
        String titulo = entrada.nextLine().trim();
        
        List<Livro> resultado = sistema.pesquisarLivrosPorTitulo(titulo);
        if (resultado.isEmpty()) {
            System.out.println("Nenhum livro encontrado!");
        } else {
            System.out.println("\n--- RESULTADOS DA PESQUISA ---");
            for (Livro livro : resultado) {
                System.out.println(livro);
            }
        }
    }
    
    private static void menuUsuarios() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- MENU USUÁRIOS ---");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Listar Usuários");
            System.out.println("3. Pesquisar Usuário");
            System.out.println("4. Voltar");
            System.out.print("Opção: ");
            
            int opcao = lerOpcao();
            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    sistema.listarUsuarios();
                    break;
                case 3:
                    pesquisarUsuario();
                    break;
                case 4:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void cadastrarUsuario() {
        System.out.println("\n--- CADASTRO DE USUÁRIO ---");
        
        System.out.print("CPF: ");
        String cpf = entrada.nextLine().trim();
        
        System.out.print("Nome: ");
        String nome = entrada.nextLine().trim();
        
        System.out.print("Email: ");
        String email = entrada.nextLine().trim();
        
        System.out.print("Telefone: ");
        String telefone = entrada.nextLine().trim();
        
        Usuario usuario = new Usuario(cpf, nome, email, telefone);
        
        if (sistema.cadastrarUsuario(usuario)) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Erro: CPF já cadastrado!");
        }
    }
    
    private static void pesquisarUsuario() {
        System.out.print("\nDigite o CPF: ");
        String cpf = entrada.nextLine().trim();
        
        Usuario usuario = sistema.pesquisarUsuario(cpf);
        if (usuario != null) {
            System.out.println(usuario.detalhes());
        } else {
            System.out.println("Usuário não encontrado!");
        }
    }
    
    private static void menuEmprestimos() {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- EMPRÉSTIMOS E DEVOLUÇÕES ---");
            System.out.println("1. Realizar Empréstimo");
            System.out.println("2. Realizar Devolução");
            System.out.println("3. Voltar");
            System.out.print("Opção: ");
            
            int opcao = lerOpcao();
