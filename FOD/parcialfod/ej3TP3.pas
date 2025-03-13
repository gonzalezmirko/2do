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
proporcionado por el usuario}
program ej3;
const 
	valorAlto=999;
type
	novela=record
		cod:integer;
		nombre:string;
		precio:real;
	end;
	archivo=file of novela;
	procedure leerN(var n:novela);
	begin
		randomize;
		write('Codigo de novela:');
		readln(n.cod);
		if(n.cod<>-1)then begin
			write('Nombre de novela:');
			readln(n.nombre);
			n.precio:=random(10);
		end;
	end;
	procedure crearArchivo(var a:archivo);
	var n:novela;
	begin
		writeln('-Creando Archivo Novelas ...-');
		rewrite(a);
		n.cod:=0;
		n.nombre:='';
		n.precio:=0.0;
		write(a,n);
		leerN(n);
		while(n.cod<>-1)do begin
			write(a,n);
			leerN(n);
		end;
		close(a);
	end;
	procedure darAlta(var a:archivo);
	var n,cabecera:novela;pos:integer;
	begin
		writeln('Agregando una nueva novela...');
		leerN(n);
		reset(a);
		read(a,cabecera);
		if(cabecera.cod=0)then begin//no hay espacio libre , se agrega al final del archivo
			seek(a,filesize(a));
			write(a,n);
			writeln('Se agrego correctamente la novela.');
		end
		else
			begin
				pos:=cabecera.cod*-1;//conviero el codigo en positivo
				seek(a,pos);//voy a la posicion 
				read(a,cabecera);//leo esa posicion
				seek(a,filepos(a)-1);//reacomodo punteros
				write(a,n);//escribo la nueva novela
				seek(a,0);//voy a la posicion de cabecera
				write(a,cabecera);//y guardo lo que habia en la posicion eliminada
				write('Se agrego en la posicion ',pos,' la novela.');
			end;
		close(a);
	end;
	procedure darBaja(var a:archivo);
	var cod:integer;cabecera:
	begin
		write('Eliminar una novela por su codigo:');
		readln(cod);
		reset(a);
		read(a,);
		close(a);
	end;
	procedure incisoB(var a:archivo);
	var op:integer;
	begin
		assign(a,'Novelas');
		writeln('-MENU ARCHIVO EXISTENTE-');
		writeln('Opcion 1:Dar alta');
		writeln('Opcion 2:Modificar archivo');
		writeln('Opcion 3:Dar baja');
		write('Seleccionar opcion:');
		readln(op);
		case op of
			1:
			begin
				darAlta(a);
			end;
			3:
			begin
				darBaja(a);
			end;
		end;
	end;
	procedure menu(var ok:boolean);
	var num:integer;a:archivo;
	begin
		writeln('-MENU DE OPCIONES-');
		writeln('Opcion 1:Crear Archivo');
		writeln('Opcion 2:Abrir Archivo existente');
		writeln('Opcion 3:Imprimir Archivo');
		writeln('Opcion 9:Salir');
		write('Seleccionar opcion:');
		readln(num);
		case (num) of
			1:
			begin
				assign(a,'Novelas');
				crearArchivo(a);
			end;
			2:
			begin
				incisoB(a);
			end;
			9:
			begin
				ok:=false;
			end;
		end;
	end;
var
	ok:boolean;
begin
	ok:=true;
	while(ok)do
		menu(ok);
end.
