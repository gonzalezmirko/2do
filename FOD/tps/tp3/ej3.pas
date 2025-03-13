{3. Realizar un programa que genere un archivo de novelas filmadas durante el presente
año. De cada novela se registra: código, género, nombre, duración, director y precio.
El programa debe presentar un menú con las siguientes opciones:
a. Crear el archivo y cargarlo a partir de datos ingresados por teclado. Se
utiliza la técnica de lista invertida para recuperar espacio libre en el
archivo. Para ello, durante la creación del archivo, en el primer registro del
mismo se debe almacenar la cabecera de la lista. Es decir un registro
ficticio, inicializando con el valor cero (0) el campo correspondiente al
código de novela, el cual indica que no hay espacio libre dentro del
archivo.
b. Abrir el archivo existente y permitir su mantenimiento teniendo en cuenta el
inciso a., se utiliza lista invertida para recuperación de espacio. En
particular, para el campo de ´enlace´ de la lista, se debe especificar los
números de registro referenciados con signo negativo, (utilice el código de
novela como enlace).Una vez abierto el archivo, brindar operaciones para:
i. Dar de alta una novela leyendo la información desde teclado. Para
esta operación, en caso de ser posible, deberá recuperarse el
espacio libre. Es decir, si en el campo correspondiente al código de
novela del registro cabecera hay un valor negativo, por ejemplo -5,
se debe leer el registro en la posición 5, copiarlo en la posición 0
(actualizar la lista de espacio libre) y grabar el nuevo registro en la
posición 5. Con el valor 0 (cero) en el registro cabecera se indica
que no hay espacio libre.
ii. Modificar los datos de una novela leyendo la información desde
teclado. El código de novela no puede ser modificado.
iii. Eliminar una novela cuyo código es ingresado por teclado. Por
ejemplo, si se da de baja un registro en la posición 8, en el campo
código de novela del registro cabecera deberá figurar -8, y en el
registro en la posición 8 debe copiarse el antiguo registro cabecera.
c. Listar en un archivo de texto todas las novelas, incluyendo las borradas, que
representan la lista de espacio libre. El archivo debe llamarse “novelas.txt”.
NOTA: Tanto en la creación como en la apertura el nombre del archivo debe ser
proporcionado por el usuario.}
program ej3;
const
	valorAlto=999;
