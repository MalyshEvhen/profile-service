CREATE TABLE user_profiles
(
    profile_id          BIGSERIAL,
    user_id             BIGINT,
    profile_name        TEXT,
    age                 INTEGER,
    gender              TEXT,
    weight              DOUBLE PRECISION,
    hight               DOUBLE PRECISION,
    diet_type           TEXT,
    diet_calorie        DOUBLE PRECISION,
    diet_carbohydrates  DOUBLE PRECISION,
    diet_water          DOUBLE PRECISION,
    diet_fat            DOUBLE PRECISION,
    diet_protein        DOUBLE PRECISION,
    CONSTRAINT pk_user_profiles_profile_id PRIMARY KEY profile_id,
    CONSTRAINT uq_user_profiles_user_id UNIQUE user_id
);
CREATE INDEX idx_user_profiles_user_id ON user_profiles(user_id);