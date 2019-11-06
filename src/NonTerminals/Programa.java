package NonTerminals;

public class Programa extends No{
    private int declaracao = -1;
    private int comando = -1;
    private int comandoMais = -1;

/*    public boolean addNo(No no, int posicao) {
        if (posicao == 1) {
            declaracao = (Declaracao) no;
        }
        else if (posicao == 2) {
            comando = (Comando) no;
        }
        else if(posicao == 3) {
            comandoMais = (ComandoMais) no;
        }
        else {
            return false;
        }
        return true;
    }

    public Declaracao getDeclaracao() {
        return declaracao;
    }

    public void setDeclaracao(Declaracao declaracao) {
        this.declaracao = declaracao;
    }

    public Comando getComando() {
        return comando;
    }

    public void setComando(Comando comando) {
        this.comando = comando;
    }

    public ComandoMais getComandoMais() {
        return comandoMais;
    }

    public void setComandoMais(ComandoMais comandoMais) {
        this.comandoMais = comandoMais;
    }*/

    @Override
    public void addNo(int posicaoNoArrayTree) {
        if (declaracao == -1) {
            declaracao = posicaoNoArrayTree;
        }
        else if (comando == -1) {
            comando = posicaoNoArrayTree;
        }
        else {
            comandoMais = posicaoNoArrayTree;
        }
    }

    @Override
    public boolean temCampoVazio() {
        if (declaracao == -1 || comando == -1 || comandoMais == -1) {
            return true;
        }
        return false;
    }
}
