package system;

public class Funcionario extends Thread {
    private Conta contaSalario;
    private Conta investir;
    private double salario;
    private static final double TAXA_INVESTIMENTO = 0.2;

    public Funcionario(Conta contaSalario, Conta investir, double salario) {
        this.contaSalario = contaSalario;
        this.investir = investir;
        this.salario = salario;
    }

    @Override
    public void run() {
        receberSalario();
        investirParteSalario();
    }

    private void receberSalario() {
        synchronized (contaSalario) {
            if (salario > 0) {
                double valorRecebido = salario;
                contaSalario.credito(valorRecebido);
                System.out.println("Salário: R$ " + valorRecebido);
            }
        }
    }

    private void investirParteSalario() {
        synchronized (contaSalario) {
            double valorInvestido = calcularValorInvestido();
            if (contaSalario.getSaldo() >= valorInvestido) {
                contaSalario.debito(valorInvestido);
                investir.credito(valorInvestido);
                System.out.println("Valor investido em investimentos: R$ " + valorInvestido);
            } else {
                System.out.println("O saldo não foi suficiente para realizar o investimento.");
            }
        }
    }

    private double calcularValorInvestido() {
        return salario * TAXA_INVESTIMENTO;
    }
}
