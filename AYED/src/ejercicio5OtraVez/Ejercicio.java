package ejercicio5OtraVez;
/*
El Banco Itaú se suma a las campañas "QUEDATE EN CASA" lanzando un programa para acercar el sueldo a 
los jubilados hasta sus domicilios. Para ello el banco cuenta con información que permite definir un grafo 
de personas donde la persona puede ser un jubilado o un empleado del banco que llevará el dinero.
Se necesita armar la cartera de jubilados para cada empleado repartidor del banco, incluyendo en cada lista, 
los jubilados que vivan un radio cercano a su casa y no hayan percibido la jubilación del mes.
Para ello, implemente un algoritmo que dado un Grafo<Persona> retorne una lista de jubilados que se encuentren
 a una distancia menor a un valor dado del empleado Itaú (grado de separación del empleado Itaú).
  El método recibirá un Grafo<Persona>, un empleado y un grado de separación/distancia y debe retornar 
  una lista de hasta 40 jubilados que no hayan percibido la jubilación del mes y se encuentre a una distancia
   menor a recibido como parámetro.En este grafo simple, donde los empleados del banco están en color rojo, si 
   se desea retornar los jubilados hasta distancia 2, se debería retornar los jubilados en color negro.
   La persona conoce si es empleado o jubilado, el nombre y el domicilio

 */
import java.util.*;
import tp5.ejercicio1.*;
import tp1.ejercicio8.Queue;
public class Ejercicio {
	public List<Persona>resolver(Graph<Persona>grafo,Persona empleado,int distancia){
		List<Persona>lista=new ArrayList<Persona>();
		if(!grafo.isEmpty()) {
			Vertex<Persona>origen=grafo.search(empleado);
			if(origen != null) {
				bfs(grafo,origen,distancia,new boolean[grafo.getSize()],lista);
			}
		}
		return lista;
	}
	private void bfs(Graph<Persona>grafo,Vertex<Persona>origen,int distancia,boolean[]marca,List<Persona>lista) {
		marca[origen.getPosition()]=true;
		Queue<Vertex<Persona>>queue=new Queue<Vertex<Persona>>();
		queue.enqueue(origen);
		queue.enqueue(null);
		int nivel=0;
		while(!queue.isEmpty() && nivel<=distancia && lista.size()<=40) {
			Vertex<Persona>vertex=queue.dequeue();
			if(vertex != null) {
				List<Edge<Persona>>edges=grafo.getEdges(vertex);
				for(Edge<Persona>edge:edges) {
					Vertex<Persona>vecino=edge.getTarget();
					int pos=vecino.getPosition();
					if(!marca[pos] ) {
						marca[pos]=true;
						queue.enqueue(vecino);
						if(lista.size()<=40 && vecino.getData().getTipo().equals("Jubilado")) {
							lista.add(vecino.getData());
						}
					}
				}
			}
			else {
				nivel++;
				if(!queue.isEmpty()) {
					queue.enqueue(null);
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
