import random

class NodoTreap:
    def __init__(self, clave):
        self.clave = clave
        # Asignamos una prioridad aleatoria para cumplir la regla del Max-Heap
        self.prioridad = random.randint(1, 100)
        self.izquierda = None
        self.derecha = None

class Treap:
    def __init__(self):
        self.raiz = None

    # Rotación a la derecha para mantener el balance del Heap
    def _rotar_derecha(self, y):
        x = y.izquierda
        y.izquierda = x.derecha
        x.derecha = y
        return x

    # Rotación a la izquierda
    def _rotar_izquierda(self, x):
        y = x.derecha
        x.derecha = y.izquierda
        y.izquierda = x
        return y

    def insertar(self, clave):
        self.raiz = self._insertar_rec(self.raiz, clave)

    def _insertar_rec(self, nodo, clave):
        if not nodo:
            return NodoTreap(clave)

        # Regla de Árbol Binario de Búsqueda (BST)
        if clave < nodo.clave:
            nodo.izquierda = self._insertar_rec(nodo.izquierda, clave)
            # Si se rompe la regla del Max-Heap, rotamos
            if nodo.izquierda.prioridad > nodo.prioridad:
                nodo = self._rotar_derecha(nodo)
        elif clave > nodo.clave:
            nodo.derecha = self._insertar_rec(nodo.derecha, clave)
            if nodo.derecha.prioridad > nodo.prioridad:
                nodo = self._rotar_izquierda(nodo)
        return nodo

    def obtener_in_orden(self):
        resultado = []
        self._in_orden(self.raiz, resultado)
        return " ".join(resultado)

    def _in_orden(self, nodo, resultado):
        if nodo:
            self._in_orden(nodo.izquierda, resultado)
            resultado.append(f"[Clave: {nodo.clave} | Prio: {nodo.prioridad}]")
            self._in_orden(nodo.derecha, resultado)