{4. Dada la siguiente estructura:
type
reg_flor = record
nombre: String[45];
codigo:integer;
end;
tArchFlores = file of reg_flor;
Las bajas se realizan apilando registros borrados y las altas reutilizando registros
borrados. El registro 0 se usa como cabecera de la pila de registros borrados: el
número 0 en el campo código implica que no hay registros borrados y -N indica que el
próximo registro a reutilizar es el N, siendo éste un número relativo de registro válido.
a. Implemente el siguiente módulo:
Abre el archivo y agrega una flor, recibida como parámetro
manteniendo la política descrita anteriormente
procedure agregarFlor (var a: tArchFlores ; nombre: string;
codigo:integer);
b. Liste el contenido del archivo omitiendo las flores eliminadas. Modifique lo que
considere necesario para obtener el listado.
5. Dada la estructura planteada en el ejercicio anterior, implemente el siguiente módulo:
Abre el archivo y elimina la flor recibida como parámetro manteniendo
la política descripta anteriormente
procedure eliminarFlor (var a: tArchFlores; flor:reg_flor);}
program ej4;
type
	reg_flor=record
		nombre:string;
		codigo:integer;
	end;
	aFlores=file of reg_flor;
	procedure leerF(var f:reg_flor);
	begin
		write('Codigo de flor:');
		readln(f.codigo);
		if(f.codigo<>-1)then begin
			write('Nombre flor:');
			readln(f.nombre);
		end;
	end;
	procedure crearArchivo(var a:aFlores);
	var f:reg_flor;
	begin
		writeln('-CREANDO ARCHIVO DE FLORES-');
		rewrite(a);
		f.nombre:='';
		f.codigo:=0;
		write(a,f);
		leerF(f);
		while(f.codigo<>-1)do begin
			write(a,f);
			leerF(f);
		end;
		close(a);
		writeln();
	end;
	procedure darBaja(var a:aFlores);
	var f,aux:reg_flor;cod:integer;ok:boolean;
	begin
		writeln('-ELIMINANDO UNA FLOR-');
		reset(a);
		write('Codigo de flor a eliminar:');
		readln(cod);
		read(a,aux);
		ok:=false;
		while((not eof(a))and(not ok))do begin
			read(a,f);
			if(f.codigo=cod)then begin
				ok:=true;
				seek(a,filepos(a)-1);
				write(a,aux);
				aux.codigo:=(filepos(a)-1)*-1;
				seek(a,0);
				write(a,aux);
			end;
		end;
		if(ok)then
			writeln('Se elimino correctamente la flor con codigo, ',cod)
		else
			writeln('No se encontro el codigo de flor');
		close(a);
	end;
	{procedure agregarFlor (var a: tArchFlores ; nombre: string;
	codigo:integer);}
	procedure darAlta(var a:aFlores);
	var f,aux:reg_flor;pos:integer;
	begin
		writeln('-Agregar una flor-');
		leerF(f);
		reset(a);
		read(a,aux);
		if(aux.codigo=0)then begin
			seek(a,filesize(a));
			write(a,f);
			writeln('Se agrego la flor en el final del archivo');
		end
		else
			begin
				pos:=aux.codigo*-1;
				seek(a,pos);
				read(a,aux);
				seek(a,filepos(a)-1);
				write(a,f);
				seek(a,0);
				write(a,aux);
				writeln('Se agrego la flor en la posicion,',pos);
			end;
		close(a);
	end;
var a:aFlores;
begin
	assign(a,'Flores');
	crearArchivo(a);
	darBaja(a);
	darAlta(a);
end.
