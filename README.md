# Imobiliária – Console App (Java)

Pequeno sistema em **Java** para gerenciar imóveis (casas e apartamentos), com cadastro, aluguel/devolução, cálculo de aluguel e listagem. Todo o feedback de ações é exibido no **console**, conforme a especificação.

---

## ✨ Funcionalidades

- Cadastrar **Casa** e **Apartamento** (com `Endereco`, `Proprietario`)
- Listar imóveis (endereço, metragem, proprietário)
- Ver detalhes de um imóvel
- Alugar / Encerrar aluguel (com `Inquilino`)
- Ver status (alugado/disponível)
- Calcular aluguel:
  - **Mês atual** (considera meses **completos** desde o início do contrato)
  - **Período informado** (`meses`)
- Atualizar valor do aluguel
- Ver contato do proprietário
- **Suíte automática** de testes (opção 12) exercita todo o fluxo

> **Observação sobre descontos**: há desconto progressivo conforme **antiguidade do contrato**:  
> `>= 12 meses: -10%`, `>= 24 meses: -20%`, `>= 36 meses: -30%`.

---

## 🧱 Estrutura do Projeto

/src
Main.java // loop principal e roteamento de opções
Menu.java // impressão do menu
ConsoleUtils.java // leitura de entrada e helpers de console
ImovelActions.java // ações que manipulam a lista de imóveis
Imovel.java // classe abstrata base
Casa.java // subclasse de Imovel
Apartamento.java // subclasse de Imovel
Endereco.java // record
Proprietario.java // record
Inquilino.java // record

yaml
Copiar código

> O projeto está **sem `package`** por simplicidade. Se preferir, adicione um pacote e ajuste os `imports`.

---

## 📦 Requisitos

- **Java 17+** (Java 11 também funciona)
- Terminal/console

---

## ▶️ Como Compilar e Executar

No diretório `src` (Linux/macOS):

```bash
# Compilar
javac *.java

# Rodar
java Main
Windows (PowerShell):

powershell
Copiar código
javac *.java
java Main
📋 Menu (opções)
Cadastrar Casa

Cadastrar Apartamento

Listar imóveis

Ver detalhes do imóvel

Alugar imóvel

Encerrar aluguel

Ver status (alugado/disponível)

Calcular aluguel (mês atual)

Calcular aluguel por período (meses)

Atualizar valor do aluguel

Ver contato do proprietário

Rodar SUÍTE AUTOMÁTICA de testes

Sair

A seleção de imóvel é feita pelo número da lista mostrado entre colchetes ([0], [1], …).

🧪 Suíte Automática
Use a opção 12 para executar, sem interação, um roteiro que:

Cria imóveis de exemplo

Aluga, tenta alugar novamente (erro esperado)

Calcula aluguel (mês atual e por período)

Atualiza valor

Encerra aluguel e valida estados

Ótimo para validar rapidamente tudo que foi implementado.

🔎 Detalhes Importantes
Encapsulamento: endereco, alugado, numero são protected em Imovel (como exigido).

Datas: java.time (LocalDate / Period), meses completos pelo toTotalMonths().

Impressões: métodos de domínio imprimem o resultado das ações (requisito).

Dinheiro: atualmente com double (fácil e suficiente para o trabalho).
Melhoria futura: migrar para BigDecimal se quiser precisão monetária total.

Descontos: função calculaDesconto() usa tempo já alugado.
Documentado: o desconto reflete a antiguidade do contrato, não o período solicitado.

🧰 Dicas de Uso
Entrada inválida: seletores e leituras pedem novamente até receber valor válido (nas ações e utils).

Cancelar seleção: o seletor de imóvel pode aceitar c para cancelar (se você usar a versão com cancelamento).

Acentos e formatação: se o console exibir caracteres estranhos, configure UTF-8.

➕ Próximos Passos (opcionais)
ID estável por repositório (Map<Integer, Imovel>) ao invés de índice da lista

Persistência simples (CSV/JSON)

BigDecimal para valores monetários

calcularAluguel(int meses) com desconto por período (caso deseje essa semântica)

