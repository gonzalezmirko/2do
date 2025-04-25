package tp3.ejercicio1;
/*
Los métodos altura(), nivel(T) y ancho() se resolverán en el ejercicio 3.
Analice la implementación en JAVA de la clase GeneralTree brindada por la cátedra.

 */
import java.util.*;
import tp1.ejercicio8.Queue;
public class GeneralTree <T>{
	private T data;
	private List<GeneralTree<T>>children=new LinkedList<GeneralTree<T>>();
	
	/*
	El constructor GeneralTree(T data) inicializa un árbol que tiene como raíz un nodo y 
	este nodo tiene el dato pasado como parámetro y una lista vacía.
	 */
	public GeneralTree(T data){
		this.data=data;
	}
	/*
	El constructor GeneralTree (T data, List<GeneralTree <T>> children) inicializa un árbol que tiene 
	como raíz a un nodo y este nodo tiene el dato pasado como parámetro y como hijos children.
	 */
	public GeneralTree(T data,List<GeneralTree<T>>children) {
		this(data);
		this.children=children;
	}
	
	//El método getData():T retorna el dato almacenado en la raíz del árbol.
	public T getData() {
		return data;
	}
	
	//El método getChildren():List<GeneralTree <T>>, retorna la lista de hijos de la raíz del árbol.
	public List<GeneralTree<T>>getChildren() {
		return this.children;
	}
	
	//El método addChild(GeneralTree <T> child) agrega un hijo al final de la lista de hijos del árbol.
	public void addChild(GeneralTree<T>child) {
		this.children.add(child);
	}
	
	//El método removeChild(GeneralTree <T> child) elimina del árbol el hijo pasado como parámetro.
	public void removeChild(GeneralTree<T>child) {
		if(this.hasChildren()) {
			this.children.remove(child);
		}
	}
	
	//El método hasChildren() devuelve verdadero si la lista de hijos del árbol no es null y tampoco es vacía.
	public boolean hasChildren() {
		return this.children!=null && !this.isEmpty();
	}
	
	//El método isEmpty() devuelve verdadero si el dato del árbol es null y además no tiene hijos.
	public boolean isEmpty() {
		return this.data==null && !this.hasChildren();
	}
	
	public boolean isLeaf() {
		return !this.hasChildren();
	}
	
	public void setChildren(List<GeneralTree<T>>child) {
		if(this.children!=null) {
			this.children=child;
		}
	}
	
	public void setData(T dato) {
		this.data=dato;
	}
	
