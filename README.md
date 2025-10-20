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


