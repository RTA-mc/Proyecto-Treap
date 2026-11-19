package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Comparador {
    private final int repeticiones;
    private long sumidero;

    public Comparador(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public long getSumidero() { return sumidero; }

    public List<ResultadoComparacion> correr(int[] tamanos) {
        for (int i = 0; i < 5; i++) ejecutarUna(2_000, 100 + i); // calentamiento
        List<ResultadoComparacion> lista = new ArrayList<>();
        for (int n : tamanos) lista.add(medir(n));
        return lista;
    }

    private ResultadoComparacion medir(int n) {
        double[][] v = new double[6][repeticiones]; // 0-2 alturas, 3-5 tiempos en ms
        for (int r = 0; r < repeticiones; r++) {
            System.gc();
            double[] x = ejecutarUna(n, r);
            for (int k = 0; k < 6; k++) v[k][r] = x[k];
        }
        return new ResultadoComparacion(n,
                (int) mediana(v[0]), (int) mediana(v[1]), (int) mediana(v[2]),
                mediana(v[3]), mediana(v[4]), mediana(v[5]));
    }


    private double[] ejecutarUna(int n, long semilla) {
        int[] ordenadas = new int[n];
        for (int i = 0; i < n; i++) ordenadas[i] = i;
        int[] aleatorias = barajado(n, semilla);

        BST bstOrd = new BST();
        long t0 = System.nanoTime();
        for (int c : ordenadas) bstOrd.insertar(c);
        long t1 = System.nanoTime();

        BST bstAle = new BST();
        for (int c : aleatorias) bstAle.insertar(c);
        long t2 = System.nanoTime();

        Treap treap = new Treap(semilla);
        for (int c : ordenadas) treap.insertar(c);
        long t3 = System.nanoTime();

        if (bstOrd.tamano() != n || bstAle.tamano() != n || treap.tamano() != n)
            throw new IllegalStateException("Alguna estructura no quedó con " + n + " claves");
        sumidero += bstOrd.tamano() + bstAle.tamano() + treap.tamano();

        return new double[] {
            bstOrd.altura(), bstAle.altura(), treap.altura(),
            (t1 - t0) / 1e6, (t2 - t1) / 1e6, (t3 - t2) / 1e6
        };
    }

    private int[] barajado(int n, long semilla) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i;
        Random r = new Random(semilla);
        for (int i = n - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
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