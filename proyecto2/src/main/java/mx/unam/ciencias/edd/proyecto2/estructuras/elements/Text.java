package mx.unam.ciencias.edd.proyecto2.estructuras.elements;

public class Text extends Element {

	// Initializes the text element.
	public Text() { super("text", false); }

	/** 
	 * Initializes the text element with a given string.
	 * To be used in a {@Link: Group} tag.
	 * @param text the text to be used.
	 */
	public Text(String text) {
		super("text", false);
		this.setText(text);
	}

	/**
	 * Initializes the text element with a given string.
	 * @param x the coordinate in the x-axis.
	 * @param y the coordinate in the y-axis.
	 * @param text the text to be used.
	 */
	public Text(int x, int y, String text) {
	      super("text", false);

	      this.setAttribute("x", x);
	      this.setAttribute("y", y);
	      this.setText(text);
	}

	public String getText() {
		return innerContent;
	}

	public void setText(String text) {
		this.setInnerContent(text);
	}
}
