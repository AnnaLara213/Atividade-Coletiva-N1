package system;

public class Conta {
	private double saldo;

    public Conta(double saldo) {
        this.saldo = saldo;
    }
    public synchronized void credito(double valor) {
        saldo += valor;
    }
   
    public synchronized void debito(double valor) {
        saldo -= valor;
    }

    public synchronized double getSaldo() {
        return saldo;
    }

}
