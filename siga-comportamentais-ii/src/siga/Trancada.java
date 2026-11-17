package siga;

public class Trancada implements EstadoMatricula {
    @Override
    public void confirmar(Matricula matricula) {
        matricula.mudarPara(new Confirmada());
    }

    @Override
    public void trancar(Matricula matricula) {
        throw erro("trancar");
    }

    @Override
    public void cancelar(Matricula matricula) {
        matricula.mudarPara(new Cancelada());
    }

    @Override
    public String getSituacao() {
        return "TRANCADA";
    }

    private IllegalStateException erro(String operacao) {
        return new IllegalStateException("Nao e possivel " + operacao
                + " uma matricula " + getSituacao());
    }
}
