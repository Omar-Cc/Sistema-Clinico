DROP TABLE IF EXISTS tempubigeo;

-- Insertar UBIGEO
CREATE TABLE tempubigeo (
    codigo VARCHAR(6),
    departamento VARCHAR(50),
    provincia VARCHAR(50),
    distrito VARCHAR(50)
);


-- Cargar datos desde un archivo CSV a una tabla temporal en MySQL
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/UBIGEO_2022_1891_distritos.csv'
INTO TABLE tempubigeo
FIELDS TERMINATED BY ';'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

INSERT INTO ubigeo (codigo, departamento, provincia, distrito)
SELECT codigo, departamento, provincia, distrito
FROM tempubigeo;

DROP TABLE tempubigeo;


-- Precargar datos en la tabla Estados
INSERT INTO estados (nombre, descripcion) VALUES
('Reservado', 'Estado para las citas'),
('Asistió', 'Estado para las citas'),
('Cancelado', 'Estado para las citas'),
('No asistió', 'Estado para las citas'),
('Pendiente', 'Estado para las facturas y resultados de laboratorio'),
('Pagado', 'Estado para las facturas'),
('Vencido', 'Estado para las facturas'),
('En Proceso', 'Estado para resultados de laboratorio y detalles de orden'),
('Completado', 'Estado para resultados de laboratorio y detalles de orden'),
('Revisado', 'Estado para resultados de laboratorio');


-- Precargar datos en la tabla Categorias
INSERT INTO categorias (nombre, descripcion) VALUES
('Analgesicos', 'Medicamentos para aliviar el dolor'),
('Antibioticos', 'Medicamentos para tratar infecciones'),
('Antisepticos', 'Medicamentos para prevenir infecciones'),
('Antiinflamatorios', 'Medicamentos para reducir la inflamación'),
('Antipireticos', 'Medicamentos para reducir la fiebre');


-- Precargar datos en la tabla Proveedores
INSERT INTO proveedores (nombre, direccion, telefono, email) VALUES
('Proveedor 1', 'Calle Falsa 123', '123456789', 'proveedor1@example.com'),
('Proveedor 2', 'Avenida Siempre Viva 742', '987654321', 'proveedor2@example.com'),
('Proveedor 3', 'Calle Luna 456', '456789123', 'proveedor3@example.com');


-- Precargar datos en la tabla Medicamentos
INSERT INTO medicamentos (nombre, descripcion, id_categoria, precio, stock, fecha_vencimiento, id_proveedor) VALUES
('Paracetamol', 'Medicamento para aliviar el dolor y reducir la fiebre', 1, 0.50, 100, '2024-12-31', 1),
('Amoxicilina', 'Antibiótico para tratar infecciones bacterianas', 2, 1.00, 50, '2023-06-30', 1),
('Alcohol', 'Antiséptico para desinfectar heridas', 3, 0.75, 200, '2025-01-01', 1),
('Ibuprofeno', 'Medicamento para reducir la inflamación y aliviar el dolor', 4, 0.60, 150, '2024-11-30', 1),
('Aspirina', 'Medicamento para reducir la fiebre y aliviar el dolor', 5, 0.40, 120, '2024-10-31', 1);


-- Precargar datos en la tabla Metodos_Pago
INSERT INTO metodos_pago (nombre) VALUES
('Efectivo'),
('Tarjeta de Crédito'),
('Transferencia Bancaria');


-- Precargar datos en la tabla Especialidades
INSERT INTO especialidades (nombre, descripcion) VALUES
('Cardiología', 'Especialidad médica que se ocupa del corazón y el sistema circulatorio'),
('Dermatología', 'Especialidad médica que se ocupa de la piel'),
('Gastroenterología', 'Especialidad médica que se ocupa del sistema digestivo'),
('Neurología', 'Especialidad médica que se ocupa del sistema nervioso'),
('Pediatría', 'Especialidad médica que se ocupa de la salud de los niños'),
('Oftalmología', 'Especialidad médica que se ocupa de los ojos y la visión'),
('Oncología', 'Especialidad médica que se ocupa del cáncer'),
('Psiquiatría', 'Especialidad médica que se ocupa de la salud mental'),
('Reumatología', 'Especialidad médica que se ocupa de las enfermedades reumáticas'),
('Urología', 'Especialidad médica que se ocupa del sistema urinario y el sistema reproductor masculino'),
('Traumatología', 'Especialidad médica que se ocupa de las lesiones y enfermedades del aparato locomotor'),
('Endocrinología', 'Especialidad médica que se ocupa de las enfermedades hormonales'),
('Ginecología', 'Especialidad médica que se ocupa de la salud de la mujer'),
('Otorrinolaringología', 'Especialidad médica que se ocupa de los oídos, la nariz y la garganta'),
('Neumología', 'Especialidad médica que se ocupa de las enfermedades del aparato respiratorio'),
('Infectología', 'Especialidad médica que se ocupa de las enfermedades infecciosas'),
('Hematología', 'Especialidad médica que se ocupa de las enfermedades de la sangre'),
('Nefrología', 'Especialidad médica que se ocupa de las enfermedades del riñón'),
('Anestesiología', 'Especialidad médica que se ocupa de la anestesia y el dolor'),
('Medicina Interna', 'Especialidad médica que se ocupa de las enfermedades de los órganos internos'),
('Medicina General', 'Especialidad médica que se ocupa de la atención primaria de la salud');


