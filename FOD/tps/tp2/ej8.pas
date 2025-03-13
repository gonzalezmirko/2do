{8. Se cuenta con un archivo que posee información de las ventas que realiza una empresa a
los diferentes clientes. Se necesita obtener un reporte con las ventas organizadas por
cliente. Para ello, se deberá informar por pantalla: los datos personales del cliente, el total
mensual (mes por mes cuánto compró) y finalmente el monto total comprado en el año por el
cliente. Además, al finalizar el reporte, se debe informar el monto total de ventas obtenido
por la empresa.
El formato del archivo maestro está dado por: cliente (cod cliente, nombre y apellido), año,
mes, día y monto de la venta. El orden del archivo está dado por: cod cliente, año y mes.
Nota: tenga en cuenta que puede haber meses en los que los clientes no realizaron
compras. No es necesario que informe tales meses en el reporte.}
program ej8;
const
	valorAlto=999;
type
	rangoM=1..12;
	cli=record
		cod:integer;
		nombreYapellido:string;
	end;
	fe=record
		anio:2000..2024;
		mes:rangoM;
		dia:1..31;
	end;
	regMaestro=record
		cliente:cli;
		fecha:fe;
		monto:real;
	end;
	vMeses=array [rangoM]of real;
	archivoM=file of regMaestro;
	procedure crearArchivo(var a:archivoM);
	var regM:regMaestro;
	begin
		randomize;
		rewrite(a);
		regM.cliente.cod:=1;
		regM.cliente.nombreYapellido:='Mirko Gonzalez';
		regM.fecha.anio:=2024;
		regM.fecha.mes:=12;
		regM.fecha.dia:=1;
		regM.monto:=500;
		write(a,regM);
		
		regM.cliente.cod:=2;
		regM.cliente.nombreYapellido:='Ana De Pasqua';
		regM.fecha.anio:=2023;
		regM.fecha.mes:=11;
		regM.fecha.dia:=10;
		regM.monto:=1000;//total cod 1 1000
		write(a,regM);
		
		regM.cliente.cod:=2;
		regM.cliente.nombreYapellido:='Ana De Pasqua';
		regM.fecha.anio:=2024;
		regM.fecha.mes:=11;
		regM.fecha.dia:=10;
		regM.monto:=1000;
		write(a,regM);
		
		regM.cliente.cod:=3;
		regM.cliente.nombreYapellido:='Laucha Romero';
		regM.fecha.anio:=2020;
		regM.fecha.mes:=10;
		regM.fecha.dia:=1;
		regM.monto:=random(100)+1;
		write(a,regM);
		
		close(a);
	end;
	procedure leer(var a:archivoM;var regM:regMaestro);
	begin
		if(not eof(a))then
			read(a,regM)
		else
			regM.cliente.cod:=valorAlto;
	end;
	procedure inicializarVM(var v:vMeses);
	var i:rangoM;
	begin
		for i:=1 to 12 do
			v[i]:=0;
	end;
	procedure reporteArchivoMaestro(var a:archivoM);
	var regM:regMaestro;actualCod:integer;actualAnio:2000..2024;actualMes:1..12;
		totalMensual:real;totalAnual:real;totalVentas:real;
	begin
		reset(a);
		leer(a,regM);
		totalVentas:=0;
		while(regM.cliente.cod<>valorAlto)do begin
			writeln();
			writeln('-Cliente ',regM.cliente.cod,'-');
			writeln('-Nombre y apellido:',regM.cliente.nombreYapellido);
			actualCod:=regM.cliente.cod;
			while(regM.cliente.cod=actualCod)do begin
				actualAnio:=regM.fecha.anio;
				totalAnual:=0;
				while((regM.cliente.cod=actualCod)and(regM.fecha.anio=actualAnio))do begin
					actualMes:=regM.fecha.mes;
					totalAnual:=totalAnual+regM.monto;
					totalMensual:=0;
					while((regM.cliente.cod=actualCod)and(regM.fecha.anio=actualAnio)and(regM.fecha.mes=actualMes))do begin
						totalVentas:=totalVentas+regM.monto;
						totalMensual:=totalMensual+regM.monto;
						leer(a,regM);
					end;
					writeln('TOTAL MENSUAL:',totalMensual:2:2);
				end;
				writeln('TOTAL ANUAL:',totalAnual:2:2);
			end;
		end;
		writeln('TOTAL VENTAS:',totalVentas:2:2);
		close(a);
	end;
var maestro:archivoM;
begin
	assign(maestro,'Reporte');
	crearArchivo(maestro);
	reporteArchivoMaestro(maestro);
end.
