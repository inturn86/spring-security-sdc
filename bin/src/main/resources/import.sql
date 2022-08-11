INSERT INTO sys_member (ID, USERNAME, PASSWORD, EMAIL, ROLE) VALUES (1, 'eddy', '$2a$10$g9eBn1BjiH.WBB0TrcGSgOtYpULMyhBSwMcwevULgW6j6Q3b6PqAe', 'sieunkr@gmail.com', 'WORKER');

INSERT INTO sys_member (ID, USERNAME, PASSWORD, EMAIL, ROLE) VALUES (2, 'sdc', '$2a$10$g9eBn1BjiH.WBB0TrcGSgOtYpULMyhBSwMcwevULgW6j6Q3b6PqAe', 'sieunkr2@gmail.com', 'MANAGER');

INSERT INTO sys_role(role, role_name) values ('WORKER', 'WORKER')

INSERT INTO sys_role(role, role_name) values ('MANAGER', 'MANAGER')

INSERT INTO sys_app(app_id, app_name) values ('ROLE_BOOK', 'BOOK')

INSERT INTO sys_app(app_id, app_name) values ('ROLE_COFFEE', 'COFFEE')

INSERT INTO sys_app_mapping(app_mapping_id, app_id, role) values ('1', 'ROLE_BOOK', 'WORKER')
INSERT INTO sys_app_mapping(app_mapping_id, app_id, role) values ('2', 'ROLE_COFFEE', 'MANAGER')
INSERT INTO sys_app_mapping(app_mapping_id, app_id, role) values ('3', 'ROLE_BOOK', 'MANAGER')
