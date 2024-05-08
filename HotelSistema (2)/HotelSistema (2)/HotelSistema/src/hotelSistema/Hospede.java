package hotelSistema;


public class Hospede extends Thread {

    private String nome;

    public Hospede(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    
}
