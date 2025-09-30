{7. Realizar un programa que permita:
a) Crear un archivo binario a partir de la información almacenada en un archivo de
texto. El nombre del archivo de texto es: “novelas.txt”. La información en el
archivo de texto consiste en: código de novela, nombre, género y precio de
diferentes novelas argentinas. Los datos de cada novela se almacenan en dos
líneas en el archivo de texto. La primera línea contendrá la siguiente información:
código novela, precio y género, y la segunda línea almacenará el nombre de la
novela.
b) Abrir el archivo binario y permitir la actualización del mismo. Se debe poder
agregar una novela y modificar una existente. Las búsquedas se realizan por
código de novela.
NOTA: El nombre del archivo binario es proporcionado por el usuario desde el teclado.}
program ej7;
type 
	novela=record
		cod:integer;
		nombre:string;
		genero:string;
		precio:real;
	end;
	archivo=file of novela;
	procedure menu(var num:integer);
	begin
		writeln('-MENU DE OPCIONES-');
		writeln('Opcion 1:Crear archivo binario.');
		writeln('Opcion 2:Imprimir archivo binario.');
		writeln('Opcion 3:Agregar una novela al archivo binario.');
		writeln('Opcion 4:Modificar una novela existente del archivo binario.');
		writeln('Opcion 9:SALIR');
		write('Seleccionar una opcion:');
		readln(num);
	end;
	procedure crearArchivoBinario();
	var	a:archivo; arch:text; nom:string; n:novela;
	begin
		writeln('Nombre del archivo binario a crear:');
		readln(nom);
		Assign(a,nom);
		Assign(arch,'novelas.txt');
		reset(arch);
		rewrite(a);
		while(not eof(arch))do begin
			readln(arch,n.cod,n.precio,n.genero);
			readln(arch,n.nombre);
			write(a,n);
		end;
		close(a);
		close(arch);
	end;
	procedure imprimirArchivo();
	var a:archivo;n:novela;nom:string;
	begin
		writeln('Nombre del archivo binario a imprimir:');
		readln(nom);
		Assign(a,nom);
		reset(a);
		writeln('-NOVELAS-');
		while(not eof(a))do begin
			read(a,n);
			writeln('Nombre:',n.nombre);
			writeln('Codigo:',n.cod);
			writeln('Precio:',n.precio:2:2);
			writeln('Genero:',n.genero);
		end;
		writeln('-NOVELAS-');
		close(a);
	end;
	procedure leerNovela(var n:novela);
	begin
		writeln('Codigo de novela:');
		readln(n.cod);
		if(n.cod<>-1)then begin
			writeln('Nombre:');
			readln(n.nombre);
			writeln('Precio:');
			readln(n.precio);
			writeln('Genero:');
			readln(n.genero);
		end;
	end;
	procedure agregarUnaNovela();
	var a:archivo;n:novela;nom:string;
	begin
		writeln('Nombre del archivo binaro para agregar novelas(finaliza con codigo -1):');
		readln(nom);
		Assign(a,nom);
		reset(a);
		leerNovela(n);
		while(n.cod<>-1)do begin
			seek(a,fileSize(a));
			write(a,n);
			leerNovela(n);
		end;
		close(a);
		writeln('!NOVELA/S AGREGADA/S!');
	end;
	procedure modificarUnaNovela();
	var a:archivo;n:novela;nom:string;cod:integer;pos:integer;
	begin
		writeln('Nombre del archivo binario para modificar una novela:');
		readln(nom);
		assign(a,nom);
		reset(a);
		writeln('Codigo de novela a modificar:');
		readln(cod);
		pos:=-1;
		while( (not eof(a)) and (pos = -1))do begin
			read(a,n);
			if(n.cod=cod)then
				pos:=filePos(a);
		end;
		if(pos<>-1)then begin
			seek(a,pos-1);
			writeln('-MODIFICAR NOVELA-');
			leerNovela(n);
			write(a,n);
			writeln('!NOVELA MODIFICADA!');
		end
		else
			writeln('No se encontro el codigo en el archivo.');
		close(a);
	end;
var num:integer;
begin
	repeat
		menu(num);
		case num of
			1:
			begin
				crearArchivoBinario();
			end;
			2:
			begin
				imprimirArchivo();
			end;
			3:
			begin
				agregarUnaNovela();
			end;
			4:
			begin
				modificarUnaNovela();
			end;
		else
			writeln('ERROR!! Seleccionar una opcion correcta.');
		end;
	until(num=9);
end.
