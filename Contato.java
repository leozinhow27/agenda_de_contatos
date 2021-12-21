
package labp3;

public class Contato {

    private String nome;
    private String sobrenome;
    private String telefone;

    /**
     * o boolean será responsável para o funcionamento do "toString", pois dependo do "boolean" o "toString" deve retornar uma menssagem específica.
     */
    private boolean favorito = false;
    private String menssagem;


    @Override

/**
 * o "toSrtring" retornará essas menssagens dependendo do contato ser favorito ou não.
 */
    public String toString() {
        if (favorito == (true)) {
            menssagem = "❤️" + this.nome + " " + this.sobrenome + "\n"
                    + this.telefone;
        } else {
            menssagem = this.nome + " " + this.sobrenome + "\n"
                    + this.telefone;


        }
        return menssagem;
    }

    /**
     * @param nome nome da pessoa. exp: Leonardo
     * @param sobrenome sobrenome da pessoa. exp: Cavalcanti
     * @param telefone telefone da pessoa. exp: (81)995462254
     */
    public Contato(String nome, String sobrenome, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;

    }

    /**
     * @return retorna o nome.
     */
    public String getNome() {
        return nome;
    }
    /**
     * @return retorna o sobrenome.
     */
    public String getSobrenome() {
        return sobrenome;
    }

    /**
     * @param favorito analisa se o contato é favorito ou não.
     */
    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}