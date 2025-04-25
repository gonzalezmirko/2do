package tp3.ejercicio4;
/*
 El esquema de comunicación de una empresa está organizado en una estructura jerárquica, 
 en donde cada nodo envía el mensaje a sus descendientes. 
 Cada nodo posee el tiempo que tarda en transmitir el mensaje.
 Se debe devolver el mayor promedio entre todos los valores promedios de los niveles.
Para el ejemplo presentado, el promedio del nivel 0 es 14, el del nivel 1 es 16 y el del nivel 2 es 10.
 Por lo tanto, debe devolver 16.
a)	Indique y justifique qué tipo de recorrido utilizará para resolver el problema.
Usaria entre niveles para poder calcular el promedio por nivel y tener un maximo generel calcular entre todos los niveles
b)	Implementar en una clase AnalizadorArbol, el método con la siguiente firma:
public double devolverMaximoPromedio (GeneralTree<AreaEmpresa>arbol)
Donde AreaEmpresa es una clase que representa a un área de la empresa mencionada y que contiene
 la identificación de la misma representada con un String y una tardanza de transmisión de mensajes
  interna representada con int.

 */
import tp3.ejercicio1.GeneralTree;
import tp1.ejercicio8.Queue;
public class AnalizadorArbol {
	
	public static double devolverMaximoPromedio (GeneralTree<AreaEmpresa>arbol) {
		if(arbol.isEmpty() || arbol==null ) {
			return -1;
		}
		Queue<GeneralTree<AreaEmpresa>>queue=new Queue<GeneralTree<AreaEmpresa>>();
		double maxPromedio=Double.MIN_VALUE;
		double maxPromedioNivel=0;
		
		queue.enqueue(arbol);
		queue.enqueue(null);
		int contadorNivel=0;
		int contadorNivelPromedio=0;
		while(!queue.isEmpty()) {
			GeneralTree<AreaEmpresa>aux=queue.dequeue();
			if(aux!=null) {
				contadorNivel++;
				int valor=aux.getData().getTardanzaTransmicion();
				maxPromedioNivel += valor;
				for(GeneralTree<AreaEmpresa>child:aux.getChildren()) {
					queue.enqueue(child);
				}
			}
			else {
				if(!queue.isEmpty()) {
					queue.enqueue(null);
					if(maxPromedioNivel>maxPromedio) {
						maxPromedio=maxPromedioNivel;
						contadorNivelPromedio=contadorNivel;
					}
					maxPromedioNivel=0;
					contadorNivel=0;
				}
			}
		}
		return maxPromedio/contadorNivelPromedio;
	}
	public static void main(String[] args) {
		GeneralTree<AreaEmpresa>arbol=new GeneralTree<AreaEmpresa>(new AreaEmpresa("M",14));
		arbol.addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("J",13)));
		arbol.addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("K",25)));
		arbol.addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("L",10)));
		arbol.getChildren().get(0).addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("A",4)));
		arbol.getChildren().get(0).addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("B",7)));
		arbol.getChildren().get(0).addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("C",5)));
		arbol.getChildren().get(1).addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("D",6)));
		arbol.getChildren().get(1).addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("E",10)));
		arbol.getChildren().get(1).addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("F",18)));
		arbol.getChildren().get(2).addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("G",9)));
		arbol.getChildren().get(2).addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("H",12)));
		arbol.getChildren().get(2).addChild(new GeneralTree<AreaEmpresa>(new AreaEmpresa("I",19)));
		System.out.println("Maximo promedio del arbol es:"+devolverMaximoPromedio(arbol));
	}

}
