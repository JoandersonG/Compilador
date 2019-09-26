public class LinhaAutomato {
    private int letra;
    private int numero;
    private int simbolo;

    public LinhaAutomato(int letra, int numero, int simbolo) {
        this.letra = letra;
        this.numero = numero;
        this.simbolo = simbolo;
    }

    public int getLetra() {
        return letra;
    }

    public void setLetra(int letra) {
        this.letra = letra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(int simbolo) {
        this.simbolo = simbolo;
    }
}
