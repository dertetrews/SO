public class App {
    public static void main(String[] args) {
        int[] tempos = {10000, 5000, 7000, 3000, 3000, 8000, 2000, 5000, 4000, 10000};

        SimuladorSO simulador = new SimuladorSO(tempos);
        simulador.rodar();
    }
}
