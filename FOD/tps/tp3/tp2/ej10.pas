{10. Se tiene información en un archivo de las horas extras realizadas por los empleados de una
empresa en un mes. Para cada empleado se tiene la siguiente información: departamento,
división, número de empleado, categoría y cantidad de horas extras realizadas por el
empleado. Se sabe que el archivo se encuentra ordenado por departamento, luego por
división y, por último, por número de empleado. Presentar en pantalla un listado con el
siguiente formato:
Departamento
División
Número de Empleado Total de Hs. Importe a cobrar
...... .......... .........
...... .......... .........
Total de horas división: ____
Monto total por división: ____
División
.................
Total horas departamento: ____
Monto total departamento: ____
Para obtener el valor de la hora se debe cargar un arreglo desde un archivo de texto al
iniciar el programa con el valor de la hora extra para cada categoría. La categoría varía
de 1 a 15. En el archivo de texto debe haber una línea para cada categoría con el número
de categoría y el valor de la hora, pero el arreglo debe ser de valores de horas, con la
posición del valor coincidente con el número de categoría.}
program ej10;
const
	totalCat=10;
	valorAlto=999;
type
	rangoCat=1..totalCat;
	empleado=record
		departamento:integer;
		division:integer;
		numE:integer;
		cat:integer;
		cantHoras:integer;
	end;
	archivoM=file of empleado;
	vectorH=array[rangoCat]of real;
	procedure cargarV(var v:vectorH);
	var i:rangoCat;
	begin
		for i:=1 to totalCat do
			v[i]:=i*2;
	end;
	procedure crearArchivo(var maestro:archivoM);
	var e:empleado;
	begin
		rewrite(maestro);
		
		e.departamento:=10;
		e.division:=1;
		e.numE:=1;
		e.cat:=10;
		e.cantHoras:=5;
		write(maestro,e);
		
		e.departamento:=10;
		e.division:=1;
		e.numE:=2;
		e.cat:=10;
		e.cantHoras:=5;
		write(maestro,e);//200?
		
		e.departamento:=10;
		e.division:=2;
		e.numE:=1;
		e.cat:=9;
		e.cantHoras:=3;
		write(maestro,e);
		
		e.departamento:=20;
		e.division:=3;
		e.numE:=3;
		e.cat:=5;
		e.cantHoras:=10;
		write(maestro,e);//niidea pero tiene q cambiar 
		
		e.departamento:=30;
		e.division:=6;
		e.numE:=10;
		e.cat:=3;
		e.cantHoras:=2;
		write(maestro,e);
		
		close(maestro);
	end;
	procedure leer(var maestro:archivoM;var regM:empleado);
	begin
		if(not eof(maestro))then
			read(maestro,regM)
		else
			regM.departamento:=valorAlto;
	end;
	procedure informarMaestro(var maestro:archivoM;v:vectorH);
	var regM:empleado;actDepartamento,actDivision,actNumeroE:integer;
		totalHorasNumeroE,totalHorasDivision,totalHorasDepartamento:integer;
		totalDepartamento,totalDivision,totalNumeroE:real;
	begin
		reset(maestro);
		leer(maestro,regM);
		writeln();
		writeln('-INFORME HORAS EXTRAS-');
		while(regM.departamento<>valorAlto)do begin
			actDepartamento:=regM.departamento;
			writeln('-Departamento: ',actDepartamento);
			totalDepartamento:=0;
			totalHorasDepartamento:=0;
			while(regM.departamento=actDepartamento)do begin
				actDivision:=regM.division;
				writeln('-Division: ',actDivision);
				totalDivision:=0;
				totalHorasDivision:=0;
				while((regM.departamento=actDepartamento)and(regM.division=actDivision))do begin
					actNumeroE:=regM.numE;
					writeln('-Numero Empleado: ',actNumeroE);
					totalNumeroE:=0;
					totalHorasNumeroE:=0;
					while((regM.departamento=actDepartamento)and(regM.division=actDivision)and(regM.numE=actNumeroE))do begin
						totalDepartamento:=totalDepartamento+(v[regM.cat]*regM.cantHoras);
						totalDivision:=totalDivision+(v[regM.cat]*regM.cantHoras);
						totalNumeroE:=totalNumeroE+(v[regM.cat]*regM.cantHoras);
						totalHorasNumeroE:=totalHorasNumeroE+regM.cantHoras;
						totalHorasDivision:=totalHorasDivision+regM.cantHoras;
						totalHorasDepartamento:=totalHorasDepartamento+regM.cantHoras;
						leer(maestro,regM);
					end;
					writeln('Total de horas Empleado:',totalHorasNumeroE);
					writeln('Total importe a cobrar:',totalNumeroE:2:2);
				end;
				writeln('Total de horas Division:',totalHorasDivision);
				writeln('Total importe a cobrar:',totalDivision:2:2);
			end;
			writeln('Total de horas Departamento:',totalHorasDepartamento);
			writeln('Total importe a cobrar:',totalDepartamento:2:2);
		end;
		close(maestro);
	end;
var
	vValorHora:vectorH;maestro:archivoM;
begin
	cargarV(vValorHora);
	assign(maestro,'Horas Extras');
	crearArchivo(maestro);
	informarMaestro(maestro,vValorHora);
end.
