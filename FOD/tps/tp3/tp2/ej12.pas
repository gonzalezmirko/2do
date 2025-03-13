{12. Suponga que usted es administrador de un servidor de correo electrónico. En los logs del
mismo (información guardada acerca de los movimientos que ocurren en el server) que se
encuentra en la siguiente ruta: /var/log/logmail.dat se guarda la siguiente información:
nro_usuario, nombreUsuario, nombre, apellido, cantidadMailEnviados. Diariamente el
servidor de correo genera un archivo con la siguiente información: nro_usuario,
cuentaDestino, cuerpoMensaje. Este archivo representa todos los correos enviados por los
usuarios en un día determinado. Ambos archivos están ordenados por nro_usuario y se
sabe que un usuario puede enviar cero, uno o más mails por día.
a. Realice el procedimiento necesario para actualizar la información del log en un
día particular. Defina las estructuras de datos que utilice su procedimiento.
b. Genere un archivo de texto que contenga el siguiente informe dado un archivo
detalle de un día determinado:
nro_usuarioX…………..cantidadMensajesEnviados
………….
nro_usuarioX+n………..cantidadMensajesEnviados
Nota: tener en cuenta que en el listado deberán aparecer todos los usuarios que
existen en el sistema. Considere la implementación de esta opción de las
siguientes maneras:
i- Como un procedimiento separado del punto a).
ii- En el mismo procedimiento de actualización del punto a). Qué cambios
se requieren en el procedimiento del punto a) para realizar el informe en
el mismo recorrido?}
program ej12;
uses
	sysutils;
const 
	valorAlto=999;
	totalD=5;
type
	rangoD=1..totalD;
	regMaestro=record
		nro_usuario:integer;
		nombreUsuario:string;
		nombreYape:string;
		cantMailEnviados:integer;
	end;
	regDetalle=record
		nroUsuario:integer;
		cuentaDestino:integer;
		cuerpoMensaje:string;
	end;
	arcMaestro=file of regMaestro;
	arcDetalle=file of regDetalle;
	vectorD=array [rangoD]of arcDetalle;
	vectoRegD=array[rangoD]of regDetalle;
	
	procedure assignarDetalles(var v:vectorD);
	var i:rangoD;
	begin
		for i:=1 to totalD do
			assign(v[i],'Correo enviado '+intToStr(i));
	end;
	procedure crearMaestro(var maestro:arcMaestro);
	var regM:regMaestro;
	begin
		rewrite(maestro);
		
		regM.nro_usuario:=1;
		regM.nombreUsuario:='mirgonza70';
		regM.nombreYape:='Mirko Gonzalez';
		regM.cantMailEnviados:=10;
		write(maestro,regM);
		
		regM.nro_usuario:=2;
		regM.nombreUsuario:='anaguada';
		regM.nombreYape:='Ana De Pasqua';
		regM.cantMailEnviados:=5;
		write(maestro,regM);
		
		regM.nro_usuario:=3;
		regM.nombreUsuario:='laucharomero';
		regM.nombreYape:='Lautaro Romero';
		regM.cantMailEnviados:=1;
		write(maestro,regM);
		
		regM.nro_usuario:=4;
		regM.nombreUsuario:='colosi';
		regM.nombreYape:='Francisco Colosi';
		regM.cantMailEnviados:=0;
		write(maestro,regM);
		
		regM.nro_usuario:=5;
		regM.nombreUsuario:='renzoq';
		regM.nombreYape:='Renzo Quaggia';
		regM.cantMailEnviados:=2;
		write(maestro,regM);
		
		close(maestro);
	end;
	procedure rewrites(var v:vectorD);
	var i:rangoD;
	begin
		for i:=1 to totalD do
			rewrite(v[i]);
	end;
	procedure closes(var v:vectorD);
	var i:rangoD;
	begin
		for i:=1 to totalD do 
			close(v[i]);
	end;
	procedure resets(var v:vectorD);
	var i:rangoD;
	begin
		for i:=1 to totalD do 
			reset(v[i]);
	end;
	procedure crearDetalles(var v:vectorD);
	var regD:regDetalle;i:rangoD;
	begin
		rewrites(v);
		randomize;
		for i:=1 to totalD do begin
			regD.nroUsuario:=random(5)+1;
			regD.cuentaDestino:=random(5)+1;
			regD.cuerpoMensaje:='Mensaje '+intToStr(i);
			write(v[i],regD);
		end;
		closes(v);
	end;
	procedure imprimirMaestro(var maestro:arcMaestro);
	var regM:regMaestro;
	begin
		reset(maestro);
		writeln('-MAESTRO-');
		while(not eof(maestro))do begin
			read(maestro,regM);
			writeln('Numero de usuario:',regM.nro_usuario);
			writeln('Nombre de usuario:',regM.nombreUsuario);
			writeln('Nombre y Apellido:',regM.nombreYape);
			writeln('Cantidad Mail enviado:',regM.cantMailEnviados);
		end;
		writeln();
		close(maestro);
	end;
	procedure imprimirDetalle(var det:arcDetalle);
	var regD:regDetalle;
	begin
		while(not eof(det))do begin
			read(det,regD);
			writeln('Numero de usuario:',regD.nroUsuario);
			writeln('Cuenta destino:',regD.cuentaDestino);
			writeln('Mensaje:',regD.cuerpoMensaje);
		end;
	end;
	procedure imprimirDetalles(var v:vectorD);
	var i:rangoD;
	begin
		resets(v);
		for i:=1 to totalD do begin
			writeln('-Detalle ',i,'-');
			imprimirDetalle(v[i]);
		end;
		writeln();
		closes(v);
	end;
var
	maestro:arcMaestro;detalles:vectorD;
begin
	assign(maestro,'Log');
	assignarDetalles(detalles);
	crearMaestro(maestro);
	crearDetalles(detalles);
	imprimirMaestro(maestro);
	imprimirDetalles(detalles);
end.
