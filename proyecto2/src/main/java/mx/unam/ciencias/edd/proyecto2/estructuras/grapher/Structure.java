package mx.unam.ciencias.edd.proyecto2.estructuras.grapher;

import mx.unam.ciencias.edd.proyecto2.estructuras.elements.Wrapper;
import mx.unam.ciencias.edd.proyecto2.estructuras.elements.Text;

public abstract class Structure {

	protected Wrapper wrapper = new Wrapper();
	protected int[] rawData;                     // Data extracted.
	protected String title;			     // Title for the XML Doc
	
	/* Starting position for the elements in the SVG. */
	protected int x = 100;
	protected int y = 150;

	/**
	 * Returns a string representation of the SVG, to be used in the
	 * XML Document. Implementation is dependent of each data struct
	 */
	public abstract String generateXML();

	/**
	 * Adds a Text tag with the title of the document, specifying the
	 * coordinates in which the title will be positioned.
	 * @param int x the coordinate in the x-axis.
	 * @param int y the coordinate in the y-axis.
	 */
	protected void addTitleToDocument(int x, int y) {
		Text XMLTitle = new Text(x,y, this.title);
		wrapper.addElement(XMLTitle);
	}

	/** 
	 * Creates a string representation of the XML Document.
	 * @return a string representation of the XML Document.
	 */
	public String toString() {
		return wrapper.toString();
	}
}		
