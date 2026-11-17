package siga;

public class Confirmada implements EstadoMatricula {
    @Override
    public void confirmar(Matricula matricula) {
        matricula.mudarPara(new Concluida());
    }

    @Override
    public void trancar(Matricula matricula) {
        matricula.mudarPara(new Trancada());
    }

    @Override
    public void cancelar(Matricula matricula) {
        matricula.mudarPara(new Cancelada());
    }

    @Override
    public String getSituacao() {
        return "CONFIRMADA";
    }

}
