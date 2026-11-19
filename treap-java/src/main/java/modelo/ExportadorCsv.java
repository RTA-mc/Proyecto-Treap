package modelo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

public class ExportadorCsv {

	public static void guardar(List<Resultado> resultados, String ruta) throws IOException {
	    java.io.File archivo = new java.io.File(ruta);
	    if (archivo.getParentFile() != null) archivo.getParentFile().mkdirs();
	    try (PrintWriter out = new PrintWriter(archivo, "UTF-8")) {
	        out.println("n,insertar_ns,buscar_ns,eliminar_ns,insertar_sobre_log2n");
	        for (Resultado r : resultados) {
	            out.println(String.format(Locale.US, "%d,%.2f,%.2f,%.2f,%.3f",
	                    r.getN(), r.getInsertarNs(), r.getBuscarNs(),
	                    r.getEliminarNs(), r.getInsertarSobreLog()));
	        }
	    }
	}
	public static void guardarComparacion(List<ResultadoComparacion> res, String ruta) throws IOException {
	    java.io.File archivo = new java.io.File(ruta);
	    if (archivo.getParentFile() != null) archivo.getParentFile().mkdirs();
	    try (PrintWriter out = new PrintWriter(archivo, "UTF-8")) {
	        out.println("n,altura_bst_ordenado,altura_bst_aleatorio,altura_treap,ms_bst_ordenado,ms_bst_aleatorio,ms_treap");
	        for (ResultadoComparacion r : res) {
	            out.println(String.format(Locale.US, "%d,%d,%d,%d,%.3f,%.3f,%.3f",
	                    r.getN(), r.getAlturaBstOrdenado(), r.getAlturaBstAleatorio(), r.getAlturaTreap(),
	                    r.getMsBstOrdenado(), r.getMsBstAleatorio(), r.getMsTreap()));
	        }
	    }
	}
}
   