type
	novela=record
		codigo:integer;
		genero:string;
		duracion:real;
		director:String;
		precio:real;
	end;
	archivo=file of novela;
	procedure leerN(var n:novela);
	begin
		with n do begin
			write('Codigo de novela:');
			readln(codigo);
			if(codigo<>-1)then begin
				write('Genero;');
				readln(genero);
				write('Duracion:');
				readln(duracion);
				write('Director');
				readln(director);
				write('Precio:');
				readln(precio);
			end;
		end;
	end;
	{a. Crear el archivo y cargarlo a partir de datos ingresados por teclado. Se
	utiliza la técnica de lista invertida para recuperar espacio libre en el
	archivo. Para ello, durante la creación del archivo, en el primer registro del
	mismo se debe almacenar la cabecera de la lista. Es decir un registro
	ficticio, inicializando con el valor cero (0) el campo correspondiente al
	código de novela, el cual indica que no hay espacio libre dentro del archivo.}
	procedure crearArchivo(var a:archivo);
	var n:novela;
	begin
		rewrite(a);
		n.codigo:=0;
		n.genero:='';
		n.director:='';
		n.precio:=0.0;
		n.duracion:=0.0;
		write(a,n);
		leerN(n);
		while (n.codigo<>-1)do begin
			write(a,n);
			leerN(n);
		end;
		close(a);
	end;
	{i. Dar de alta una novela leyendo la información desde teclado. Para
	esta operación, en caso de ser posible, deberá recuperarse el
	espacio libre. Es decir, si en el campo correspondiente al código de
	novela del registro cabecera hay un valor negativo, por ejemplo -5,
	se debe leer el registro en la posición 5, copiarlo en la posición 0
	(actualizar la lista de espacio libre) y grabar el nuevo registro en la
	posición 5. Con el valor 0 (cero) en el registro cabecera se indica
	que no hay espacio libre.}
	procedure darAlta(var a:archivo);
	var n,aux:novela;pos:integer;
	begin
		writeln('Agregar una novela(espacio libre o al final):');
		leerN(n);
		reset(a);
		read(a,aux);
		if(aux.codigo=0)then begin
			seek(a,filesize(a));
			write(a,n);
			writeln('-Se agrego la novela al final del archivo-');
		end
		else
			begin
				pos:=aux.codigo*-1;
				seek(a,pos);
				read(a,aux);
				seek(a,filepos(a)-1);
				write(a,n);
				seek(a,0);
				write(a,aux);
			end;
		close(a);
	end;
	{ii. Modificar los datos de una novela leyendo la información desde
	teclado. El código de novela no puede ser modificado.}
	procedure leerArchivo(var a:archivo;var n:novela);
	begin
		if(not eof(a))then
			read(a,n)
		else
			n.codigo:=valorAlto;
	end;
	procedure encontre(var a:archivo;num:integer;var pos:integer);
	var ok:boolean;n:novela;
	begin
		reset(a);
		leerArchivo(a,n);
		ok:=false;
		pos:=0;
		while((n.codigo<>valorAlto)and(not ok))do begin
			if(n.codigo=num)then begin
				ok:=true;
				pos:=filepos(a)-1;
			end;
			leerArchivo(a,n);
		end;
		close(a);
	end;
	procedure modificarNovela(var a:archivo);
	var	numN,pos:integer;genero,director:string;precio,duracion:real;n:novela;
	begin
		writeln('-Modificar novela(exceptuando codigo)-');
		write('Codigo de novela a modificar:');
		readln(numN);
		encontre(a,numN,pos);
		if(pos<>0)then begin
			write('Modificar genero:');
			readln(genero);
			write('Modificar duracion:');
			readln(duracion);
			write('Modificar director:');
			readln(director);
			write('Modificar precio:');
			readln(precio);
			n.genero:=genero;
			n.duracion:=duracion;
			n.director:=director;
			n.precio:=precio;
			reset(a);
			seek(a,pos);
			write(a,n);
			close(a);
		end
		else
			writeln('-No se encontro el codigo de novela-');
	end;
	procedure imprimirArchivo(var a:archivo);
	var n:novela;
	begin
		reset(a);
		writeln('-NOVELAS-');
		while(not eof(a))do begin
			read(a,n);
			writeln('Codigo de novela:',n.codigo);
			writeln('Genero:',n.genero);
			writeln('Director:',n.director);
			writeln('Duracion:',n.duracion:2:2);
			writeln('Precio:',n.precio:2:2);
		end;
		close(a);
	end;
	{iii. Eliminar una novela cuyo código es ingresado por teclado. Por
	ejemplo, si se da de baja un registro en la posición 8, en el campo
	código de novela del registro cabecera deberá figurar -8, y en el
	registro en la posición 8 debe copiarse el antiguo registro cabecera.}
	procedure darBaja(var a:archivo);
	var num:integer;ok:boolean;aux,n:novela;
	begin
		writeln('-Eliminar una novela(con codigo de novela)-');
		write('Codigo de novela a eliminar:');
		readln(num);
		reset(a);
		ok:=false;
		//leo cabecera
		read(a,aux);
		while(not eof(a)and not ok)do begin
			read(a,n);
			if(n.codigo=num)then begin
				ok:=true;
				seek(a,filepos(a)-1);
				write(a,aux);
				aux.codigo:=(filepos(a)-1)*-1;
				seek(a,0);
				write(a,aux);
			end;
		end;
		close(a);
		if(ok)then
			writeln('Se elimino correctamente la novela con codigo :',num)
		else
			writeln('No se encontro la novela con codigo:',num);
	end;
	procedure subMenu(var a:archivo);
	var ok:boolean;num:integer;
	begin
		ok:=true;
		while(ok)do begin
			writeln('-MENU DE ARCHIVO EXISTENTE-');
			writeln('-Seleccionar un numero:-');
			writeln('-1: Dar alta una novela (agregar novela en espacio vacio o no)-');
			writeln('-2: Modificar novela(sin modificar el codigo de novela)-');
			writeln('-3: Dar baja una novela (eliminar una novela por codigo)-');
			writeln('-4: Imprimir Archivo-');
			writeln('-9: Volver al menu principal-');
			readln(num);
			case num of
				1:
				begin
					darAlta(a);
				end;
				2:
				begin
					modificarNovela(a);
				end;
				3:
				begin
					darBaja(a);
				end;
				4:
				begin
					imprimirArchivo(a);
				end;
				9:
				begin
					ok:=false;
				end
				else
					writeln('-Opcion invalida, por favor ingrese una opcion correcta-');
			end;
		end;
	end;
	procedure menu(var ok:boolean);
	var arch:archivo;num:integer;
	begin
		writeln('-MENU DE OPCIONES-');
		writeln('-Seleccionar un numero :-');
		writeln('-1: Crear archivo-');
		writeln('-2: Abrir archivo existente-');
		writeln('-3: Listar en un archivo de texto todas las novelas-');
		writeln('-9:SALIR-');
		readln(num);
		assign(arch,'Novela');
		case num of
			1:
			begin
				crearArchivo(arch);
			end;
			2:
			begin
				subMenu(arch);
			end;
			3:
			begin
				writeln('3');
			end;
			9:
			begin
				ok:=false;
			end;
			else
				writeln('-Opcion invalida, por favor ingrese una opcion correcta-');
		end;
	end;
var ok:boolean;
begin
	ok:=true;
	menu(ok);
	while(ok)do begin
		menu(ok);
	end;
end.
