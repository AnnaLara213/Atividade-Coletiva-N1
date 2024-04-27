package system;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {

    private Lock lock = new ReentrantLock();

    public void transferencia(Conta entrada, Conta saida, double valor) {
        lock.lock();
        try {
            if (!transferenciaValida(entrada, saida, valor)) {
                System.out.println("Erro na transferência: " + getMensagemErro(entrada, saida, valor));
                return;
            }

            entrada.debito(valor);
            saida.credito(valor);
            System.out.println("Transferência de R$ " + valor + " realizada com sucesso!");
        } finally {
            lock.unlock();
        }
    }

    private boolean transferenciaValida(Conta entrada, Conta saida, double valor) {
        return entrada != null && saida != null && valor > 0 && entrada.getSaldo() >= valor;
    }

    private String getMensagemErro(Conta entrada, Conta saida, double valor) {
        String mensagem = "Erro na transferência de R$ " + valor;
        if (entrada == null || saida == null) {
            mensagem += ": Contas inválidas.";
        } else if (valor <= 0) {
            mensagem += ": Valor inválido.";
        } else if (entrada.getSaldo() < valor) {
            mensagem += ": Saldo insuficiente na conta de origem.";
        }
        return mensagem;
    }
}



