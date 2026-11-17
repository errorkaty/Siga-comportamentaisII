package siga;

public class AtestadoFrequencia extends DocumentoAcademico {
    @Override
    protected String corpo(Aluno aluno) {
        return "ATESTADO DE FREQUENCIA\n" + aluno.getNome();
    }

    @Override
    protected String tipoDocumento() {
        return "ATESTADO";
    }

    @Override
    protected boolean exigeAssinatura() {
        return false;
    }
}
