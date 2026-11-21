class VistaTreap:
    @staticmethod
    def pedir_clave():
        try:
            return int(input("Ingrese clave entera a insertar (-1 para salir): "))
        except ValueError:
            print("Por favor, ingrese un número entero válido.")
            return 0

    @staticmethod
    def mostrar_arbol(recorrido):
        print(f"Treap (In-Orden): {recorrido}\n")