-- Precargar datos en la tabla Roles
INSERT INTO roles (nombre, descripcion) VALUES
('Administrador', 'Rol con todos los permisos'),
('Medico', 'Rol para los médicos'),
('Paciente', 'Rol para los pacientes'),
('Farmaceutico', 'Persona que atiende la farmacia');


-- Precargar datos en la tabla Usuarios
INSERT INTO usuarios (username, email, password, fecha_creacion) VALUES
('12345678', 'administrador@example.com', 'password1', NOW()), -- usuario para el administrador
('23456789', 'medico1@example.com', 'password2', NOW()), -- usuario para el médico
('34567890', 'medico2@example.com', 'password3', NOW()), -- usuario para el médico
('45678901', 'medico3@example.com', 'password4', NOW()), -- usuario para el médico
('56789012', 'medico4@example.com', 'password5', NOW()), -- usuario para el médico
('67890123', 'medico5@example.com', 'password6', NOW()), -- usuario para el médico
('78901234', 'medico6@example.com', 'password7', NOW()), -- usuario para el médico
('89012345', 'medico7@example.com', 'password8', NOW()), -- usuario para el médico
('90123456', 'medico8@example.com', 'password9', NOW()), -- usuario para el médico
('01234567', 'medico9@example.com', 'password10', NOW()), -- usuario para el médico
('11234567', 'medico10@example.com', 'password11', NOW()), -- usuario para el médico
('12234567', 'paciente1@example.com', 'password12', NOW()), -- usuario para el paciente
('13234567', 'paciente2@example.com', 'password13', NOW()), -- usuario para el paciente
('14234567', 'paciente3@example.com', 'password14', NOW()), -- usuario para el paciente
('15234567', 'paciente4@example.com', 'password15', NOW()), -- usuario para el paciente
('16234567', 'paciente5@example.com', 'password16', NOW()), -- usuario para el paciente
('17234567', 'paciente6@example.com', 'password17', NOW()), -- usuario para el paciente
('18234567', 'paciente7@example.com', 'password18', NOW()), -- usuario para el paciente
('19234567', 'paciente8@example.com', 'password19', NOW()), -- usuario para el paciente
('20234567', 'paciente9@example.com', 'password20', NOW()), -- usuario para el paciente
('21234567', 'paciente10@example.com', 'password21', NOW()); -- usuario para el paciente


-- Precargar datos en la tabla usuarios_roles
INSERT INTO usuarios_roles (id_usuario, id_rol) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2),
(12, 2),
(12, 3),
(13, 3),
(14, 3),
(15, 3),
(16, 3),
(17, 3),
(18, 3),
(19, 3),
(20, 3),
(21, 3);


-- Precargar datos en la tabla Medicos
INSERT INTO medicos (id_especialidad, cmp, nombre, apellido_paterno, apellido_materno, telefono, sueldo, id_usuario) VALUES
(1, 'CMP12345', 'Médico 1', 'Pérez', 'García', '123456789', 2500.00, 2),
(2, 'CMP67890', 'Médico 2', 'Gómez', 'Lopez', '987654321', 3000.00, 3),
(3, 'CMP11223', 'Médico 3', 'Hernández', 'Martínez', '456789123', 2800.00, 4),
(4, 'CMP44556', 'Médico 4', 'Ramírez', 'Salas', '321654987', 2700.00, 5),
(5, 'CMP78901', 'Médico 5', 'Fernández', 'Morales', '159753258', 2900.00, 6),
(6, 'CMP23456', 'Médico 6', 'Torres', 'Cruz', '753159486', 3100.00, 7),
(7, 'CMP34567', 'Médico 7', 'Ríos', 'Mendoza', '654987321', 3300.00, 8),
(8, 'CMP45678', 'Médico 8', 'Vargas', 'Paz', '987123654', 3500.00, 9),
(9, 'CMP56789', 'Médico 9', 'Alonso', 'Mora', '321789654', 3600.00, 10),
(10, 'CMP67890', 'Médico 10', 'Ortega', 'Chávez', '159654123', 3800.00, 11);


