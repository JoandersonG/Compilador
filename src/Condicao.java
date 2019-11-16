public class Condicao  extends No {

    private int estadoRobo = -1;
    //prod 2,3,4 não têm não-term
    //prod 5
    private int estadoLampada = -1;
    private int localLampada = -1;
    private String regra;

    public Condicao(String regra) {
        this.regra = regra;

    }

    @Override
    public boolean addNoEmProximaPosicaoVazia(int posicaoNoArrayTree, String classe) {

        if (regra.equals("FRENTE ROBO BLOQUEADA") || regra.equals("DIREITA ROBO BLOQUEADA") || regra.equals("ESQUERDA ROBO BLOQUEADA")) {
            //não pode adicionar nenhum
            return false;
        }
        if (regra.matches("ROBO.*")) {
            estadoRobo = posicaoNoArrayTree;
            return true;
        }
        if (regra.matches("LAMPADA.*")) {
            if (classe.equals("ESTADOLAMPADA")) {
                estadoLampada = posicaoNoArrayTree;
            }
            else if (classe.equals("LOCALLAMPADA")) {
                estadoLampada = posicaoNoArrayTree;
            }
            return true;
        }

        //todo: salvar a produção, mas como saber a produção de sí terminais? ex: "aguarde ate robo pronto"? ::: resolvi?
        return false;

    }

    @Override
    public boolean temCampoVazio() {
        if (regra.matches("FRENTE.*") || regra.matches("ESQUERDA.*") || regra.matches("")) {
            return false;
        }
        if (regra.matches("ROBO.*")) {
            return estadoRobo == -1;
        }
        return (estadoLampada == -1 || localLampada == -1);
    }
}
