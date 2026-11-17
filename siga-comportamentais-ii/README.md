# SIGA — Padrões comportamentais II: Template Method e State

**Técnicas de Programação II (TP2) · Aula 13** — CST em Desenvolvimento de Software Multiplataforma · Fatec de Porto Ferreira

## Como compilar e executar

Pré-requisito: JDK 17 ou superior.

```bash
javac -encoding UTF-8 -d bin siga-comportamentais-ii/src/siga/*.java
java -cp bin siga.Main
```

## 1. Rito duplicado e inversão identificada

`HistoricoEscolar`, `AtestadoFrequencia` e `DeclaracaoVinculo` repetiam o mesmo rito: validar o aluno, montar cabeçalho, montar corpo, incluir rodapé e registrar a emissão.

Na declaração de vínculo, `LogAcademico.registrar` era chamado antes da validação. Por isso, ao emitir para o aluno irregular `2024002`, o programa lançava o erro de irregularidade, mas o log já registrava uma declaração que não foi emitida.

## 2. Esqueleto único de emissão

`DocumentoAcademico` concentra o rito no método `emitir(Aluno)`, declarado como `final`. A ordem é validação, cabeçalho, corpo, rodapé, assinatura quando aplicável e registro no log. Como o método é final, as subclasses não conseguem trocar essa ordem.

O cabeçalho, o rodapé, a validação e o registro aparecem somente nessa classe. Para um aluno irregular, a validação interrompe a emissão antes do registro, impedindo rastros no log.

## 3. Documentos convertidos

`HistoricoEscolar`, `DeclaracaoVinculo` e `AtestadoFrequencia` estendem `DocumentoAcademico` e implementam apenas o corpo e o tipo do documento.

O gancho `exigeAssinatura()` tem valor padrão `true`. `AtestadoFrequencia` o sobrescreve com `false`, por isso é emitido sem assinatura digital.

## 4. Ciclo de vida da matrícula

`EstadoMatricula` define as operações de confirmar, trancar e cancelar. As classes `PreMatriculada`, `Confirmada`, `Trancada` e `Cancelada` contêm as transições e os erros permitidos em cada fase.

`Matricula` mantém o estado atual e seus métodos públicos `confirmar`, `trancar` e `cancelar` delegam em uma linha para ele, sem condicionais. As transições são feitas pelas próprias classes de estado, por exemplo `matricula.mudarPara(new Confirmada())`.

Isso diferencia State de Strategy: no Strategy, uma política calcula ou executa uma variação do comportamento; no State, cada estado conhece a próxima fase e troca o estado do próprio contexto.

## 5. Estado concluída

`Concluida` foi acrescentada como fase final, alcançável a partir de `Confirmada`. Para essa ampliação, foram tocados dois arquivos do ciclo de vida já estabelecido: `Concluida.java`, com a nova fase, e `Confirmada.java`, que passa a direcionar para ela. Na versão inicial, uma fase nova exigiria alterar os três métodos condicionais de `Matricula`.

## Verificação

A execução demonstra a emissão dos três documentos, a ausência de registro adicional para o aluno irregular, as transições de pré-matrícula até cancelamento e a transição até concluída. A saída está em [docs/execucao.txt](../docs/execucao.txt).
