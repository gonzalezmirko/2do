{2. Realizar un algoritmo, que utilizando el archivo de números enteros no ordenados
creados en el ejercicio 1, informe por pantalla cantidad de números menores a 1500 y el
promedio de los números ingresados. El nombre del archivo a procesar debe ser
proporcionado por el usuario una única vez. Además, el algoritmo deberá listar el
contenido del archivo en pantalla.}
program ej2;
type
	archivo=file of integer;
var
	a:archivo;
	nombreA:string[15];
procedure informar(var a:archivo;nom:string);
var num:integer;cantN:integer;sumaN:integer;prom:real;
begin
	cantN:=0;
	sumaN:=0;
	reset(a);
	while(not(eof(a)))do begin
		read(a,num);
		if(num<1500)then
			cantN += 1;
		sumaN += num;
		writeln('Numero:',num);
	end;
	prom := sumaN/fileSize(a);
	writeln('Cantidad de numeros menores a 1500:',cantN);
	writeln('El promedio de los numeros es:',prom:2:2);
	close(a);
end;
begin
	writeln('Nombre del archivo generado:');
	readln(nombreA);
	Assign(a,nombreA);
	informar(a,nombreA);
end.
