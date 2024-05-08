package hotelSistema;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
public class Camareira extends Thread {

    private List<Familia> familias;
    private List<Quarto> quartos;
    private ReentrantLock lock;
    
    public Camareira(List<Familia> familias, List<Quarto> quartos) {
        this.familias = familias;
        this.quartos = quartos;
        this.lock = new ReentrantLock();
    }

    @Override
    public void run() {
        // Famílias saem dos quartos
        for (int i = 0; i < 10; i++) {
            Familia familia = familias.get(i);
            for (Hospede hospede : familia.getHospedes()) {
                lock.lock(); // Adquira o lock
                try {
                    for (Quarto quarto : quartos) {
                        if (quarto.getHospedes().contains(hospede)) {
                            quarto.getHospedes().remove(hospede);
                            System.out.println("Hóspede " + hospede.getNome() + " saiu do quarto.");
                            break;
                        }
                    }
                } finally {
                    lock.unlock(); // Libere o lock
                }
            }
        }

        // Limpeza dos quartos
        for (int i = 0; i < 10; i++) {
            lock.lock(); // Adquira o lock
            try {
                for (Quarto quarto : quartos) {
                    if (quarto.getCapacidade() == 4) {
                        System.out.println("Camareira " + (i + 1) + " está limpando o Quarto " + (i + 1) + ".");
                        break;
                    }
                }
            } finally {
                lock.unlock(); // Libere o lock
            }
        }
    }
}
