{9. Se necesita contabilizar los votos de las diferentes mesas electorales registradas por
provincia y localidad. Para ello, se posee un archivo con la siguiente información: código de
provincia, código de localidad, número de mesa y cantidad de votos en dicha mesa.
Presentar en pantalla un listado como se muestra a continuación:
Código de Provincia
Código de Localidad Total de Votos
................................ ......................
................................ ......................
Total de Votos Provincia: ____
Código de Provincia
Código de Localidad Total de Votos
................................ ......................
Total de Votos Provincia: ___
…………………………………………………………..
Total General de Votos: ___
NOTA: La información está ordenada por código de provincia y código de localidad.}
program ej9;
const 
	valorAlto=999;
type
	regMaestro=record
		codProvincia:integer;
		codLocalidad:integer;
		numMesa:integer;
		cantVotos:integer;
	end;
	archivoM=file of regMaestro;
	procedure crearMaestro(var a:archivoM);
	var regM:regMaestro;
	begin
		rewrite(a);
		randomize;
		
		regM.codProvincia:=1;
		regM.codLocalidad:=1;
		regM.numMesa:=random(10)+1;
		regM.cantVotos:=100;
		write(a,regM);
		
		regM.codProvincia:=1;
		regM.codLocalidad:=2;
		regM.numMesa:=random(10)+1;;
		regM.cantVotos:=500;
		write(a,regM);
		
		regM.codProvincia:=1;
		regM.codLocalidad:=2;
		regM.numMesa:=random(10);;
		regM.cantVotos:=500;
		write(a,regM);
		
		regM.codProvincia:=2;
		regM.codLocalidad:=1;
		regM.numMesa:=1;
		regM.cantVotos:=50;
		write(a,regM);
		
		regM.codProvincia:=2;
		regM.codLocalidad:=1;
		regM.numMesa:=1;
		regM.cantVotos:=50;
		write(a,regM);
			
		close(a);
	end;
	procedure leer(var a:archivoM;var regM:regMaestro);
	begin
		if(not eof(a))then
			read(a,regM)
		else
			regM.codProvincia:=valorAlto;
	end;
	procedure contarVotos(var a:archivoM);
	var regM:regMaestro;actualProvincia,actualLocalidad,totalVotosP,totalVotosL,totalVotos:integer;
	begin
		reset(a);
		leer(a,regM);
		totalVotos:=0;
		writeln('-VOTOS MESAS ELECTORALES-');
		while(regM.codProvincia<>valorAlto)do begin
			writeln('-Codigo de Provincia ',regM.codProvincia,'-');
			actualProvincia:=regM.codProvincia;
			totalVotosP:=0;
			while(actualProvincia=regM.codProvincia)do begin
				writeln('-Codigo de Localidad ',regM.codLocalidad,'.');
				actualLocalidad:=regM.codLocalidad;
				totalVotosL:=0;
				while((actualProvincia=regM.codProvincia)and(actualLocalidad=regM.codLocalidad))do begin
					totalVotosP:=totalVotosP+regM.cantVotos;
					totalVotos:=totalVotos+regM.cantVotos;
					totalVotosL:=totalVotosL+regM.cantVotos;
					leer(a,regM);
				end;
				writeln('-Total de votos por Localidad:',totalVotosL);
			end;
			writeln('-Total de votos por Provincia:',totalVotosP);
			writeln();
		end;
		writeln('-Total de votos generales:',totalVotos);
		close(a);
	end;
var maestro:archivoM;
begin
	assign(maestro,'Votos');
	crearMaestro(maestro);
	contarVotos(maestro);
end.
