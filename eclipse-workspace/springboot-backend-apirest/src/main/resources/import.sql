    INSERT INTO public.regiones(
	id, nombre)
	VALUES (1, 'Centroamérica');
	INSERT INTO public.regiones(
	id, nombre)
	VALUES (2, 'Norteamérica');
	INSERT INTO public.regiones(
	id, nombre)
	VALUES (3, 'SudAmérica');
	INSERT INTO public.regiones(
	id, nombre)
	VALUES (4, 'Europa');
  
  insert into clientes (id,nombre,apellido,email,created_at,region_id) values(1,'Daniel','Montero','dmontero7@gmail.com',CURRENT_DATE,1);
  insert into clientes (id,nombre,apellido,email,created_at,region_id) values(2,'Danilo','Montero','danielmonterobadilla@gmail.com',CURRENT_DATE,1);
  insert into clientes (id,nombre,apellido,email,created_at,region_id) values(3,'Marta','Badilla','mardan@gmail.com',CURRENT_DATE,2);
  insert into clientes (id,nombre,apellido,email,created_at,region_id) values(4,'Sandra','Montero','spmontero@gmail.com',CURRENT_DATE,2);
  insert into clientes (id,nombre,apellido,email,created_at,region_id) values(5,'Angélica','Cruz','angelcg27@gmail.com',CURRENT_DATE,2);
  insert into clientes (id,nombre,apellido,email,created_at,region_id) values(6,'Sharom','Murillo','sharommm@gmail.com',CURRENT_DATE,1);
  insert into clientes (id,nombre,apellido,email,created_at,region_id) values(7,'Maribel','Cruz','maricruz@gmail.com',CURRENT_DATE,3);
  insert into clientes (id,nombre,apellido,email,created_at,region_id) values(8,'Fabian','Mena','famena@gmail.com',CURRENT_DATE,4);
  insert into clientes (id,nombre,apellido,email,created_at,region_id) values(9,'Carlos','Cruz','ccruz@gmail.com',CURRENT_DATE,3);
  insert into clientes (id,nombre,apellido,email,created_at,region_id) values(10,'Candre','González','candreg@gmail.com',CURRENT_DATE,3);
