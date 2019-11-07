package NonTerminals;

public class Condicao  extends No{

    private int estadoRobo;
    //prod 2,3,4 não têm não-term
    //prod 5
    private int estadoLampada;
    private int localLampada;

    @Override
    public boolean addNo(int posicaoNoArrayTree, String classe) {
        if (classe.equals("ESTADOROBO")) {
            estadoLampada = posicaoNoArrayTree;
        }
        else if (classe.equals("ESTADOLAMPADA")) {
            estadoLampada = posicaoNoArrayTree;
        }
        else if (classe.equals("LOCALLAMPADA")) {
            localLampada = posicaoNoArrayTree;
        }
        //todo: salvar a produção, mas como saber a produção de sí terminais? ex: "aguarde ate robo pronto"?
        return false;

    }

    @Override
    public boolean temCampoVazio() {
        return false;
    }
}
