package modelo;

import java.util.ArrayDeque;

public class BST {
    private Nodo raiz;
    private int tamano;

    public void insertar(int clave) {
        if (raiz == null) {
            raiz = new Nodo(clave, 0);
            tamano++;
            return;
        }
        Nodo n = raiz;
        while (true) {
            if (clave < n.clave) {
                if (n.izq == null) { n.izq = new Nodo(clave, 0); tamano++; return; }
                n = n.izq;
            } else if (clave > n.clave) {
                if (n.der == null) { n.der = new Nodo(clave, 0); tamano++; return; }
                n = n.der;
            } else {
                return;
            }
        }
    }

    public boolean buscar(int clave) {
        Nodo n = raiz;
        while (n != null) {
            if (clave == n.clave) return true;
            n = (clave < n.clave) ? n.izq : n.der;
        }
        return false;
    }

    public int tamano() { return tamano; }

    public int altura() {
        if (raiz == null) return 0;
        ArrayDeque<Nodo> nodos = new ArrayDeque<>();
        ArrayDeque<Integer> niveles = new ArrayDeque<>();
        nodos.push(raiz);
        niveles.push(1);
        int max = 0;
        while (!nodos.isEmpty()) {
            Nodo n = nodos.pop();
            int nivel = niveles.pop();
            if (nivel > max) max = nivel;
            if (n.izq != null) { nodos.push(n.izq); niveles.push(nivel + 1); }
            if (n.der != null) { nodos.push(n.der); niveles.push(nivel + 1); }
        }
        return max;
    }
}