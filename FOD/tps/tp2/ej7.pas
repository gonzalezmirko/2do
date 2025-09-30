{7. Se desea modelar la información necesaria para un sistema de recuentos de casos de covid
para el ministerio de salud de la provincia de buenos aires.
Diariamente se reciben archivos provenientes de los distintos municipios, la información
contenida en los mismos es la siguiente: código de localidad, código cepa, cantidad de
casos activos, cantidad de casos nuevos, cantidad de casos recuperados, cantidad de casos
fallecidos.
El ministerio cuenta con un archivo maestro con la siguiente información: código localidad,
nombre localidad, código cepa, nombre cepa, cantidad de casos activos, cantidad de casos
nuevos, cantidad de recuperados y cantidad de fallecidos.
Se debe realizar el procedimiento que permita actualizar el maestro con los detalles
recibidos, se reciben 10 detalles. Todos los archivos están ordenados por código de
localidad y código de cepa.
Para la actualización se debe proceder de la siguiente manera:
1. Al número de fallecidos se le suman el valor de fallecidos recibido del detalle.
2. Idem anterior para los recuperados.
3. Los casos activos se actualizan con el valor recibido en el detalle.
4. Idem anterior para los casos nuevos hallados.
Realice las declaraciones necesarias, el programa principal y los procedimientos que
requiera para la actualización solicitada e informe cantidad de localidades con más de 50
casos activos (las localidades pueden o no haber sido actualizadas).}
program ej7;
uses
	sysutils;
const
	valorAlto=999;
	totalA=5;
