package mx.unam.ciencias.edd.proyecto2;

/**
 * Enumeracion para Estructuras de Datos.
 */
public enum EstructurasDeDatos {
	/* Por omision tendremos ninguna */
	NINGUNA,
	/* Lista */
	LISTA,
	/* Cola */ 
	COLA,
	/* Pila */
	PILA,
	/* Arbol Binario Completo */
	ARBOL_BINARIO_COMPLETO,
	/* Arbol Binario Ordenado */
	ARBOL_BINARIO_ORDENADO,
	/* Arbol Rojinegro */
	ARBOL_ROJINEGRO,
	/* Arbol AVL */
	ARBOL_AVL,
	/* Grafica */
	GRAFICA,
	/* Monticulo Minimo */ 
	MONTICULO_MINIMO;

	/** 
	 * Regresa una representacion en cadena de la estructura
	 * para ser usada en la aplicacion.
	 * @return una representacion en cadena de la estructura.
	 */
	@Override public String toString() { 
		String s = "";
		switch (this) {
			case LISTA:
				s = "Lista";
				break;
			case COLA:
				s = "Cola";
				break;
			case PILA:
				s = "Pila";
				break;
			case ARBOL_BINARIO_COMPLETO:
				s = "Arbol Binario Completo";
				break;
			case ARBOL_BINARIO_ORDENADO:
				s = "Arbol Binario Ordenado";
				break;
			case ARBOL_ROJINEGRO:
				s = "Arbol Rojinegro";
				break;
			case ARBOL_AVL:
				s = "Arbol AVL";
				break;
			case GRAFICA: 
				s = "Grafica";
				break;
			case MONTICULO_MINIMO:
				s = "Monticulo Minimo";
				break;
			default:
				s = "Sin Seleccionar";
				break;
			}
		return s;
	}
}
