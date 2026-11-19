package modelo;

public class ResultadoComparacion {
    private final int n;
    private final int alturaBstOrdenado, alturaBstAleatorio, alturaTreap;
    private final double msBstOrdenado, msBstAleatorio, msTreap;

    public ResultadoComparacion(int n,
            int alturaBstOrdenado, int alturaBstAleatorio, int alturaTreap,
            double msBstOrdenado, double msBstAleatorio, double msTreap) {
        this.n = n;
        this.alturaBstOrdenado = alturaBstOrdenado;
        this.alturaBstAleatorio = alturaBstAleatorio;
        this.alturaTreap = alturaTreap;
        this.msBstOrdenado = msBstOrdenado;
        this.msBstAleatorio = msBstAleatorio;
        this.msTreap = msTreap;
    }

    public int getN() { return n; }
    public int getAlturaBstOrdenado() { return alturaBstOrdenado; }
    public int getAlturaBstAleatorio() { return alturaBstAleatorio; }
    public int getAlturaTreap() { return alturaTreap; }
    public double getMsBstOrdenado() { return msBstOrdenado; }
    public double getMsBstAleatorio() { return msBstAleatorio; }
    public double getMsTreap() { return msTreap; }
}