type
	rangoA=1..totalA;
	regDetalle=record
		codLocalidad:integer;
		codCepa:integer;
		cantCasosActivos:integer;
		cantCasosNuevos:integer;
		cantCasosRecuperados:integer;
		cantCasosFallecidos:integer;
	end;
	regMaestro=record
		codLocalidad:integer;
		nombreLocalidad:string;
		codCepa:integer;
		cantCasosActivos:integer;
		cantCasosNuevos:integer;
		cantCasosRecuperados:integer;
		cantCasosFallecidos:integer;
	end;
	archivoM=file of regMaestro;
	archivoD=file of regDetalle;
	vectorD=array [rangoA] of archivoD;
	vectorRegD=array[rangoA]of regDetalle;
	
	procedure rewrites(var v:vectorD);
	var i:rangoA;
	begin
		for i:=1 to totalA do
			rewrite(v[i]);
	end;
	procedure resets(var v:vectorD);
	var i:rangoA;
	begin
		for i:=1 to totalA do
			reset(v[i]);
	end;
	procedure closes(var v:vectorD);
	var i:rangoA;
	begin
		for i:=1 to totalA do
			close(v[i]);
	end;
	procedure crearDetalles(var v:vectorD);
	var regD:regDetalle;i:rangoA;
	begin
		writeln('-Creando detalles-');
		rewrites(v);
		randomize;
		for i:=1 to totalA do begin
			regD.codLocalidad:=random(5)+1;
			regD.codCepa:=random(5)+1;
			regD.cantCasosActivos:=random(10);
			regD.cantCasosNuevos:=random(10);
			regD.cantCasosRecuperados:=random(10);
			regD.cantCasosFallecidos:=(10);
			write(v[i],regD);
		end;
		closes(v);
		writeln('-SE HA CREADO DETALLES-');
	end;
	procedure imprimirDetalles(var v:vectorD);
	var regD:regDetalle;i:rangoA;
	begin
		for i:=1 to totalA do begin
			writeln('-DETALLE ',i,'-');
			reset(v[i]);
			while(not eof(v[i]))do begin
				read(v[i],regD);
				writeln('Codigo de localidad:',regD.codLocalidad);
				writeln('Codigo de Cepa:',regD.codCepa);
				writeln('Cantidad de casos actidos:',regD.cantCasosActivos);
				writeln('Cantidad de casos nuevos:',regD.cantCasosNuevos);
				writeln('Cantidad de casos recuperados:',regD.cantCasosRecuperados);
				writeln('Cantidad de casos fallecidos:',regD.cantCasosFallecidos);
			end;
			close(v[i]);
		end;
	end;
	procedure crearMaestro(var a:archivoM);
	var regM:regMaestro;i:rangoA;
	begin
		rewrite(a);
		randomize;
		for i:=1 to totalA do begin
			regM.codLocalidad:=random(5)+1;
			writeln('NOMBRE LOCALIDAD:');
			readln(regM.nombreLocalidad);
			regM.codCepa:=random(5)+1;
			regM.cantCasosActivos:=random(10);
			regM.cantCasosNuevos:=random(10);
			regM.cantCasosRecuperados:=random(10);
			regM.cantCasosFallecidos:=random(10);
			write(a,regM);
		end;
		close(a);
	end;
	procedure imprimirMaestro(var a:archivoM);
	var regM:regMaestro;
	begin
		reset(a);
		writeln('-MAESTRO-');
		while(not eof(a))do begin
			read(a,regM);
			writeln('Codigo de localidad:',regM.codLocalidad);
			writeln('Nombre de localidad:',regM.nombreLocalidad);
			writeln('Codigo de Cepa:',regM.codCepa);
			writeln('Cantidad de casos actidos:',regM.cantCasosActivos);
			writeln('Cantidad de casos nuevos:',regM.cantCasosNuevos);
			writeln('Cantidad de casos recuperados:',regM.cantCasosRecuperados);
			writeln('Cantidad de casos fallecidos:',regM.cantCasosFallecidos);
		end;
		close(a);
	end;
	procedure leer(var det:archivoD;var regD:regDetalle);
	begin
		if(not eof(det))then
			read(det,regD)
		else
			regD.codLocalidad:=valorAlto;
	end;
	procedure minimo(var vD:vectorD;var vecReg:vectorRegD;var min:regDetalle);
	var i:rangoA;pos:integer;
	begin
		min.codLocalidad:=valorAlto;
		pos:=0;
		for i:=1 to totalA do begin
			if(vecReg[i].codLocalidad<>valorAlto)then begin
				if(vecReg[i].codLocalidad<=min.codLocalidad)then begin
					min:=vecReg[i];
					pos:=i;
				end;
			end;
		end;
		for i:=1 to totalA do begin
			if(vecReg[i].codLocalidad<>valorAlto)then begin
				if((vecReg[i].codLocalidad=min.codLocalidad)and(vecReg[i].codCepa<=min.codCepa))then begin
					min:=vecReg[i];
					pos:=i;
				end;
			end;
		end;
		if(pos<>0)then
			leer(vD[pos],vecReg[pos]);
	end;
	procedure actualizarMaestro(var maestro:archivoM;var vD:vectorD);
	var vecReg:vectorRegD;i:rangoA;min:regDetalle;actual:regDetalle;totalL,activos:integer;regM:regMaestro;
	begin
		activos:=0;
		totalL:=0;
		reset(maestro);
		resets(vD);
		for i:=1 to totalA do
			leer(vD[i],vecReg[i]);
		minimo(vD,vecReg,min);
		read(maestro,regM);
		while(not eof(maestro))do begin//TUVE QUE PONER ESTA CONDICION PARA QUE ME ANDE
			actual:=min;
			actual.cantCasosActivos:=0;
			actual.cantCasosNuevos:=0;
			actual.cantCasosRecuperados:=0;
			actual.cantCasosFallecidos:=0;
			while((min.codLocalidad=actual.codLocalidad)and(min.codCepa=actual.codCepa))do begin
				actual.cantCasosActivos:=actual.cantCasosActivos+min.cantCasosActivos;
				actual.cantCasosNuevos:=actual.cantCasosNuevos+min.cantCasosNuevos;
				actual.cantCasosRecuperados:=actual.cantCasosRecuperados+min.cantCasosRecuperados;
				actual.cantCasosFallecidos:=actual.cantCasosFallecidos+min.cantCasosFallecidos;
				minimo(vD,vecReg,min);
			end;
			while((regM.codLocalidad<>actual.codLocalidad)and(regM.codCepa<>actual.codCepa))do
				read(maestro,regM);
			regM.cantCasosActivos:=regM.cantCasosActivos+actual.cantCasosActivos;
			regM.cantCasosNuevos:=regM.cantCasosNuevos+actual.cantCasosNuevos;
			regM.cantCasosRecuperados:=regM.cantCasosRecuperados+actual.cantCasosRecuperados;
			regM.cantCasosFallecidos:=regM.cantCasosFallecidos+actual.cantCasosFallecidos;
			
			seek(maestro,filepos(maestro)-1);
			write(maestro,regM);
			activos:=activos+regM.cantCasosActivos;
			if(min.codLocalidad<>regM.codLocalidad)then begin
				if(activos>30)then
					totalL:=totalL+1;
				activos:=0;
			end;
			if(not eof(maestro))then
				read(maestro,regM);
		end;
		writeln('-SE ACTUALIZO EL MAESTRO-');
		writeln('Cantidad de localidades con más de 30 casos activos :',totalL);
		closes(vD);
		close(maestro);
	end;
var 
	maestro:archivoM;detalles:vectorD;i:rangoA;
begin
	assign(maestro,'Ministerio Maestro');
	for i:=1 to totalA do
		assign(detalles[i],'Ministerio Detalle '+intToStr(i));
	crearDetalles(detalles);
	//imprimirDetalles(detalles);
	crearMaestro(maestro);
	imprimirMaestro(maestro);
	actualizarMaestro(maestro,detalles);
	imprimirMaestro(maestro);
end.
