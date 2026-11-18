
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
		
	}
}
