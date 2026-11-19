package vista;

import java.util.List;
import java.util.Scanner;
import modelo.Resultado;
import modelo.ResultadoComparacion;

public class VistaConsola {
    private final Scanner sc = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println();
        System.out.println("===== EXPERIMENTOS CON TREAP =====");
        System.out.println("--- Tiempo vs n ---");
        System.out.println("1. Correr experimento");
        System.out.println("2. Ver tabla");
        System.out.println("3. Ver gráficas en ventana");
        System.out.println("4. Guardar gráficas como PNG");
        System.out.println("5. Exportar a CSV");
        System.out.println("--- Treap vs BST (entrada ordenada) ---");
        System.out.println("6. Correr comparación");
        System.out.println("7. Ver tabla");
        System.out.println("8. Ver gráficas en ventana");
        System.out.println("9. Guardar PNG y CSV");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    public void mostrarTablaComparacion(List<ResultadoComparacion> res) {
        System.out.printf("%8s | %10s %10s %10s | %12s %12s %12s%n",
                "n", "h BST ord", "h BST ale", "h Treap", "ms BST ord", "ms BST ale", "ms Treap");
        for (ResultadoComparacion r : res) {
            System.out.printf("%8d | %10d %10d %10d | %12.2f %12.2f %12.2f%n",
                    r.getN(), r.getAlturaBstOrdenado(), r.getAlturaBstAleatorio(), r.getAlturaTreap(),
                    r.getMsBstOrdenado(), r.getMsBstAleatorio(), r.getMsTreap());
        }
    }

    public void mostrarTabla(List<Resultado> res) {
        System.out.printf("%10s %12s %12s %12s %12s%n",
                "n", "ins ns/op", "bus ns/op", "eli ns/op", "ins/log2n");
        for (Resultado r : res) {
            System.out.printf("%10d %12.1f %12.1f %12.1f %12.2f%n",
                    r.getN(), r.getInsertarNs(), r.getBuscarNs(),
                    r.getEliminarNs(), r.getInsertarSobreLog());
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}