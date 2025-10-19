import java.util.List;
import java.util.Scanner;

public final class ImovelActions {
    private ImovelActions() {}

    private static Imovel escolherImovel(Scanner sc, List<Imovel> imoveis) {
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
            return null;
        }
        while (true) {
            listarImoveis(imoveis); // imprime [0], [1], ...
            System.out.print("Informe o NÚMERO DA LISTA (0 a " + (imoveis.size()-1) + ") ou 'c' para cancelar: ");
            String entrada = sc.nextLine().trim();
            if (entrada.equalsIgnoreCase("c")) {
                System.out.println("Operação cancelada.");
                return null;
            }
            try {
                int pos = Integer.parseInt(entrada);
                if (pos >= 0 && pos < imoveis.size()) return imoveis.get(pos);
            } catch (NumberFormatException ignored) {}
            System.out.println("Entrada inválida. Tente novamente.");
        }
    }

    private static Imovel escolherImovelDisponivel(Scanner sc, List<Imovel> imoveis) {
        // filtra disponíveis
        java.util.List<Imovel> disponiveis = new java.util.ArrayList<>();
        for (Imovel im : imoveis) {
            if (!im.alugado) {
                disponiveis.add(im);
            }
        }
        if (disponiveis.isEmpty()) {
            System.out.println("Não há imóveis disponíveis para locação.");
            return null;
        }
        // lista apenas os disponíveis
        ConsoleUtils.printlnTitulo("Imóveis disponíveis");
        for (int i = 0; i < disponiveis.size(); i++) {
            Imovel im = disponiveis.get(i);

            // Endereço (sem ternário)
            Endereco e = im.getEndereco();
            String end;
            if (e == null) {
                end = "sem endereço";
            } else {
                end = e.rua() + ", " + im.getNumero() + " - "
                        + e.bairro() + " - " + e.cidade() + "/" + e.estado();
            }

            // Proprietário (nome)
            Proprietario p = im.getProprietario();
            String dono;
            if (p == null) {
                dono = "-";
            } else {
                String nome = p.nome();
                if (nome == null || nome.isBlank()) {
                    dono = "-";
                } else {
                    dono = nome;
                }
            }
            System.out.printf("[%d] %s — %s — proprietário: %s — metragem: %dm²%n",
                    i, tipo(im), end, dono, im.getMetragem());
        }
        // leitura robusta
        while (true) {
            System.out.print("Informe o NÚMERO DA LISTA (0 a " + (disponiveis.size() - 1) + ") ou 'c' para cancelar: ");
            String entrada = sc.nextLine().trim();
            if (entrada.equalsIgnoreCase("c")) {
                System.out.println("Operação cancelada.");
                return null;
            }
            try {
                int pos = Integer.parseInt(entrada);
                if (pos >= 0 && pos < disponiveis.size()) {
                    return disponiveis.get(pos);
                }
            } catch (NumberFormatException ignored) { }
            System.out.println("Entrada inválida. Tente novamente.");
        }
    }

    private static Imovel escolherImovelAlugado(Scanner sc, List<Imovel> imoveis) {
        // filtra disponíveis
        java.util.List<Imovel> indisponiveis = new java.util.ArrayList<>();
        for (Imovel im : imoveis) {
            // acessa 'alugado' porque estamos no mesmo pacote e o campo é protected
            if (im.alugado) {
                indisponiveis.add(im);
            }
        }
        if (indisponiveis.isEmpty()) {
            System.out.println("Não há imóveis locados.");
            return null;
        }
        // lista apenas os disponíveis
        ConsoleUtils.printlnTitulo("Imóveis locados");
        for (int i = 0; i < indisponiveis.size(); i++) {
            Imovel im = indisponiveis.get(i);

            // Endereço (sem ternário)
            Endereco e = im.getEndereco();
            String end;
            if (e == null) {
                end = "sem endereço";
            } else {
                end = e.rua() + ", " + im.getNumero() + " - "
                        + e.bairro() + " - " + e.cidade() + "/" + e.estado();
            }

            // Proprietário (nome)
            Proprietario p = im.getProprietario();
            String dono;
            if (p == null) {
                dono = "-";
            } else {
                String nome = p.nome();
                if (nome == null || nome.isBlank()) {
                    dono = "-";
                } else {
                    dono = nome;
                }
            }
            System.out.printf("[%d] %s — %s — proprietário: %s — metragem: %dm²%n",
                    i, tipo(im), end, dono, im.getMetragem());
        }
        // leitura robusta
        while (true) {
            System.out.print("Informe o NÚMERO DA LISTA (0 a " + (indisponiveis.size() - 1) + ") ou 'c' para cancelar: ");
            String entrada = sc.nextLine().trim();
            if (entrada.equalsIgnoreCase("c")) {
                System.out.println("Operação cancelada.");
                return null;
            }
            try {
                int pos = Integer.parseInt(entrada);
                if (pos >= 0 && pos < indisponiveis.size()) {
                    return indisponiveis.get(pos);
                }
            } catch (NumberFormatException ignored) { }
            System.out.println("Entrada inválida. Tente novamente.");
        }
    }


    private static String tipo(Imovel im) {
        if (im instanceof Casa) {
            return "Casa";
        } else if (im instanceof Apartamento) {
            return "Apartamento";
        } else {
            return "Imóvel";
        }
    }


    public static void cadastrarCasa(Scanner sc, List<Imovel> imoveis) {
        ConsoleUtils.printlnTitulo("Cadastrar Casa");
        String rua    = ConsoleUtils.lerStr(sc, "Rua: ");
        String bairro = ConsoleUtils.lerStr(sc, "Bairro: ");
        String cidade = ConsoleUtils.lerStr(sc, "Cidade: ");
        String estado = ConsoleUtils.lerStr(sc, "Estado: ");
        String cep    = ConsoleUtils.lerStr(sc, "CEP: ");
        int numero    = ConsoleUtils.lerInt(sc, "Número: ");

        String nomeProp = ConsoleUtils.lerStr(sc, "Nome do proprietário: ");
        String telProp  = ConsoleUtils.lerStr(sc, "Telefone do proprietário: ");
        String cpfProp  = ConsoleUtils.lerStr(sc, "CPF do proprietário: ");

        double valor   = ConsoleUtils.lerDouble(sc, "Valor do aluguel (R$): ");
        int metragem   = ConsoleUtils.lerInt(sc, "Metragem (m²): ");

        boolean temAlpendre = ConsoleUtils.lerBool(sc, "Tem alpendre");
        boolean temQuintal  = ConsoleUtils.lerBool(sc, "Tem quintal");

        Casa casa = new Casa(rua, bairro, cidade, estado, cep, numero,
                false, nomeProp, telProp, cpfProp, valor, metragem, temAlpendre, temQuintal);

        imoveis.add(casa);
        System.out.println("Casa cadastrada com sucesso!");
    }

    public static void cadastrarApartamento(Scanner sc, List<Imovel> imoveis) {
        ConsoleUtils.printlnTitulo("Cadastrar Apartamento");
        String rua    = ConsoleUtils.lerStr(sc, "Rua: ");
        String bairro = ConsoleUtils.lerStr(sc, "Bairro: ");
        String cidade = ConsoleUtils.lerStr(sc, "Cidade: ");
        String estado = ConsoleUtils.lerStr(sc, "Estado: ");
        String cep    = ConsoleUtils.lerStr(sc, "CEP: ");
        int numero    = ConsoleUtils.lerInt(sc, "Número (apto): ");

        String nomeProp = ConsoleUtils.lerStr(sc, "Nome do proprietário: ");
        String telProp  = ConsoleUtils.lerStr(sc, "Telefone do proprietário: ");
        String cpfProp  = ConsoleUtils.lerStr(sc, "CPF do proprietário: ");

        double valor   = ConsoleUtils.lerDouble(sc, "Valor do aluguel (R$): ");
        int metragem   = ConsoleUtils.lerInt(sc, "Metragem (m²): ");

        boolean temSacada = ConsoleUtils.lerBool(sc, "Tem sacada");

        Apartamento ap = new Apartamento(rua, bairro, cidade, estado, cep, numero,
                false, nomeProp, telProp, cpfProp, valor, metragem, temSacada);

        imoveis.add(ap);
        System.out.println("Apartamento cadastrado com sucesso!");
    }

    public static void listarImoveis(List<Imovel> imoveis) {
        ConsoleUtils.printlnTitulo("Lista de imóveis");
        if (imoveis.isEmpty()) {
            System.out.println("(vazio)");
            return;
        }

        for (int i = 0; i < imoveis.size(); i++) {
            Imovel im = imoveis.get(i);

            // Endereço
            Endereco e = im.getEndereco();
            String end;
            if (e == null) {
                end = "sem endereço";
            } else {
                end = e.rua() + ", " + im.getNumero() + " - " + e.bairro() + " - " + e.cidade() + "/" + e.estado();
            }

            // Proprietário
            Proprietario p = im.getProprietario();
            String dono;
            if (p == null) {
                dono = "-";
            } else {
                String nome = p.nome();
                if (nome == null || nome.isBlank()) {
                    dono = "-";
                } else {
                    dono = nome;
                }
            }

            System.out.printf("[%d] %s — %s — proprietário: %s — metragem: %dm²%n",i, tipo(im), end, dono, im.getMetragem());
        }
    }

    public static void verDetalhesImovel(Scanner sc, List<Imovel> imoveis) {
        Imovel im = escolherImovel(sc, imoveis);
        if (im == null) return;

        ConsoleUtils.printlnTitulo("Detalhes");
        System.out.println("Tipo: " + tipo(im));

        Endereco e = im.getEndereco();
        String end;
        if (e == null) {
            end = "-";
        } else {
            end = e.rua() + ", " + im.getNumero() + " - " + e.bairro() + " - " + e.cidade() + "/" + e.estado();
        }
        System.out.println("Endereço: " + end);
        System.out.println("Metragem: " + im.getMetragem() + " m²");

        Proprietario p = im.getProprietario();
        String dono;
        if (p == null || p.nome() == null || p.nome().isBlank()) {
            dono = "-";
        } else {
            dono = p.nome();
        }
        System.out.println("Proprietário: " + dono);
        // Status sem acionar prints do domínio
        boolean status = im.estaAlugado();
        String s;
        if (status) {
            s = "Alugado";
        } else {
            s = "Disponível";
        }

        System.out.print("Status: " +s);
        System.out.printf("Valor aluguel atual: R$ %.2f%n", im.getValorAluguelMensal());

    }

    public static void alugarImovel(Scanner sc, List<Imovel> imoveis) {
        Imovel im = escolherImovelDisponivel(sc, imoveis);
        if (im == null) return;

        String nome = ConsoleUtils.lerStr(sc, "Nome do inquilino: ");
        String tel  = ConsoleUtils.lerStr(sc, "Telefone do inquilino: ");
        String cpf  = ConsoleUtils.lerStr(sc, "CPF do inquilino: ");

        Inquilino inq = new Inquilino(nome, tel, cpf);
        im.alugar(inq);
        System.out.println("Ação: Alugar imóvel — concluída.");
    }

    public static void encerrarAluguel(Scanner sc, List<Imovel> imoveis) {
        Imovel im = escolherImovelAlugado(sc, imoveis);
        if (im == null) return;
        im.encerrarAluguel();
        System.out.println("Ação: Encerrar aluguel — concluída.");
    }

    public static void verStatus(Scanner sc, List<Imovel> imoveis) {
        Imovel im = escolherImovel(sc, imoveis);
        if (im == null) return;

        boolean status = im.estaAlugado();
        System.out.print("Ação: Consultar status — ");
        if (status) {
            System.out.println("Alugado.");
        } else {
            System.out.println("Disponível.");
        }

    }

    public static void calcularAluguelMesAtual(Scanner sc, List<Imovel> imoveis) {
        Imovel im = escolherImovel(sc, imoveis);
        if (im == null) return;

        double valor = im.calcularAluguel();
        if (valor > 0) {
            System.out.printf("Resumo: aluguel do mês = R$ %.2f%n", valor);
        } else {
            System.out.println("Resumo: não há valor devido.");
        }
        System.out.println("Ação: Calcular aluguel (mês atual) — concluída.");
    }

    public static void calcularAluguelPorPeriodo(Scanner sc, List<Imovel> imoveis) {
        Imovel im = escolherImovel(sc, imoveis);
        if (im == null) return;

        int meses = ConsoleUtils.lerInt(sc, "Quantos meses? ");
        double valor = im.calcularAluguel(meses);
        if (valor > 0) {
            System.out.printf("Resumo: aluguel para %d meses = R$ %.2f%n", meses, valor);
        } else {
            System.out.println("Resumo: período inválido ou sem cobrança.");
        }
        System.out.println("Ação: Calcular aluguel (período) — concluída.");
    }

    public static void atualizarValorAluguel(Scanner sc, List<Imovel> imoveis) {
        Imovel im = escolherImovel(sc, imoveis);
        if (im == null) return;
        double novo = ConsoleUtils.lerDouble(sc, "Novo valor do aluguel (R$): ");
        im.setValorAluguelMensal(novo);
        System.out.println("Ação: Atualizar valor do aluguel — concluída.");
    }

    public static void verContatoProprietario(Scanner sc, List<Imovel> imoveis) {
        Imovel im = escolherImovel(sc, imoveis);
        if (im == null) return;
        String contato = im.contatoProprietario();
        System.out.println("Contato do proprietário: " + contato);
        System.out.println("Ação: Ver contato do proprietário — concluída.");
    }

    // ---------- Seed & suíte automática ----------
    public static void seedDados(List<Imovel> imoveis) {
        Casa c1 = new Casa("Rua das Flores", "Centro", "São Carlos", "SP", "13560-000", 100,
                false, "ANA MARIA", "16 99999-1111", "123.456.789-00",
                2500.00, 120, true, true);

        Apartamento a1 = new Apartamento("Av. Brasil", "Jardim Azul", "Bordeaux", "FR", "33000", 501,
                false, "JEAN DUPONT", "+33 6 12 34 56 78", "111.222.333-44",
                3200.00, 85, true);

        imoveis.add(c1);
        imoveis.add(a1);
    }

    public static void rodarSuiteAutomatica(List<Imovel> imoveis) {
        ConsoleUtils.printlnTitulo("SUÍTE AUTOMÁTICA — INÍCIO");

        Casa c2 = new Casa("Rua A", "Bairro B", "Cidade C", "SP", "01010-010", 10,
                false, "MARCOS SILVA", "11 98888-7777", "222.333.444-55",
                1800.00, 90, false, true);
        Apartamento a2 = new Apartamento("Rua X", "Bairro Y", "Cidade Z", "RJ", "22000-000", 1203,
                false, "CLARA SOUZA", "21 97777-6666", "999.888.777-66",
                2900.00, 70, false);

        imoveis.add(c2);
        imoveis.add(a2);

        listarImoveis(imoveis);

        System.out.println("\n-- Alugando c2 --");
        c2.alugar(new Inquilino("Pedro Santos", "16 91111-2222", "555.666.777-88"));

        System.out.println("\n-- Tentando alugar c2 novamente --");
        c2.alugar(new Inquilino("Fulano", "00 0000-0000", "000.000.000-00"));

        System.out.println("\n-- Status c2 --");
        c2.estaAlugado();

        System.out.println("\n-- Calcular aluguel (mês atual) c2 --");
        double vMesC2 = c2.calcularAluguel();
        System.out.printf("Resumo (mês atual c2): R$ %.2f%n", vMesC2);

        System.out.println("\n-- Calcular aluguel por período c2 --");
        int[] periodos = {0, 6, 12, 24, 36};
        for (int m : periodos) {
            double total = c2.calcularAluguel(m);
            System.out.printf("Período %d meses: R$ %.2f%n", m, total);
        }

        System.out.println("\n-- Atualizar valor de a2 e calcular por 18 meses --");
        a2.setValorAluguelMensal(3100.00);
        double a2Total = a2.calcularAluguel(18);
        System.out.printf("a2 total (18 meses): R$ %.2f%n", a2Total);

        System.out.println("\n-- Contato do proprietário de c2 --");
        c2.contatoProprietario();

        System.out.println("\n-- Encerrar aluguel de c2 --");
        c2.encerrarAluguel();

        System.out.println("\n-- Encerrar novamente c2 --");
        c2.encerrarAluguel();

        System.out.println("\n-- Status final c2 --");
        c2.estaAlugado();

        ConsoleUtils.printlnTitulo("SUÍTE AUTOMÁTICA — FIM");
    }

}
