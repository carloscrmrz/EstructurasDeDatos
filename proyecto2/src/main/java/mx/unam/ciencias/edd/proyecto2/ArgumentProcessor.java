package mx.unam.ciencias.edd.proyecto2;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import mx.unam.ciencias.edd.Lista;

public class ArgumentProcessor {

	private int[] rawData;
	private EstructurasDeDatos edd;
	private String inputFilePath;
	private String outputFilePath;

	public ArgumentProcessor(String[] args) {
		if ( args.length > 0 ) {
			for ( int i = 0; i < args.length; i++ ) {
			       if ( args[i].indexOf("-") != -1 ) { // Flag.
			       		if ( args[i].equals("-o") ) {
						i++;
						if ( i == args.length ) 
							usage();
						this.outputFilePath = args[i];
			       		} else {
				     usage();
			       		}
			       } else {
			       		if ( inputFilePath != null ) 
				 		usage();
					inputFilePath = args[i];
			       }		
			} 
		}
	}

	public int[] getRawData() { 
		if ( rawData == null ) 
			readFile();
		return rawData;
	}

	public EstructurasDeDatos getDSName() {
		if ( edd == null ) 
			readFile();
		return edd;
	}

	public String getOutputFile() {
		return outputFilePath;
	}

	private void readFile() {
		Lista<String> content;
		if ( inputFilePath == null ) { // Reads from STDIN.
			InputStreamReader in = new InputStreamReader(System.in);
			readFromStream(in, content);
			parseInput(content);
		} else { // Reads from a file
			try {
				InputStreamReader in = new InputStreamReader(new FileInputStream(this.inputFilePath));
				readFromStream(in, content);
				parseInput(content);
			} catch ( IOException ioe ) {
				System.out.println("File not found or inaccessible\n");
				System.exit(1);
			}
		}
	}

	private void readFromStream(InputStreamReader in, Lista<String> content) {
		String currentLine;
		try {
			BufferedReader br = new BufferedReader(in);
			int i = 0;
			while ( ( currentLine = br.readLine()) != null ) {
				currentLine = currentLine.trim().replaceAll("\\#(\\ |).*", "");
				if ( !currentLine.isEmpty() )
					content.agrega(currentLine);
			}
		} catch ( IOException ioe ) {
				System.out.println("Error while reading from input");
				System.exit(1);
		}
	}

	private void parseInput(Lista<String> content) {
		String dsName = content.getPrimero();
		getDataStructure(dsName);

		Lista<Integer> intData = new Lista<Integer>();
		for(String s: content)
			for(String e: s.split("(?!(-?\\d)).")) 
				if(e.length() > 0)  intData.agrega(Integer.parseInt(e)); 
		

		rawData = new int[intData.getLongitud()];
		int i = 0;
		for ( int n : intData ) 
			rawData[i++] = n;
	}

	private void getDataStructure(String name) {
		switch (name) {
			case "Lista":
				edd = EstructurasDeDatos.LISTA;
				break;
			case "Cola":
				edd = EstructurasDeDatos.COLA;
				break;
			case "Pila":
				edd = EstructurasDeDatos.PILA;
				break;
			case "ArbolBinarioCompleto":
				edd = EstructurasDeDatos.ARBOL_BINARIO_COMPLETO;
				break;
			case "ArbolBinarioOrdenado":
				edd = EstructurasDeDatos.ARBOL_BINARIO_ORDENADO;
				break;
			case "ArbolRojinegro":
				edd = EstructurasDeDatos.ARBOL_ROJINEGRO;
				break;
			case "ArbolAVL":
				edd = EstructurasDeDatos.ARBOL_AVL;
				break;
			case "Grafica":
				edd = EstructurasDeDatos.GRAFICA;
				break;
			case "MonticuloMinimo":
				edd = EstructurasDeDatos.MONTICULO_MINIMO;
				break;
			default:
				System.out.println("Invalid name received: " + name);
				System.out.println("Valid Data Structures names are:");
				System.out.println("\tLista\n\tCola\n\tPila\n\tArbolBinarioCompleto\n\tArbolBinarioOrdenado\n\tArbolRojinegro\n\tArbolAVL\n\tGrafica\n\tMonticuloMinimo\n");
				System.exit(1);
		}
	}

	private void usage() {
		System.out.println("Uso: java -jar target/proyecto2.jar <archivo [argumentos...]\n");
		System.out.println("Grafica una estrucutra de datos en SVG a partir de un archivo dado, o desde STDIN.\n");
		System.out.println("Opciones:\n\n: -o <archivo> \t\t Guarda la salida del programa en la ruta seleccionada por el usuario.\n");
	}

}	
