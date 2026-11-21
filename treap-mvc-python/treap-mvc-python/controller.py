from model import Treap
from view import VistaTreap 

class ControladorTreap:
    def __init__(self):
        self.modelo = Treap()
        self.vista = VistaTreap()

    def iniciar(self):
        while True:
            clave = self.vista.pedir_clave()
            if clave == -1:
                print("Saliendo del programa...")
                break
            self.modelo.insertar(clave)
            self.vista.mostrar_arbol(self.modelo.obtener_in_orden())