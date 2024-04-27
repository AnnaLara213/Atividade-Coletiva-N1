package system;

import java.util.Random;

public class Cliente extends Thread {
    private Conta conta;
    private Loja[] lojas;
    private Random random;

    public Cliente(Conta conta, Loja[] lojas) {
        this.conta = conta;
        this.lojas = lojas;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (temSaldoSuficienteParaCompras()) {
            realizarCompra();
            esperar();
        }
        System.out.println("Cliente " + this.getName() + " não possui mais saldo.");
    }

    private boolean temSaldoSuficienteParaCompras() {
        return conta.getSaldo() > 0;
    }

    private void realizarCompra() {
        int lojaIndex = random.nextInt(lojas.length);
        double valorCompra = obterValorCompraAleatorio();
        synchronized (conta) {
            if (valorCompra > 0 && conta.getSaldo() >= valorCompra) {
                conta.debito(valorCompra);
                lojas[lojaIndex].receberPagamento(valorCompra);
                System.out.println("Cliente realizou uma compra de R$ " + valorCompra);
            } else {
                if (valorCompra <= 0) {
                    System.out.println("Erro: Valor de compra inválido.");
                } else {
                    System.out.println("Erro: Saldo insuficiente para realizar compra de R$ " + valorCompra);
                }
            }
        }
    }

    private double obterValorCompraAleatorio() {
        return random.nextBoolean() ? 100.0 : 200.0;
    }

    private void esperar() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}





