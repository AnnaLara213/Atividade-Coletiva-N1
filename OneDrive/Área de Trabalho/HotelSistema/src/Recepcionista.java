package hotelSistema;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class Recepcionista extends Thread {

    private List<Familia> familias;
    private List<Quarto> quartos;
    private Lock lock;

    public Recepcionista(List<Familia> familias, List<Quarto> quartos, Lock lock) {
        this.familias = familias;
        this.quartos = quartos;
        this.lock = lock;
    }
//verifica se tem quartos para alocar os hospedes 
    @Override
    public void run() {
        for (Familia familia : familias) {
            for (Hospede hospede : familia.getHospedes()) {
                boolean alocado = false;
                lock.lock();
                try {
                    for (Quarto quarto : quartos) {
                        if (quarto.getHospedes().size() < quarto.getCapacidade()) {
                            quarto.adicionarHospede(hospede); // adiciona os hospedes caso tenha capacidade 
                            alocado = true;   //para dizer que os hospedes foram alocados 
                            System.out.println("Hóspede " + hospede.getNome() + " alocado no quarto.");
                            break;
                        }
                    }

                    if (!alocado) {
                        System.out.println("Quartos lotados!");
                    }
                } finally {
                    lock.unlock();
                }
            }
        }

        System.out.println("Hotel lotado!");
    }
}
