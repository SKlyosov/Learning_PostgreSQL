CREATE OR REPLACE FUNCTION audit_posts()
   RETURNS TRIGGER
AS $BODY$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO posts_audit_log (post_id, operation, processed_At, old_title)
        SELECT OLD.id, TG_OP, now(), OLD.title;
        RETURN OLD;
    ELSIF (TG_OP = 'UPDATE') THEN
        INSERT INTO posts_audit_log (post_id, operation, processed_At, old_title, new_title)
        SELECT NEW.id, TG_OP, now(), OLD.title, NEW.title;
        RETURN NEW;
    END IF;
    RETURN NULL;
END;
$BODY$
LANGUAGE PLPGSQL;

CREATE OR REPLACE TRIGGER trg_posts_UPDATE_DELETE
AFTER UPDATE OR DELETE ON posts
FOR EACH ROW EXECUTE PROCEDURE audit_posts();
