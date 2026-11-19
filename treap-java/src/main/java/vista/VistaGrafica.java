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
import modelo.ResultadoComparacion;

public class VistaGrafica {


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
    private JFreeChart construirLogY(String titulo, String ejeX, String ejeY, XYSeriesCollection datos) {
        JFreeChart chart = construir(titulo, ejeX, ejeY, datos);
        chart.getXYPlot().setRangeAxis(new LogAxis(ejeY));
        return chart;
    }

    private JFreeChart graficaAlturas(List<ResultadoComparacion> res) {
        XYSeries bo = new XYSeries("BST (claves ordenadas)");
        XYSeries ba = new XYSeries("BST (claves aleatorias)");
        XYSeries tr = new XYSeries("Treap (claves ordenadas)");
        for (ResultadoComparacion r : res) {
            bo.add(r.getN(), r.getAlturaBstOrdenado());
            ba.add(r.getN(), r.getAlturaBstAleatorio());
            tr.add(r.getN(), r.getAlturaTreap());
        }
        XYSeriesCollection d = new XYSeriesCollection();
        d.addSeries(bo); d.addSeries(ba); d.addSeries(tr);
        return construirLogY("Altura del árbol", "n (escala log)", "altura (escala log)", d);
    }

    private JFreeChart graficaTiemposComparacion(List<ResultadoComparacion> res) {
        XYSeries bo = new XYSeries("BST (claves ordenadas)");
        XYSeries ba = new XYSeries("BST (claves aleatorias)");
        XYSeries tr = new XYSeries("Treap (claves ordenadas)");
        for (ResultadoComparacion r : res) {
            bo.add(r.getN(), r.getMsBstOrdenado());
            ba.add(r.getN(), r.getMsBstAleatorio());
            tr.add(r.getN(), r.getMsTreap());
        }
        XYSeriesCollection d = new XYSeriesCollection();
        d.addSeries(bo); d.addSeries(ba); d.addSeries(tr);
        return construirLogY("Tiempo total de insertar n claves", "n (escala log)", "ms (escala log)", d);
    }

    public void mostrarComparacion(List<ResultadoComparacion> res) {
        mostrarVentana("Altura", graficaAlturas(res));
        mostrarVentana("Tiempo de inserción", graficaTiemposComparacion(res));
    }

    public void guardarPngComparacion(List<ResultadoComparacion> res, String carpeta) throws IOException {
        File dir = new File(carpeta);
        dir.mkdirs();
        ChartUtils.saveChartAsPNG(new File(dir, "comparacion_altura.png"), graficaAlturas(res), 1000, 600);
        ChartUtils.saveChartAsPNG(new File(dir, "comparacion_tiempo.png"), graficaTiemposComparacion(res), 1000, 600);
    }
}