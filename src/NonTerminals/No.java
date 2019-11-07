package NonTerminals;

public abstract class No {
    private No pai;
    private boolean isVazia;

    public boolean isVazia() {
        return isVazia;
    }

    public void setVazia(boolean vazia) {
        isVazia = vazia;
    }

    public abstract boolean addNo(int posicaoNoArrayTree, String classe);

    public abstract boolean temCampoVazio();

    public No primeiroAtributo() {
        return null;
    }
}
