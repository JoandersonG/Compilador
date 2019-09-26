public class Token {
    private String lexema;
    private String token;
    private int linha;
    private int coluna;

    public Token(String lexema, String token, int linha, int coluna) {
        this.lexema = lexema;
        this.token = token;
        this.linha = linha;
        this.coluna = coluna;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
}
