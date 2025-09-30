{2. Definir un programa que genere un archivo con registros de longitud fija conteniendo
información de asistentes a un congreso a partir de la información obtenida por
teclado. Se deberá almacenar la siguiente información: nro de asistente, apellido y
nombre, email, teléfono y D.N.I. Implementar un procedimiento que, a partir del
archivo de datos generado, elimine de forma lógica todos los asistentes con nro de
asistente inferior a 1000.
Para ello se podrá utilizar algún carácter especial situándolo delante de algún campo
String a su elección. Ejemplo: ‘@Saldaño’}
program ej2;
const valorAlto=999;
type
	asistente=record
		nro:integer;
		apenom:string;
		email:string;
		telefono:integer;
		dni:integer;
	end;
	arch=file of asistente;
	
	procedure leerAsistente(var a:asistente);
	begin
		with a do begin
			write('Leer numero:');
			readln(nro);
			if(nro<>-1)then begin
				write('Leer Apellido y nombre:');
				readln(apenom);
				write('email:');
				readln(email);
				write('telefono:');
				readln(telefono);
				write('DNI:');
				readln(dni);
			end;
		end;
	end;
	procedure crearArchivo(var a:arch);
	var asis:asistente;
	begin
		rewrite(a);
		leerAsistente(asis);
		while(asis.nro<>-1)do begin
			write(a,asis);
			leerAsistente(asis);
		end;
		close(a);
	end;
	procedure leerArc(var a:arch;var asis:asistente);
	begin
		if(not eof(a))then
			read(a,asis)
		else
			asis.nro:=valorAlto;
	end;
	procedure eliminarAsistentes(var a_log:arch);
	var asis:asistente;
	begin
		reset(a_log);
		leerArc(a_log,asis);
		while (asis.nro<>valorAlto)do begin
			if(asis.nro<1000)then begin
				asis.apenom:='@Eliminado';
				seek(a_log,filepos(a_log)-1);
				write(a_log,asis);
			end;
			leerArc(a_log,asis);
		end;
		close(a_log);
	end;
	procedure imprimir(a:asistente);
	begin
		writeln('-Asistentes-');
		if(a.apenom<>'@Eliminado')then begin
			writeln('Nro:',a.nro);
			writeln('Ape:',a.apenom);
		end;
		writeln();
	end;
	procedure imprimirA(var a:arch);
	var asis:asistente;
	begin
		reset(a);
		while (not eof(a))do begin
			read(a,asis);
			imprimir(asis);
		end;
		close(a);
	end;
var 
	archivo:arch;
begin
	assign(archivo,'Asistentes');
	crearArchivo(archivo);
	imprimirA(archivo);
	eliminarAsistentes(archivo);
	imprimirA(archivo);
end.