-- Precargar datos en la tabla Pacientes
INSERT INTO pacientes (fecha_nacimiento, genero, nombre, apellido, documento, telefono, direccion, id_ubigeo, id_usuario) VALUES
('1990-01-01', 'hombre', 'Juan', 'Pérez', '12234567', '987654321', 'Calle Falsa 123', 1, 12),
('1985-05-15', 'mujer', 'María', 'Gómez', '13234567', '963852741', 'Av. Siempre Viva 456', 2, 13),
('2000-07-20', 'hombre', 'Carlos', 'López', '14234567', '321654987', 'Pasaje del Sol 789', 3, 14),
('1995-09-30', 'mujer', 'Ana', 'Martínez', '15234567', '147258369', 'Calle de la Amistad 101', 4, 15),
('1988-02-14', 'hombre', 'Luis', 'Ramírez', '16234567', '258963147', 'Calle 12 de Octubre 202', 5, 16),
('1992-03-10', 'mujer', 'Laura', 'Fernández', '17234567', '789456123', 'Av. Los Pinos 303', 6, 17),
('1987-11-22', 'hombre', 'Miguel', 'Torres', '18234567', '951753852', 'Calle Las Flores 404', 7, 18),
('1999-06-18', 'mujer', 'Sofía', 'Hernández', '19234567', '753951456', 'Av. Los Álamos 505', 8, 19),
('1993-12-25', 'hombre', 'David', 'García', '20234567', '852147963', 'Calle Los Olivos 606', 9, 20),
('1986-08-08', 'mujer', 'Elena', 'Vargas', '21234567', '159357486', 'Pasaje Las Rosas 707', 10, 21);


-- Precargar datos en la tabla Citas
INSERT INTO citas (fecha_cita, hora_cita, fecha_registrada, hora_registrada, id_estado, id_medico, id_paciente, motivo) VALUES
('2024-01-10', '09:00:00', NOW(), NOW(), 1, 1, 1, 'Consulta de control'),
('2024-01-15', '10:30:00', NOW(), NOW(), 2, 2, 2, 'Consulta por fiebre'),
('2024-01-20', '11:00:00', NOW(), NOW(), 3, 3, 3, 'Chequeo general'),
('2024-01-25', '14:00:00', NOW(), NOW(), 4, 4, 4, 'Consulta de urgencia'),
('2024-02-01', '08:00:00', NOW(), NOW(), 5, 5, 5, 'Consulta dermatológica'),
('2024-02-05', '09:30:00', NOW(), NOW(), 6, 6, 6, 'Consulta de seguimiento'),
('2024-02-10', '10:00:00', NOW(), NOW(), 7, 7, 7, 'Consulta psiquiátrica'),
('2024-02-15', '11:30:00', NOW(), NOW(), 8, 8, 8, 'Chequeo anual'),
('2024-02-20', '13:00:00', NOW(), NOW(), 9, 9, 9, 'Consulta pediátrica'),
('2024-02-25', '15:00:00', NOW(), NOW(), 10, 10, 10, 'Consulta ginecológica');


-- Precargar datos en la tabla Historiales
INSERT INTO historiales_clinico (fecha_consulta, hora_consulta, id_cita, id_paciente, descripcion_consulta, diagnostico, tratamiento) VALUES
('2024-01-10', '09:00:00', 1, 1, 'Consulta de control', 'Diagnóstico 1', 'Tratamiento 1'),
('2024-01-15', '10:30:00', 2, 2, 'Consulta por fiebre', 'Diagnóstico 2', 'Tratamiento 2'),
('2024-01-20', '11:00:00', 3, 3, 'Chequeo general', 'Diagnóstico 3', 'Tratamiento 3'),
('2024-01-25', '14:00:00', 4, 4, 'Consulta de urgencia', 'Diagnóstico 4', 'Tratamiento 4'),
('2024-02-01', '08:00:00', 5, 5, 'Consulta dermatológica', 'Diagnóstico 5', 'Tratamiento 5'),
('2024-02-05', '09:30:00', 6, 6, 'Consulta de seguimiento', 'Diagnóstico 6', 'Tratamiento 6'),
('2024-02-10', '10:00:00', 7, 7, 'Consulta psiquiátrica', 'Diagnóstico 7', 'Tratamiento 7'),
('2024-02-15', '11:30:00', 8, 8, 'Chequeo anual', 'Diagnóstico 8', 'Tratamiento 8'),
('2024-02-20', '13:00:00', 9, 9, 'Consulta pediátrica', 'Diagnóstico 9', 'Tratamiento 9'),
('2024-02-25', '15:00:00', 10, 10, 'Consulta ginecológica', 'Diagnóstico 10', 'Tratamiento 10');
