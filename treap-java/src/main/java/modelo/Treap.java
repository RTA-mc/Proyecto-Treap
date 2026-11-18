package modelo;

import java.util.Random;

public class Treap {
	private Nodo raiz;
	private int tamanio;
	private final Random rnd;
	
	public Treap() { this(new Random()); }
    public Treap(long semilla) { this(new Random(semilla)); }
    private Treap(Random rnd) { this.rnd = rnd; }
    
    
    public void insertar(int clave) {
    	raiz= insertar(raiz, clave);
    }
    
    public Nodo insertar(Nodo n, int clave) {
    	if(n==null) {
    		tamanio++;
    		return new Nodo(clave,rnd.nextInt());
    	}
    	if(clave<n.clave) {
    		n.izq= insertar(n.izq, clave);
    		if(n.izq.prioridad>n.prioridad) n= rotarDerecha(n);
    		}else if(clave>n.clave) {
    			n.der= insertar(n.der,clave);
    			if(n.der.prioridad>n.prioridad) n = rotarIzquierda(n);
    		}
    	return n;
    }
    public boolean buscar(int clave) {
    	Nodo n=raiz;
    	while(n!=null) {
    		if(clave==n.clave) return true;
    		n= (clave<n.clave) ? n.izq : n.der;
    	}
    	return false;
    }
    
    public void eliminar(int clave) {
    	raiz= eliminar(raiz,clave);
    }
    public Nodo eliminar(Nodo n , int clave) {
    	if(n==null) return null;;
    	if(clave<n.clave) {
    		n.izq=eliminar(n.izq,clave);
    	}else if (clave>n.clave) {
    		n.der=eliminar(n.der,clave);
    	}
    	else {
    		if(n.izq==null) {tamanio--; return n.der;}
    		if(n.der==null) {tamanio--;return n.izq;}
    		if(n.izq.prioridad>n.der.prioridad) {
    			n=rotarDerecha(n);
    			n.der=eliminar(n.der,clave);
    		}else {
    			n=rotarIzquierda(n);
    			n.izq=eliminar(n.izq,clave);
    			
    		}
    		
    	}
    	return n;
    }
    private Nodo rotarDerecha(Nodo y ) {
    	Nodo x =y.izq;
    	y.izq=x.der;
    	x.der=y;
    	return x;
    }
    private Nodo rotarIzquierda(Nodo x) {
        Nodo y = x.der;
        x.der = y.izq;
        y.izq = x;
        return y;
    }
    public int tamano() { return tamanio; }

    public int altura() { return altura(raiz); }

    private int altura(Nodo n) {
        return n == null ? 0 : 1 + Math.max(altura(n.izq), altura(n.der));
    }

    public boolean esValido() {
        return validar(raiz, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validar(Nodo n, long min, long max) {
        if (n == null) return true;
        if (n.clave <= min || n.clave >= max) return false;
        if (n.izq != null && n.izq.prioridad > n.prioridad) return false;
        if (n.der != null && n.der.prioridad > n.prioridad) return false;
        return validar(n.izq, min, n.clave) && validar(n.der, n.clave, max);
    }
}
	
