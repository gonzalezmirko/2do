{8. Se cuenta con un archivo con información de las diferentes distribuciones de linux
existentes. De cada distribución se conoce: nombre, año de lanzamiento, número de
versión del kernel, cantidad de desarrolladores y descripción. El nombre de las
distribuciones no puede repetirse.
Este archivo debe ser mantenido realizando bajas lógicas y utilizando la técnica de
reutilización de espacio libre llamada lista invertida.
Escriba la definición de las estructuras de datos necesarias y los siguientes
procedimientos:
ExisteDistribucion: módulo que recibe por parámetro un nombre y devuelve verdadero si
la distribución existe en el archivo o falso en caso contrario.
AltaDistribución: módulo que lee por teclado los datos de una nueva distribución y la
agrega al archivo reutilizando espacio disponible en caso de que exista. (El control de
unicidad lo debe realizar utilizando el módulo anterior). En caso de que la distribución que
se quiere agregar ya exista se debe informar “ya existe la distribución”.
BajaDistribución: módulo que da de baja lógicamente una distribución  cuyo nombre se
lee por teclado. Para marcar una distribución como borrada se debe utilizar el campo
cantidad de desarrolladores para mantener actualizada la lista invertida. Para verificar
que la distribución a borrar exista debe utilizar el módulo ExisteDistribucion. En caso de no
existir se debe informar “Distribución no existente”}
program ej8;
type
	distribucion=record
		nombre:string;
		anio:integer;
		numK:integer;
		cantD:integer;
		des:string;
	end;
	arch=file of distribucion;

	function existeDistrubucion(nombre:string;var a:arch):boolean;
	var ok:boolean;d,cabecera:distribucion;
	begin
		reset(a);
		read(a,cabecera);
		ok:=false;
		while((not eof(a))and(not ok))do begin
			read(a,d);
			if(d.nombre=nombre)then
				ok:=true;
		end;
		existeDistrubucion:=ok;
		close(a);
	end;
	procedure leerD(var d:distribucion);
	begin
		readln(d.nombre);
		readln(d.anio);
		readln(d.numK);
		readln(d.cantD);
		readln(d.des);
	end;
	procedure AltaDistribucion(var a:arch);
	var d,cabecera:distribucion;pos:integer;
	begin
		leerD(d);
		if(existeDistrubucion(d.nombre,a))then
			writeln('Ya existe la distribucion.')
		else
			begin
				reset(a);
				read(a,cabecera);
				if(cabecera.cantD=0)then begin//no hay espacio disponible y se agrega al final
					seek(a,filesize(a));
					write(a,d);
					writeln('Se agrego la distribucion al final del archivo.');
				end
				else
					begin
						pos:=cabecera.cantD*-1;
						seek(a,pos);
						read(a,cabecera);
						seek(a,pos);
						write(a,d);
						seek(a,0);
						write(a,cabecera);
					end;
				close(a);
			end;
	end;
	procedure BajaDistribucion(var a:arch);
	var nombre:string;cabecera,d:distribucion;
	begin
		readln(nombre);
		if(not existeDistrubucion(nombre,a))then
			writeln('No existe la distribucion a eliminar.')
		else
			begin
				reset(a);
				read(a,cabecera);
				read(a,d);
				while(d.nombre<>nombre)do
					read(a,d);
				seek(a,filepos(a)-1);
				write(a,cabecera);
				cabecera.cantD:=(filepos(a)-1)*-1;
				seek(a,0);
				write(a,cabecera);
				close(a);
				writeln('Se elimino la distribucion con nombre:',nombre);
			end;
	end;
var
	archivo:arch;
begin
	assign(archivo,'distribuciones');
	//crearArchivo(archivo);//se dispone por que es un archivo para mantener
	AltaDistribucion(archivo);
	BajaDistribucion(archivo);
end.
