{7. Se cuenta con un archivo que almacena información sobre especies de aves en vía
de extinción, para ello se almacena: código, nombre de la especie, familia de ave,
descripción y zona geográfica. El archivo no está ordenado por ningún criterio. Realice
un programa que elimine especies de aves, para ello se recibe por teclado las
especies a eliminar. Deberá realizar todas las declaraciones necesarias, implementar
todos los procedimientos que requiera y una alternativa para borrar los registros. Para
ello deberá implementar dos procedimientos, uno que marque los registros a borrar y
posteriormente otro procedimiento que compacte el archivo, quitando los registros
marcados. Para quitar los registros se deberá copiar el último registro del archivo en la
posición del registro a borrar y luego eliminar del archivo el último registro de forma tal
de evitar registros duplicados.
Nota: Las bajas deben finalizar al recibir el código 500000}
program ej7.pas;
type
	ave=record
		cod:integer;
		nombreE:string;
		familia:string;
		descrip:string;
		zona:string;
	end;
	maestro=file of ave;
	procedure leerAve(var a:ave);
	begin
		randomize;
		writeln('Cod de ave:');
		readln(a.cod);
		if(a.cod<>-1)then begin
			writeln('Nombre de especie:');
			readln(a.nombreE);
			a.familia:='';
			a.descrip:='';
			a.zona:='';
		end;
	end;
	procedure crearArchivo(var a:maestro);
	var av:ave;
	begin
		rewrite(a);
		{//cabecera
		av.cod:=0;
		av.nombreE:='';
		av.familia:='';
		av.descrip:='';
		av.zona:='';
		write(a,av);
		*no se necesita en el enunciado}
		leerAve(av);
		while(av.cod<>-1)do begin
			write(a,av);
			leerAve(av);
		end;
		close(a);
	end;
	procedure imprimirArchivo(var a:maestro);
	var av:ave;
	begin
		reset(a);
		while(not eof(a))do begin
			read(a,av);
			writeln('-Cod Ave:',av.cod,'- -Nombre especie:',av.nombreE,'-');
		end;
		close(a);
		writeln();
	end;
	{Realice un programa que elimine especies de aves, para ello se recibe por teclado las
	especies a eliminar. Deberá realizar todas las declaraciones necesarias, implementar
	todos los procedimientos que requiera y una alternativa para borrar los registros. Para
	ello deberá implementar dos procedimientos, uno que marque los registros a borrar y
	posteriormente otro procedimiento que compacte el archivo, quitando los registros
	marcados. Para quitar los registros se deberá copiar el último registro del archivo en la
	posición del registro a borrar y luego eliminar del archivo el último registro de forma tal
	de evitar registros duplicados.
	Nota: Las bajas deben finalizar al recibir el código 500000}
	procedure bajaLogica(var a:maestro);
	var av,cabecera:ave;cod:integer;encontre:boolean;
	begin
		writeln('Codigo de ave a eliminar:');
		readln(cod);
		reset(a);
		read(a,cabecera);
		while(cod<>5000)do begin
			encontre:=false;
			seek(a,0);
			while((not eof(a))and(not encontre))do begin
				read(a,av);
				if(cod=av.cod)then
					encontre:=true;
			end;
			//solo tengo que ponerle una marca a la posicion a borrar?
			if(encontre)then begin
				seek(a,filepos(a)-1);
				av.cod:=av.cod*-1;
				write(a,av);
				{
				//cabecera.cod:=(filepos(a)-1)*-1;
				//seek(a,0);
				//write(a,cabecera);
				* no se necesita en el enunciado}
				writeln('Chau ave!');
			end
			else
				writeln('No se encontro el codigo en el archivo.');
			writeln('Codigo de ave a eliminar:');
			readln(cod);
		end;
		close(a);
	end;
	{Para ello deberá implementar dos procedimientos, uno que marque los registros a borrar y
	posteriormente otro procedimiento que compacte el archivo, quitando los registros
	marcados. Para quitar los registros se deberá copiar el último registro del archivo en la
	posición del registro a borrar y luego eliminar del archivo el último registro de forma tal
	de evitar registros duplicados.}
	procedure bajaFisica(var a:maestro);
	var av:ave;pos:integer;
	begin
		reset(a);
		while(not eof(a))do begin
			read(a,av);
			if(av.cod<0)then begin
				pos:=filepos(a)-1;
				seek(a,filesize(a)-1);
				read(a,av);
				seek(a,pos);
				write(a,av);
				seek(a,filesize(a)-1);
				truncate(a);
				seek(a,pos);
			end;
		end;
		close(a);
	end;
var	a:maestro;
begin
	assign(a,'Aves');
	crearArchivo(a);
	imprimirArchivo(a);
	bajaLogica(a);
	imprimirArchivo(a);
	bajaFisica(a);
	imprimirArchivo(a);
end.
