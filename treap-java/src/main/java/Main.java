
import java.util.List;
import modelo.Medidor;
import modelo.Resultado;

public class Main {
    public static void main(String[] args) {
        int[] tamanos = {1_000, 10_000, 100_000, 1_000_000};
        Medidor m = new Medidor(7);
        List<Resultado> res = m.correr(tamanos);

        System.out.printf("%10s %12s %12s %12s %12s%n",
                "n", "ins ns/op", "bus ns/op", "eli ns/op", "ins/log2n");
        for (Resultado r : res) {
            System.out.printf("%10d %12.1f %12.1f %12.1f %12.2f%n",
                    r.getN(), r.getInsertarNs(), r.getBuscarNs(),
                    r.getEliminarNs(), r.getInsertarSobreLog());
        }
        System.out.println("(sumidero = " + m.getSumidero() + ")");
    }
}