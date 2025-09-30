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
	totalD=7;
	valorAlto=999;
type
	producto=record
		cod:integer;
		nombre:string;
		precio:real;
		stockA:integer;
		stockM:integer;
	end;
	venta=record
		cod:integer;
		cantUV:integer;
	end;
	maestro=file of producto;
	detalle=file of venta;
	rangoD=1..totalD;
	vectorD=array[rangoD]of detalle;
	vectorV=array[rangoD]of venta;
	procedure leer(var a:detalle;var reg:venta);
	begin
		if(not eof(a))then
			read(a,reg)
		else
			reg.cod:=valorAlto;
	end;
	procedure minimo(var detalles:vectorD;var vReg:vectorV;var min:venta);
	var i,pos:integer;
	begin
		pos:=0;
		min.cod:=valorAlto;
		for i:=1 to totalD do begin
			if(vReg[i].cod<=min.cod)then begin
				min:=vReg[i];
				pos:=i;
			end;
		end;
		if(pos<>0)then
			leer(detalles[pos],vReg[pos]);
	end;
	procedure actualizarMaestro(var detalles:vectorD;var ma:maestro);
	var min:venta;i:integer;p:producto;vReg:vectorV;
	begin
		for i:=1 to totalD do 
			reset(detalles[i]);
		reset(ma);
		for i:=1 to totalD do
			leer(detalles[i],vReg[i]);
		minimo(detalles,vReg,min);
		while(min.cod<>valorAlto)do begin
			read(ma,p);
			while(min.cod<>p.cod)do
				read(ma,p);
			while(min.cod=p.cod)do begin
				p.stockA:=p.stockA-min.cantUV;
				minimo(detalles,vReg,min);
			end;
			seek(ma,filepos(ma)-1);//reubico puntero
			write(ma,p);
		end;
		for i:=1 to totalD do
			close(detalles[i]);
		close(ma);
	end;
	{b. Listar en un archivo de texto llamado “stock_minimo.txt” aquellos productos cuyo
	stock actual esté por debajo del stock mínimo permitido.}
	procedure listarTxt(var ma:maestro);
	var	p:producto;txt:text;
	begin
		reset(ma);
		assign(txt,'stock_minimo');
		rewrite(txt);
		while(not eof(ma))do begin
			read(ma,p);
			if(p.stockA<p.stockM)then
				writeln(txt,'Cod:',p.cod,'Nombre:',p.nombre,'Precio:',p.precio:2:2,'StockA:',p.stockA,'StockM:',p.stockM);
		end;
		close(txt);
		close(ma);
	end;
var
	detalles:vectorD;m:maestro;
begin
	//crearArchivo(m);
	//crearDetalles(detalles);
	actualizarMaestro(detalles,m);
	listarTxt(m);
end.
