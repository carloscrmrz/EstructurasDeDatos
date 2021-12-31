package mx.unam.ciencias.edd.proyecto2.estructuras.elements;

/**
 * A class to wrap all the document into the svg tags, contains a method to add
 * instances of {@Link: Element}.
 */

public class Wrapper {

	private String wrapped;

	public Wrapper() {
		this.wrapped = "<svg xmlns=\"http://www.w3.org/2000/svg\" style=\"background-color: #F0EBEA;\" >\n";
	}

	/**
	 * Method to add a new {@Link: Element} to the SVG document.
	 * @param e the Element instance to be added.
	 */
	public void addElement(Element e) {
		this.wrapped += String.format("  %s\n", e.toString());
	}

	/**
	 * Returns a string with the complete wrapped document.
	 * @return a string with the complete wrapped document.
	 */
	public String toString() {
		return String.format("%s</svg>", this.wrapped);
	}
}
