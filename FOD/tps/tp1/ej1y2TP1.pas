{1. Realizar un algoritmo que cree un archivo de números enteros no ordenados y permita
incorporar datos al archivo. Los números son ingresados desde teclado. El nombre del
archivo debe ser proporcionado por el usuario desde teclado. La carga finaliza cuando
se ingrese el número 30000, que no debe incorporarse al archivo.
}
{2. Realizar un algoritmo, que utilizando el archivo de números enteros no ordenados
creados en el ejercicio 1, informe por pantalla cantidad de números menores a 1500 y el
promedio de los números ingresados. El nombre del archivo a procesar debe ser
proporcionado por el usuario una única vez. Además, el algoritmo deberá listar el
contenido del archivo en pantalla.}
program ej1;
const fin=30000;
type
    archivo = file of integer;
var
   numeros:integer;
   nombre_archivo:string[15];
   a:archivo;
procedure recorrerArchivo(var a:archivo);
var num:integer;cant:integer;promedio:real;
begin
	cant:=0;
	promedio:=0;
	reset(a);//apertura del archivo
	while(not eof(a))do begin //mientras no sea el fin del archivo
		read(a,num);
		if(num<1500)then
			cant:=cant+1;
		promedio:=promedio+num;
		writeln('Numero ',FilePos(a),' :',num);
	end;
	writeln('La cantidad de numeros menores a 1500 es:',cant);
	writeln('El promedio de los numeros ingresados es:',promedio/FileSize(a):2:2);
	close(a);
end;
begin
	{readln(nombre_archivo);
	assign(a,nombre_archivo);//asigno numeros al nombre del archivo
	rewrite(a);//creo el archivo de numeros
	writeln('Ingrese numero a agregar:');
	readln(numeros);
	while(numeros<>fin)do begin
		write(a,numeros);//meto en el archivo los numeros
		writeln('Ingrese numero a agregar:');
		readln(numeros);
	end;
	close(a);//cierro el archivo
	recorrerArchivo(a);}
	nombre_archivo:='mirko';
	assign(a,nombre_archivo);//asignar mismo nombre que el archivo del anterior ejercicio
	recorrerArchivo(a);//recuperarDatos
end.
