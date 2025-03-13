{3. Realizar un programa que presente un menú con opciones para:
a. Crear un archivo de registros no ordenados de empleados y completarlo con
datos ingresados desde teclado. De cada empleado se registra: número de
empleado, apellido, nombre, edad y DNI. Algunos empleados se ingresan con
DNI 00. La carga finaliza cuando se ingresa el String ‘fin’ como apellido.
b. Abrir el archivo anteriormente generado y
i. Listar en pantalla los datos de empleados que tengan un nombre o apellido
determinado, el cual se proporciona desde el teclado.
ii. Listar en pantalla los empleados de a uno por línea.
iii. Listar en pantalla los empleados mayores de 70 años, próximos a jubilarse.
NOTA: El nombre del archivo a crear o utilizar debe ser proporcionado por el usuario.}
{4. Agregar al menú del programa del ejercicio 3, opciones para:
a. Añadir uno o más empleados al final del archivo con sus datos ingresados por
teclado. Tener en cuenta que no se debe agregar al archivo un empleado con
un número de empleado ya registrado (control de unicidad).
b. Modificar la edad de un empleado dado.
c. Exportar el contenido del archivo a un archivo de texto llamado
“todos_empleados.txt”.
d. Exportar a un archivo de texto llamado: “faltaDNIEmpleado.txt”, los empleados
que no tengan cargado el DNI (DNI en 00).
NOTA: Las búsquedas deben realizarse por número de empleado.}
program ej3;
type
	empleado=record
		numE:integer;
		ape:string;
		nom:string;
		edad:integer;
		DNI:integer;
	end;
	archivo=file of empleado;
	procedure leerE(var e:empleado);
	begin
		writeln('Apellido:');
		readln(e.ape);
		if(e.ape<>'fin')then begin
			writeln('numE:');
			readln(e.numE);
			writeln('nombre:');
			readln(e.nom);
			writeln('edad:');
			readln(e.edad);
			writeln('DNI:');
			readln(e.DNI);
		end;
	end;
	procedure cargarArchivo(var a:archivo);
	var e:empleado;nombreA:string;
	begin
		writeln('Nombre archivo:');
		readln(nombreA);
		Assign(a,nombreA);
		rewrite(a);
		leerE(e);
		while(e.ape<>'fin')do begin
			write(a,e);
			leerE(e);
		end;
		close(a)
	end;
	{i. Listar en pantalla los datos de empleados que tengan un nombre o apellido
	determinado, el cual se proporciona desde el teclado.}
	procedure listarNomApe(var a:archivo);
	var	nomApe:string;e:empleado;
	begin
		reset(a);
		writeln('Buscar nombre o apellido:');
		readln(nomApe);
		while(not eof(a))do begin
			read(a,e);
			if((nomApe=e.nom)or(nomApe=e.ape))then
				writeln(e.numE);
		end;
		close(a);
	end;
	procedure listarEmpleados(var a:archivo);
	var e:empleado;
	begin
		writeln('-LISTA DE EMPLEADOS-');
		reset(a);
		while(not eof(a))do begin
			read(a,e);
			writeln('Nombre:',e.nom);
			writeln('Numero Empleado:',e.numE);
			writeln('Edad:',e.edad);
		end;
		close(a);
	end;
	procedure listarJubilados(var a:archivo);
	var e:empleado;
	begin
		reset(a);
		while(not eof(a))do begin
			read(a,e);
			if(e.edad>70)then
				writeln('El empleado ',e.ape,' esta proximo a jubilarse.');
		end;
		close(a);
	end;
	{a. Añadir uno o más empleados al final del archivo con sus datos ingresados por
	teclado. Tener en cuenta que no se debe agregar al archivo un empleado con
	un número de empleado ya registrado (control de unicidad).}
	function buscarEmpleado(var a:archivo;numE:integer):boolean;
	var encontre:boolean;e:empleado;
	begin
		encontre:=false;
		Seek(a,0);
		while((not(eof(a)))and(not encontre))do begin
			read(a,e);
			if(numE=e.numE)then
				encontre:=true;
		end;
		buscarEmpleado:=encontre;
	end;
	procedure agregarEmpleado(var a:archivo);
	var e:empleado;
	begin
		reset(a);
		leerE(e);
		while(e.ape<>'fin')do begin
			if(buscarEmpleado(a,e.numE))then
				writeln('El empleado ya esta registrado.')
			else begin
				Seek(a,filePos(a));
				write(a,e);
				writeln('Empleado agregado.');
			end;
			leerE(e);
		end;
		close(a);
	end;
	procedure modificarEdadEmpleado(var a:archivo);
	var numE:integer;encontre:boolean;e:empleado;pos:integer;
	begin
		writeln('Numero de empleado a modificar:');
		readln(numE);
		reset(a);
		encontre:=false;
		pos:=-1;
		while((not eof(a))and(not encontre))do begin
			read(a,e);
			if(numE=e.numE)then begin
				pos:=filePos(a)-1;//pos menos 1?
				encontre:=true;//esta bien que corte?
			end;
		end;
		if((encontre)and(pos<>-1))then begin
			writeln('Edad a modificar:');
			readln(e.edad);
			seek(a,pos);//o aca pos-1?
			write(a,e);
		end
		else
			writeln('No se encontro el numero de empleado.');
		close(a);
	end;
	{c. Exportar el contenido del archivo a un archivo de texto llamado
	“todos_empleados.txt”.}
	procedure exportarArchivo(var a:archivo);
	var archTxt:text;nom:string;e:empleado;
	begin
		reset(a);
		nom:='todos_empleados';
		Assign(archTxt,nom);
		rewrite(archTxt);
		while(not eof(a))do begin
			read(a,e);
			writeln(archTxt,e.numE,' ',e.nom,' ',e.ape,e.dni,e.edad);
		end;
		close(a);
		close(archTxt);
	end;
	{d. Exportar a un archivo de texto llamado: “faltaDNIEmpleado.txt”, los empleados
	que no tengan cargado el DNI (DNI en 00).}
	procedure incisoD(var a:archivo);
	var e:empleado; arch:text; nom:string;
	begin
		reset(a);
		nom:='faltaDNIEmpleado';
		Assign(arch,nom);
		rewrite(arch);
		while(not eof(a))do begin
			read(a,e);
			if(e.dni = 00)then
				writeln(arch,e.numE,' ',e.nom,' ',e.ape,e.dni,e.edad);
		end;
		close(a);
		close(arch);
		writeln('Archivo de texto generado.');
	end;
	procedure abrirArchivo();
	var nomArch:string;a:archivo;num:integer;
	begin
		writeln('Nombre del archivo:');
		readln(nomArch);
		Assign(a,nomArch);
		writeln('Seleccionar opcion ...');
		writeln('Opcion 1:Listar(ejercicio3).');
		writeln('Opcion 2:Agregar Empleado.');
		writeln('Opcion 3:Modificar edad de un empleado.');
		writeln('Opcion 4:Exportar archivo binario a txt.');
		writeln('Opcion 5:Exportar a archivo de texto los dni no cargados(dni 00).');
		readln(num);
		case num of
			1:
			begin
				listarNomApe(a);
				listarEmpleados(a);
				listarJubilados(a);
			end;
			2:
			begin
				agregarEmpleado(a);
			end;
			3:
			begin
				modificarEdadEmpleado(a);
			end;
			4:
			begin
				exportarArchivo(a);
			end;
			5:
			begin
				incisoD(a);
			end;
		end;
	end;
	procedure menu(var num:integer);
	begin
		writeln('-MENU DE OPCIONES-');
		writeln('Opcion 1: Crear Archivo de empleados.');
		writeln('Opcion 2: Abrir archivo existente.');
		writeln('Opcion 9: Terminar.');
		writeln('Seleccione una opcion ...');
		readln(num);
	end;
var
	a:archivo;
	num:integer;
begin
	menu(num);
	while(num<>9)do begin
		case num of
			1:
			begin
				cargarArchivo(a);
			end;
			2:
			begin
				abrirArchivo();
			end;
		else
			writeln('Opcion invalida.');
		end;
		menu(num);
	end;
end.
