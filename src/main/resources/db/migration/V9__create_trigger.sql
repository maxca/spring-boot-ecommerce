CREATE OR REPLACE FUNCTION update_total()
  RETURNS trigger AS $abc$
BEGIN
  	UPDATE shopping_cart
    SET total_qty = aa.total_qty, total_price = aa.total_price
	FROM (
		SELECT SUM(DISTINCT quantity) as total_qty, SUM(DISTINCT total_price) as total_price
        FROM cart_item
		WHERE cart_id = NEW.cart_id OR cart_id = OLD.cart_id
	) as aa
	WHERE id = NEW.cart_id OR id = OLD.cart_id;
RETURN NEW;
END;
$abc$ LANGUAGE plpgsql;


CREATE TRIGGER update_shopping_cart
AFTER INSERT OR UPDATE OR DELETE ON cart_item
FOR EACH ROW EXECUTE PROCEDURE update_total();

