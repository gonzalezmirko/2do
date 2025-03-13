{6. Una cadena de tiendas de indumentaria posee un archivo maestro no ordenado con
la información correspondiente a las prendas que se encuentran a la venta. De cada
prenda se registra: cod_prenda, descripción, colores, tipo_prenda, stock y
precio_unitario. Ante un eventual cambio de temporada, se deben actualizar las
prendas a la venta. Para ello reciben un archivo conteniendo: cod_prenda de las
prendas que quedarán obsoletas. Deberá implementar un procedimiento que reciba
ambos archivos y realice la baja lógica de las prendas, para ello deberá modificar el
stock de la prenda correspondiente a valor negativo.
Adicionalmente, deberá implementar otro procedimiento que se encargue de
efectivizar las bajas lógicas que se realizaron sobre el archivo maestro con la
información de las prendas a la venta. Para ello se deberá utilizar una estructura
auxiliar (esto es, un archivo nuevo), en el cual se copien únicamente aquellas prendas
que no están marcadas como borradas. Al finalizar este proceso de compactación
del archivo, se deberá renombrar el archivo nuevo con el nombre del archivo maestro
original}
program ej6;
type
	prenda=record
		cod_prenda:integer;
		descripcion:string;
		colores:string;
		tipo_prenda:string;
		stock:integer;
		precio_unitario:real;
	end;
	maestro=file of prenda;
	detalle=file of integer;
	
	procedure leerP(var p:prenda);
	begin
		randomize;
		with p do begin
			write('Codigo de prenda:');
			readln(cod_prenda);
			if(cod_prenda<>-1)then begin
				write('Descrip:');
				readln(descripcion);
				stock:=random(100);
				precio_unitario:=random(5000)+1;
			end;
		end;
	end;
	procedure crearMaestro(var a:maestro);
	var	p:prenda;
	begin
		rewrite(a);
		writeln('-CREANDO MAESTOR DE PRENDAS-');
		p.cod_prenda:=0;
		p.descripcion:='CABECERA';
		p.colores:='';
		p.tipo_prenda:='';
		p.stock:=0;
		p.precio_unitario:=0;
		write(a,p);
		leerP(p);
		while(p.cod_prenda<>-1)do begin
			write(a,p);
			leerP(p);
		end;
		close(a);
		writeln();
	end;
	procedure crearDetalle(var a:detalle);
	var i:integer;
	begin
		rewrite(a);
		for i:=1 to 3 do 
		begin
			write(a,i);
		end;
		close(a);
	end;
	{Adicionalmente, deberá implementar otro procedimiento que se encargue de
	efectivizar las bajas lógicas que se realizaron sobre el archivo maestro con la
	información de las prendas a la venta. Para ello se deberá utilizar una estructura
	auxiliar (esto es, un archivo nuevo), en el cual se copien únicamente aquellas prendas
	que no están marcadas como borradas. Al finalizar este proceso de compactación
	del archivo, se deberá renombrar el archivo nuevo con el nombre del archivo maestro original}
	procedure compactar(var com,ma:maestro);
	var	p:prenda;
	begin
		rewrite(com);
		reset(ma);
		while(not eof(ma))do begin
			read(ma,p);
			if(p.cod_prenda>0)then
				write(com,p);
		end;
		close(ma);
		close(com);
	end;
	procedure imprimirAM(var a:maestro);
	var p:prenda;
	begin
		reset(a);
		while (not eof(a))do begin
			read(a,p);
			writeln('Codigo de prenda:',p.cod_prenda);
			writeln('Descripcion:',p.descripcion);
			writeln('Stock:',p.stock);
			writeln('Precio unitario:',p.precio_unitario:2:2);
		end;
		close(a);
		writeln();
	end;
	procedure imprimirDetalle(var a:detalle);
	var i:integer;
	begin
		reset(a);
		while (not eof(a))do begin
			read(a,i);
			writeln('Codigo de prendas que quedaran obsoletas:',i);
		end;
		close(a);
		writeln();
	end;
	procedure bajas(var ma:maestro;var de:detalle);
	var p,cabecera:prenda;i:integer;ok:boolean;
	begin
		reset(ma);
		reset(de);
		read(ma,cabecera);
		while(not eof(de))do begin
			read(de,i);
			ok:=false;
			seek(ma,0);//por que el archivo no esta ordenado
			while(not eof(ma)and(not ok))do begin
				read(ma,p);
				if(p.cod_prenda=i)then begin
					ok:=true;
					seek(ma,filepos(ma)-1);
					write(ma,cabecera);
					cabecera.cod_prenda:=(filepos(ma)-1)*-1;
					seek(ma,0);
					write(ma,cabecera);
				end;
			end;
		end;
		close(de);
		close(ma);
	end;
var ma:maestro;de:detalle;compactado:maestro;
begin
	assign(ma,'Maestro de prendas');
	assign(de,'Detalle de prendas');
	assign(compactado,'Compactado de prendas');
	crearMaestro(ma);
	imprimirAM(ma);
	crearDetalle(de);
	imprimirDetalle(de);
	bajas(ma,de);
	compactar(compactado,ma);
	imprimirAM(compactado);
end.
