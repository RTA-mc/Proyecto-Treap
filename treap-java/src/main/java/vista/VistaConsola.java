package vista;

import java.util.List;
import java.util.Scanner;
import modelo.Resultado;

public class VistaConsola {
    private final Scanner sc = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println();
        System.out.println("===== EXPERIMENTOS CON TREAP =====");
        System.out.println("1. Correr experimento tiempo vs n");
        System.out.println("2. Ver tabla de resultados");
        System.out.println("3. Ver gráfica en ventana");
        System.out.println("4. Guardar gráficas como PNG");
        System.out.println("5. Exportar resultados a CSV");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
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