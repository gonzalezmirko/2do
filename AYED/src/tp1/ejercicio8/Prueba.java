package tp1.ejercicio8;

public class Prueba {
	public static void main (String[]args) {
		//Queue<Integer> cola=new Queue<Integer>();
		//CircularQueue<Integer> cola=new CircularQueue<Integer>();
		DoubleEndedQueue<Integer> cola=new DoubleEndedQueue<Integer>();
		//el shift pone el primero a lo ultimo : rota el primer elemento de la cola al final
		cola.enqueue(10);
		cola.enqueue(20);
		cola.enqueue(30);
		cola.enqueue(40);
		cola.enqueueFirst(50);//encola al principio
		cola.dequeue();//borra el primer elemento de la cola en este caso 50 porque lo encole al principip
		cola.dequeue();//borra el 10
		
		System.out.println(cola.toString());
	}
}
