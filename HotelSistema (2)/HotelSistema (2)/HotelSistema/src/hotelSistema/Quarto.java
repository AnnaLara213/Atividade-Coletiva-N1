package hotelSistema;
import java.util.ArrayList;
import java.util.List;

public class Quarto {

    private int capacidade;
    private List<Hospede> hospedes;

    public Quarto(int capacidade) {
        this.capacidade = capacidade;
        hospedes = new ArrayList<>();
    }

    public void adicionarHospede(Hospede hospede) {
        if (hospedes.size() < capacidade) {
            hospedes.add(hospede);
        } else {
            System.out.println("Quartos lotados!");
        }
    }

    public int getCapacidade() {
        return capacidade;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }
}
