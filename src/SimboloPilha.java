public class SimboloPilha {
    private String prox;
    private String atual;
    private String valorReal;

    public SimboloPilha(String valorReal) {
        this.valorReal = valorReal;
    }

    public String getProx() {
        return prox;
    }

    public void setProx(String prox) {
        this.prox = prox;
    }

    public String getAtual() {
        return atual;
    }

    public void setAtual(String atual) {
        this.atual = atual;
    }

    public String getValorReal() {
        return valorReal;
    }

    public void setValorReal(String valorReal) {
        this.valorReal = valorReal;
    }
}
