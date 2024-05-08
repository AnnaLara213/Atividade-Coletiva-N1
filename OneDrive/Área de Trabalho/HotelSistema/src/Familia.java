package hotelSistema;
import java.util.ArrayList;
import java.util.List;

public class Familia {

    private List<Hospede> hospedes;

    public Familia() {
        hospedes = new ArrayList<>();
    }

    public void adicionarHospede(Hospede hospede) {
        hospedes.add(hospede);
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }
}
