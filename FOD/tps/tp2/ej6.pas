{6. Suponga que trabaja en una oficina donde está montada una LAN (red local). La misma fue
construida sobre una topología de red que conecta 5 máquinas entre sí y todas las
máquinas se conectan con un servidor central. Semanalmente cada máquina genera un
archivo de logs informando las sesiones abiertas por cada usuario en cada terminal y por
cuánto tiempo estuvo abierta. Cada archivo detalle contiene los siguientes campos:
cod_usuario, fecha, tiempo_sesion. Debe realizar un procedimiento que reciba los archivos
detalle y genere un archivo maestro con los siguientes datos: cod_usuario, fecha,
tiempo_total_de_sesiones_abiertas.
Notas:
● Cada archivo detalle está ordenado por cod_usuario y fecha.
● Un usuario puede iniciar más de una sesión el mismo día en la misma máquina, o
inclusive, en diferentes máquinas.
● El archivo maestro debe crearse en la siguiente ubicación física: /var/log.}
program ej6;
uses
	SysUtils;
const
	valorAlto=999;
	totalM=5;
type
	rangoM=1..totalM;
	regDetalle=record
		cod:integer;
		fecha:integer;
		tiempo:real;
	end;
	regMaestro=record
		cod:integer;
		fecha:integer;
		tiempoT:real;
	end;
	archivoM=file of regMaestro;
	archivoD=file of regDetalle;
	vectorD=array[rangoM]of archivoD;
	vectorRegD=array[rangoM]of regDetalle;
	
	procedure rewrites(var v:vectorD);
	var i:rangoM;
	begin
		for i:=1 to totalM do
			rewrite(v[i]);
	end;
	procedure closes(var v:vectorD);
	var i:rangoM;
	begin
		for i:=1 to totalM do
			close(v[i]);
	end;
	procedure resets(var v:vectorD);
	var i:rangoM;
	begin
		for i:=1 to totalM do
			reset(v[i]);
	end;
	procedure crearDetalles(var v:vectorD);
	var i:rangoM;regD:regDetalle;
	begin
		randomize;
		rewrites(v);
		for i:=1 to totalM do begin
			regD.cod:=random(5)+1;
			regD.fecha:=random(3)+1;
			regD.tiempo:=random(20);
			write(v[i],regD);
		end;
		closes(v);
	end;
	procedure leer(var det:archivoD;var regD:regDetalle);
	begin
		if(not eof(det))then
			read(det,regD)
		else
			regD.cod:=valorAlto;
	end;
	procedure minimo(var v:vectorD;var vecReg:vectorRegD;var min:regDetalle);
	var i:rangoM;pos:integer;
	begin
		pos:=0;
		min.cod:=valorAlto;
		for i:=1 to totalM do begin
			if(vecReg[i].cod<>valorAlto)then begin
				if(vecReg[i].cod <= min.cod)then begin
					min:=vecReg[i];
					pos:=i;
				end;
			end;
		end;
		for i:=1 to totalM do begin
			if(vecReg[i].cod<>valorAlto)then begin
				if((vecReg[i].cod=min.cod)and(vecReg[i].fecha<=min.fecha))then begin
					min:=vecReg[i];
					pos:=i;
				end;
			end;
		end;
		if(pos<>0)then
			leer(v[pos],vecReg[pos]);
	end;
	procedure crearMaestro(var maestro:archivoM;var v:vectorD);
	var i:rangoM;vecReg:vectorRegD;actual:regMaestro;min:regDetalle;
	begin
		writeln('-CREANDO MAESTRO A PARTIR DE DETALLES-');
		rewrite(maestro);
		resets(v);
		for i:=1 to totalM do
			leer(v[i],vecReg[i]);
		minimo(v,vecReg,min);
		while(min.cod<>valorAlto)do begin
			actual.cod:=min.cod;
			while(min.cod=actual.cod)do begin
				actual.fecha:=min.fecha;
				actual.tiempoT:=0;
				while((min.cod=actual.cod)and(min.fecha=actual.fecha))do begin
					actual.tiempoT := actual.tiempoT + min.tiempo;
					minimo(v,vecReg,min);
				end;
				write(maestro,actual);
			end;
		end;
		closes(v);
		close(maestro);
		writeln('-SE HA CREADO-');
	end;
	procedure imprimirMaestro(var maestro:archivoM);
	var regM:regMaestro;
	begin
		reset(maestro);
		writeln();
		writeln('-SERVIDOR CENTRAL-');
		while(not eof(maestro))do begin
			read(maestro,regM);
			writeln('Codigo:',regM.cod);
			writeln('Fecha:',regM.fecha);
			writeln('Tiempo:',regM.tiempoT:2:2);
		end;
		close(maestro);
	end;
	procedure imprimirDetalles(var v:vectorD);
	var regD:regDetalle;i:rangoM;
	begin
		for i:=1 to totalM do begin
			writeln('-SERVIDOR LOCAL ',i,'-');
			reset(v[i]);
			while(not eof(v[i]))do begin
				read(v[i],regD);
				writeln('Codigo:',regD.cod);
				writeln('Fecha:',regD.fecha);
				writeln('Tiempo:',regD.tiempo:2:2);
			end;
			close(v[i]);
		end;
	end;
var maestro:archivoM;detalles:vectorD;i:rangoM;
begin
	assign(maestro,'Servidor Central');
	for i:=1 to totalM do
		assign(detalles[i],'Servidor Local '+inttoStr(i));
	crearDetalles(detalles);
	crearMaestro(maestro,detalles);
	imprimirDetalles(detalles);
	imprimirMaestro(maestro);
end.
