
package labp3;

/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100 contatos.
 *
 * @author nazareno
 */
public class Agenda {

    /**
     * array criado para guardar os contatos e os favoritos, no caso 100 contatos e 10 favoritos.
     */
    private static final int TAMANHO_AGENDA = 100;
    private static final int TAMANHO_FAVORITO = 10;

    private Contato[] contatos;
    private Contato[] favorito;

    /**
     * Cria uma agenda.
     */
    public Agenda() {
        this.contatos = new Contato[TAMANHO_AGENDA];
        this.favorito = new Contato[TAMANHO_FAVORITO];
    }

    /**
     * Acessa a lista de contatos mantida.
     *
     * @return O array de contatos.
     */
    public Contato[] getContatos() {
        return this.contatos.clone();
    }

    /**
     * Acessa os dados de um contato específico.
     *
     * @param posicao Posição do contato na agenda.
     * @return Dados do contato. Null se não há contato na posição.
     */
    public String getContato(int posicao) {
        if (posicao < 1 || posicao > 100) {
            return "POSIÇÃO INVÁLIDA!";
        }


        return contatos[posicao - 1].toString();

    }

    /**
     * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior.
     *
     * @param posicao   Posição do contato.
     * @param nome      Nome do contato.
     * @param sobrenome Sobrenome do contato.
     * @param telefone  Telefone do contato.
     */
    public void cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
        if (posicao < 1 || posicao > 100) {
            throw new IndexOutOfBoundsException("posição inválida!");
        }
        if (nome == null) {
            throw new NullPointerException("Nome inválido!");

        }

        Contato contato = new Contato(nome, sobrenome, telefone);
        this.contatos[posicao - 1] = contato;
    }

    /**
     * @param contato pega o contato.
     * @param posicao pega a posição do contato.
     */
    public void adicionaFavorito(int contato, int posicao) {
        this.favorito[posicao - 1] = this.contatos[contato - 1];
        this.contatos[contato - 1].setFavorito(true);
    }


    /**
     * @return retorna ele já adicionado na lista de favoritos.
     */
    public Contato[] getFavorito() {
        return this.favorito.clone();
    }

}
