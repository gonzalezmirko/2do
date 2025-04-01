package tp2.ejercicio1;

public class BinaryTree <T>{
	private T data;
	private BinaryTree<T> leftChild;
	private BinaryTree<T> rightChild;
	
	//●	El constructor BinaryTree(T data) inicializa un árbol con el dato pasado como parámetro y ambos hijos nulos.
	public BinaryTree(T dato) {
		this.data=dato;
		this.leftChild=null;
		this.rightChild=null;
	}
	
	//Constructor BinaryTree nulo.
	public BinaryTree() {
		this.leftChild=null;
		this.rightChild=null;
		this.data=null;
	}
	
	//Retorna el dato del arbol binario.
	public T getData() {
		return data;
	}
	
	//Setea un dato del arbol binario.
	public void setData(T data) {
		this.data = data;
	}

	//Retorna el arbol binario del hijo izquierdo.
	public BinaryTree<T> getLeftChild() {
		return leftChild;
	}

	//Retorna el arbol binario del hijo derecho.
	public BinaryTree<T> getRightChild() {
		return rightChild;
	}

	//El método addLeftChild(BinaryTree<T> child) agrega un hijo como hijo izquierdo del árbol.
	public void addLeftChild(BinaryTree<T> child) {
		this.leftChild=child;
	}
	
	//El método addRightChild(BinaryTree<T> child) agrega un hijo como hijo derecho del árbol.
	public void addRightChild(BinaryTree<T> child) {
		this.rightChild=child;
	}
	
	//El método removeLeftChild() eliminan el hijo correspondiente.
	public void removeLeftChild() {
		this.leftChild=null;
	}
	
	
	//El métodoremoveRightChild(), eliminan el hijo correspondiente.
	public void removeRightChild() {
		this.rightChild=null;
	}
	
	//El método isEmpty() indica si el árbol está vacío.
	public boolean isEmpty() {
		return this.isLeaf() && this.getData().equals(null);
	}
	
	//El método isLeaf() indica si no tiene hijos.
	public boolean isLeaf() {
		return this.hasLeftChild() && this.hasRitghChild();
	}
	
	//El método hasLeftChild() devuelve un booleano indicando si tiene dicho hijo el árbol receptor del mensaje.
	public boolean hasLeftChild() {
		return this.leftChild.getData().equals(null) && this.leftChild==null;
	}
	
	//El método hasRightChild() devuelve un booleano indicando si tiene dicho hijo el árbol receptor del mensaje.
	public boolean hasRitghChild() {
		return this.rightChild.getData().equals(null) && this.rightChild==null;
	}
}
