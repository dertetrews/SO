import java.util.Arrays;

public class Memoria {
    private int[] blocos;

    public Memoria(int tamanho) {
        this.blocos = new int[tamanho];
        Arrays.fill(blocos, 0);
    }

    public int[] getBlocos() { return blocos; }

    public void exibirMapa() {
        System.out.print("Mapa de memória: [");
        for (int i = 0; i < blocos.length; i++) {
            System.out.print(blocos[i]);
            if (i < blocos.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public void ocupar(int inicio, int tamanho) {
        for (int i = inicio; i < inicio + tamanho; i++) {
            blocos[i] = 1;
        }
    }

    public void liberar(int inicio, int tamanho) {
        for (int i = inicio; i < inicio + tamanho; i++) {
            blocos[i] = 0;
        }
    }

    public boolean blocoLivre(int pos) {
        return blocos[pos] == 0;
    }
}
