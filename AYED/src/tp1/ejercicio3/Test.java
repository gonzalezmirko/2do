package tp1.ejercicio3;

/*
 * d.	Escriba una clase llamada Test con el método main, el cual cree un arreglo con 2 objetos Estudiante, 
 * otro arreglo con 3 objetos Profesor, y luego recorra ambos arreglos imprimiendo los valores obtenidos mediante 
 * el método tusDatos(). Recuerde asignar los valores de los atributos de los objetos Estudiante y Profesor invocando
 *  los respectivos métodos setters.
e.	Agregue dos breakpoints, uno en la línea donde itera sobre los estudiantes y otro en la línea donde itera sobre 
los profesores
f.	Ejecute la clase Test en modo debug y avance paso a paso visualizando si el estudiante o el profesor recuperado 
es lo esperado.

 */
public class Test {
	public static void imprimirEstudiantes(Estudiante[]arreglo) {
		for(int i=0;i<arreglo.length;i++){
			System.out.println("----->Estudiante<------");
			System.out.println(arreglo[i].tusDatos());
		}
	}
	public static void imprimirProfesores(Profesor[]arreglo) {
		for(int i=0;i<arreglo.length;i++){
			System.out.println("------>Profesor<----------");
			System.out.println(arreglo[i].tusDatos());
		}
	}
	public static void main(String []args){
		//crear estudiantes
		Estudiante est1=new Estudiante("Mirko","Gonzalez",1,"m@g","17 esq 59");
		Estudiante est2=new Estudiante("Ana","De Pasqua",2,"a@g","80 y 7");
		//arreglo estudiantes
		Estudiante[] arregloEstudiantes=new Estudiante[2];
		arregloEstudiantes[0]=est1;
		arregloEstudiantes[1]=est2;
		//profesores
		Profesor pro1=new Profesor("Marcos","Bassini","m@b",1,"i");
		Profesor pro2=new Profesor("Juan","Teta","j@t",2,"m");
		Profesor pro3=new Profesor("Gonza","Bell","g@b",3,"p");
		//arreglo profesores
		Profesor[] arregloProfesores=new Profesor[3];
		arregloProfesores[0]=pro1;
		arregloProfesores[1]=pro2;
		arregloProfesores[2]=pro3;
		
		imprimirEstudiantes(arregloEstudiantes);
		imprimirProfesores(arregloProfesores);
	}
}
