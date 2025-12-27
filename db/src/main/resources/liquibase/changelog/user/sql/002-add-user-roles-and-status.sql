ALTER TABLE user_db.users
    ADD COLUMN status VARCHAR(30) NOT NULL DEFAULT 'PENDING_VERIFICATION';

ALTER TABLE user_db.users
    ALTER COLUMN status DROP DEFAULT;

CREATE TABLE user_db.user_roles
(
    user_id UUID        NOT NULL,
    role    VARCHAR(50) NOT NULL,

    PRIMARY KEY (user_id, role),

    CONSTRAINT fk_user_roles_user
        FOREIGN KEY (user_id)
            REFERENCES user_db.users (id) ON DELETE CASCADE
);

CREATE INDEX idx_users_email ON user_db.users (email);
CREATE INDEX idx_user_roles_user_id ON user_db.user_roles (user_id);