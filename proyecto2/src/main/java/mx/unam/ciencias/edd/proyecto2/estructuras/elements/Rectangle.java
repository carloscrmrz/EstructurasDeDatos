package mx.unam.ciencias.edd.proyecto2.estructuras.elements;

/**
 * Rectangle element. For more info about the element go to: 
 * https://developer.mozilla.org/en-US/docs/Web/SVG/Element/rect
 */

public class Rectangle extends Element {

	// Initializes the element object with a self-closing tag.
	public Rectangle() { super("rect", true); }

	/** Initializes the element object with a self-closing tag
	 *  and with height and width. To be used with the group tag.
	 *  @param height the rectangle height.
	 *  @param width the rectangle width.
	 */
	public Rectangle(int height, int width) {
		super("rect", true);

		this.setAttribute("height", height);
		this.setAttribute("width", width);
	}	

	/** Initializes the element object with a self-closing tag,
	 *  with height and width and with position x and y.
	 *  @param height the rectangle height.
	 *  @param width the rectangle width.
	 *  @param x the position on the x-axis.
	 *  @param y the position on the y-axis.
	 */
	public Rectangle(int height, int width) {
		super("rect", true);

		this.setAttribute("height", height);
		this.setAttribute("width", width);
		this.setAttribute("x", x);
		this.setAttribute("y", y);
	}
}	
