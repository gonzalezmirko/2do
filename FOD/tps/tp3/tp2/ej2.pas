{2. Se dispone de un archivo con información de los alumnos de la Facultad de Informática. Por
cada alumno se dispone de su código de alumno, apellido, nombre, cantidad de materias
(cursadas) aprobadas sin final y cantidad de materias con final aprobado. Además, se tiene
un archivo detalle con el código de alumno e información correspondiente a una materia
(esta información indica si aprobó la cursada o aprobó el final).
Todos los archivos están ordenados por código de alumno y en el archivo detalle puede
haber 0, 1 ó más registros por cada alumno del archivo maestro. Se pide realizar un
programa con opciones para:
a. Actualizar el archivo maestro de la siguiente manera:
i.Si aprobó el final se incrementa en uno la cantidad de materias con final aprobado,
y se decrementa en uno la cantidad de materias sin final aprobado.
ii.Si aprobó la cursada se incrementa en uno la cantidad de materias aprobadas sin
final.
b. Listar en un archivo de texto aquellos alumnos que tengan más materias con finales
aprobados que materias sin finales aprobados. Teniendo en cuenta que este listado
es un reporte de salida (no se usa con fines de carga), debe informar todos los
campos de cada alumno en una sola línea del archivo de texto.
NOTA: Para la actualización del inciso a) los archivos deben ser recorridos sólo una vez.}
program ej2;
const 
	valorAlto=999;
type
	alumno=record
		cod:integer;
		apellido:string;
		nombre:string;
		cantMSF:integer;
		cantMCF:integer;
	end;
	infoMatAlu=record
		codA:integer;
		aproboC:boolean;
		aproboF:boolean;
	end;
	archivoMaestro=file of alumno;
	archivoDetalle=file of infoMatAlu;
	procedure crearArchivoMaestro(var a:archivoMaestro);
	var al:alumno;i:integer;
	begin
		rewrite(a);
		randomize;
		for i:=1 to 3 do begin
			write('Leer nombre:');
			readln(al.nombre);
			write('Leer apellido:');
			readln(al.apellido);
			al.cod:=i;
			al.cantMSF:=random(5)+1;
			al.cantMCF:=random(10)+1;
			write(a,al);
		end;
		close(a);
	end;
	procedure crearArchivoDetalle(var a:archivoDetalle);
	var al:infoMatAlu;i:integer;
	begin
		rewrite(a);
		randomize;
		for i:=1 to 10 do begin
			al.codA:=random(3)+1;
			al.aproboC:=true;
			al.aproboF:=false;
			write(a,al);
		end;
		close(a);
	end;
	procedure imprimirAM(var a:archivoMaestro);
	var	al:alumno;
	begin
		reset(a);
		while(not eof(a))do begin
			read(a,al);
			writeln('CodA:',al.cod);
			writeln('Nombre:',al.nombre);
			writeln('Apellido:',al.apellido);
			writeln('cantMatSinFinal:',al.cantMSF);
			writeln('cantMatConFinal:',al.cantMCF);
		end;
		close(a);
	end;
	procedure imprimirAD(var a:archivoDetalle);
	var al:infoMatAlu;
	begin
		reset(a);
		while(not eof(a))do begin
			read(a,al);
			writeln('Codigo Alu:',al.codA);
			writeln('Aprobo final:',al.aproboF);
			writeln('Aprobo cursada:',al.aproboC);
		end;
		close(a);
	end;
	{a. Actualizar el archivo maestro de la siguiente manera:
	i.Si aprobó el final se incrementa en uno la cantidad de materias con final aprobado,
	y se decrementa en uno la cantidad de materias sin final aprobado.
	ii.Si aprobó la cursada se incrementa en uno la cantidad de materias aprobadas sin final.}
	procedure leer(var a:archivoDetalle;var regD:infoMatAlu);
	begin
		if(not eof(a))then
			read(a,regD)
		else
			regD.codA:=valorAlto;
	end;
	procedure ActualizarMaestro(var aM:archivoMaestro;var aD:archivoDetalle);
	var regM:alumno;regD:infoMatAlu;aux:integer;totalF,totalC:integer;
	begin
		reset(aM);
		reset(aD);
		read(aM,regM);
		leer(aD,regD);
		while(regD.codA<>valorAlto)do begin
			aux:=regD.codA;
			totalF:=0;
			totalC:=0;
			while(aux=regD.codA)do begin
				if(regD.aproboF)then
					totalF:=totalF+1;
				if(regD.aproboC)then
					regM.cantMSF:=regM.cantMSF+1;
				leer(aD,regD);
			end;
			while(not eof(aM))and(aux<>regM.cod)do
				read(aM,regM);
			regM.cantMSF:=regM.cantMSF+totalC;
			regM.cantMCF:=regM.cantMCF+totalF;
			seek(aM,filePos(aM)-1);
			write(aM,regM);
			if(not eof(aM))then
				read(aM,regM);
		end;
		close(aD);
		close(aM);
	end;
	{. Listar en un archivo de texto aquellos alumnos que tengan más materias con finales
	aprobados que materias sin finales aprobados. Teniendo en cuenta que este listado
	es un reporte de salida (no se usa con fines de carga), debe informar todos los
	campos de cada alumno en una sola línea del archivo de texto.}
	procedure exportar(var a:archivoMaestro;var aT:text);
	var al:alumno;
	begin
		reset(a);
		Assign(aT,'Alumnos.txt');
		rewrite(aT);
		while(not eof(a))do begin
			read(a,al);
			if(al.cantMCF>al.cantMSF)then
				writeln(aT,al.cod,al.nombre,al.apellido);
		end;
		close(aT);
		close(a);
	end;
	{procedure imprimirTxt(var a:text);
	var al:alumno;
	begin
		reset(a);
		while(not eof(a))do begin
			read(a,al);
			writeln(al.nombre);
			writeln(al.apellido);
		end;
		close(a);
	end;}
var aM:archivoMaestro;aD:archivoDetalle;archTxt:text;
begin
	Assign(aM,'Alumnos');
	Assign(aD,'InfoAlumnos');
	crearArchivoMaestro(aM);//para prueba
	crearArchivoDetalle(aD);//para prueba
	imprimirAM(aM);
	//imprimirAD(aD);
	//writeln('-!DESPUES DE LA ACTUALIZACION!-');
	//ActualizarMaestro(aM,aD);
	//imprimirAM(aM);
	exportar(aM,archTxt);
	//imprimirTxt(archTxt);
end.
