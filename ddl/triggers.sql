-- TRIGGERS ON ORDERS TABLE
-- after insert
delimiter //
CREATE DEFINER=`root`@`localhost` TRIGGER `updateSalesPerPackage`
AFTER INSERT ON `orders` FOR EACH ROW
BEGIN

    -- If the order is placed AND in table 'revenue_per_package' exists a tuple with the package id just bought THEN
	IF NEW.servicepkg IN (SELECT package_id FROM revenue_per_package) THEN

        -- 1) update revenue for the package just bought both with and without considering the optional product bought with it (effects REVENUE_PER_PACKAGE)
        UPDATE revenue_per_package SET revenue_with_optional = revenue_with_optional + NEW.total_value,
                                       revenue_without_optional = revenue_without_optional + NEW.package_value_without_options
        WHERE package_id IN (SELECT o.servicepkg
                             FROM orders o
                             WHERE o.servicepkg = NEW.servicepkg);

    END IF;

    -- If the order is placed AND in table 'revenue_per_package' does not exist a tuple with the package id just bought THEN
    IF NEW.servicepkg NOT IN (SELECT package_id FROM revenue_per_package) THEN

        -- 1) create an entry for the package just bought (effects REVENUE_PER_PACKAGE)
		INSERT INTO revenue_per_package(package_id, revenue_with_optional, revenue_without_optional)
		VALUES(NEW.servicepkg, NEW.total_value, NEW.package_value_without_options);

    END IF;

END //
delimiter ;


-- after update
delimiter //
CREATE DEFINER=`root`@`localhost` TRIGGER `incrementPurchasesPerPackage & updateOptionalProductRevenue`
AFTER UPDATE ON `orders` FOR EACH ROW
BEGIN
DECLARE numberOfOptionalAddedToASpecificPackage int;
DECLARE valueBestOptional double;

    -- If the order is placed AND the payment works correctly THEN
	IF NEW.is_rejected = false THEN

        -- 1) increment by 1 the number of purchases of the package bought with the current order (effects PURCHASES_PER_PACKAGE)
        UPDATE purchases_per_package SET number_of_purchases = number_of_purchases + 1
        WHERE id IN (SELECT o.servicepkg
                     FROM orders o
                     WHERE o.servicepkg = NEW.servicepkg);

        -- 2) increment by 1 the number of purchases of the package with a specific validity period bought with the current order (effects PURCHASES_PER_PACKAGE_AND_PERIOD)
        UPDATE purchases_per_package_and_period SET number_of_purchases = number_of_purchases + 1
        WHERE (package_id, period_id) IN (SELECT o.servicepkg, o.period_id
                                          FROM orders o
                                          WHERE o.servicepkg = NEW.servicepkg);

        -- 3) remove from table 'suspended_orders' the tuple related to the order just payed (effects SUSPENDED_ORDERS)
        DELETE FROM suspended_orders
        WHERE(order_id = NEW.id);

        -- 4) Updates the total revenue per optional product (effects REVENUE_PER_OPTIONAL_PRODUCT)
        DELETE FROM optional_products_in_order; -- additional table (only in the db) added to help us computing revenue for each optional product

        INSERT INTO optional_products_in_order
        SELECT op.product_code, (op.monthly_fee * v.months)
        FROM orders o
                 JOIN order_opt_product oop on oop.orders_id = o.id
                 JOIN optional_product op on op.product_code = oop.optional_products_product_code
                 JOIN validity_period v on v.id = o.period_id
        WHERE o.id = NEW.id;

        UPDATE revenue_per_optional_product r, optional_products_in_order opo
        SET r.total_revenue = r.total_revenue + opo.sales_of_optional_prod
        WHERE r.optional_id = opo.optional_product_id;

        -- 5) Calculate which is the best optional product and set 'is_best' attribute of that product to true (effects REVENUE_PER_OPTIONAL_PRODUCT)
        UPDATE revenue_per_optional_product SET is_best = false
        WHERE is_best = true;

        SELECT max(total_revenue) INTO valueBestOptional
        FROM revenue_per_optional_product;


        UPDATE revenue_per_optional_product SET is_best = true
        WHERE total_revenue = valueBestOptional;

        -- 6) Calculate and add to table 'average_optional_per_package' the average number of optional products bought with each package (effects AVERAGE_OPTIONAL_PER_PACKAGE)
        SELECT count(*) INTO numberOfOptionalAddedToASpecificPackage -- is a variable defined after BEGIN
        FROM orders o JOIN order_opt_product oop on oop.orders_id = o.id
        WHERE o.is_rejected = false and o.servicepkg IN (SELECT o.servicepkg
                                                         FROM orders o
                                                         WHERE o.id = NEW.id);

        DELETE FROM average_optional_per_package
        WHERE package_id IN (SELECT s.id
                             FROM servicepkg s
                             WHERE s.id = NEW.servicepkg);

        INSERT INTO average_optional_per_package
        SELECT p.id, numberOfOptionalAddedToASpecificPackage/p.number_of_purchases
        FROM purchases_per_package as p
        WHERE p.id IN (SELECT s.id
                       FROM servicepkg s
                       WHERE s.id = NEW.servicepkg);

        -- 7) ADDITIONAL: set back an insolvent user to false when does not have any rejected order
        UPDATE users SET is_insolvent = false
        WHERE username NOT IN (SELECT o.order_owner
                               FROM orders o
                               WHERE o.order_owner = NEW.order_owner and o.is_rejected = true);

    END IF;


    -- If the order is placed AND the payment DOES NOT work correctly THEN
    IF NEW.is_rejected = true THEN

        -- 1) add to table 'suspended_orders' a tuple related to the current order (effects SUSPENDED_ORDERS)
		INSERT INTO suspended_orders(order_id)
		VALUES(NEW.id);

    END IF;

END //
delimiter ;



-- TRIGGERS ON OPTIONAL_PRODUCT TABLE
-- after insert
delimiter //
CREATE DEFINER=`root`@`localhost` TRIGGER `addEntryToRevenuePerOptionalProduct`
AFTER INSERT ON `optional_product` FOR EACH ROW
BEGIN

    -- every time a new optional product is created a new entry is created also in revenue_per_optional_product table
    INSERT INTO revenue_per_optional_product(optional_id, is_best)
    VALUES(NEW.product_code, false);

END //
delimiter ;



-- TRIGGERS ON SERVICE_PKG TABLE
-- after insert
delimiter //
CREATE DEFINER=`root`@`localhost` TRIGGER `createPurchasedPkg`
AFTER INSERT ON `servicepkg` FOR EACH ROW
BEGIN

    -- every time a new service package is created a new entry is created also in purchases_per_package table
    INSERT INTO purchases_per_package(id)
    VALUES(NEW.id);

    -- every time a new service package is created a new entry is created also in average_optional_per_package table
    INSERT INTO average_optional_per_package(package_id)
    VALUES(NEW.id);

END //
delimiter ;



-- TRIGGERS ON SERVICE_PKG_PERIOD TABLE
-- after insert
delimiter //
CREATE DEFINER=`root`@`localhost` TRIGGER `createPurchasedPkgandPeriod`
AFTER INSERT ON `service_pkg_period` FOR EACH ROW
BEGIN

    -- every time a new entry that relates a service package to a validity period  is created a new entry is created also in purchases_per_package_and_period table
    INSERT INTO purchases_per_package_and_period(package_id, period_id)
    VALUES(NEW.servicepkgs_id, NEW.periods_id);

END //
delimiter ;