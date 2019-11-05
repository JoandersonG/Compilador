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

    public String getJustToken() {
        char[] tk = null;
        StringBuilder value = new StringBuilder();

        if( token.charAt(0) == '<') {

            tk = token.toCharArray();
            for (int i = 0; i < tk.length; i++) {
                if(tk[i] == '<') {
                    continue; //ignora
                }
                if(tk[i] == ',') {
                    break;
                }
                value.append(tk[i]);

            }
        }
        else {
            return token;
        }
        return value.toString();
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
