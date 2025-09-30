{1. Realizar un algoritmo que cree un archivo de números enteros no ordenados y permita
incorporar datos al archivo. Los números son ingresados desde teclado. El nombre del
archivo debe ser proporcionado por el usuario desde teclado. La carga finaliza cuando
se ingrese el número 30000, que no debe incorporarse al archivo.
}
program ej1;
const fin=0;
type
    archivo = file of integer;
var
   numeros:integer;
   nombre_archivo:string[15];
   a:archivo;
begin
	writeln('Nombre del archivo:');
	readln(nombre_archivo);
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
end.
