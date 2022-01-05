package mx.unam.ciencias.edd.proyecto2;

import java.io.File;
import java.io.PrintStream;

import mx.unam.ciencias.edd.proyecto2.estructuras.grapher.Lista;
import mx.unam.ciencias.edd.proyecto2.estructuras.grapher;//.Cola;
import mx.unam.ciencias.edd.proyecto2.estructuras.grapher;//.Pila;
import mx.unam.ciencias.edd.proyecto2.estructuras.grapher;//.ArbolBinarioCompleto;
import mx.unam.ciencias.edd.proyecto2.estructuras.grapher;//.ArbolBinarioOrdenado;
import mx.unam.ciencias.edd.proyecto2.estructuras.grapher;//.ArbolRojinegro;
import mx.unam.ciencias.edd.proyecto2.estructuras.grapher;//.ArbolAVL;
import mx.unam.ciencias.edd.proyecto2.estructuras.grapher;//.Grafica;
import mx.unam.ciencias.edd.proyecto2.estructuras.grapher;//.MonticuloMinimo;

public class Proyecto2 {
	private static ArgumentProcessor argsProcessor;

	public static void main(String[] args) {
		argsProcessor = new ArgumentProcessor(args);

		String XMLDoc = drawDataStructure( 
		   		   argsProcessor.getDSName(),
				   argsProcessor.getRawData()
				);

		write(XMLDoc, argsProcessor.getOutputFile());	
	}

	private static String drawDataStructure(EstructurasDeDatos edd, int[] data) {
	       Structure struct;
	       switch (edd) {
		       case LISTA:
			       struct = new Lista(data);
			       break;
	               case COLA:
			       struct = new Cola(data);
			       break;
		       case PILA:
			       struct = new Pila(data);
			       break;
		       case ARBOL_BINARIO_COMPLETO:
			       struct = new ArbolBinarioCompleto(data);
			       break;			       
		       case ARBOL_BINARIO_ORDENADO:
			       struct = new ArbolBinarioOrdenado(data);
			       break;
		       case ARBOL_ROJINEGRO:
			       struct = new ArbolRojinegro(data);
			       break;
		       case ARBOL_AVL:
			       struct = new ArbolAVL(data);
			       break;
		       case GRAFICA:
			       struct = new Grafica(data);
			       break;
		       case MONTICULO_MINIMO:
			       struct = new MonticuloMinimo(data);
			       break;
		       default:
			       // Impossible to reach.
	       }
	       struct.generateXML();
	}

	private static void write(String XMLDoc, String outputPath) {
        if( outputPath != null ) {
            try {
                System.setOut(new PrintStream(new File(outputFile)));
            } catch (Exception e) {
                System.out.println("There was a problem trying to write to: " + outputPath + "\n");
                System.exit(1);
            }
        }

        System.out.println(XMLDoc);
        System.setOut(System.out);
    }
}
