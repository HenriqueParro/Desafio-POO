import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final Scanner SC = new Scanner(System.in);
    private static final List<Imovel> IMOVEIS = new ArrayList<>();

    public static void main(String[] args) {
        // dados iniciais
        ImovelActions.seedDados(IMOVEIS);

        boolean rodando = true;
        while (rodando) {
            try {
                Menu.show(); // <<< menu em outro arquivo
                int op = ConsoleUtils.lerInt(SC, "Escolha uma opção: ");
                System.out.println();

                switch (op) {
                    case 1  -> ImovelActions.cadastrarCasa(SC, IMOVEIS);
                    case 2  -> ImovelActions.cadastrarApartamento(SC, IMOVEIS);
                    case 3  -> ImovelActions.listarImoveis(IMOVEIS);
                    case 4  -> ImovelActions.verDetalhesImovel(SC, IMOVEIS);
                    case 5  -> ImovelActions.alugarImovel(SC, IMOVEIS);
                    case 6  -> ImovelActions.encerrarAluguel(SC, IMOVEIS);
                    case 7  -> ImovelActions.verStatus(SC, IMOVEIS);
                    case 8  -> ImovelActions.calcularAluguelMesAtual(SC, IMOVEIS);
                    case 9  -> ImovelActions.calcularAluguelPorPeriodo(SC, IMOVEIS);
                    case 10 -> ImovelActions.atualizarValorAluguel(SC, IMOVEIS);
                    case 11 -> ImovelActions.verContatoProprietario(SC, IMOVEIS);
                    case 12 -> ImovelActions.rodarSuiteAutomatica(IMOVEIS);

                    case 0  -> { System.out.println("Saindo..."); rodando = false; }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            System.out.println();
        }
    }
}