	public void preOrden() {
		if(this.getData()!=null) {
			System.out.println("Dato:"+this.getData().toString());
		}
		if(this.hasChildren()) {
			for (GeneralTree<T> child:this.getChildren()){
				child.preOrden();
			}
		}
	}
	public void postOrden() {
		if(this.hasChildren()) {
			for (GeneralTree<T>child:this.getChildren()) {
				child.postOrden();
			}
		}
		if(this.getData()!=null) {
			System.out.println("Dato:"+this.getData().toString());
		}
	}
	//recorrido en orden
    public void inOrdenR(GeneralTree<T> a) {
    	List<GeneralTree<T>>children=a.getChildren();
        if(!children.isEmpty()) {
        	inOrdenR(children.get(0));
        }
        System.out.println("Dato:"+a.getData().toString());
        for(int i=1;i<children.size();i++) {
        	inOrdenR(children.get(i));
        }
    }
    public void inOrden() {
    	if(!this.isEmpty()) {
    		inOrdenR(this);
    	}
    }
    public void entreNiveles() {
    	Queue<GeneralTree<T>> queue=new Queue<GeneralTree<T>>();
    	int nivel=0;
    	queue.enqueue(this);
    	queue.enqueue(null);
    	while(!queue.isEmpty()) {
    		GeneralTree<T>aux=queue.dequeue();
    		if(aux!=null) {
    			if(nivel==0) {
    				System.out.println("----NIVEL"+nivel+"----");
    			}
    			System.out.println("----> Dato "+aux.getData().toString()+" <----");
    			if(aux.hasChildren()) {
    				List<GeneralTree<T>>children=aux.getChildren();
    				for(GeneralTree<T> child:children) {
    					queue.enqueue(child);
    				}
    			}
    		}
    		else {
    			if(!queue.isEmpty()) {
    				queue.enqueue(null);
    				nivel++;
    				System.out.println("----NIVEL"+nivel+"----");
    			}
    		}
    	}
    }
    /*
     * Implemente en la clase GeneralTree los siguientes métodos
	a)	public int altura(): int devuelve la altura del árbol,
	 es decir, la longitud del camino más largo desde el nodo raíz hasta una hoja.
	b)	public int nivel(T dato) devuelve la profundidad o nivel del dato en el árbol. 
	El nivel de un nodo es la longitud del único camino de la raíz al nodo.
	c)	public int ancho(): int la amplitud (ancho) de un árbol se define como
	 la cantidad de nodos que se encuentran en el nivel que posee la mayor cantidad de nodos.

     */
    public int altura() {
    	/*
    	 * 		1
			 2  3
			 
		4 5 6 7 ->>>>> 0
    	 */
    	int alturaMax=-1;
    	if(this.isLeaf()) {
    		return 0;
    	}
    	for(GeneralTree<T>child:this.getChildren()) {
    		int altura=child.altura();
    		if(altura>alturaMax) {
    			alturaMax=altura;
    		}
    	}
    	return alturaMax+1;
    	
    }
    /*
     * b)	public int nivel(T dato) devuelve la profundidad o nivel del dato en el árbol. 
	El nivel de un nodo es la longitud del único camino de la raíz al nodo.
     */
    public int nivel(T dato) {
    	Queue<GeneralTree<T>>queue=new Queue<GeneralTree<T>>();
    	int nivel=0;
    	boolean ok=false;
    	queue.enqueue(this);
    	queue.enqueue(null);
    	while(!queue.isEmpty() && !ok) {
    		GeneralTree<T>aux=queue.dequeue();
    		if(aux!=null) {
    			if(aux.getData()==dato) {
    				ok=true;
    			}
    			for(GeneralTree<T>child:aux.getChildren()) {
    				queue.enqueue(child);
    			}
    		}
    		else {
    			if(!queue.isEmpty()) {
    				queue.enqueue(null);
    				nivel++;
    			}
    		}
    	}
    	if(ok) {
    		return nivel;
    	}
    	return -1;
    }
    /*
     * c)	public int ancho(): int la amplitud (ancho) de un árbol se define como
	 la cantidad de nodos que se encuentran en el nivel que posee la mayor cantidad de nodos.
     */
    public int ancho() {
    	Queue<GeneralTree<T>>queue=new Queue<GeneralTree<T>>();
    	//int nivel=0;
    	int maxNodos=Integer.MIN_VALUE;
    	int nodos=0;
    	queue.enqueue(this);
    	queue.enqueue(null);
    	while(!queue.isEmpty()) {
    		GeneralTree<T>aux=queue.dequeue();
    		if(aux!=null) {
    			nodos++;
    			if(nodos>maxNodos) {
    				maxNodos=nodos;
    			}
    			for(GeneralTree<T>child:aux.getChildren()) {
    				queue.enqueue(child);
    			}
    		}
    		else {
    			if(!queue.isEmpty()) {
    				queue.enqueue(null);
    				nodos=0;
    			}
    		}
    	}
    	return maxNodos;
    }
    /*
     * Ejercicio 5
     Se dice que un nodo n es ancestro de un nodo m si existe un camino desde n a m.
      Implemente un método en la clase GeneralTree con la siguiente firma:
       public boolean esAncestro(T a, T b): devuelve true si el valor “a” es ancestro del valor “b”.

     */
    private boolean contiene(GeneralTree<T>arbol,T v) {
    	if(arbol.getData().equals(v)) {
    		return true;
    	}
    	for(GeneralTree<T>child:arbol.getChildren()) {
    		if(contiene(child,v)) {
    			return true;
    		}
    	}
    	return false;
    }
    private boolean esAncestroRec(GeneralTree<T> arbol,T a,T v) {
    	if(arbol.getData().equals(v)) {
    		return false;
    	}
    	if(arbol.getData().equals(a)) {
    		return contiene(arbol,v);
    	}
    	for(GeneralTree<T>child:arbol.getChildren()) {
    		if(esAncestroRec(child,a,v)) {
    			return true;
    		}
    	}
    	return false;
    }
    public boolean esAncestro(T a, T v) {
    	if(this.isEmpty()||this.getData()==null) {
    		return false;
    	}
    	//List<T>lista=new ArrayList<T>();
    	return esAncestroRec(this,a,v);
    }
	public static void main(String[] args) {
		/*
					1
			 2  3
			 
		4 5 6 7
		8
		 */
		GeneralTree<Integer> arbol=new GeneralTree<Integer>(1);
		arbol.addChild(new GeneralTree<Integer>(2));
		arbol.addChild(new GeneralTree<Integer>(3));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(4));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(5));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(6));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(7));
		arbol.getChildren().get(0).getChildren().get(0).addChild(new GeneralTree<Integer>(8));
		//arbol.preOrden();//1 2 4 
		//arbol.inOrden();//4 2 5 6 7 1 3
		//arbol.entreNiveles();
		//System.out.println(arbol.altura());
		//System.out.println(arbol.nivel(10));
		//System.out.println(arbol.altura());
		System.out.println(arbol.esAncestro(1, 8)); // true
		System.out.println(arbol.esAncestro(2, 7)); // true
		System.out.println(arbol.esAncestro(4, 8)); // true
		System.out.println(arbol.esAncestro(8, 1)); // false
		System.out.println(arbol.esAncestro(3, 2)); // false
		System.out.println(arbol.esAncestro(1, 1)); // false (uno no puede ser ancestro de sí mismo en este caso)
	}

}
