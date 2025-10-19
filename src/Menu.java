public final class Menu {
    private Menu() {}

    public static void show() {
        System.out.println("===== IMOBILIÁRIA =====");
        System.out.println("1) Cadastrar Casa");
        System.out.println("2) Cadastrar Apartamento");
        System.out.println("3) Listar imóveis");
        System.out.println("4) Ver detalhes do imóvel");
        System.out.println("5) Alugar imóvel");
        System.out.println("6) Encerrar aluguel");
        System.out.println("7) Ver status (alugado/disponível)");
        System.out.println("8) Calcular aluguel (mês atual)");
        System.out.println("9) Calcular aluguel por período (meses)");
        System.out.println("10) Atualizar valor do aluguel");
        System.out.println("11) Ver contato do proprietário");
        System.out.println("12) Rodar SUÍTE AUTOMÁTICA de testes");
        System.out.println("0) Sair");
    }
}
