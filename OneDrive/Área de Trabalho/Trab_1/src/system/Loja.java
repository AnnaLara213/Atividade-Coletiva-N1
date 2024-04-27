package system;

public class Loja {
    private Conta conta;

    public Loja(Conta conta) {
        this.conta = conta;
    }

    public void receberPagamento(double valor) {
        synchronized (conta) {
            realizarRecebimento(valor);
        }
    }

    private void realizarRecebimento(double valor) {
        conta.credito(valor);
        System.out.println("Loja recebeu um pagamento de R$ " + valor);
    }

    public void pagamentoFuncionario(double salario) {
        synchronized (conta) {
            realizarPagamentoFuncionario(salario);
        }
    }

    private void realizarPagamentoFuncionario(double salario) {
        if (salario > 0) {
            if (conta.getSaldo() >= salario) {
                conta.debito(salario);
                System.out.println("Funcionário foi pago com sucesso.");
            } else {
                System.out.println("Erro: Saldo é insuficiente para pagar o funcionário.");
            }
        }
    }

    public Conta getConta() {
        return conta;
    }
}
