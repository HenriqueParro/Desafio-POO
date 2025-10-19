import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public abstract class Imovel {

    private static final DateTimeFormatter BR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    protected Endereco endereco;
    protected boolean alugado;
    protected int numero;         // número do imóvel (ou apto)

    private Proprietario proprietario;
    private double valorAluguelMensal;
    private int metragem;
    private LocalDate  dataInicioAluguel;
    private Inquilino inquilino;


    protected Imovel(Endereco endereco,int numero,boolean alugado, Proprietario proprietario, double valorAluguelMensal, int metragem) {

        this.endereco = endereco;
        this.alugado = false;
        this.proprietario = proprietario;
        this.valorAluguelMensal = valorAluguelMensal;
        this.metragem = metragem;
        this.dataInicioAluguel = null;
        this.inquilino = null;
    }

    public Endereco getEndereco() { return endereco; }
    public int getNumero() { return numero; }
    public int getMetragem() { return metragem; }
    public Proprietario getProprietario() { return proprietario; }
    public Inquilino getInquilino() { return inquilino; }

    public void setValorAluguelMensal(double valorAluguelMensal) {
        this.valorAluguelMensal = valorAluguelMensal;
        System.out.printf("Valor do aluguel atualizado: R$ %.2f%n", valorAluguelMensal);
    }
    public double getValorAluguelMensal() {
        return valorAluguelMensal;
    }

    public boolean estaAlugado() {

        if (alugado) {
            //System.out.println("Imóvel está alugado desde: " + dataInicioAluguel.toString());
            return true;
        } else {
            //System.out.println("Imóvel está disponivel para locação");
            return false;
        }
    }

    private int mesesAlugadosCompletos() {
        if (!alugado || dataInicioAluguel == null) return 0;
        return (int) Period.between(dataInicioAluguel, LocalDate.now()).toTotalMonths();
    }


    public void alugar(Inquilino inquilino) {

        if (alugado) {
            System.out.println("Imovel já locado");

        } else {
            this.inquilino = inquilino;
            this.dataInicioAluguel = LocalDate.now();
            this.alugado = true;
            System.out.println("Imovel alugado com sucesso");
        }
    }


    public void encerrarAluguel() {
        if (alugado) {
            this.alugado = false;
            this.dataInicioAluguel = null;
            this.inquilino = null;
            System.out.println("Contrato encerrado");

        } else {
            System.out.println("Imovel não locado");
        }
    }

    private double calculaDesconto() {
        int tempoAlugado = mesesAlugadosCompletos();
        if (tempoAlugado >= 36) return 0.70;
        if (tempoAlugado >= 24) return 0.80;
        if (tempoAlugado >= 12) return 0.90;
        return 1.00;
    }

    // valor total para um PERÍODO informado
    public double calcularAluguel(int meses) {

        double desconto = calculaDesconto();

        if (meses == 0) {
            System.out.println("Imóvel não alugado ou não há tempo mínimo para cobrança");
            return 0;
        } else {
            //double valorAluguel = desconto * valorAluguelMensal * meses;
            //System.out.println("O valor do aluguel para " +meses+ " meses é " + valorAluguel + "R$");
            return desconto * valorAluguelMensal * meses;
        }
    }

    // valor do aluguel que sera pago naquele mes
    public double calcularAluguel() {

        int tempoAlugado = mesesAlugadosCompletos();
        double desconto = calculaDesconto();

        if (tempoAlugado == 0) {
            System.out.println("Imóvel não alugado ou não há tempo mínimo para cobrança");
            return 0;
        } else {
            return desconto * valorAluguelMensal;
            //System.out.println("O valor do aluguel é " + valorAluguel + "R$");
            //return valorAluguel;
        }
    }



    public String contatoProprietario() {
        //System.out.println(proprietario.nome() + " — " + proprietario.telefone());
        return proprietario.nome() + " — " + proprietario.telefone();
    }

}
