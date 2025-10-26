import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Memoria memoria = new Memoria(32);

        List<Processo> processos = new ArrayList<>();
        processos.add(new Processo("P1", 5));
        processos.add(new Processo("P2", 4));
        processos.add(new Processo("P3", 2));
        processos.add(new Processo("P4", 5));
        processos.add(new Processo("P5", 8));
        processos.add(new Processo("P6", 3));
        processos.add(new Processo("P7", 5));
        processos.add(new Processo("P8", 8));
        processos.add(new Processo("P9", 2));
        processos.add(new Processo("P10", 6));

        GerenciadorMemoria gm = new GerenciadorMemoria(memoria, processos);
        Random rand = new Random();

        for (int i = 0; i < 30; i++) {
            Processo p = processos.get(rand.nextInt(processos.size()));

            System.out.println("\n--- Operação " + (i + 1) + " com " + p.getId() + " ---");
            if (p.isAlocado()) {
                gm.desalocar(p);
            } else {
                // Você pode trocar o algoritmo aqui:
                gm.firstFit(p);
                // gm.nextFit(p);
                // gm.bestFit(p);
                // gm.worstFit(p);
            }
            memoria.exibirMapa();
        }
    }
}
