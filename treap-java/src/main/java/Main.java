
import java.util.Random;

import modelo.Treap;

public class Main {

	public static void main(String[] args) {
		Treap t = new Treap(42);
		Random r = new Random(1);
		
		for(int i=0; i<100_000;i++) t.insertar(r.nextInt(1_000_000));
		  System.out.println("tamaño = " + t.tamano());
	        System.out.println("altura = " + t.altura());
	        System.out.println("válido = " + t.esValido());
		
	
	Treap p = new Treap(7);
    for (int i = 1; i <= 10; i++) p.insertar(i * 10);
    System.out.println("buscar 50 (debe ser true)   = " + p.buscar(50));
    System.out.println("buscar 55 (debe ser false)  = " + p.buscar(55));


    p.eliminar(50);
    System.out.println("buscar 50 tras eliminar     = " + p.buscar(50) + " (debe ser false)");
    System.out.println("tamaño tras eliminar        = " + p.tamano() + " (debe ser 9)");
    System.out.println("válido tras eliminar        = " + p.esValido());

   
    Treap o = new Treap(3);
    for (int i = 1; i <= 100_000; i++) o.insertar(i);
    System.out.println("altura con claves ordenadas = " + o.altura() + " (un BST normal daría 100000)"+o.tamano());
	}
}