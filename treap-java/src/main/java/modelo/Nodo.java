package modelo;

public class Nodo {
	int clave;
	int prioridad;
	Nodo izq;
	Nodo der;
	Nodo(int clave, int prioridad){
		this.clave=clave;
		this.prioridad= prioridad;
	}

}
