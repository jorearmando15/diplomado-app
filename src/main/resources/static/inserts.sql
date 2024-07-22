USE helpme_iud;

-- ROLES
INSERT INTO roles (id, nombre, descripcion)
VALUES (1, 'ROLE_ADMIN', 'Usuario administrador');

INSERT INTO roles (id, nombre, descripcion)
VALUES (2, 'ROLE_USER', 'Usuario normal');

-- USUARIOS
INSERT INTO usuarios
(id, username, nombre, apellido, password, fecha_Nacimiento, enabled, red_social, image)
VALUES
(1, 'julio.martinez@iudigital.edu.co', 'Julio', 'Martinez',
'', '1985-08-05', 1, 0,
'https://static.vecteezy.com/system/resources/previews/018/765/757/non_2x/user-profile-icon-in-flat-style-member-avatar-illustration-on-isolated-background-human-permission-sign-business-concept-vector.jpg');

-- ASIGNAR ROL A USERS
INSERT INTO roles_usuarios(usuarios_id, roles_id)
VALUES
(1, 1);
INSERT INTO roles_usuarios(usuarios_id, roles_id)
VALUES
(1, 2);
