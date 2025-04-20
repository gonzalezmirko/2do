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
	public static void main(String[] args) {
		/*
					1
			 2  3
			 
		4 5 6 7
		
		 */
		GeneralTree<Integer> arbol=new GeneralTree<Integer>(1);
		arbol.addChild(new GeneralTree<Integer>(2));
		arbol.addChild(new GeneralTree<Integer>(3));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(4));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(5));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(6));
		arbol.getChildren().get(0).addChild(new GeneralTree<Integer>(7));
		//arbol.preOrden();//1 2 4 
		//arbol.inOrden();//4 2 5 6 7 1 3
		arbol.entreNiveles();
	}

}
