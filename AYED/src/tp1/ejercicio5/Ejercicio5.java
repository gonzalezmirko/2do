package tp1.ejercicio5;

/*
5.	Dado un arreglo de valores tipo entero se desea calcular el valor máximo, mínimo, y promedio en un único método. 
Escriba tres métodos de clase, donde respectivamente:
a.	Devuelva lo pedido por el mecanismo de retorno de un método en Java ("return").
b.	Devuelva lo pedido interactuando con algún parámetro (el parámetro no puede ser de tipo arreglo).
c.	Devuelva lo pedido sin usar parámetros ni la sentencia "return".

 */
public class Ejercicio5 {
	private int[]arreglo;
	private Datos d;
	//puntoA
	public static Datos calcularTipoReturn(int[]arreglo) {
		int max=Integer.MIN_VALUE;
		int min=Integer.MAX_VALUE;
		int suma=0;
		for(int i=0;i<arreglo.length;i++) {
			if(arreglo[i]>max) {
				max=arreglo[i];
			}
			if(arreglo[i]<min) {
				min=arreglo[i];
			}
			suma += arreglo[i];
		}
		return new Datos(max,min,suma/arreglo.length);
	}
	//puntoB
	public static void calcularConParametros(Datos datos) {
		int []arreglo=datos.getArreglo();
		int max=Integer.MIN_VALUE;
		int min=Integer.MAX_VALUE;
		int suma=0;
		for(int i=0;i<arreglo.length;i++) {
			if(arreglo[i]>max) {
				max=arreglo[i];
			}
			if(arreglo[i]<min) {
				min=arreglo[i];
			}
			suma += arreglo[i];
		}
		datos.setMax(max);
		datos.setMin(min);
		datos.setPro(suma/arreglo.length);
	}
	public Ejercicio5(int[]arreglo) {
		this.arreglo=arreglo;
	}
	//puntoC
	public void calcularSinParametrosNiReturn() {
		int max=Integer.MIN_VALUE;
		int min=Integer.MAX_VALUE;
		int suma=0;
		for(int i=0;i<this.arreglo.length;i++) {
			if(arreglo[i]>max) {
				max=arreglo[i];
			}
			if(arreglo[i]<min) {
				min=arreglo[i];
			}
			suma+=arreglo[i];
		}
		this.d=new Datos(max,min,suma/arreglo.length);
	}
	public Datos getD() {
		return this.d;
	}
	public static void main(String[]args) {
		int[]arregloEnteros= {1,2,3,5,-1,10};
		
		//metodo A:Devuelva lo pedido por el mecanismo de retorno de un método en Java ("return").
		Datos datos=calcularTipoReturn(arregloEnteros);
		System.out.println(datos);
		
		//metodo B:Devuelva lo pedido interactuando con algún parámetro (el parámetro no puede ser de tipo arreglo).
		Datos datos2=new Datos(arregloEnteros);
		calcularConParametros(datos2);
		System.out.println(datos2);
		
		//metodo C:Devuelva lo pedido sin usar parámetros ni la sentencia "return".
		//pense en variables de instancia
		Ejercicio5 ej=new Ejercicio5(arregloEnteros);
		ej.calcularSinParametrosNiReturn();
		System.out.println(ej.getD());
		
	}
}
