package NonTerminals;

public abstract class No {

    private String proximoObrigatorio = null;
    private String proximoProibido = null;
    private String nome = null;
    private String val = null;
    private String atualProibido = null;

    public String getAtualProibido() {
        return atualProibido;
    }

    public boolean setAtualProibido(String atualProibido) {
        this.atualProibido = atualProibido;
        return true;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProximoObrigatorio() {
        return proximoObrigatorio;
    }

    public void setProximoObrigatorio(String proximoObrigatorio) {
        this.proximoObrigatorio = proximoObrigatorio;
    }

    public String getProximoProibido() {
        return proximoProibido;
    }

    public void setProximoProibido(String proximoProibido) {
        this.proximoProibido = proximoProibido;
    }

    public abstract boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe);

    public abstract boolean temCampoVazio();

    public No primeiroAtributo() {
        return null;
    }
}
