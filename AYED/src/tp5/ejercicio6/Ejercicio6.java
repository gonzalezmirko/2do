package tp5.ejercicio6;
/*
 *Un día, Caperucita Roja decide ir desde su casa hasta la de su abuelita, 
 *recolectando frutos del bosque del camino y tratando de hacer el paseo de la manera más segura posible. 
 *La casa de Caperucita está en un claro del extremo oeste del bosque, la casa de su abuelita en un claro del 
 *extremo este, y dentro del bosque entre ambas hay algunos otros claros.
El bosque está representado por un grafo, donde los vértices representan los claros (identificados por un String)
 y las aristas los senderos que los unen. Cada arista informa la cantidad de árboles frutales que hay en el sendero. 
 Caperucita sabe que el lobo es muy goloso y le gustan mucho las frutas, entonces para no ser capturada por el lobo, 
 desea encontrar todos los caminos que no pasen por los senderos con cantidad de frutales >= 5 y 
 lleguen a la casa de la abuelita.
Su tarea es definir una clase llamada BuscadorDeCaminos, con una variable de instancia llamada "bosque" de tipo Graph,
 que representa el bosque descrito e implementar un método de instancia con la siguiente firma:
public List < List <String>> recorridosMasSeguro()
que devuelva un listado con TODOS los caminos que cumplen con las condiciones mencionadas anteriormente.
Nota: La casa de Caperucita debe ser buscada antes de comenzar a buscar el recorrido.
Para el siguiente ejemplo:
Debe retornar una lista con caminos:
1)	Casa Caperucita- Claro 1 - Claro 5 - Casa Abuelita.
2)	Casa Caperucita- Claro 2 - Claro 1 - Claro 5 - Casa Abuelita.

 */
import java.util.*;
import tp5.ejercicio1.*;
public class Ejercicio6 {
	private Graph<String>bosque;
	public List < List <String>> recorridosMasSeguro(){
		List<List<String>>caminos=new LinkedList<List<String>>();
		if(!this.bosque.isEmpty()) {
			Vertex<String>origen=bosque.search("Casa Caperucita");
			Vertex<String>destino=bosque.search("Casa Abuelita");
			if(origen!=null && destino !=null) {
				dfs(origen,destino,bosque,new boolean[bosque.getSize()],caminos,new LinkedList<String>());
			}
		}
		return caminos;
	}
	private void dfs(Vertex<String>origen,Vertex<String>destino,Graph<String>grafo,boolean[]marcas,List<List<String>>caminos,List<String>caminoActual) {
		marcas[origen.getPosition()]=true;
		caminoActual.add(origen.getData());
		
		if(origen==destino) {
			caminos.add(new LinkedList<String>(caminoActual));
		}
		else {
			List<Edge<String>>edges=grafo.getEdges(origen);
			for(Edge<String>edge:edges) {
				Vertex<String>vertex=edge.getTarget();
				int peso=edge.getWeight();
				int pos=vertex.getPosition();
				if(!marcas[pos] && peso<=5) {
					dfs(vertex,destino,grafo,marcas,caminos,caminoActual);
				}
			}
		}
		marcas[origen.getPosition()]=false;
		caminoActual.remove(caminoActual.size()-1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
