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
const
	valorAlto=999;
	totalD=10;
type
	regMaestro=record
		codL:integer;
		nombreL:string;
		codC:integer;
		nombreC:string;
		cantCA:integer;
		cantCN:integer;
		cantR:integer;
		cantF:integer;
	end;
	regDetalle=record
		codL:integer;
		codC:integer;
		cantCA:integer;
		cantCN:integer;
		cantR:integer;
		cantF:integer;
	end;
	aMaestro=file of regMaestro;
	aDetalle=file of regDetalle;
	rangoD=1..totalD;
	vectorD=array[rangoD]of aDetalle;
	vectorRD=array[rangoD]of regDetalle;
	
	procedure leer(var a:aDetalle;var reg:regDetalle);
	begin
		if(not eof(a))then
			read(a,reg)
		else
			reg.codL:=valorAlto;
	end;
	procedure minimo(var detalles:vectorD;var regD:vectorRD;var min:regDetalle);
	var i,pos:integer;
	begin
		min.codL:=valorAlto;
		min.codC:=valorAlto;
		pos:=0;
		for i:=1 to totalD do begin
			if((regD[i].codL<min.codL)or((regD[i].codL=min.codL)and(regD[i].codC<min.codC)))then begin
				min:=regD[i];
				pos:=i;
			end;
		end;
		if(pos<>0)then
			leer(detalles[pos],regD[pos]);
	end;
	procedure actualizarMaestro(var maestro:aMaestro;var detalles:vectorD);
	var	regM:regMaestro;regD:vectorRD;min:regDetalle;cantL,cantCAL,i:integer;
	begin
		reset(maestro);
		for i:=1 to totalD do begin
			reset(detalles[i]);
			leer(detalles[i],regD[i]);
		end;
		minimo(detalles,regD,min);
		cantL:=0;
		read(maestro,regM);//se asume que el archivo maestro tiene elementos
		while(min.codL<>valorAlto)do begin
			cantCAL:=0;
			while(min.codL<>regM.codL)and(min.codC<>regM.codC)do//busco el codL
				read(maestro,regM);
			while((min.codL=regM.codL)and(min.codC=regM.codC))do begin
				regM.cantCA:=regM.cantCA+min.cantCA;
				regM.cantCN:=regM.cantCN+min.cantCN;
				regM.cantR:=regM.cantR+min.cantR;
				regM.cantF:=regM.cantF+min.cantF;
				cantCAL:=cantCAL+regM.cantCA;
				minimo(detalles,regD,min);
			end;
			seek(maestro,filepos(maestro)-1);
			write(maestro,regM);
			writeln('Cantidad de casos en la localidad:',cantCAL);
			if(cantCAL>50)then
				cantL:=cantL+1
		end;
		writeln('La cantidad de localidades con mas de 50 casos activos es:',cantL);
		for i:=1 to totalD do
			close(detalles[i]);
		close(maestro);
	end;
var	maestro:aMaestro;detalles:vectorD;
begin
	assign(maestro,'Archivo Maestro');
	//crearMaestro(maestro);//se dispone
	//crearDetalles(detalles);//se dispone
	actualizarMaestro(maestro,detalles);
end.
