package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Medidor {
	private final int repeticiones;
	private long sumidero;
	public Medidor(int repeticiones) {
		this.repeticiones=repeticiones;
		
	}
	public long getSumidero() {return sumidero;}
	
	public List<Resultado> correr(int[] tamanos){
		calentar();
		List<Resultado> lista=new ArrayList<>();
		for (int n:tamanos) {
			lista.add(medir(n));
		}
		return lista;
	}
	private void calentar() {
		for(int i=0;i<200;i++) {
			ejecutarUna(20_000,1000+i);
		}
	}
	private Resultado medir(int n) {
		double[] ins = new double[repeticiones];
        double[] bus = new double[repeticiones];
        double[] eli = new double[repeticiones];
        for (int r = 0; r < repeticiones; r++) {
            System.gc();
            double[] t = ejecutarUna(n, r);
            ins[r] = t[0];
            bus[r] = t[1];
            eli[r] = t[2];
        }
        return new Resultado(n, mediana(ins), mediana(bus), mediana(eli));
    }
	
	private double[] ejecutarUna(int n, long semilla) {
		int[] clavesInsertar = barajado(n, semilla);
        int[] clavesBuscar = barajado(n, semilla + 7919);
        int[] clavesEliminar = barajado(n, semilla + 104729);
        
        Treap t = new Treap(semilla);
        
        long t0 = System.nanoTime();
        for(int c:clavesInsertar) t.insertar(c);
        long t1 = System.nanoTime();

        int encontradas = 0;
        for (int c : clavesBuscar) if (t.buscar(c)) encontradas++;
        long t2 = System.nanoTime();

        for (int c : clavesEliminar) t.eliminar(c);
        long t3 = System.nanoTime();


        if (encontradas != n) throw new IllegalStateException("Faltaron claves: " + encontradas + " de " + n);
        if (t.tamano() != 0) throw new IllegalStateException("El treap no quedó vacío");

        sumidero += encontradas;

        return new double[] {
            (t1 - t0) / (double) n,
            (t2 - t1) / (double) n,
            (t3 - t2) / (double) n
        };
    }
	private int[] barajado(int n, long semilla) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i;
        Random r = new Random(semilla);
        for (int i = n - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
        return a;
    }

    private double mediana(double[] v) {
        double[] c = v.clone();
        Arrays.sort(c);
        int m = c.length / 2;
        return (c.length % 2 == 1) ? c[m] : (c[m - 1] + c[m]) / 2.0;
    }
}