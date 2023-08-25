create function update_modtime() returns trigger
    language plpgsql
as
$$
BEGIN NEW.updated_at = CURRENT_TIMESTAMP(3); RETURN NEW; END;
$$;


create table if not exists public.platform_user
(
    id         bigserial
        constraint platform_user_pk
            primary key,
    email      varchar
        constraint platform_user_pk2
            unique,
    password   varchar,
    username   varchar,
    status     varchar,
    created_at timestamp(3) default CURRENT_TIMESTAMP(3) not null,
    updated_at timestamp(3) default CURRENT_TIMESTAMP(3) not null
);

create trigger update_user_modtime
    before update
    on public.platform_user
    for each row
execute procedure public.update_modtime();

