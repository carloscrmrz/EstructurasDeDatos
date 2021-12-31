package mx.unam.ciencias.edd.proyecto2.estructuras.elements;

/**
 * An abstract class to create and personalize different <a href="https://developer.mozilla.org/en-US/docs/Web/SVG/Element">SVG Elements</a>
 */

public abstract class Element {

	protected StringBuilder content;
	protected String tag;
	protected boolean selfClose;

	// SVG Attributes, see https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute.
	public String fill, stroke, innerContent;
	public float opacity;

	/**
	 * Constructor for an Element. Receives an element tag, and if it's self-closing
	 * and initializates the element tag with its basic properties.
	 *
	 * @param tag the element's tag.
	 * @param selfClose <code>true</code> if the tag self closes, <code>false</code>
	 *                  otherwise.
	 */
	public Element(String tag, boolean selfClose) {
		this.selfClose = selfClose;
		this.tag       = tag;

		String completeTag = selfClose ? String.format("<%s />", tag) : String.format("<%s></%s>", tag, tag);
		this.content   = new StringBuilder(completeTag);
	}

	/**
	 * Sets or replaces attributes for our elements' tags. Receives the attribute name
	 * and it's value and depending if the attribute already exists in our tag it gets
	 * updated or added to the element svg object.
	 * @param attr the attribute name.
	 * @param val  the attribute's value.
	 */
	public void setAttribute(String attr, Object val) {

		String attrWithValue = String.format(" %s=\"%s\"", attr, val);

		// RegEx to find if the attribute is already in the element tag.
		String regex = String.format(" %s=([\"'])(?:\\\\.|[^\\\\])*?\\", attr);
		boolean hasAttr = this.content.toString().split(regex).length > 1;

		if ( hasAttr ) {
			this.content = new StringBuilder(this.content.toString().replaceFirst(regex, attrWithValue));
		} else {       // We insert at the end of the tags' attributes list.
			int offset = this.selfClose ? this.content.length() - 2 : this.content.indexOf(">");
			this.content.insert(offset, attrWithValue);
		}
	}

	/**
	 * Sets or replaces the content of the element tag. Receives a String to set the
	 * inner content of a non self-closing tag.
	 * @param inner the content to be set for the element.
	 */
	public void setInnerContent(String inner) {
		if ( selfClose ) 
			throw new IllegalArgumentException();
		this.content.insert(this.content.indexOf(">") + 1, inner);
		this.innerContent = inner;
	}

	/** 
	 * Adds an element to the content of the element tag. Receives an instance of
	 * {@Link: Element} to add the element to the inner content of a non self-closing
	 * tag.
	 * @param inner the content to be set for the element.
	 */
	public void setInnerContent(Element inner) {
		if ( selfClose ) 
			throw new IllegalArgumentException();
		this.content.insert(this.content.indexOf(">") + 1, "\n\t" + inner.toString());
		this.innerContent += inner.toString();
	}

	/** 
	 * Returns a string representation of the SVG element.
	 * @return a string representation of the SVG element.
	 */
	public String toString() {
		return this.content.toString();
	}
}


