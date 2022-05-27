INSERT INTO db2.employee (username, password, mail) VALUES ('admin', 'admin', 'admin@admin.admin');

INSERT INTO db2.services (id, service_type, fee_extra_gigabytes, number_of_gigabytes) VALUES ('1', '2', '32', '2323');
INSERT INTO db2.services (id, service_type, fee_extra_gigabytes, number_of_gigabytes) VALUES ('2', '3', '32', '2373');
INSERT INTO db2.services (id, service_type, fee_extra_min, number_of_minutes) VALUES ('3', '0', '44', '55');
INSERT INTO db2.services (id, service_type) VALUES ('4', '1');
INSERT INTO db2.services (id, service_type, fee_extrasms, number_ofsms) VALUES ('5', '0', '67', '77');

INSERT INTO db2.users (username, password, mail) VALUES ('talla', 'pesce', 'talla.pippo@gmail.com');
INSERT INTO db2.users (username, password, mail) VALUES ('rita', 'pippo', 'rita.pippo@gmail.com');

INSERT INTO db2.validity_period (id, months, monthly_fee) VALUES ('1', '12', '22');
INSERT INTO db2.validity_period (id, months, monthly_fee) VALUES ('2', '24', '18');
INSERT INTO db2.validity_period (id, months, monthly_fee) VALUES ('3', '36', '15');

INSERT INTO db2.optional_product (product_code, brief_product_description, monthly_fee, name)
    VALUES ('1', 'questo coso contiene robe', '5', 'optional 1');
INSERT INTO db2.optional_product (product_code, brief_product_description, monthly_fee, name)
    VALUES ('2', 'questo coso contiene robe', '5', 'optional 2');

INSERT INTO db2.servicepkg (id, name) VALUES ('1', 'Mille Mila Baci');
INSERT INTO db2.servicepkg (id, name) VALUES ('2', 'rita abita in campagna');

INSERT INTO db2.orders (id, creation_time, starting_date_of_subscription, total_value, servicepkg, package_value_without_options,order_owner, period_id)
VALUES ('1', '2022-04-29 17:39:37', '2022-05-23', '672','1', '432', 'talla', '2');

-- bridge tables

INSERT INTO db2.order_opt_product VALUES ('1','1');
INSERT INTO db2.order_opt_product VALUES ('1','2');

INSERT INTO db2.service_opt_product VALUES ('1', '2');
INSERT INTO db2.service_opt_product VALUES ('2', '1');
INSERT INTO db2.service_opt_product VALUES ('2', '2');

INSERT INTO db2.service_pkg_period VALUES ('1', '1');
INSERT INTO db2.service_pkg_period VALUES ('1', '2');
INSERT INTO db2.service_pkg_period VALUES ('1', '3');
INSERT INTO db2.service_pkg_period VALUES ('2', '2');
INSERT INTO db2.service_pkg_period VALUES ('2', '3');

INSERT INTO db2.service_service_pkg VALUES ('1', '1');
INSERT INTO db2.service_service_pkg VALUES ('1', '2');
INSERT INTO db2.service_service_pkg VALUES ('1', '3');
INSERT INTO db2.service_service_pkg VALUES ('2', '3');
INSERT INTO db2.service_service_pkg VALUES ('2', '5');