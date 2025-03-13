{3. El encargado de ventas de un negocio de productos de limpieza desea administrar el stock
de los productos que vende. Para ello, genera un archivo maestro donde figuran todos los
productos que comercializa. De cada producto se maneja la siguiente información: código de
producto, nombre comercial, precio de venta, stock actual y stock mínimo. Diariamente se
genera un archivo detalle donde se registran todas las ventas de productos realizadas. De
cada venta se registran: código de producto y cantidad de unidades vendidas. Se pide
realizar un programa con opciones para:
a. Actualizar el archivo maestro con el archivo detalle, sabiendo que:
● Ambos archivos están ordenados por código de producto.
● Cada registro del maestro puede ser actualizado por 0, 1 ó más registros del
archivo detalle.
● El archivo detalle sólo contiene registros que están en el archivo maestro.
b. Listar en un archivo de texto llamado “stock_minimo.txt” aquellos productos cuyo
stock actual esté por debajo del stock mínimo permitido.}
program ej3;
const
	valorAlto=999;
	total=5;
type
	producto=record
		cod:integer;
		nombre:string[20];
		precio:real;
		stockA:integer;
		stockM:integer;
	end;
	venta=record
		cod:integer;
		cantUV:integer;
	end;
	archMaestro=file of producto;
	archDetalle=file of venta;
	rango=1..5;
	vectorDetalle=array[rango]of archDetalle;
	vectorVentas=array[rango]of venta;
	procedure crearArchMaestro(var a:archMaestro);
	var p:producto;i:rango;
	begin
		rewrite(a);
		randomize;
		for i:=1 to total do begin
			p.cod:=i;
			write('Nombre P:');
			readln(p.nombre);
			p.precio:=random(100);
			p.stockA:=random(10);
			p.stockM:=random(5);
			write(a,p);
		end;
		close(a);
	end;
	procedure asignarDetalles(var va:vectorDetalle);
	var nom:string;i:integer;j:char;
	begin
		i:=0;
		for j:='1' to '5' do begin
			nom:='Venta '+j;
			i:=i+1;
			assign(va[i],nom);
		end;
	end;
	procedure rewriteDetalles(var va:vectorDetalle);
	var	i:integer;
	begin
		for i:=1 to 5 do 
			rewrite(va[i]);
	end;
	procedure crearArchDetalles(var va:vectorDetalle);
	var v:venta;i:integer;
	begin
		randomize;
		rewriteDetalles(va);
		for i:=1 to total do begin
			v.cod:=i;
			v.cantUV:=random(20);
			write(va[i],v);
			close(va[i]);
		end;
	end;
	procedure imprimirMaestro(var a:archMaestro);
	var p:producto;
	begin
		reset(a);
		writeln('-ARCHIVO MAESTRO-');
		while(not eof(a))do begin
			read(a,p);
			writeln('Cod P:',p.cod);
			writeln('Precio:',p.precio:2:2);
			writeln('Stock:',p.stockA);
		end;
		close(a);
	end;
	procedure imprimirDetalles(var va:vectorDetalle);
	var i:integer;v:venta;
	begin
		for i:=1 to total do begin
			writeln('-ARCHIVO DETALLE ',i,'-');
			reset(va[i]);
			while(not eof(va[i]))do begin
				read(va[i],v);
				writeln('codV:',v.cod);
				writeln('cantUV:',v.cantUV);
			end;
			close(va[i]);
		end;
	end;
	procedure resetDetalles(var det:vectorDetalle);
	var i:integer;
	begin
		for i:=1 to total do 
			reset(det[i]);
	end;
	procedure leer(var a:archDetalle;var v:venta);
	begin
		if(not eof(a))then
			read(a,v)
		else
			v.cod:=valorAlto;
	end;
	procedure leerDetalles(var vD:vectorDetalle;var v:vectorVentas);
	var i:integer;
	begin
		for i:=1 to total do
			leer(vD[i],v[i]);
	end;
	procedure minimo(var vD:vectorDetalle;var v:vectorVentas;var min:venta);
	var i,pos:rango;minimo:integer;aux:venta;
	begin
		minimo:=valorAlto;
		for i:=1 to total do begin
			if(v[i].cod<=minimo)then begin
				minimo:=v[i].cod;
				pos:=i;
			end;
		end;
		min:=v[pos];
		leer(vD[pos],aux);//no entiendo
		v[pos]:=aux;//
	end;
	procedure closeDetalles(var vD:vectorDetalle);
	var i:rango;
	begin
		for i:=1 to total do 
			close(vD[i]);
	end;
	procedure actualizarMaestro(var maestro:archMaestro;var vD:vectorDetalle);
	var	ventas:vectorVentas;min:venta;p:producto;
	begin
		reset(maestro);
		resetDetalles(vD);
		leerDetalles(vD,ventas);
		minimo(vD,ventas,min);
		while(min.cod<>valorAlto)do begin
			read(maestro,p);
			while(p.cod<>min.cod)do
				read(maestro,p);
			while(p.cod=min.cod)do begin
				p.stockA:=p.stockA-min.cantUV;
				minimo(vD,ventas,min);
			end;
			seek(maestro,filepos(maestro)-1);
			write(maestro,p);
		end;
		closeDetalles(vD);
		close(maestro);
	end;
var maestro:archMaestro;detalles:vectorDetalle;
begin
	assign(maestro,'Productos');
	asignarDetalles(detalles);
	crearArchMaestro(maestro);
	crearArchDetalles(detalles);
	imprimirMaestro(maestro);
	//imprimirDetalles(detalles);
	actualizarMaestro(maestro,detalles);
	writeln('-POST ACTUALIZACION-');
	imprimirMaestro(maestro);
end.
