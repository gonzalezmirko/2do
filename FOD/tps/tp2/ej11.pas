{11. La empresa de software ‘X’ posee un servidor web donde se encuentra alojado el sitio web
de la organización. En dicho servidor, se almacenan en un archivo todos los accesos que se
realizan al sitio. La información que se almacena en el archivo es la siguiente: año, mes, día,
idUsuario y tiempo de acceso al sitio de la organización. El archivo se encuentra ordenado
por los siguientes criterios: año, mes, día e idUsuario.
Se debe realizar un procedimiento que genere un informe en pantalla, para ello se indicará
el año calendario sobre el cual debe realizar el informe. El mismo debe respetar el formato
mostrado a continuación:
Año : ---
Mes:-- 1
día:-- 1
idUsuario 1 Tiempo Total de acceso en el dia 1 mes 1
--------
idusuario N Tiempo total de acceso en el dia 1 mes 1
Tiempo total acceso dia 1 mes 1
-------------
día N
idUsuario 1 Tiempo Total de acceso en el dia N mes 1
--------
idusuario N Tiempo total de acceso en el dia N mes 1
Tiempo total acceso dia N mes 1
Total tiempo de acceso mes 1
------
Mes 12
día 1
idUsuario 1 Tiempo Total de acceso en el dia 1 mes 12
--------
idusuario N Tiempo total de acceso en el dia 1 mes 12
Tiempo total acceso dia 1 mes 12
-------------
día N
idUsuario 1 Tiempo Total de acceso en el dia N mes 12
--------
idusuario N Tiempo total de acceso en el dia N mes 12
Tiempo total acceso dia N mes 12
Total tiempo de acceso mes 12
Total tiempo de acceso año
Se deberá tener en cuenta las siguientes aclaraciones:
● El año sobre el cual realizará el informe de accesos debe leerse desde el teclado.
● El año puede no existir en el archivo, en tal caso, debe informarse en pantalla “año
no encontrado”.
● Debe definir las estructuras de datos necesarias.
● El recorrido del archivo debe realizarse una única vez procesando sólo la información
necesaria.}
program ej11;
const
	valorAlto=999;
type
	rangoMes=1..12;
	rangoDias=1..31;
	regMaestro=record
		anio:integer;
		mes:rangoMes;
		dia:rangoDias;
		id:integer;
		tiempo:real;
	end;
	archivoM=file of regMaestro;
	procedure crearArchivo(var maestro:archivoM);
	var	regM:regMaestro;
	begin
		rewrite(maestro);
		
		regM.anio:=2024;
		regM.mes:=12;
		regM.dia:=31;
		regM.id:=1;
		regM.tiempo:=10;
		write(maestro,regM);
		
		regM.anio:=2024;
		regM.mes:=12;
		regM.dia:=31;
		regM.id:=1;
		regM.tiempo:=90;
		write(maestro,regM);
		
		regM.anio:=2024;
		regM.mes:=10;
		regM.dia:=2;
		regM.id:=2;
		regM.tiempo:=500;
		write(maestro,regM);
		
		regM.anio:=2020;
		regM.mes:=1;
		regM.dia:=1;
		regM.id:=1;
		regM.tiempo:=100;
		write(maestro,regM);
		
		regM.anio:=2020;
		regM.mes:=12;
		regM.dia:=3;
		regM.id:=4;
		regM.tiempo:=1;
		write(maestro,regM);
		
		close(maestro);
	end;
	procedure leer(var maestro:archivoM;var regM:regMaestro);
	begin
		if(not eof(maestro))then
			read(maestro,regM)
		else
			regM.anio:=valorAlto;
	end;
	procedure informar(var maestro:archivoM);
	var regM:regMaestro;anio:integer;actMes:rangoMes;actDia:rangoDias;actID:integer;
		totalAnio,totalMes,totalDia,totalID:real;
	begin
		reset(maestro);
		writeln('-Informe anual accesos al sitio x-');
		writeln('-!Que anio quiere procesar?-');
		readln(anio);
		leer(maestro,regM);
		while((regM.anio<>valorAlto))do begin
			if(regM.anio<>anio)then begin
				writeln('no se encontro anio');
				writeln();
			end
			else begin
				writeln('se ecnotnre');
				writeln();
				leer(maestro,regM);
			end;
		end;
		close(maestro);
	end;
var maestro:archivoM;
begin
	assign(maestro,'Servidor X');
	crearArchivo(maestro);
	informar(maestro);
end.
