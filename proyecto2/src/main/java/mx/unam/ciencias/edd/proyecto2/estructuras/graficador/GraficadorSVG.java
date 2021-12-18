package mx.unam.ciencias.edd.proyecto2.estructuras.graficador;

/**
 * <p> Interface to create svg documents, it contains methods to create the header and
 * other important tags so the document can work properly, also methods that can draw 
 * the Data Structures. Data Structures can only contain instances of {@link #Integer}.
 * </p>
 */

public interface GraficadorSVG {

	/**
	 * Creates a string with the XML document header tag.
	 * @return a string with the XML document header tag.
	 */
	public String writeHeader() {
		return "<?xml version=\"1.0\" encoding\"UTF-8\" ?>";
	}

	/** 
	 * Creates a string with the outermost svg root element tag.
	 * @return a string with the outermost svg root element tag.
	 */
	public String writeOpeningRoot() {
		return "<svg width=\"100%\" height=\"100%\" xmlns=\"http://www.w3.org/2000/svg\">";
	}

	/**
	 * Creates a string with the outermost svg root element tag.
	 * @param width the width of the displayed viewport in px.
	 * @param height the height of the displayed viweport in px.
	 * @return a string with the outermost svg root element tag.
	 */
	public String writeOpeningRoot(int width, int height) {
		return String.format("<svg width=\"%dpx\" height=\"%dpx\" xmlns=\"http://www.w3.org/2000/svg\">",
			       		width,
				       	height);
	}

	/**
	 * Creates a string closing the outermost svg root element tag.
	 * @return a string closing the outermost svg root element tag.
	 */
	public String writeClosingRoot() {
		return "</svg>";
	}
	/**
	 * Crea un nuevo vertice en formato svg.
	 * 
	 */
	public String dibujaEstructura();


}
