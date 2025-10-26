public class Processo {
    private String id;
    private int tamanho;
    private boolean alocado;
    private int inicio;

    public Processo(String id, int tamanho) {
        this.id = id;
        this.tamanho = tamanho;
        this.alocado = false;
        this.inicio = -1;
    }

    public String getId() { return id; }
    public int getTamanho() { return tamanho; }
    public boolean isAlocado() { return alocado; }
    public int getInicio() { return inicio; }

    public void setAlocado(boolean alocado) { this.alocado = alocado; }
    public void setInicio(int inicio) { this.inicio = inicio; }
}
