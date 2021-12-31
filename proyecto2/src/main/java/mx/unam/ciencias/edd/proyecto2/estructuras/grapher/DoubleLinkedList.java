package mx.unam.ciencias.edd.proyecto2.estructuras.grapher;

import mx.unam.ciencias.edd.proyecto2.estructuras.elements.Group;
import mx.unam.ciencias.edd.proyecto2.estructuras.elements.Rectangle;
import mx.unam.ciencias.edd.proyecto2.estructuras.elements.Text;

import mx.unam.ciencias.edd.Lista;

public class DoubleLinkedList extends Structure {

	private int[] data;
	private Rectangle rect;

	public DoubleLinkedList(int[] data) {
		this.rawData = data;
		this.data    = data;
		this.title   = "Arreglo";
	}

	public String generateXML() {
		addTitleToDocument(10, 10);

		rect = new Rectangle(60, 100);

		int i = 0;
		for ( int n : this.rawData ) {
			Group g = new Group(this.x + 100 * i++, this.y);
			g.addTag(new Text(String.valueOf(n)));
			g.addTag(rect);
			wrapper.addElement(g);
		}
	}
}
