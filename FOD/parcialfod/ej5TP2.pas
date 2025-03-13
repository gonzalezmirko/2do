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
const
	valorAlto=999;
	totalD=30;
type
	producto=record
		cod:integer;
		nombre:string;
		descrip:string;
		stockD:integer;
		stockM:integer;
		precio:real;
	end;
	regDetalle=record
		cod:integer;
		cantV:integer;
	end;
	aMaestro=file of producto;
	aDetalle=file of regDetalle;
	rangoD=1..totalD;
	vectorD=array[rangoD]of aDetalle;
	vectorRD=array[rangoD]of regDetalle;
	
	procedure leer(var a:aDetalle;var reg:regDetalle);
	begin
		if(not eof(a))then
			read(a,reg)
		else
			reg.cod:=valorAlto;
	end;
	procedure minimo(var detalles:vectorD;var regD:vectorRD;var min:regDetalle);
	var i,pos:integer;
	begin
		pos:=0;
		min.cod:=valorAlto;
		for i:=1 to totalD do begin
			if(regD[i].cod<=min.cod)then begin
				min.cod:=regD[i].cod;
				pos:=i;
			end;
		end;
		if (pos<>0)then
			leer(detalles[pos],regD[pos]);
	end;
	procedure actualizarMaestro(var maestro:aMaestro;var detalles:vectorD);
	var
		regM:producto;min:regDetalle;i:integer;vectorReg:vectorRD;
	begin
		reset(maestro);
		for i:=1 to totalD do
			reset(detalles[i]);
		for i:=1 to totalD do
			leer(detalles[i],vectorReg[i]);
		minimo(detalles,vectorReg,min);
		while(min.cod<>valorAlto)do begin
			read(maestro,regM);
			while(min.cod<>regM.cod)do
				read(maestro,regM);
			while(min.cod=regM.cod)do begin
				regM.stockD:=regM.stockD-min.cantV;
				minimo(detalles,vectorReg,min);
			end;
			seek(maestro,filepos(maestro)-1);
			write(maestro,regM);
		end;
		for i:=1 to totalD do
			close(detalles[i]);
		close(maestro);
	end;
var
	maestro:aMaestro;detalles:vectorD;
begin
	//crearMaestro(maestro);//se dispone
	//crearDetalles(detalles);//se dispone
	actualizarMaestro(maestro,detalles);
end.
