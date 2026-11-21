import time
import random
import sys

# Aumentamos el límite de recursión para soportar árboles grandes en Python
sys.setrecursionlimit(2000000)

from model import Treap

def ejecutar_benchmark():
    tamanos = [1_000, 2_000, 5_000, 10_000, 20_000, 50_000,
               100_000, 200_000, 500_000, 1_000_000]

    print("=" * 50)
    print(f"{'TAMAÑO (N)':<15} | {'TIEMPO (Segundos)':<20}")
    print("=" * 50)

    for n in tamanos:
        # Creamos un nuevo Treap para cada prueba
        arbol = Treap()
        
        # Generamos una lista de n números aleatorios únicos (o revueltos)
        datos = [random.randint(1, 10_000_000) for _ in range(n)]

        # Medimos el tiempo de inserción de los N elementos
        inicio = time.time()
        for clave in datos:
            arbol.insertar(clave)
        fin = time.time()

        tiempo_total = fin - inicio
        print(f"{n:<15} | {tiempo_total:.4f} s")

    print("=" * 50)
    print("¡Prueba de rendimiento finalizada!")

if __name__ == "__main__":
    ejecutar_benchmark()