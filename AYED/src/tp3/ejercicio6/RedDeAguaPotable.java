package tp3.ejercicio6;
/*
 * Sea una red de agua potable, la cual comienza en un caño maestro y 
 * la misma se va dividiendo sucesivamente hasta llegar a cada una de las casas.
Por el caño maestro ingresan “x” cantidad de litros y en la medida que el caño se divide,
 de acuerdo con las bifurcaciones que pueda tener, el caudal se divide en partes iguales en cada una de ellas. 
 Es decir, si un caño maestro recibe 1000 litros y tiene por ejemplo 4 bifurcaciones se divide en 4 partes iguales, 
 donde cada división tendrá un caudal de 250 litros.Luego, si una de esas divisiones se vuelve a dividir, 
 por ej. en 5 partes, cada una tendrá un caudal de 50 litros y así sucesivamente hasta llegar a un lugar
  sin bifurcaciones.
Se debe implementar una clase RedDeAguaPotable que contenga el método con la siguiente firma:
public double minimoCaudal(double caudal)
que calcule el caudal de cada nodo y determine cuál es el caudal mínimo que recibe una casa. Asuma que la estructura 
de caños de la red está representada por una variable de instancia de la clase RedAguaPotable y 
que es un GeneralTree<Character>.
Extendiendo el ejemplo en el siguiente gráfico, al llamar al método minimoCaudal con un valor de
1000.0 debería retornar 25.0

 */
import tp3.ejercicio1.*;
public class RedDeAguaPotable {
	private GeneralTree<Character> red;
	
	public RedDeAguaPotable(GeneralTree<Character>red) {
		this.red=red;
	}
	private double minimoCaudalRec(GeneralTree<Character> arbol,double caudal) {
		if(arbol.isLeaf()){
			return caudal;
		}
		double minCaudal=Double.MAX_VALUE;
		double caudalDiv=caudal/arbol.getChildren().size();
		for(GeneralTree<Character>child:arbol.getChildren()) {
			double caudalHijo=minimoCaudalRec(child,caudalDiv);
			minCaudal=Math.min(minCaudal, caudalHijo);
			/*
			if(caudalHijo<minCaudal) {
				minCaudal=caudalHijo;
			}
			*/
		}
		return minCaudal;
	}
	public double minimoCaudal(double caudal) {
		if(this.red==null || this.red.isEmpty() || caudal<0) {
			return -1;
		}
		return minimoCaudalRec(this.red,caudal);
	}
	public static void main(String[] args) {
		GeneralTree<Character>arbol=new GeneralTree<Character>('A');
		arbol.addChild(new GeneralTree<Character>('B'));
		arbol.addChild(new GeneralTree<Character>('C'));
		arbol.addChild(new GeneralTree<Character>('D'));
		arbol.addChild(new GeneralTree<Character>('E'));
		arbol.getChildren().get(1).addChild(new GeneralTree<Character>('F'));
		arbol.getChildren().get(1).addChild(new GeneralTree<Character>('G'));
		arbol.getChildren().get(2).addChild(new GeneralTree<Character>('H'));
		arbol.getChildren().get(2).addChild(new GeneralTree<Character>('I'));
		arbol.getChildren().get(2).addChild(new GeneralTree<Character>('J'));
		arbol.getChildren().get(2).addChild(new GeneralTree<Character>('K'));
		arbol.getChildren().get(2).addChild(new GeneralTree<Character>('P'));
		arbol.getChildren().get(1).getChildren().get(1).addChild(new GeneralTree<Character>('L'));
		arbol.getChildren().get(2).getChildren().get(2).addChild(new GeneralTree<Character>('M'));
		arbol.getChildren().get(2).getChildren().get(2).addChild(new GeneralTree<Character>('N'));
		//arbol.entreNiveles();
		RedDeAguaPotable red=new RedDeAguaPotable(arbol);
		System.out.println(red.minimoCaudal(1000));
	}

}
