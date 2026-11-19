package controlador;

import java.io.IOException;
import java.util.List;
import modelo.Comparador;
import modelo.ExportadorCsv;
import modelo.Medidor;
import modelo.Resultado;
import modelo.ResultadoComparacion;
import vista.VistaConsola;
import vista.VistaGrafica;

public class Controlador {
    private static final int[] TAMANOS = {1_000, 2_000, 5_000, 10_000, 20_000, 50_000,
                                          100_000, 200_000, 500_000, 1_000_000};
    // El BST ordenado hace ~n²/2 pasos: más de 50.000 se vuelve muy lento
    private static final int[] TAMANOS_COMPARACION = {1_000, 2_000, 5_000, 10_000, 20_000, 50_000};
    private static final String CARPETA = "resultados";

    private final VistaConsola consola = new VistaConsola();
    private final VistaGrafica grafica = new VistaGrafica();
    private List<Resultado> resultados;
    private List<ResultadoComparacion> comparacion;

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
                case 6: correrComparacion(); break;
                case 7: if (hayComparacion()) consola.mostrarTablaComparacion(comparacion); break;
                case 8: if (hayComparacion()) grafica.mostrarComparacion(comparacion); break;
                case 9: if (hayComparacion()) guardarComparacion(); break;
                case 0: consola.mostrarMensaje("Hasta luego."); break;
                default: consola.mostrarMensaje("Opción no válida.");
            }
        } while (op != 0);
        System.exit(0);
    }

    // ---------- Tiempo vs n ----------
    private void correrExperimento() {
        consola.mostrarMensaje("Midiendo... puede tardar un par de minutos.");
        resultados = new Medidor(7).correr(TAMANOS);
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
            consola.mostrarMensaje("PNG guardados en '" + CARPETA + "'.");
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

    // ---------- Treap vs BST ----------
    private void correrComparacion() {
        consola.mostrarMensaje("Comparando Treap vs BST... puede tardar un minuto.");
        comparacion = new Comparador(3).correr(TAMANOS_COMPARACION);
        consola.mostrarMensaje("Listo.");
        consola.mostrarTablaComparacion(comparacion);
    }

    private boolean hayComparacion() {
        if (comparacion == null) {
            consola.mostrarMensaje("Primero corre la comparación (opción 6).");
            return false;
        }
        return true;
    }

    private void guardarComparacion() {
        try {
            grafica.guardarPngComparacion(comparacion, CARPETA);
            ExportadorCsv.guardarComparacion(comparacion, CARPETA + "/comparacion_bst_treap.csv");
            consola.mostrarMensaje("PNG y CSV guardados en '" + CARPETA + "'.");
        } catch (IOException e) {
            consola.mostrarMensaje("No se pudo guardar: " + e.getMessage());
        }
    }
}