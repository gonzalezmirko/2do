{5. Realizar un programa para una tienda de celulares, que presente un menú con
opciones para:
a. Crear un archivo de registros no ordenados de celulares y cargarlo con datos
ingresados desde un archivo de texto denominado “celulares.txt”. Los registros
correspondientes a los celulares deben contener: código de celular, nombre,
descripción, marca, precio, stock mínimo y stock disponible.
b. Listar en pantalla los datos de aquellos celulares que tengan un stock menor al
stock mínimo.
***c. Listar en pantalla los celulares del archivo cuya descripción contenga una
cadena de caracteres proporcionada por el usuario.
d. Exportar el archivo creado en el inciso a) a un archivo de texto denominado
“celulares.txt” con todos los celulares del mismo. El archivo de texto generado
podría ser utilizado en un futuro como archivo de carga (ver inciso a), por lo que
debería respetar el formato dado para este tipo de archivos en la NOTA 2.
NOTA 1: El nombre del archivo binario de celulares debe ser proporcionado por el usuario.
NOTA 2: El archivo de carga debe editarse de manera que cada celular se especifique en
tres líneas consecutivas. En la primera se especifica: código de celular, el precio y
marca, en la segunda el stock disponible, stock mínimo y la descripción y en la tercera
nombre en ese orden. Cada celular se carga leyendo tres líneas del archivo
“celulares.txt”.}
{6. Agregar al menú del programa del ejercicio 5, opciones para:
a. Añadir uno o más celulares al final del archivo con sus datos ingresados por
teclado.
b. Modificar el stock de un celular dado.
c. Exportar el contenido del archivo binario a un archivo de texto denominado:
”SinStock.txt”, con aquellos celulares que tengan stock 0.
NOTA: Las búsquedas deben realizarse por nombre de celular.}
program ej5;
type
	celular=record
		cod:integer;
		nombre:string;
		descripcion:string;
		marca:string;
		precio:real;
		stockMin:integer;
		stockDis:integer;
	end;
	archivo=file of celular;
	procedure menu(var num:integer);
	begin
		writeln('-MENU DE OPCIONES-');
		writeln('Opcion 1:Crear archivo binario de celulares.');
		writeln('Opcion 2:Listar en pantalla el archivo creado.');
		writeln('Opcion 3:Listar en pantalla los celulares (descripcion).');
		writeln('Opcion 4:Exportar el archivo creado en el inciso a.');
		writeln('Opcion 5:Añadir celulares al final del archivo.');
		writeln('Opcion 6:Modificar el stock de un celular.');
		writeln('Opcion 7: Exportar el contenido del archivo binario con stock en 0.');
		writeln('Opcion 8:Imprimir archivo binario.');
		writeln('Opcion 9:Salir');
		write('Seleccionar una opcion :');
		readln(num);
	end;
	{a. Crear un archivo de registros no ordenados de celulares y cargarlo con datos
	ingresados desde un archivo de texto denominado “celulares.txt”. Los registros
	correspondientes a los celulares deben contener: código de celular, nombre,
	descripción, marca, precio, stock mínimo y stock disponible.}
	{En la primera se especifica: código de celular, el precio y
	marca, en la segunda el stock disponible, stock mínimo y la descripción y en la tercera
	nombre en ese orden. Cada celular se carga leyendo tres líneas del archivo
	“celulares.txt”.}
	procedure crearArchivo(var arc:archivo);
	var nom:string; cel:celular; carga:text;
	begin
		write('Ingrese el nombre del archivo a crear: ');
		readln(nom);
		assign(carga,'celulares.txt');
		reset(carga);
		assign(arc,nom);
		rewrite(arc);
		while(not eof(carga))do begin
			readln(carga,cel.cod,cel.precio,cel.marca);
			readln(carga,cel.stockDis,cel.stockMin,cel.descripcion);
			readln(carga,cel.nombre);
			write(arc,cel);
		end;
		close(arc);
		close(carga);
		writeln('');
		writeln('Archivo binario generado !');
	end;
	{d. Exportar el archivo creado en el inciso a) a un archivo de texto denominado
	“celulares.txt” con todos los celulares del mismo. El archivo de texto generado
	podría ser utilizado en un futuro como archivo de carga (ver inciso a), por lo que
	debería respetar el formato dado para este tipo de archivos en la NOTA 2.}
	procedure abrirArchivo(var a:archivo);
	var c:celular; nom:string; arch:text;
	begin
		writeln('Ingrese el nombre del archivo a exportar:');
		readln(nom);
		Assign(a,nom);
		reset(a);
		Assign(arch,'celulares.txt');
		rewrite(arch);
		while(not eof(a))do begin
			read(a,c);
			writeln(c.cod,c.precio,c.marca);
			writeln(c.stockDis,c.stockMin,c.descripcion);
			writeln(c.nombre);
		end;
		close(a);
		close(arch);
	end;
	{b. Listar en pantalla los datos de aquellos celulares que tengan un stock menor al
	stock mínimo.}
	procedure incisoB(var a:archivo);
	var c:celular;nom:string;
	begin
		writeln('Nombre del archivo a listar:');
		readln(nom);
		Assign(a,nom);
		reset(a);
		while(not eof(a))do begin
			read(a,c);
			if(c.stockDis<c.stockMin)then begin
				writeln('-Lista-');
				writeln('Nombre:',c.nombre);
			end;
		end;
		close(a);
	end;
	procedure leerCel(var c:celular);
	begin
		writeln('Nombre celu:');
		readln(c.nombre);
		if(c.nombre<>'fin')then begin
			writeln('Cod celu:');
			readln(c.cod);
			writeln('Precio:');
			readln(c.precio);
			writeln('Marca:');
			readln(c.marca);
			writeln('Stock Dis:');
			readln(c.stockDis);
			writeln('Stock Min:');
			readln(c.stockMin);
			writeln('Stock Desc:');
			readln(c.descripcion);
		end;
	end;
	{a. Añadir uno o más celulares al final del archivo con sus datos ingresados por
	teclado.}
	procedure agregarCelular();
	var a:archivo;c:celular;
	begin
		Assign(a,'celulares.txt');
		reset(a);
		leerCel(c);
		seek(a,fileSize(a));
		while(c.nombre<>'fin')do begin
			write(a,c);
			leerCel(c);
		end;
		close(a);
	end;
	{b. Modificar el stock de un celular dado.}
	procedure modificarStock();
	var a:archivo;c:celular;nom:string;pos:integer;encontre:boolean;
	begin
		writeln('Modificar stock del celular(nombre):');
		readln(nom);
		Assign(a,'celulares.txt');
		reset(a);
		pos:=-1;
		encontre:=false;
		while((not eof(a))and(not encontre))do begin
			read(a,c);
			if(c.nombre=nom)then begin
				encontre:=true;
				pos:=filePos(a)-1;
			end;
		end;
		if((encontre)and(pos<>-1))then begin
			writeln('StockMin:');
			readln(c.stockMin);
			writeln('StockDis:');
			readln(c.stockDis);
			seek(a,pos);
			write(a,c);
		end;
		close(a);
	end;
	procedure imprimirArchivoBin();
	var a:archivo; c:celular;
	begin
		Assign(a,'celulares.txt');
		reset(a);
		while(not eof(a))do begin
			read(a,c);
			writeln(c.nombre);
			writeln(c.stockMin);
			writeln(c.stockDis);
		end;
		close(a);
	end;
	{c. Exportar el contenido del archivo binario a un archivo de texto denominado:
	”SinStock.txt”, con aquellos celulares que tengan stock 0.}
	procedure exportarArchivoBinario();
	var a:archivo; arch:text; c:celular;
	begin
		Assign(a,'celulares.txt');
		Assign(arch,'SinStock.txt');
		reset(a);
		rewrite(arch);
		while(not eof(a))do begin
			read(a,c);
			if(c.stockDis=0)then begin
				writeln(arch,c.nombre);
				writeln(arch,c.StockDis);
				writeln(arch,c.precio);
			end;
		end;
		close(a);
		close(arch);
	end;
var num:integer;a:archivo;
begin
	menu(num);
	while(num<>9)do begin
		case num of
			1:
			begin
				crearArchivo(a);
			end;
			2:
			begin
				incisoB(a);
			end;
			3:
			begin
				//incisoC(a);
			end;
			4:
			begin
				abrirArchivo(a);
			end;
			5:
			begin
				agregarCelular();
			end;
			6:
			begin
				modificarStock();
			end;
			7:
			begin
				exportarArchivoBinario();
			end;
			8:
			begin
				imprimirArchivoBin();
			end;
		else
			writeln('ERROR!! Opcion invalida.');
		end;
		menu(num);
	end;
end.
