CREATE TABLE user_profiles
(
    profile_id          BIGSERIAL,
    user_id             BIGINT,
    username            TEXT,
    profile_name        TEXT,
    age                 INTEGER,
    gender              TEXT,
    weight              DOUBLE PRECISION,
    height              DOUBLE PRECISION,
    activity_level      TEXT,
    diet_type           TEXT,
    diet_calorie        DOUBLE PRECISION,
    diet_carbohydrates  DOUBLE PRECISION,
    diet_water          DOUBLE PRECISION,
    diet_fat            DOUBLE PRECISION,
    diet_protein        DOUBLE PRECISION,
    created_at          TIMESTAMP,
    modified_at         TIMESTAMP,
    CONSTRAINT pk_user_profiles_profile_id PRIMARY KEY (profile_id),
    CONSTRAINT uq_user_profiles_user_id UNIQUE (user_id),
    CONSTRAINT uq_user_profiles_username UNIQUE (username)
);
CREATE INDEX idx_user_profiles_user_id ON user_profiles(user_id);
CREATE INDEX idx_user_profiles_username ON user_profiles(username);