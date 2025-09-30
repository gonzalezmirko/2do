package tp5.ejercicio5;
/*
Ejercicio 5
El Banco Itaú se suma a las campañas "QUEDATE EN CASA" lanzando un programa para acercar el sueldo 
a los jubilados hasta sus domicilios. Para ello el banco cuenta con información que permite definir 
un grafo de personas donde la persona puede ser un jubilado o un empleado del banco que llevará el dinero.
Se necesita armar la cartera de jubilados para cada empleado repartidor del banco, incluyendo en cada lista, 
los jubilados que vivan un radio cercano a su casa y no hayan percibido la jubilación del mes.
Para ello, implemente un algoritmo que dado un Grafo<Persona> retorne una lista de jubilados que se encuentren
 a una distancia menor a un valor dado del empleado Itaú (grado de separación del empleado Itaú). 
 El método recibirá un Grafo<Persona>, un empleado y un grado de separación/distancia y 
 debe retornar una lista de hasta 40 jubilados que no hayan percibido la jubilación del mes y 
 se encuentre a una distancia menor a recibido como parámetro.
 En este grafo simple, donde los empleados del banco están en color rojo, 
 si se desea retornar los jubilados hasta distancia 2, se debería retornar los jubilados en color negro.
La persona conoce si es empleado o jubilado, el nombre y el domicilio.

 */
import java.util.*;
import tp5.ejercicio1.*;
import tp1.ejercicio8.Queue;
public class Ejercicio5 {
	private Vertex<Persona>buscarPersona(Graph<Persona>grafo,Persona per){
		Vertex<Persona>vertex=null;
		boolean ok=false;
		List<Vertex<Persona>>vertices=grafo.getVertices();
		Iterator<Vertex<Persona>>it=vertices.iterator();
		while(it.hasNext() && !ok) {
			Vertex<Persona>v=it.next();
			if(v.getData().getDomicilio()==per.getDomicilio() &&
					v.getData().isEmpleado()==per.isEmpleado() &&
					v.getData().getNombre().equals(per.getNombre())) {
					ok=true;
					vertex=v;
			}
		}
		return vertex;
	}
	public List<Persona>retornarLista(Graph<Persona>grafo,Persona per,int grado) {
		List<Persona>lista=new LinkedList<Persona>();
		if(!grafo.isEmpty()) {
			Vertex<Persona>origen=buscarPersona(grafo,per);
			if(origen != null) {
				bfs(origen,grafo,lista,grado,new boolean[grafo.getSize()]);
			}
		}
		return lista;
	}
	private void bfs(Vertex<Persona>vertex,Graph<Persona>grafo,List<Persona>lista,int grado,boolean[]marca) {
		marca[vertex.getPosition()]=true;
		Queue<Vertex<Persona>>queue=new Queue<Vertex<Persona>>();
		queue.enqueue(vertex);
		queue.enqueue(null);
		
		while(!queue.isEmpty() && grado>=0 && lista.size()<=40) {
			Vertex<Persona>v=queue.dequeue();
			
			if(v!=null) {
				if(!v.getData().isEmpleado()) {
					lista.add(v.getData());
				}
				List<Edge<Persona>>edges=grafo.getEdges(v);
				Iterator<Edge<Persona>>it=edges.iterator();
				while(it.hasNext()) {
					Edge<Persona>edge=it.next();
					Vertex<Persona>ver=edge.getTarget();
					if(!marca[ver.getPosition()]) {
						queue.enqueue(ver);
						marca[ver.getPosition()]=true;
					}
				}
			}
			else {
				if(!queue.isEmpty()) {
					grado--;
					queue.enqueue(null);
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
