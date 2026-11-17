package siga;

import java.time.LocalDate;

public abstract class DocumentoAcademico {
    public final String emitir(Aluno aluno) {
        validar(aluno);
        StringBuilder documento = new StringBuilder();
        documento.append(cabecalho());
        documento.append(corpo(aluno));
        documento.append(rodape());
        if (exigeAssinatura()) {
            documento.append(assinatura());
        }
        registrarEmissao(aluno);
        return documento.toString();
    }

    protected abstract String corpo(Aluno aluno);

    protected abstract String tipoDocumento();

    protected boolean exigeAssinatura() {
        return true;
    }

    private void validar(Aluno aluno) {
        if (!aluno.isRegular()) {
            throw new IllegalStateException("Aluno irregular: " + aluno.getMatricula());
        }
    }

    private String cabecalho() {
        return "FATEC DE PORTO FERREIRA\nSistema de Gestao Academica\n---\n";
    }

    private String rodape() {
        return "\n---\nEmitido em " + LocalDate.now();
    }

    private String assinatura() {
        return "\nAssinado digitalmente pela secretaria academica.";
    }

    private void registrarEmissao(Aluno aluno) {
        LogAcademico.registrar(tipoDocumento(), aluno);
    }
}
