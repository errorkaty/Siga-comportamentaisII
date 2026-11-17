package siga;

public class Cancelada implements EstadoMatricula {
    @Override
    public void confirmar(Matricula matricula) {
        throw erro("confirmar");
    }

    @Override
    public void trancar(Matricula matricula) {
        throw erro("trancar");
    }

    @Override
    public void cancelar(Matricula matricula) {
        throw erro("cancelar");
    }

    @Override
    public String getSituacao() {
        return "CANCELADA";
    }

    private IllegalStateException erro(String operacao) {
        return new IllegalStateException("Nao e possivel " + operacao
                + " uma matricula " + getSituacao());
    }
}
