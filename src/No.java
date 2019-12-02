import java.util.ArrayList;

public abstract class No {

    private String proximoObrigatorio = null;
    private String proximoProibido = null;
    private String nome = null;
    private String val = null;
    private String atualProibido = null;
    private String atualObrigatorio = null;
    private ArrayList<String> asm = new ArrayList<>();

    public ArrayList<String> getAsm() {
        return asm;
    }

    public void setAsm(ArrayList<String> asm) {
        this.asm = asm;
    }

    public void addAsm(ArrayList<String> asm) {
        this.asm.addAll(asm);
    }

    public void addAsm(String[] asm) {
        for (String line : asm) {
            this.asm.add(line);
        }
    }

    public void addAsm(String line) {
        asm.add(line);
    }

    public abstract void updateAsm();

    public String getAtualObrigatorio() {
        return atualObrigatorio;
    }

    public void setAtualObrigatorio(String atualObrigatorio) {
        if (val != null && !temCampoVazio() && atualObrigatorio != null && atualObrigatorio.matches("AGUARDE.*") && !val.equals("PRONTO")) {
            //erro
            Tree.errosSemanticos.add("<sem_instr_necessaria>,\"aguarde ate robo pronto\"");
        }
        this.atualObrigatorio = atualObrigatorio;
    }

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

}
