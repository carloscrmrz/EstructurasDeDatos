package mx.unam.ciencias.edd.proyecto2.estructuras.elements;

/** 
 * Circle element. For more info about the element go to:
 * https://developer.mozilla.org/en-US/docs/Web/SVG/Element/circle
 */

public class Circle extends Element {

	// Initializes the element object with a self-closing tag.
	public Circle() { super("circle", true); }

	/** Initialize the element object with a self-closing tag
	 *  and a certain radius r. To be used with the group tag.
	 *  @param r the radius length.
	 */
	public Circle(int r) {
		super("circle", true);

		this.setAttribute("r", r);
	}

	/** Initialize the element object with a self-closing tag
	 *  a certain radius r, and positions x and y.
	 */
        public Circle(int x, int y, int r) {
		super("circle", true);
		
		this.setAttribute("cx", x);
		this.setAttribute("cy", y);
		this.setAttribute("r", r);
	}		
}

