package mx.unam.ciencias.edd.proyecto2.estructuras.elements;

/**
 * Group element. For more info about the element go to:
 * https://developer.mozilla.org/en-US/docs/Web/SVG/Element/g
 */

public class Group extends Element {

	// Initializes the group object.
	public Group() { super("g", false); }

	/**
	 * Initializes the group object with given coordinates.
	 * @param x the coordinate in the x-axis.
	 * @param y the coordinate in the y-axis.
	 */
	public Group(int x, int y) {
		super("g", false);

		this.setAttribute("x", x);
		this.setAttribute("y", y);
	}

	/** 
	 * Adds another element tag in the innerContent.
	 * @param e the element object to be added.
	 */
	public void addTag(Element e) {
		if ( !(e instanceof Element ) ) 
			throw new IllegalArgumentException();

		this.setInnerContent(e);
	}
}
