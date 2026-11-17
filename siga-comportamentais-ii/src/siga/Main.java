package siga;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SIGA - Aula 13: Template Method e State ===");

        Aluno regular = new Aluno("2024001", "Maria Silva");
        regular.setCargaCumprida(560);
        Aluno irregular = new Aluno("2024002", "Joao Souza");
        irregular.setRegular(false);

        System.out.println();
        System.out.println("1) Documentos emitidos pelo mesmo roteiro");
        System.out.println(recuar(new HistoricoEscolar().emitir(regular)));
        System.out.println(recuar(new AtestadoFrequencia().emitir(regular)));
        System.out.println(recuar(new DeclaracaoVinculo().emitir(regular)));

        System.out.println();
        System.out.println("2) Documento para aluno irregular");
        try {
            new DeclaracaoVinculo().emitir(irregular);
        } catch (IllegalStateException e) {
            System.out.println("   [erro] " + e.getMessage());
        }
        System.out.println("   Registros no log: " + LogAcademico.getRegistros());

        System.out.println();
        System.out.println("3) Ciclo de vida da matricula");
        Matricula matricula = new Matricula(regular, "DSM-2A");
        exibirSituacao("situacao inicial", matricula);
        matricula.confirmar();
        exibirSituacao("apos confirmar", matricula);
        matricula.trancar();
        exibirSituacao("apos trancar", matricula);
        matricula.confirmar();
        exibirSituacao("apos confirmar", matricula);
        matricula.cancelar();
        exibirSituacao("apos cancelar", matricula);
        try {
            matricula.confirmar();
        } catch (IllegalStateException e) {
            System.out.println("   [erro] " + e.getMessage());
        }

        Matricula concluida = new Matricula(regular, "DSM-2B");
        concluida.confirmar();
        concluida.confirmar();
        exibirSituacao("apos concluir", concluida);
    }

    private static void exibirSituacao(String descricao, Matricula matricula) {
        System.out.println("   " + descricao + ": " + matricula.getSituacao());
    }

    private static String recuar(String texto) {
        StringBuilder saida = new StringBuilder();
        for (String linha : texto.split("\n")) {
            saida.append("     ").append(linha).append("\n");
        }
        return saida.toString().stripTrailing();
    }
}
