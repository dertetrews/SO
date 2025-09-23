import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SimuladorSO {
    private static final int QUANTUM = 1000;
    private Random random = new Random();

    private Queue<Processo> prontos = new LinkedList<>();
    private List<Processo> bloqueados = new ArrayList<>();

    public SimuladorSO(int[] tempos) {
        for (int i = 0; i < tempos.length; i++) {
            prontos.add(new Processo(i, tempos[i]));
        }
    }

    private void salvarContexto(Processo p, String proximoEstado) {
        try (FileWriter fw = new FileWriter("tabela_processos.txt", true)) {
            fw.write("PID=" + p.pid +
                    " TP=" + p.TP +
                    " CP=" + p.CP +
                    " EP=" + p.estado +
                    " NES=" + p.NES +
                    " N_CPU=" + p.N_CPU +
                    " >>> " + proximoEstado + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void verificarBloqueados() {
        Iterator<Processo> it = bloqueados.iterator();
        while (it.hasNext()) {
            Processo b = it.next();
            if (random.nextInt(100) < 30) {
                b.estado = "PRONTO";
                prontos.add(b);
                it.remove();
            }
        }
    }

    private void executarProcesso(Processo p) {
        p.estado = "EXECUTANDO";
        p.N_CPU++;

        int ciclos = 0;
        boolean bloqueou = false;

        while (ciclos < QUANTUM && !p.terminou()) {
            p.TP++;
            p.CP = p.TP + 1;
            ciclos++;

            if (random.nextInt(100) < 1) {
                p.estado = "BLOQUEADO";
                p.NES++;
                salvarContexto(p, "BLOQUEADO");
                bloqueados.add(p);
                bloqueou = true;
                break;
            }
        }

        if (p.terminou()) {
            p.estado = "TERMINADO";
            System.out.println("PID=" + p.pid +
                    " TP=" + p.TP +
                    " CP=" + p.CP +
                    " NES=" + p.NES +
                    " N_CPU=" + p.N_CPU);
        } else if (!bloqueou) {
            p.estado = "PRONTO";
            salvarContexto(p, "PRONTO");
            prontos.add(p);
        }
    }

    public void rodar() {
        while (!prontos.isEmpty() || !bloqueados.isEmpty()) {
            verificarBloqueados();

            if (prontos.isEmpty()) continue;

            Processo p = prontos.poll();
            executarProcesso(p);
        }
    }
}
