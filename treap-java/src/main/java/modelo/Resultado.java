package modelo;

public class Resultado {
	private final int n;
	private final double insertarNs;
	private final double buscarNs;
	private final double eliminarNs ;
	public Resultado(int n,double insertarNs,double buscarNs,double eliminarNs ) {
		this.n = n;
        this.insertarNs = insertarNs;
        this.buscarNs = buscarNs;
        this.eliminarNs = eliminarNs;
		
	}
	  public int getN() { return n; }
	    public double getInsertarNs() { return insertarNs; }
	    public double getBuscarNs() { return buscarNs; }
	    public double getEliminarNs() { return eliminarNs; }

	    private static double log2(int n) {
	        return Math.log(n) / Math.log(2);
	    }
	    public double getInsertarSobreLog() { return insertarNs / log2(n); }
	    public double getBuscarSobreLog() { return buscarNs / log2(n); }
	    public double getEliminarSobreLog() { return eliminarNs / log2(n); }

}
