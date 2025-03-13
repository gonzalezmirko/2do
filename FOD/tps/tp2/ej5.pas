{5. Se cuenta con un archivo de productos de una cadena de venta de alimentos congelados.
De cada producto se almacena: código del producto, nombre, descripción, stock disponible,
stock mínimo y precio del producto.
Se recibe diariamente un archivo detalle de cada una de las 30 sucursales de la cadena. Se
debe realizar el procedimiento que recibe los 30 detalles y actualiza el stock del archivo
maestro. La información que se recibe en los detalles es: código de producto y cantidad
vendida. Además, se deberá informar en un archivo de texto: nombre de producto,
descripción, stock disponible y precio de aquellos productos que tengan stock disponible por
debajo del stock mínimo. Pensar alternativas sobre realizar el informe en el mismo
procedimiento de actualización, o realizarlo en un procedimiento separado (analizar
ventajas/desventajas en cada caso).
Nota: todos los archivos se encuentran ordenados por código de productos. En cada detalle
puede venir 0 o N registros de un determinado producto.}
program ej5;
uses SysUtils;
const totalDias=5;
	valorAlto=999;
type
	rangoD=1..totalDias;
	producto=record
		cod:integer;
		nombre:string;
		desc:string;
		stockDis:integer;
		stockMin:integer;
		precio:real;
	end;
	venta=record
		cod:integer;
		cantV:integer;
	end;
	archivoM=file of producto;
	archivoD=file of venta;
	vectorD=array[rangoD]of archivoD;
	vVenta=array[rangoD]of venta;
	
	procedure crearMaestro(var maestro:archivoM);
	var p:producto;i:integer;
	begin
		writeln('-Carga archivo maestro-');
		rewrite(maestro);
		randomize;
		for i:=1 to 5 do begin
			p.cod:=i;
			write('Nombre del producto:');
			readln(p.nombre);
			p.stockDis:=random(20);
			p.stockMin:=random(20);
			p.precio:=random(1000)+1;
			write(maestro,p);
		end;
		close(maestro);
		writeln();
	end;
	procedure rewriteDetalles(var v:vectorD);
	var i:rangoD;
	begin
		for i:=1 to totalDias do 
			rewrite(v[i]);
	end;
	procedure closeDetalles(var v:vectorD);
	var i:rangoD;
	begin
		for i:=1 to totalDias do
			close(v[i]);
	end;
	procedure  crearDetalles(var vDetalles:vectorD);
	var regD:venta;i:rangoD;
	begin
		writeln('-CARGA DETALLES-');
		randomize;
		rewriteDetalles(vDetalles);
		for i:=1 to totalDias do begin
			regD.cod:=random(5)+1;
			regD.cantV:=random(20)+1;
			write(vDetalles[i],regD);
		end;
		closeDetalles(vDetalles);
		writeln();
	end;
	procedure resetDetalles(var v:vectorD);
	var i:rangoD;
	begin
		for i:=1 to totalDias do
			reset(v[i]);
	end;
	procedure leer(var det:archivoD;var regD:venta);
	begin
		if(not eof(det))then
			read(det,regD)
		else
			regD.cod:=valorAlto;
	end;
	procedure minimo(var v:vectorD;var vregD:vVenta;var min:venta);
	var pos:integer;i:rangoD;
	begin
		pos:=0;
		min.cod:=valorAlto;
		for i:=1 to totalDias do begin
			if(vregD[i].cod<min.cod)then begin
				min:=vregD[i];
				pos:=i;
			end;
		end;
		if(pos<>0)then
			leer(v[pos],vregD[pos]);
	end;
	procedure actualizarMaestro(var maestro:archivoM;var v:vectorD);
	var regM:producto;vregD:vVenta;i:rangoD;min:venta;
	begin
		reset(maestro);
		resetDetalles(v);
		for i:=1 to totalDias do
			leer(v[i],vRegD[i]);
		minimo(v,vregD,min);
		while(min.cod<>valorAlto)do begin
			read(maestro,regM);
			while(min.cod<>regM.cod)do
				read(maestro,regM);
			while(min.cod=regM.cod)do begin
				regM.stockDis:=regM.stockDis-min.cantV;
				minimo(v,vregD,min);
			end;
			seek(maestro,filepos(maestro)-1);
			write(maestro,regM);
		end;
		closeDetalles(v);
		close(maestro);
		writeln('-Se actualizo el maestro-');
		writeln();
	end;
	procedure imprimirMaestro(var a:archivoM);
	var p:producto;
	begin
		writeln('-Maestro-');
		reset(a);
		while(not eof(a))do begin
			read(a,p);
			writeln('Codigo de producto:',p.cod);
			writeln('Stock disponible:',p.stockDis);
		end;
		close(a);
	end;
var
	maestro:archivoM;
	vDetalles:vectorD;
	i:rangoD;
begin
	assign(maestro,'Productos_ej5');
	for i:=1 to totalDias do 
		assign(vDetalles[i],'Venta '+IntToStr(i));
	crearMaestro(maestro);
	crearDetalles(vDetalles);
	imprimirMaestro(maestro);
	actualizarMaestro(maestro,vDetalles);
	imprimirMaestro(maestro);
end.
