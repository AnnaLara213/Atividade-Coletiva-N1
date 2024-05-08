package hotelSistema;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        // Criar hóspedes
        List<Hospede> hospedes = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            hospedes.add(new Hospede("Hóspede " + i));
        }

        // Criar famílias
        List<Familia> familias = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Familia familia = new Familia();
            int numHospedes = 2 + i % 3; // 2, 3 ou 4 hóspedes
            for (int j = 0; j < numHospedes; j++) {
                familia.adicionarHospede(hospedes.get(i * 4 + j));
            }
            familias.add(familia);
        }

        // Mostra as familias e seus respectivos hospedes
        for (int i = 0; i < familias.size(); i++) {
            Familia familia = familias.get(i);
            System.out.println("Família " + (i + 1) + ":");
            for (Hospede hospede : familia.getHospedes()) {
                System.out.println(" - " + hospede.getNome());
            }
        }

        // Criar quartos
        List<Quarto> quartos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            quartos.add(new Quarto(4));
        }

        // Criar e iniciar recepcionistas e camareiras 
        Lock lock = new ReentrantLock();
        Recepcionista recepcionista = new Recepcionista(familias, quartos, lock);
        recepcionista.start();

        Camareira camareira = new Camareira(familias, quartos);
        camareira.start();

        // Reclamações de hóspedes
        for (Hospede hospede : hospedes) {
            if (!hospedeJaAlocado(familias, hospede)) {
                lock.lock();
                try {
                    System.out.println(" - " + hospede.getNome() + ": 'Hospede sai para passear e depois volta. Hotel ainda ta cheio e faz uma reclamação'");
                } finally {
                    lock.unlock();
                }
            }
        }
    }
// hospede alocar recebe lista de familia e hospede, verifica se os hospedes já estão em uma familia
    private static boolean hospedeJaAlocado(List<Familia> familias, Hospede hospede) {
        for (Familia familia : familias) {
            if (familia.getHospedes().contains(hospede)) {
                return true;
            }
        }
        return false;
    }
}
