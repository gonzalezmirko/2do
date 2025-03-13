{8. Se cuenta con un archivo con información de las diferentes distribuciones de linux
existentes. De cada distribución se conoce: nombre, año de lanzamiento, número de
versión del kernel, cantidad de desarrolladores y descripción. El nombre de las
distribuciones no puede repetirse. Este archivo debe ser mantenido realizando bajas
lógicas y utilizando la técnica de reutilización de espacio libre llamada lista invertida.
Escriba la definición de las estructuras de datos necesarias y los siguientes
procedimientos:
a. ExisteDistribucion: módulo que recibe por parámetro un nombre y devuelve
verdadero si la distribución existe en el archivo o falso en caso contrario.
b. AltaDistribución: módulo que lee por teclado los datos de una nueva
distribución y la agrega al archivo reutilizando espacio disponible en caso
de que exista. (El control de unicidad lo debe realizar utilizando el módulo
anterior). En caso de que la distribución que se quiere agregar ya exista se
debe informar “ya existe la distribución”.
c. BajaDistribución: módulo que da de baja lógicamente una distribución 
cuyo nombre se lee por teclado. Para marcar una distribución como
borrada se debe utilizar el campo cantidad de desarrolladores para
mantener actualizada la lista invertida. Para verificar que la distribución a
borrar exista debe utilizar el módulo ExisteDistribucion. En caso de no existir
se debe informar “Distribución no existente”.}
program ej8;
type
	distri=record
		nombre:string;
		anio:integer;
		numeroK:integer;
		cantD:integer;
		descrip:integer;
	end;
	maestro=file of distri;
	procedure leerD(var d:distri);
	begin
		randomize;
		write('Cantidad de desarroladores:');
		readln(d.cantD);
		if(d.cantD<>-1)then begin
			write('Nombre:');
			readln(d.nombre);
			d.numeroK:=random(10);
			d.anio:=random(10);
			d.descrip:=random(10);
		end;
	end;
	procedure crearArchivo(var a:maestro);
	var d:distri;
	begin
		writeln('-Creando archivo distribucion-');
		rewrite(a);
		d.nombre:='CABECERA';
		d.anio:=0;
		d.numeroK:=0;
		d.cantD:=0;
		d.descrip:=0;
		write(a,d);
		leerD(d);
		while(d.cantD<>-1)do begin
			write(a,d);
			leerD(d);
		end;
		close(a);
	end;
	{a. ExisteDistribucion: módulo que recibe por parámetro un nombre y devuelve
	verdadero si la distribución existe en el archivo o falso en caso contrario.}
	function ExisteDistribucion(var a:maestro;nom:string):boolean;
	var ok:boolean;d:distri;
	begin
		reset(a);
		ok:=false;
		while((not eof(a))and(not ok))do begin
			read(a,d);
			if(d.nombre=nom)then
				ok:=true;
		end;
		close(a);
		ExisteDistribucion:=ok;
	end;
	{b. AltaDistribución: módulo que lee por teclado los datos de una nueva
	distribución y la agrega al archivo reutilizando espacio disponible en caso
	de que exista. (El control de unicidad lo debe realizar utilizando el módulo
	anterior). En caso de que la distribución que se quiere agregar ya exista se
	debe informar “ya existe la distribución”.}
	procedure AltaDistribución(var a:maestro);
	var d,cabecera:distri;
	begin
		leerD(d);
		reset(a);
		read(a,cabecera);
		if(ExisteDistribucion(a,d.nombre))then
			writeln('Ya existe la distribucion.');
		else
		begin
			while(not eof(a))do begin
				read(a,d);
				
			end;
		end;
		close(a);
	end;
var a:maestro;
begin
	assign(a,'Distribuciones');
	crearArchivo(a);
	
end.
