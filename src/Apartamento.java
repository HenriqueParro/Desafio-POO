public class Apartamento extends Imovel{

    private boolean temSacada;


    public Apartamento(Endereco endereco, boolean alugado, int numero,
                Proprietario proprietario, double valorAluguelMensal, int metragem,
                boolean temSacada) {
        super(endereco, numero ,alugado ,proprietario, valorAluguelMensal, metragem);
        this.temSacada  = temSacada;
    }

    public Apartamento(String rua, String bairro, String cidade, String estado, String cep, int numero,
                boolean alugado, String nomeProp, String telefoneProp, String cpfProp,
                double valorAluguelMensal, int metragem, boolean temSacada) {
        this(new Endereco(rua, bairro, cidade, estado, cep), false, numero,
                new Proprietario(nomeProp, telefoneProp, cpfProp),
                valorAluguelMensal, metragem, temSacada);
    }

    public int getNumeroAp() {
        System.out.println("Numero ap: " + this.numero);
        return this.numero;
    }

    public boolean isTemSacada() {
        if (temSacada) {System.out.println("O apartamento possui sacada.");}
        else {System.out.println("O apartamento não possui um sacada.");}
        return temSacada;
    }

    @Override
    public boolean estaAlugado() {

        if (alugado) {
            System.out.println("Este apartamento está alugado");
            return true;
        } else {
            System.out.println("Este apartamento está disponível");
            return false;
        }
    }
}
