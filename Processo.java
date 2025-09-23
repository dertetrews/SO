class Processo {
    int pid;
    int tempoTotal;   // tempo necessário para terminar
    int TP = 0;       // tempo de processamento já executado
    int CP = 1;       // contador de programa (sempre TP + 1)
    String estado = "PRONTO"; // estado inicial
    int NES = 0;      // nº de vezes que fez E/S
    int N_CPU = 0;    // nº de vezes que usou a CPU

    public Processo(int pid, int tempoTotal) {
        this.pid = pid;
        this.tempoTotal = tempoTotal;
    }

    public boolean terminou() {
        return TP >= tempoTotal;
    }
}