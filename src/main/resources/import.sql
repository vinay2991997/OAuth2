INSERT INTO access_token (access_token,expires_at,phone) VALUES ('0000000000000007', '9930789393000', '0123456789');

INSERT INTO role (name) VALUES ('admin')
INSERT INTO role (name) VALUES ('user')


INSERT INTO permission (type) VALUES ('add role')
INSERT INTO permission (type) VALUES ('add permission')
INSERT INTO permission (type) VALUES ('add role permission')
INSERT INTO permission (type) VALUES ('read resource')

INSERT INTO role_permission (role_name, permission_type) VALUES ('admin', 'add role')
INSERT INTO role_permission (role_name, permission_type) VALUES ('admin', 'add permission')
INSERT INTO role_permission (role_name, permission_type) VALUES ('admin', 'add role permission')
INSERT INTO role_permission (role_name, permission_type) VALUES ('admin', 'read resource')
INSERT INTO role_permission (role_name, permission_type) VALUES ('user', 'read resource')

INSERT INTO users (phone, email, name, password, role) VALUES ('0123456789', 'admin@company.com', 'admin', 'password', 'admin');