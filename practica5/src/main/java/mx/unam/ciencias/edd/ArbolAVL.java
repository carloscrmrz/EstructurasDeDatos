package mx.unam.ciencias.edd;

/**
 * <p>Clase para árboles AVL.</p>
 *
 * <p>Un árbol AVL cumple que para cada uno de sus vértices, la diferencia entre
 * la áltura de sus subárboles izquierdo y derecho está entre -1 y 1.</p>
 */
public class ArbolAVL<T extends Comparable<T>>
    extends ArbolBinarioOrdenado<T> {

    /**
     * Clase interna protegida para vértices.
     */
    protected class VerticeAVL extends Vertice {

        /** La altura del vértice. */
        public int altura;

        /**
         * Constructor único que recibe un elemento.
         * @param elemento el elemento del vértice.
         */
        public VerticeAVL(T elemento) {
		super(elemento);
		altura = 0;
        }

        /**
         * Regresa la altura del vértice.
         * @return la altura del vértice.
         */
        @Override public int altura() {
		return altura;
        }

        /**
         * Regresa una representación en cadena del vértice AVL.
         * @return una representación en cadena del vértice AVL.
         */
        @Override public String toString() {
		return String.format("%s %d/%d",
			             elemento, 
				     altura, 
				     getBalance(this));	
        }

        /**
         * Compara el vértice con otro objeto. La comparación es
         * <em>recursiva</em>.
         * @param objeto el objeto con el cual se comparará el vértice.
         * @return <code>true</code> si el objeto es instancia de la clase
         *         {@link VerticeAVL}, su elemento es igual al elemento de éste
         *         vértice, los descendientes de ambos son recursivamente
         *         iguales, y las alturas son iguales; <code>false</code> en
         *         otro caso.
         */
        @Override public boolean equals(Object objeto) {
            if (objeto == null || getClass() != objeto.getClass())
                return false;
            @SuppressWarnings("unchecked") VerticeAVL vertice = (VerticeAVL)objeto;
	    return ( altura == vertice.altura &&
			    super.equals(vertice));
        }
    }

    /**
     * Constructor sin parámetros. Para no perder el constructor sin parámetros
     * de {@link ArbolBinarioOrdenado}.
     */
    public ArbolAVL() { super(); }

    /**
     * Construye un árbol AVL a partir de una colección. El árbol AVL tiene los
     * mismos elementos que la colección recibida.
     * @param coleccion la colección a partir de la cual creamos el árbol AVL.
     */
    public ArbolAVL(Coleccion<T> coleccion) {
	    super(coleccion);
    }

    /**
     * Construye un nuevo vértice, usando una instancia de {@link VerticeAVL}.
     * @param elemento el elemento dentro del vértice.
     * @return un nuevo vértice con el elemento recibido dentro del mismo.
     */
    @Override protected Vertice nuevoVertice(T elemento) {
        return new VerticeAVL(elemento);
    }

    /**
     * Agrega un nuevo elemento al árbol. El método invoca al método {@link
     * ArbolBinarioOrdenado#agrega}, y después balancea el árbol girándolo como
     * sea necesario.
     * @param elemento el elemento a agregar.
     */
    @Override public void agrega(T elemento) {
    		super.agrega(elemento);
		VerticeAVL padre = (VerticeAVL) ultimoAgregado.padre;
		rebalancea(padre);
    }

    /**
     * Elimina un elemento del árbol. El método elimina el vértice que contiene
     * el elemento, y gira el árbol como sea necesario para rebalancearlo.
     * @param elemento el elemento a eliminar del árbol.
     */
    @Override public void elimina(T elemento) {
    		VerticeAVL v = (VerticeAVL) busca(elemento);
		if ( v == null) 
			return;

		if ( v.izquierdo != null && v.derecho != null )
			v = (VerticeAVL) intercambiaEliminable(v);

		VerticeAVL padre = (VerticeAVL) v.padre;
		elementos--;
		eliminaVertice(v);

		rebalancea(padre);
    }


    private int altura(VerticeAVL v) {
	    if ( v == null ) 
		    return -1;

	    int alturaIzq = altura((VerticeAVL)v.izquierdo) + 1;
	    int alturaDer = altura((VerticeAVL)v.derecho) + 1;

	    if ( alturaIzq >= alturaDer ) 
		    return alturaIzq;
	    return alturaDer;
    }

    private int getBalance(VerticeAVL v) {
	    return v == null ? 0: altura((VerticeAVL) v.izquierdo) - altura((VerticeAVL) v.derecho);
    }
    
    private void actualizaAltura(VerticeAVL v) {
	    v.altura = altura(v);
    }

    private void rebalancea(VerticeAVL v) {
	    if ( v == null ) 
		    return;

	    actualizaAltura(v);
	    VerticeAVL padreV = (VerticeAVL) v.padre;

	    VerticeAVL hijoDerechoV = (VerticeAVL) v.derecho;
	    VerticeAVL hijoIzquierdoV = (VerticeAVL) v.izquierdo;

	    //VerticeAVL hijoDerechoHDV;
	    //VerticeAVL hijoIzquierdoHIV;

	    //	hijoDerechoHDV = (VerticeAVL) hijoDerechoV.derecho;
	    //	hijoIzquierdoHIV = (VerticeAVL) hijoIzquierdoV.izquierdo;

	    if ( getBalance(v) == -2 ) {
		    if ( getBalance(hijoDerechoV) == 1 ) {
			    super.giraDerecha(hijoDerechoV);
			    hijoDerechoV = (VerticeAVL) v.derecho;
			    VerticeAVL hijoDerechoHDV = (VerticeAVL) hijoDerechoV.derecho;

			    actualizaAltura(hijoDerechoV);
			    actualizaAltura(hijoDerechoHDV);
		    }
	            super.giraIzquierda(v);

		    // Por conveniencia no actualizaremos las referencias.
		    actualizaAltura(v); // Ahora hijo izquierdo del vertice referenciado como hijoDerechoV.
		    actualizaAltura(hijoDerechoV); // Padre de v
	    }

	    if ( getBalance(v) == 2 ) {
		    if ( getBalance(hijoIzquierdoV) == -1 ) {
			    super.giraIzquierda(hijoIzquierdoV);
			    hijoIzquierdoV = (VerticeAVL) v.izquierdo;
			    VerticeAVL hijoIzquierdoHIV = (VerticeAVL) hijoIzquierdoV.izquierdo;

                            actualizaAltura(hijoIzquierdoV);
			    actualizaAltura(hijoIzquierdoHIV);
		    }			    
		    super.giraDerecha(v);
		    // Por conveniencia no actualizaremos las referencias.
		    actualizaAltura(v);  // Ahora hijo derecho del vertice referenciado como hijoIzquierdoV.
		    actualizaAltura(hijoIzquierdoV); // Padre de v.
	    }

	    rebalancea(padreV);			
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles AVL
     * no pueden ser girados a la derecha por los usuarios de la clase, porque
     * se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraDerecha(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles AVL no  pueden " +
                                                "girar a la izquierda por el " +
                                                "usuario.");
    }

    /**
     * Lanza la excepción {@link UnsupportedOperationException}: los árboles AVL
     * no pueden ser girados a la izquierda por los usuarios de la clase, porque
     * se desbalancean.
     * @param vertice el vértice sobre el que se quiere girar.
     * @throws UnsupportedOperationException siempre.
     */
    @Override public void giraIzquierda(VerticeArbolBinario<T> vertice) {
        throw new UnsupportedOperationException("Los árboles AVL no  pueden " +
                                                "girar a la derecha por el " +
                                                "usuario.");
    }
}
