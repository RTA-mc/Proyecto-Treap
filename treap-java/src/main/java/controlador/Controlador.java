package controlador;

import java.io.IOException;
import java.util.List;
import modelo.ExportadorCsv;
import modelo.Medidor;
import modelo.Resultado;
import vista.VistaConsola;
import vista.VistaGrafica;

public class Controlador {
    private static final int[] TAMANOS = {1_000, 2_000, 5_000, 10_000, 20_000, 50_000,
                                          100_000, 200_000, 500_000, 1_000_000};
    private static final String CARPETA = "resultados";

    private final VistaConsola consola = new VistaConsola();
    private final VistaGrafica grafica = new VistaGrafica();
    private List<Resultado> resultados;

    public void iniciar() {
        int op;
        do {
            op = consola.mostrarMenu();
            switch (op) {
                case 1: correrExperimento(); break;
                case 2: if (hayDatos()) consola.mostrarTabla(resultados); break;
                case 3: if (hayDatos()) grafica.mostrar(resultados); break;
                case 4: if (hayDatos()) guardarPng(); break;
                case 5: if (hayDatos()) exportarCsv(); break;
                case 0: consola.mostrarMensaje("Hasta luego."); break;
                default: consola.mostrarMensaje("Opción no válida.");
            }
        } while (op != 0);
        System.exit(0); 
    }

    private void correrExperimento() {
        consola.mostrarMensaje("Midiendo... puede tardar un par de minutos.");
        Medidor medidor = new Medidor(7);
        resultados = medidor.correr(TAMANOS);
        consola.mostrarMensaje("Listo.");
        consola.mostrarTabla(resultados);
    }

    private boolean hayDatos() {
        if (resultados == null) {
            consola.mostrarMensaje("Primero corre el experimento (opción 1).");
            return false;
        }
        return true;
    }

    private void guardarPng() {
        try {
            grafica.guardarPng(resultados, CARPETA);
            consola.mostrarMensaje("PNG guardados en la carpeta '" + CARPETA + "'.");
        } catch (IOException e) {
            consola.mostrarMensaje("No se pudo guardar: " + e.getMessage());
        }
    }

    private void exportarCsv() {
        try {
            ExportadorCsv.guardar(resultados, CARPETA + "/tiempo_vs_n.csv");
            consola.mostrarMensaje("CSV guardado en '" + CARPETA + "/tiempo_vs_n.csv'.");
        } catch (IOException e) {
            consola.mostrarMensaje("No se pudo exportar: " + e.getMessage());
        }
    }
}