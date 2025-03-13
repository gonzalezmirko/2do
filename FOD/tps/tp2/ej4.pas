{4. A partir de información sobre la alfabetización en la Argentina, se necesita actualizar un
archivo que contiene los siguientes datos: nombre de provincia, cantidad de personas
alfabetizadas y total de encuestados. Se reciben dos archivos detalle provenientes de dos
agencias de censo diferentes, dichos archivos contienen: nombre de la provincia, código de
localidad, cantidad de alfabetizados y cantidad de encuestados. Se pide realizar los módulos
necesarios para actualizar el archivo maestro a partir de los dos archivos detalle.
NOTA: Los archivos están ordenados por nombre de provincia y en los archivos detalle
pueden venir 0, 1 ó más registros por cada provincia.
}
program ej4;
const valorAlto='';
type
	alfabetizacion=record
		nombreP:string;
		cantAl:integer;
		totalE:integer;
	end;
	agencia=record
		nombreP:string;
		codL:integer;
		cantAl:integer;
		cantE:integer;
	end;
	archivoMaestro=file of alfabetizacion;
	archivoDetalle=file of agencia;
	procedure crearMaestro(var a:archivoMaestro);
	var al:alfabetizacion;i:integer;
	begin
		writeln('-Carga Maestro-');
		rewrite(a);
		randomize;
		for i:=1 to 3 do begin
			writeln('Nombre Provincia:');
			readln(al.nombreP);
			al.cantAl:=random(10);
			al.totalE:=random(10);
			write(a,al);
		end;
		writeln();
		close(a);
	end;
	procedure crearDetalles(var d1,d2:archivoDetalle);
	var a:agencia;i:integer;
	begin
		writeln('-Carga de los detalles-');
		rewrite(d1);
		rewrite(d2);
		randomize;
		for i:=1 to 2 do begin
			writeln('Nombre Provincia:');
			readln(a.nombreP);
			a.codL:=random(4)+1;
			a.cantAl:=random(10)+1;
			a.cantE:=random(10)+1;
			write(d1,a);
		end;
		for i:=1 to 2 do begin
			writeln('Nombre Provincia:');
			readln(a.nombreP);
			a.codL:=random(4)+1;
			a.cantAl:=random(10)+1;
			a.cantE:=random(10)+1;
			write(d2,a);
		end;
		writeln();
		close(d1);
		close(d2);
	end;
	procedure imprimirMaestro(var a:archivoMaestro);
	var al:alfabetizacion;
	begin
		reset(a);
		writeln('-Alfabetizacion-');
		while(not eof(a))do begin
			read(a,al);
			writeln('Nombre Provincia:',al.nombreP);
			writeln('Cantidad de personaes alfabetizadas:',al.cantAl);
			writeln('Total encuestados:',al.totalE);
		end;
		writeln();
		close(a);
	end;
	procedure imprimirDetalles(var d1,d2:archivoDetalle);
	var a:agencia;
	begin
		reset(d1);
		reset(d2);
		writeln('-Agencia1-');
		while(not eof(d1))do begin
			read(d1,a);
			writeln('Nombre P:',a.nombreP);
			writeln('Codigo Localidad:',a.codL);
			writeln('Cantidad Alfabetizados:',a.cantAl);
			writeln('Cantidad encuestados:',a.cantE);
		end;
		writeln('-Agencia2-');
		while(not eof(d2))do begin
			read(d2,a);
			writeln('Nombre P:',a.nombreP);
			writeln('Codigo Localidad:',a.codL);
			writeln('Cantidad Alfabetizados:',a.cantAl);
			writeln('Cantidad encuestados:',a.cantE);
		end;
		writeln();
		close(d1);
		close(d2);
	end;
	procedure leer(var det:archivoDetalle;var regD:agencia);
	begin
		if(not eof(det))then
			read(det,regD)
		else
			regD.nombreP:=valorAlto;
	end;
	procedure minimo(var det1,det2:archivoDetalle;var r1,r2,min:agencia);
	begin
		if(r1.nombreP<=r2.nombreP)then begin
			min:=r1;
			leer(det1,r1);
		end
		else
			begin
				min:=r2;
				leer(det2,r2);
			end;
	end;
	procedure actualizarMaestro(var maestro:archivoMaestro;var det1,det2:archivoDetalle);
	var regDet1,regDet2,min:agencia;regMae:alfabetizacion;
	begin
		reset(maestro);
		reset(det1);
		reset(det2);
		leer(det1,regDet1);leer(det2,regDet2);
		minimo(det1,det2,regDet1,regDet2,min);
		while(min.nombreP<>valorAlto)do begin
			read(maestro,regMae);
			writeln('llega');
			while(min.nombreP<>regMae.nombreP)do
				read(maestro,regMae);
			while(min.nombreP=regMae.nombreP)do begin
				regMae.totalE:=regMae.totalE+min.cantE;
				minimo(det1,det2,regDet1,regDet2,min);
			end;
			seek(maestro,filepos(maestro)-1);
			write(maestro,regMae);
		end;
		close(det1);
		close(det2);
		close(maestro);
	end;
var
	maestro:archivoMaestro;
	detalle1,detalle2:archivoDetalle;
begin
	assign(maestro,'Maestro_alfabetizacion');
	assign(detalle1,'Detalle_agencia1');
	assign(detalle2,'Detalle_agencia2');
	crearMaestro(maestro);
	crearDetalles(detalle1,detalle2);
	imprimirMaestro(maestro);
	imprimirDetalles(detalle1,detalle2);
	actualizarMaestro(maestro,detalle1,detalle2);
	imprimirMaestro(maestro);
end.
