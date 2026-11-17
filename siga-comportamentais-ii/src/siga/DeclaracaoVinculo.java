package siga;

public class DeclaracaoVinculo extends DocumentoAcademico {
    @Override
    protected String corpo(Aluno aluno) {
        return "DECLARACAO DE VINCULO\nDeclaramos que " + aluno.getNome()
                + " possui vinculo ativo com esta instituicao.";
    }

    @Override
    protected String tipoDocumento() {
        return "DECLARACAO";
    }
}
