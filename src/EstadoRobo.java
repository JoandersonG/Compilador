public class EstadoRobo  extends No {
    private String valor;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
        this.setVal(valor);
    }

    public EstadoRobo(String valor) {
        this.valor = valor;
        this.setVal(valor);
        if (getAtualObrigatorio()!= null) {
            if(getAtualObrigatorio().matches("ROBO PRONTO") && valor.equals("PRONTO")) {
                //tudo certo
                setAtualObrigatorio(null);
            }
            else {
                //System.out.println("ERRO SEMÂNTICO: 'PRONTO' ERA ESPERADO");
            }
        }
    }

    @Override
    public void setAtualObrigatorio(String atualObrigatorio) {
        if (!valor.equals("PRONTO") && atualObrigatorio.equals("ROBO PRONTO")) {
            //System.out.println("erro ... ");
        }
        else {
            //setAtualObrigatorio(null);
            super.setAtualObrigatorio(atualObrigatorio);
        }
    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {
        return false;
    }

    @Override
    public boolean temCampoVazio() {
        return false;
    }
    //pronto, ocupado, parado
}
