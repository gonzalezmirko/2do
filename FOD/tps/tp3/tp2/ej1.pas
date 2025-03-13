{1. Una empresa posee un archivo con información de los ingresos percibidos por diferentes
empleados en concepto de comisión, de cada uno de ellos se conoce: código de empleado,
nombre y monto de la comisión. La información del archivo se encuentra ordenada por
código de empleado y cada empleado puede aparecer más de una vez en el archivo de
comisiones.
Realice un procedimiento que reciba el archivo anteriormente descripto y lo compacte. En
consecuencia, deberá generar un nuevo archivo en el cual, cada empleado aparezca una
única vez con el valor total de sus comisiones.
NOTA: No se conoce a priori la cantidad de empleados. Además, el archivo debe ser
recorrido una única vez.}
program ej1TP2;
const valor=9999;
type
	empleado=record
		codE:integer;
		nombre:string;
		montoC:real;
	end;
	archivo=file of empleado;
	procedure crearArchivo(var a:archivo);
	var e:empleado;i:integer;
	begin
		rewrite(a);
		Randomize;
		e.codE:=1;
		e.nombre:='Mirko';
		for i:=1 to 3 do begin
			e.montoC:=Random(100)+1;
			write(a,e);
		end;
		e.codE:=2;
		e.nombre:='Nacho';
		for i:=1 to 3 do begin
			e.montoC:=Random(50)+1;
			write(a,e);
		end;
		e.codE:=3;
		e.nombre:='Juan';
		for i:=1 to 3 do begin
			e.montoC:=Random(20)+1;
			write(a,e);
		end;
		close(a);
		writeln('-ARCHIVO CREADO-');
	end;
	procedure imprimirArchivo(var a:archivo);
	var e:empleado; 
	begin
		reset(a);
		while(not eof(a))do begin
			read(a,e);
			writeln('Nombre:',e.nombre);
			writeln('Codigo:',e.codE);
			writeln('MontoC:',e.montoC:2:2);
			writeln();
		end;
		close(a);
	end;
	procedure leer(var detalle:archivo;var e:empleado);
	begin
		if(eof(detalle))then
			e.codE:=valor
		else
			read(detalle,e);
	end;
	procedure compactarArchivo(var detalle,maestro:archivo);
	var regM,regD:empleado;suma:real;
	begin
		reset(detalle);
		rewrite(maestro);
		leer(detalle,regD);
		while(regD.codE<>valor)do begin
			suma:=0;
			regM:=regD;
			while(regD.codE=regM.codE)do begin
				suma:=suma+regD.montoC;
				leer(detalle,regD);
			end;
			regM.montoC:=suma;
			write(maestro,regM);
		end;
		close(detalle);
		close(maestro);
		writeln('-MAESTRO CREADO-');
	end;
var detalle:archivo;maestro:archivo;
begin
	writeln('-Crear archivo de empleados generado automaticamente-');
	Assign(detalle,'Empleados');
	assign(maestro,'EmpleadosCompactados');
	crearArchivo(detalle);
	imprimirArchivo(detalle);
	writeln('sadasda');
	compactarArchivo(detalle,maestro);
	imprimirArchivo(maestro);
end.
