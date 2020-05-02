ALTER TABLE users_session
    ADD CONSTRAINT user_id_too_use FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    NOT VALID;
CREATE INDEX fki_user_id_too_use
    ON users_session(user_id);