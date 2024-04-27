package system;

public class SistemaBancario {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Conta contaBanco = new Conta(0);

        Loja loja1 = new Loja(new Conta(0));
        Loja loja2 = new Loja(new Conta(0));

        Conta contaLoja1 = loja1.getConta();
        Conta contaLoja2 = loja2.getConta();

        Conta contaInvestimentosFuncionario1 = new Conta(0);
        Conta contaInvestimentosFuncionario2 = new Conta(0);
        Conta contaInvestimentosFuncionario3 = new Conta(0);
        Conta contaInvestimentosFuncionario4 = new Conta(0);
        Conta contaInvestimentosFuncionario5 = new Conta(0);

        Funcionario funcionario1 = new Funcionario(new Conta(0), contaInvestimentosFuncionario1, 1400.0);
        Funcionario funcionario2 = new Funcionario(new Conta(0), contaInvestimentosFuncionario2, 1400.0);
        Funcionario funcionario3 = new Funcionario(new Conta(0), contaInvestimentosFuncionario3, 1400.0);
        Funcionario funcionario4 = new Funcionario(new Conta(0), contaInvestimentosFuncionario4, 1400.0);

        Cliente cliente1 = new Cliente(new Conta(1000), new Loja[]{loja1, loja2});
        Cliente cliente2 = new Cliente(new Conta(1000), new Loja[]{loja1, loja2});
        Cliente cliente3 = new Cliente(new Conta(1000), new Loja[]{loja1, loja2});
        Cliente cliente4 = new Cliente(new Conta(1000), new Loja[]{loja1, loja2});
        Cliente cliente5 = new Cliente(new Conta(1000), new Loja[]{loja1, loja2});

        Thread thread1 = new Thread(cliente1);
        Thread thread2 = new Thread(cliente2);
        Thread thread3 = new Thread(cliente3);
        Thread thread4 = new Thread(cliente4);
        Thread thread5 = new Thread(cliente5);
        Thread thread6 = new Thread(funcionario1);
        Thread thread7 = new Thread(funcionario2);
        Thread thread8 = new Thread(funcionario3);
        Thread thread9 = new Thread(funcionario4);

        iniciarThreads(new Thread[]{thread1, thread2, thread3, thread4, thread5, thread6, thread7, thread8, thread9});
        esperarTerminoThreads(new Thread[]{thread1, thread2, thread3, thread4, thread5, thread6, thread7, thread8, thread9});

        System.out.println("Saldo final loja 1: R$ " + contaLoja1.getSaldo());
        System.out.println("Saldo final loja 2: R$ " + contaLoja2.getSaldo());
        System.out.println("Saldo final banco: R$ " + contaBanco.getSaldo());
        System.out.println("Saldo final dos investimentos do funcionário 1: " + contaInvestimentosFuncionario1.getSaldo());
        System.out.println("Saldo final dos investimentos do funcionário 2: " + contaInvestimentosFuncionario2.getSaldo());
        System.out.println("Saldo final dos investimentos do funcionário 3: " + contaInvestimentosFuncionario3.getSaldo());
        System.out.println("Saldo final dos investimentos do funcionário 4: " + contaInvestimentosFuncionario4.getSaldo());
        System.out.println("Saldo final dos investimentos do funcionário 5: " + contaInvestimentosFuncionario5.getSaldo());
    }

    private static void iniciarThreads(Thread[] threads) {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static void esperarTerminoThreads(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
