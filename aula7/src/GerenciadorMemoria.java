import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GerenciadorMemoria {
    private Memoria memoria;
    private List<Processo> processos;
    private int ultimoIndice = 0;
    private Random rand = new Random();

    public GerenciadorMemoria(Memoria memoria, List<Processo> processos) {
        this.memoria = memoria;
        this.processos = processos;
    }

    // ---------- ALGORITMOS DE ALOCAÇÃO ----------

    public boolean firstFit(Processo p) {
        int[] blocos = memoria.getBlocos();
        for (int i = 0; i <= blocos.length - p.getTamanho(); i++) {
            boolean livre = true;
            for (int j = 0; j < p.getTamanho(); j++) {
                if (blocos[i + j] == 1) {
                    livre = false;
                    break;
                }
            }
            if (livre) {
                memoria.ocupar(i, p.getTamanho());
                p.setAlocado(true);
                p.setInicio(i);
                System.out.println(p.getId() + " alocado no bloco " + i + " (First Fit)");
                return true;
            }
        }
        System.out.println("Falha ao alocar " + p.getId());
        return false;
    }

    public boolean nextFit(Processo p) {
        int[] blocos = memoria.getBlocos();
        int inicioBusca = ultimoIndice;

        for (int count = 0; count < blocos.length; count++) {
            int i = (inicioBusca + count) % blocos.length;
            if (i + p.getTamanho() > blocos.length) continue;

            boolean livre = true;
            for (int j = 0; j < p.getTamanho(); j++) {
                if (blocos[i + j] == 1) {
                    livre = false;
                    break;
                }
            }
            if (livre) {
                memoria.ocupar(i, p.getTamanho());
                p.setAlocado(true);
                p.setInicio(i);
                ultimoIndice = i + p.getTamanho();
                System.out.println(p.getId() + " alocado no bloco " + i + " (Next Fit)");
                return true;
            }
        }
        System.out.println("Falha ao alocar " + p.getId());
        return false;
    }

    public boolean bestFit(Processo p) {
        int[] blocos = memoria.getBlocos();
        int melhorInicio = -1;
        int menorSobra = Integer.MAX_VALUE;

        for (int i = 0; i <= blocos.length - p.getTamanho(); i++) {
            if (blocos[i] == 0) {
                int tamanhoLivre = 0;
                while (i + tamanhoLivre < blocos.length && blocos[i + tamanhoLivre] == 0) {
                    tamanhoLivre++;
                }
                if (tamanhoLivre >= p.getTamanho() && tamanhoLivre < menorSobra) {
                    menorSobra = tamanhoLivre;
                    melhorInicio = i;
                }
                i += tamanhoLivre;
            }
        }

        if (melhorInicio != -1) {
            memoria.ocupar(melhorInicio, p.getTamanho());
            p.setAlocado(true);
            p.setInicio(melhorInicio);
            System.out.println(p.getId() + " alocado no bloco " + melhorInicio + " (Best Fit)");
            return true;
        }

        System.out.println("Falha ao alocar " + p.getId());
        return false;
    }

    public boolean worstFit(Processo p) {
        int[] blocos = memoria.getBlocos();
        int piorInicio = -1;
        int maiorSobra = -1;

        for (int i = 0; i <= blocos.length - p.getTamanho(); i++) {
            if (blocos[i] == 0) {
                int tamanhoLivre = 0;
                while (i + tamanhoLivre < blocos.length && blocos[i + tamanhoLivre] == 0) {
                    tamanhoLivre++;
                }
                if (tamanhoLivre >= p.getTamanho() && tamanhoLivre > maiorSobra) {
                    maiorSobra = tamanhoLivre;
                    piorInicio = i;
                }
                i += tamanhoLivre;
            }
        }

        if (piorInicio != -1) {
            memoria.ocupar(piorInicio, p.getTamanho());
            p.setAlocado(true);
            p.setInicio(piorInicio);
            System.out.println(p.getId() + " alocado no bloco " + piorInicio + " (Worst Fit)");
            return true;
        }

        System.out.println("Falha ao alocar " + p.getId());
        return false;
    }

    // ---------- DESALOCAR ----------
    public void desalocar(Processo p) {
        if (p.isAlocado()) {
            memoria.liberar(p.getInicio(), p.getTamanho());
            p.setAlocado(false);
            p.setInicio(-1);
            System.out.println(p.getId() + " desalocado da memória.");
        }
    }
}
