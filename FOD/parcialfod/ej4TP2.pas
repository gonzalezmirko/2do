{4. A partir de información sobre la alfabetización en la Argentina, se necesita actualizar un
archivo que contiene los siguientes datos: nombre de provincia, cantidad de personas
alfabetizadas y total de encuestados. Se reciben dos archivos detalle provenientes de dos
agencias de censo diferentes, dichos archivos contienen: nombre de la provincia, código de
localidad, cantidad de alfabetizados y cantidad de encuestados. Se pide realizar los módulos
necesarios para actualizar el archivo maestro a partir de los dos archivos detalle.
NOTA: Los archivos están ordenados por nombre de provincia y en los archivos detalle
pueden venir 0, 1 ó más registros por cada provincia}
program ej4;
const
	valorAlto='ZZZ';
type
	regMaestro=record
		nombreP:string;
		cantPA:integer;
		totalE:integer;
	end;
	regDetalle=record
		nombreP:string;
		codL:integer;
		cantA:integer;
		cantE:integer;
	end;
	archivoMaestro=file of regMaestro;
	archivoDetalle=file of regDetalle;
	procedure leer(var a:archivoDetalle;var reg:regDetalle);
	begin
		if(not eof(a))then
			read(a,reg)
		else
			reg.nombreP:=valorAlto;
	end;
	procedure minimo(var det1,det2:archivoDetalle;var regD1,regD2,min:regDetalle);
	begin
		if(regD1.nombreP<=regD2.nombreP)then begin
			min:=regD1;
			leer(det1,regD1);
		end
		else
			begin
				min:=regD2;
				leer(det2,regD2);
			end;
	end;
	procedure actualizarMaestro(var maestro:archivoMaestro;var det1,det2:archivoDetalle);
	var	regM:regMaestro;regD1,regD2:regDetalle;min:regDetalle;
	begin
		reset(maestro);
		reset(det1);
		reset(det2);
		leer(det1,regD1);
		leer(det2,regD2);
		minimo(det1,det2,regD1,regD2,min);
		while(min.nombreP<>valorAlto)do begin
			read(maestro,regM);
			while(min.nombreP<>regM.nombreP)do
				read(maestro,regM);
			while(min.nombreP=regM.nombreP)do begin
				regM.cantPA:=regM.cantPA+min.cantA;
				regM.totalE:=regM.totalE+min.cantE;
				minimo(det1,det2,regD1,regD2,min);
			end;
			seek(maestro,filepos(maestro)-1);
			write(maestro,regM);
		end;
		close(maestro);
		close(det1);
		close(det2);
	end;
var
	maestro:archivoMaestro;det1,det2:archivoDetalle;
begin
	//crearMaestro(maestro;//se dispone
	//crearDetalle(det1);//se dispone
	//crearDetalle(det1);//se dispone
	actualizarMaestro(maestro,det1,det2);
end.
