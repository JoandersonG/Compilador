package NonTerminals;

public class ComandoMais  extends No{
    private Comando comando;
    private ComandoMais comandoMais;
    private boolean lambda;

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
    }

    public boolean isLambda() {
        return lambda;
    }

    public void setLambda(boolean lambda) {
        this.lambda = lambda;
    }
}
