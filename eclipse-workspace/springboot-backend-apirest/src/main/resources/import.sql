INSERT INTO regiones (id, nombre) VALUES (1, 'Sudamérica');
INSERT INTO regiones (id, nombre) VALUES (2, 'Centroamérica');
INSERT INTO regiones (id, nombre) VALUES (3, 'Norteamérica');
INSERT INTO regiones (id, nombre) VALUES (4, 'Europa');
INSERT INTO regiones (id, nombre) VALUES (5, 'Asia');
INSERT INTO regiones (id, nombre) VALUES (6, 'Africa');
INSERT INTO regiones (id, nombre) VALUES (7, 'Oceanía');
INSERT INTO regiones (id, nombre) VALUES (8, 'Antártida');
  
  insert into clientes (id,nombre,apellido,email,create_at,region_id) values(1,'Daniel','Montero','dmontero7@gmail.com',CURRENT_DATE,1);
  insert into clientes (id,nombre,apellido,email,create_at,region_id) values(2,'Danilo','Montero','danielmonterobadilla@gmail.com',CURRENT_DATE,1);
  insert into clientes (id,nombre,apellido,email,create_at,region_id) values(3,'Marta','Badilla','mardan@gmail.com',CURRENT_DATE,2);
  insert into clientes (id,nombre,apellido,email,create_at,region_id) values(4,'Sandra','Montero','spmontero@gmail.com',CURRENT_DATE,2);
  insert into clientes (id,nombre,apellido,email,create_at,region_id) values(5,'Angélica','Cruz','angelcg27@gmail.com',CURRENT_DATE,2);
  insert into clientes (id,nombre,apellido,email,create_at,region_id) values(6,'Sharom','Murillo','sharommm@gmail.com',CURRENT_DATE,1);
  insert into clientes (id,nombre,apellido,email,create_at,region_id) values(7,'Maribel','Cruz','maricruz@gmail.com',CURRENT_DATE,3);
  insert into clientes (id,nombre,apellido,email,create_at,region_id) values(8,'Fabian','Mena','famena@gmail.com',CURRENT_DATE,4);
  insert into clientes (id,nombre,apellido,email,create_at,region_id) values(9,'Carlos','Cruz','ccruz@gmail.com',CURRENT_DATE,3);
  insert into clientes (id,nombre,apellido,email,create_at,region_id) values(10,'Candre','González','candreg@gmail.com',CURRENT_DATE,3);
  
  
  insert into usuarios (username,password,enabled,nombre, apellido, email) values ('admin','$2a$10$6cW6Mf3.aFUKo6e6g5QqTOElzfmNYMCpSKbTBbhYBDtsMWbS7NLCa',true,'Antonio','Badilla','dmontero7@gmail.com');
  insert into usuarios (username,password,enabled,nombre, apellido, email) values ('dmontero','$2a$10$IYalsfMifC766kzHBnKOseppHr43aGTklMfQmFfNsW9bIL2FVPx62',true,'Daniel','Montero','dmontero7@hotmail.com');
  
  insert into roles(nombre) values ('ROLE_USER');
  insert into roles(nombre) values ('ROLE_ADMIN');
  
  insert into usuarios_roles (usuario_id,role_id) values (1,1);
  insert into usuarios_roles (usuario_id,role_id) values (1,2);
  insert into usuarios_roles (usuario_id,role_id) values (2,1);
  
  /* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creamos algunas facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());

INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);