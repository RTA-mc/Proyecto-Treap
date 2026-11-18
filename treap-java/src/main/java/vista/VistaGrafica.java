package vista;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import modelo.Resultado;

public class VistaGrafica {

    /** Gráfica 1: ns por operación vs n. */
    private JFreeChart graficaTiempos(List<Resultado> res) {
        XYSeries ins = new XYSeries("Insertar");
        XYSeries bus = new XYSeries("Buscar");
        XYSeries eli = new XYSeries("Eliminar");
        for (Resultado r : res) {
            ins.add(r.getN(), r.getInsertarNs());
            bus.add(r.getN(), r.getBuscarNs());
            eli.add(r.getN(), r.getEliminarNs());
        }
        XYSeriesCollection datos = new XYSeriesCollection();
        datos.addSeries(ins);
        datos.addSeries(bus);
        datos.addSeries(eli);
        return construir("Treap: tiempo por operación vs n",
                "n (número de claves, escala log)", "ns por operación", datos);
    }

    /** Gráfica 2: tiempo / log2(n). Si es O(log n), debería verse casi plana. */
    private JFreeChart graficaRazon(List<Resultado> res) {
        XYSeries ins = new XYSeries("Insertar / log2(n)");
        XYSeries bus = new XYSeries("Buscar / log2(n)");
        XYSeries eli = new XYSeries("Eliminar / log2(n)");
        for (Resultado r : res) {
            ins.add(r.getN(), r.getInsertarSobreLog());
            bus.add(r.getN(), r.getBuscarSobreLog());
            eli.add(r.getN(), r.getEliminarSobreLog());
        }
        XYSeriesCollection datos = new XYSeriesCollection();
        datos.addSeries(ins);
        datos.addSeries(bus);
        datos.addSeries(eli);
        return construir("Verificación de O(log n): tiempo / log2(n)",
                "n (número de claves, escala log)", "ns por operación / log2(n)", datos);
    }

    private JFreeChart construir(String titulo, String ejeX, String ejeY, XYSeriesCollection datos) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                titulo, ejeX, ejeY, datos, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = chart.getXYPlot();
        plot.setDomainAxis(new LogAxis(ejeX));
        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultShapesVisible(true);
        return chart;
    }

    public void mostrar(List<Resultado> res) {
        mostrarVentana("Tiempo vs n", graficaTiempos(res));
        mostrarVentana("Tiempo / log2(n)", graficaRazon(res));
    }

    private void mostrarVentana(String titulo, JFreeChart chart) {
        SwingUtilities.invokeLater(() -> {
            JFrame f = new JFrame(titulo);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setContentPane(new ChartPanel(chart));
            f.setSize(800, 520);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }

    public void guardarPng(List<Resultado> res, String carpeta) throws IOException {
        File dir = new File(carpeta);
        dir.mkdirs();
        ChartUtils.saveChartAsPNG(new File(dir, "tiempo_vs_n.png"), graficaTiempos(res), 1000, 600);
        ChartUtils.saveChartAsPNG(new File(dir, "razon_log2n.png"), graficaRazon(res), 1000, 600);
    }
}