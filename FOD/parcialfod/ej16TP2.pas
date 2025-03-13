{16. Una concesionaria de motos de la Ciudad de Chascomús, posee un archivo con información
de las motos que posee a la venta. De cada moto se registra: código, nombre, descripción,
modelo, marca y stock actual. Mensualmente se reciben 10 archivos detalles con
información de las ventas de cada uno de los 10 empleados que trabajan. De cada archivo
detalle se dispone de la siguiente información: código de moto, precio y fecha de la venta.
Se debe realizar un proceso que actualice el stock del archivo maestro desde los archivos
detalles. Además se debe informar cuál fue la moto más vendida.
NOTA: Todos los archivos están ordenados por código de la moto y el archivo maestro debe
ser recorrido sólo una vez y en forma simultánea con los detalles.}
program ej16;
const
	totalD=10;
type
	regMaestro=record
		cod:integer;
		nombre:string;
		des:string;
		modelo:string;
		marca:string;
		stockA:integer;
	end;
	regDetalle=record
		cod:integer;
		precio:real;
		fecha:string;
	end;
	rangoD=1..totalD;
	aMaestro=file of regMaestro;
	aDetalle=file of regDetalle;
	vectorD=array[rangoD]of aDetalle;
	vectorRD=array[rangoD]of 
