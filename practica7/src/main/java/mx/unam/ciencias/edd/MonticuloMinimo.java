package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase para montículos mínimos (<i>min heaps</i>).
 */
public class MonticuloMinimo<T extends ComparableIndexable<T>>
    implements Coleccion<T>, MonticuloDijkstra<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Índice del iterador. */
        private int indice;

        /* Nos dice si hay un siguiente elemento. */
        @Override public boolean hasNext() {
		return indice < elementos;
        }

        /* Regresa el siguiente elemento. */
        @Override public T next() {
		if ( !indiceValido(indice) ) 
			throw new NoSuchElementException();
		return arbol[indice++];
        }
    }

    /* Clase estática privada para adaptadores. */
    private static class Adaptador<T  extends Comparable<T>>
        implements ComparableIndexable<Adaptador<T>> {

        /* El elemento. */
        private T elemento;
        /* El índice. */
        private int indice;

        /* Crea un nuevo comparable indexable. */
        public Adaptador(T elemento) {
		this.elemento = elemento;
		indice = -1;
        }

        /* Regresa el índice. */
        @Override public int getIndice() {
		return indice;
        }

        /* Define el índice. */
        @Override public void setIndice(int indice) {
		this.indice = indice;
        }

        /* Compara un adaptador con otro. */
        @Override public int compareTo(Adaptador<T> adaptador) {
		return ( elemento.compareTo(adaptador.elemento) );
        }
    }

    /* El número de elementos en el arreglo. */
    private int elementos;
    /* Usamos un truco para poder utilizar arreglos genéricos. */
    private T[] arbol;

    /* Truco para crear arreglos genéricos. Es necesario hacerlo así por cómo
       Java implementa sus genéricos; de otra forma obtenemos advertencias del
       compilador. */
    @SuppressWarnings("unchecked") private T[] nuevoArreglo(int n) {
        return (T[])(new ComparableIndexable[n]);
    }

    /**
     * Constructor sin parámetros. Es más eficiente usar {@link
     * #MonticuloMinimo(Coleccion)} o {@link #MonticuloMinimo(Iterable,int)},
     * pero se ofrece este constructor por completez.
     */
    public MonticuloMinimo() {
	    arbol = nuevoArreglo(100);
    }

    /**
     * Constructor para montículo mínimo que recibe una colección. Es más barato
     * construir un montículo con todos sus elementos de antemano (tiempo
     * <i>O</i>(<i>n</i>)), que el insertándolos uno por uno (tiempo
     * <i>O</i>(<i>n</i> log <i>n</i>)).
     * @param coleccion la colección a partir de la cuál queremos construir el
     *                  montículo.
     */
    public MonticuloMinimo(Coleccion<T> coleccion) {
	    this(coleccion, coleccion.getElementos());
    }

    private void intercambiaIndices(T e1, T e2) {
	    int temp = e2.getIndice();
	    
	    arbol[e1.getIndice()] = e2;
	    arbol[e2.getIndice()] = e1;

	    e2.setIndice(e1.getIndice());
	    e1.setIndice(temp);
    }

    private boolean indiceValido(int i) {
	    return !( i < 0 || i > elementos - 1); 
    }

    private void acomodaAbajo(T e) {
	    if ( e == null ) 
		    return;
	    int hI = e.getIndice() * 2 + 1;
	    int hD = e.getIndice() * 2 + 2;

	    if ( !indiceValido(hI) && !indiceValido(hD) )
		    return;

	    int min = hD;
	    if ( indiceValido(hI) ) {
		    if ( indiceValido(hD) ) {
			    if ( arbol[hI].compareTo(arbol[hD]) < 0 )
				    min = hI;
		    } else { 
			    min = hI;
		    }
	    }

	    if ( arbol[min].compareTo(e) < 0 ) {
		    intercambiaIndices(e, arbol[min]);
		    acomodaAbajo(e);
	    }
    }

    private void acomodaArriba(T e) {
	    int indicePadre = e.getIndice() - 1;
	    indicePadre = ( indicePadre == -1 ) ? -1 : indicePadre / 2;

	    if ( !indiceValido(indicePadre) || arbol[indicePadre].compareTo(e) < 0 ) 
		    return;

	    intercambiaIndices(arbol[indicePadre], e);
	    acomodaArriba(e);
    }
    /**
     * Constructor para montículo mínimo que recibe un iterable y el número de
     * elementos en el mismo. Es más barato construir un montículo con todos sus
     * elementos de antemano (tiempo <i>O</i>(<i>n</i>)), que el insertándolos
     * uno por uno (tiempo <i>O</i>(<i>n</i> log <i>n</i>)).
     * @param iterable el iterable a partir de la cuál queremos construir el
     *                 montículo.
     * @param n el número de elementos en el iterable.
     */
    public MonticuloMinimo(Iterable<T> iterable, int n) {
	    arbol = nuevoArreglo(n);
	    elementos = n;
	    int i = 0;
	    for ( T e : iterable ) {
		    arbol[i] = e;
		    e.setIndice(i++);
	    }

	    for ( int m = ((n-1)/2); m >= 0; m-- ) 
		    acomodaAbajo(arbol[m]);			   
    }

    /**
     * Agrega un nuevo elemento en el montículo.
     * @param elemento el elemento a agregar en el montículo.
     */
    @Override public void agrega(T elemento) {
    	if ( elementos == arbol.length ) {
		T[] temp = nuevoArreglo(elementos * 2);
		for ( int i = 0; i < elementos; i++ ) {
		       temp[i] = arbol[i];
		}
 		arbol = temp;
	}

	arbol[elementos] = elemento;	
	elemento.setIndice(elementos);
	elementos++;
	acomodaArriba(elemento);
    }

    /**
     * Elimina el elemento mínimo del montículo.
     * @return el elemento mínimo del montículo.
     * @throws IllegalStateException si el montículo es vacío.
     */
    @Override public T elimina() {
    	if ( elementos == 0 ) 
		throw new IllegalStateException();

	T e = arbol[0];
	intercambiaIndices(arbol[0], arbol[elementos - 1]);
	elementos--;
	arbol[elementos].setIndice(-1);
	arbol[elementos] = null;
	acomodaAbajo(arbol[0]);

	return e;
    }

    /**
     * Elimina un elemento del montículo.
     * @param elemento a eliminar del montículo.
     */
    @Override public void elimina(T elemento) {
    	int indice = elemento.getIndice();
	if ( !indiceValido(indice) )
		return;

	intercambiaIndices(arbol[indice],arbol[elementos - 1]);
	elementos--;
	arbol[elementos] = null;
	elemento.setIndice(-1);
	reordena(arbol[indice]);
    }

    /**
     * Nos dice si un elemento está contenido en el montículo.
     * @param elemento el elemento que queremos saber si está contenido.
     * @return <code>true</code> si el elemento está contenido,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
    	int indice = elemento.getIndice();
	if ( !indiceValido(indice) )
		return false;

	return ( arbol[indice].compareTo(elemento) == 0 );
    }

    /**
     * Nos dice si el montículo es vacío.
     * @return <code>true</code> si ya no hay elementos en el montículo,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean esVacia() {
    	return ( elementos == 0 );
    }

    /**
     * Limpia el montículo de elementos, dejándolo vacío.
     */
    @Override public void limpia() {
	for ( int i = 0; i < elementos; i++) 
		arbol[i] = null;
	elementos = 0;
    }

   /**
     * Reordena un elemento en el árbol.
     * @param elemento el elemento que hay que reordenar.
     */
    @Override public void reordena(T elemento) {
    	if ( elemento == null )
		return;
	int indice = elemento.getIndice();
	int indicePadre = (indice - 1)/2;

	if ( !indiceValido(indicePadre) || arbol[indicePadre].compareTo(elemento) <= 0 ) 
		acomodaAbajo(elemento);
	else 
		acomodaArriba(elemento);
    }

    /**
     * Regresa el número de elementos en el montículo mínimo.
     * @return el número de elementos en el montículo mínimo.
     */
    @Override public int getElementos() {
    	return elementos;
    }

    /**
     * Regresa el <i>i</i>-ésimo elemento del árbol, por niveles.
     * @param i el índice del elemento que queremos, en <em>in-order</em>.
     * @return el <i>i</i>-ésimo elemento del árbol, por niveles.
     * @throws NoSuchElementException si i es menor que cero, o mayor o igual
     *         que el número de elementos.
     */
    @Override public T get(int i) {
    	if ( !indiceValido(i) )
		throw new NoSuchElementException();

	return arbol[i];
    }

    /**
     * Regresa una representación en cadena del montículo mínimo.
     * @return una representación en cadena del montículo mínimo.
     */
    @Override public String toString() {
	String s = "";
	for ( int i = 0; i < elementos; i++ ) 
		s += String.format("%s, ", arbol[i]);
	return s;
    }

    /**
     * Nos dice si el montículo mínimo es igual al objeto recibido.
     * @param objeto el objeto con el que queremos comparar el montículo mínimo.
     * @return <code>true</code> si el objeto recibido es un montículo mínimo
     *         igual al que llama el método; <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") MonticuloMinimo<T> monticulo =
            (MonticuloMinimo<T>)objeto;
	if ( elementos != monticulo.elementos ) 
		return false;
	for ( int i = 0; i < elementos; i++ ) {
		if ( arbol[i].compareTo(monticulo.arbol[i]) != 0 )
			return false;
	}

	return true;
    }

    /**
     * Regresa un iterador para iterar el montículo mínimo. El montículo se
     * itera en orden BFS.
     * @return un iterador para iterar el montículo mínimo.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Ordena la colección usando HeapSort.
     * @param <T> tipo del que puede ser el arreglo.
     * @param coleccion la colección a ordenar.
     * @return una lista ordenada con los elementos de la colección.
     */
    public static <T extends Comparable<T>>
    Lista<T> heapSort(Coleccion<T> coleccion) {
	    Lista<Adaptador<T>> L1 = new Lista<Adaptador<T>>();
	    for ( T e : coleccion ) {
		    Adaptador<T> a = new Adaptador<T>(e);
		    L1.agrega(a);
	    }

	    Lista<T> L2 = new Lista<T>();
	    MonticuloMinimo<Adaptador<T>> mm = new MonticuloMinimo<Adaptador<T>>(L1);
	    while ( mm.elementos != 0 ) 
		    L2.agrega(mm.elimina().elemento);

	    return L2;
    }
}
