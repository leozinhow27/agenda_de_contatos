

package labp3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 *
 * @author nazarenoandrade
 */
public class MainAgenda {

    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        System.out.println("Carregando agenda inicial");
        try {
            /*
             * Essa é a maneira de lidar com possíveis erros por falta do arquivo.
             */
            carregaAgenda("agenda_inicial.csv", agenda);
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro lendo arquivo: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String escolha = "";
        while (true) {
            escolha = menu(scanner);
            comando(escolha, agenda, scanner);
        }

    }

    /**
     * Exibe o menu e captura a escolha do/a usuário/a.
     *
     * @param scanner Para captura da opção do usuário.
     * @return O comando escolhido.
     */
    private static String menu(Scanner scanner) {
        System.out.println(
                "\n---\nMENU\n" +
                        "(C)adastrar Contato\n" +
                        "(L)istar Contatos\n" +
                        "(E)xibir Contato\n" +
                        "(F)avoritos\n" +
                        "(A)dicionar favoritos\n" +
                        "(S)air\n" +
                        "\n" +
                        "Opção> ");
        return scanner.next().toUpperCase();
    }

    /**
     * Interpreta a opção escolhida por quem está usando o sistema.
     *
     * @param opcao   Opção digitada.
     * @param agenda  A agenda que estamos manipulando.
     * @param scanner Objeto scanner para o caso do comando precisar de mais input.
     */
    private static void comando(String opcao, Agenda agenda, Scanner scanner) {
        switch (opcao) {
            case "C":
                cadastraContato(agenda, scanner);
                break;
            case "L":
                listaContatos(agenda);
                break;
            case "E":
                exibeContato(agenda, scanner);
                break;
            case "F":
                listaFavoritos(agenda);
                break;
            case "A":
                adicionaFavoritos(agenda, scanner);
                break;
            case "S":
                sai();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    /**
     * @param agenda aonde estão salvos os contatos.
     * @param scanner le o contato que o usuário deseja favoritar.
     */
    private static void adicionaFavoritos(Agenda agenda, Scanner scanner) {

        System.out.println("Contato>");
        int contato = scanner.nextInt();

        System.out.println("Posicao>");
        int posicao = scanner.nextInt();
        agenda.adicionaFavorito(contato, posicao);

        System.out.println("CONTATO FAVORITADO NA POSIÇÃO " + posicao + "!");


    }

    /**
     * @param agenda agenda sendo manipulada.
     * mostra a lista de contatos favoritados.
     */
    private static void listaFavoritos(Agenda agenda) {

        System.out.println("\nLista de favoritos: ");
        Contato[] favoritos = agenda.getFavorito();
        for (int i = 0; i < favoritos.length; i++) {
            if (favoritos[i] != null) {
                System.out.println(formataContato(i, favoritos[i].getNome(), favoritos[i].getSobrenome()));
            }
        }
    }


    /**
     * Imprime lista de contatos da agenda.
     *
     * @param agenda A agenda sendo manipulada.
     */
    private static void listaContatos(Agenda agenda) {
        System.out.println("\nLista de contatos: ");
        Contato[] contatos = agenda.getContatos();
        for (int i = 0; i < contatos.length; i++) {
            if (contatos[i] != null) {
                System.out.println(formataContato(i, contatos[i].getNome(), contatos[i].getSobrenome()));
            }
        }
    }

    /**
     * Imprime os detalhes de um dos contatos da agenda.
     *
     * @param agenda  A agenda.
     * @param scanner Scanner para capturar qual contato.
     */
    private static void exibeContato(Agenda agenda, Scanner scanner) {
        System.out.print("\nQual contato> ");
        int posicao = scanner.nextInt();
        String contato = agenda.getContato(posicao);
        System.out.println("Dados do contato:\n" + contato);


    }

    /**
     * Formata um contato para impressão na interface.
     *
     * @param posicao A posição do contato (que é exibida)/
     * @param nome    O contato a ser impresso.
     * @return A String formatada.
     */
    private static String formataContato(int posicao, String nome, String sobrenome) {
        return (posicao + 1) + " - " + nome + " " + sobrenome;
    }

    /**
     * Cadastra um contato na agenda.
     *
     * @param agenda  A agenda.
     * @param scanner Scanner para pedir informações do contato.
     */
    private static void cadastraContato(Agenda agenda, Scanner scanner) {
        scanner.nextLine();
        System.out.print("\nPosição na agenda> ");
        String posicao = scanner.nextLine();
        int prox = Integer.parseInt(posicao);


        /**
         * aqui estamos condicionando a posicao do contato na agenda caso seja maior que 100 ou menor que 1.
         */
        if (prox < 1 || prox > 100) {
            System.out.println("POSICÃO INVALIDA!");

        } else

            System.out.print("\nNome> ");
        String nome = scanner.nextLine();

        /**
         * usa-se ".isBlank" para representar espaços em branco, caso no nome e sobrenome não seja escrito rodará a menssagem "contato invalido".
         */
        if (nome.isBlank()) {
            System.out.println("CONTATO INVALIDO");
        } else {

            System.out.print("\nSobrenome> ");
            String sobrenome = scanner.nextLine();

            /**
             * o for vai iterar pelo "agenda.getContatos()" e ver se o contato cadastrado vai ser se o nome cadastrado é igual a um já salvo na agenda.
             * a variavel existe é um boolean que vai avaliar se existe ou não um número igual a outro.
             */

            boolean existe = false;
            for (Contato c : agenda.getContatos()) {
                if (c != null) {
                    if (c.getNome().equals(nome) && c.getSobrenome().equals(sobrenome)) {
                        System.out.println("CONTATO JA CADASTRADO");
                        existe = true;

                    }

                }
            }
            if (!existe) {
                System.out.print("\nTelefone> ");
                String telefone = scanner.nextLine();
                if (telefone.isBlank()) {
                    System.out.println("CONTATO INVALIDO");

                } else {


                    agenda.cadastraContato(prox, nome, sobrenome, telefone);

                }

            }

        }
    }


    /**
     * Sai da aplicação.
     */
    private static void sai() {
        System.out.println("\nVlw flw o/");
        System.exit(0);
    }

    /**
     * Lê uma agenda de um arquivo csv.
     *
     * @param arquivoContatos O caminho para o arquivo.
     * @param agenda          A agenda que deve ser populada com os dados.
     * @throws IOException Caso o arquivo não exista ou não possa ser lido.
     */
    private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
        LeitorDeAgenda leitor = new LeitorDeAgenda();

        int carregados = leitor.carregaContatos(arquivoContatos, agenda);
        System.out.println("Carregamos " + carregados + " registros.");
    }
}