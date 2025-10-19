public class Casa extends Imovel{

    private boolean temAlpendre;
    private boolean temQuintal;


    public Casa(Endereco endereco,boolean alugado,int numero,
                Proprietario proprietario, double valorAluguelMensal, int metragem,
                boolean temAlpendre, boolean temQuintal) {
        super(endereco, numero,alugado ,proprietario, valorAluguelMensal, metragem);
        this.temAlpendre = temAlpendre;
        this.temQuintal  = temQuintal;
    }

    public Casa(String rua, String bairro, String cidade, String estado, String cep, int numero,
                boolean alugado, String nomeProp, String telefoneProp, String cpfProp,
                double valorAluguelMensal, int metragem,
                boolean temAlpendre, boolean temQuintal) {
        this(new Endereco(rua, bairro, cidade, estado, cep),false, numero,
                new Proprietario(nomeProp, telefoneProp, cpfProp),
                valorAluguelMensal, metragem,
                temAlpendre, temQuintal);
    }

    public boolean isTemAlpendre() {
        if (temAlpendre) System.out.println("A casa tem alpendre.");
        else System.out.println("A casa não tem alpendre.");
        return temAlpendre;
    }
    public boolean isTemQuintal() {
        if (temQuintal) System.out.println("A casa tem quintal.");
        else System.out.println("A casa não tem quintal.");
        return temQuintal;
    }

    @Override
    public boolean estaAlugado() {

        if (alugado) {
            System.out.println("A casa está alugado");
            return true;
        } else {
            System.out.println("Imovel esta disponivel para locação");
            return false;
        }
    }

}
