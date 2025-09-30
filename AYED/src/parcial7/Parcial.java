package parcial7;
import java.util.*;
import tp5.ejercicio1.*;
import tp1.ejercicio8.Queue;
public class Parcial {
	private void bfs(Graph<String>grafo,Vertex<String>origen,int distancia,int limite,List<Datos>camino,boolean[]marcas){
		Queue<Vertex<String>>queue=new Queue<Vertex<String>>();
		queue.enqueue(origen);
		queue.enqueue(null);
		int nivel=1;
		boolean ok=false;
		marcas[origen.getPosition()]=true;
		while(!queue.isEmpty() && nivel<=distancia && !ok) {
			Vertex<String>aux=queue.dequeue();
			if(aux!=null) {
				List<Edge<String>>edges=grafo.getEdges(aux);
				for(Edge<String>edge:edges) {
					Vertex<String>vertex=edge.getTarget();
					int pos=vertex.getPosition();
					if(!marcas[pos] && nivel<=distancia) {
						marcas[pos]=true;
						queue.enqueue(vertex);
						if(camino.size()<limite) {
							camino.add(new Datos(vertex.getData(),nivel));
							if(camino.size()==limite) {
								ok=true;
							}
						}
					}
				}
			}else {
				if(!queue.isEmpty()) {
					queue.enqueue(null);
					nivel++;
				}
			}
		}
	}
	public List<Datos>invitacionMasterClass(Graph<String>red,String usuario,int distancia,int limite){
		List<Datos>camino=new LinkedList<Datos>();
		if(!red.isEmpty()) {
			Vertex<String> origen=red.search(usuario);
			if(origen!=null) {
				bfs(red,origen,distancia,limite,camino,new boolean[red.getSize()]);
			}
		}
		return camino;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
