truncate table role_permission;

INSERT INTO public.role_permission(
            role_id, role_name, permissions, created_date, modified_date)
    VALUES (1, 'User', '*', current_timestamp::timestamp, current_timestamp::timestamp);

INSERT INTO public.role_permission(
        role_id, role_name, permissions, created_date, modified_date)
VALUES (2, 'Business', '*', current_timestamp::timestamp, current_timestamp::timestamp);

INSERT INTO public.role_permission(
        role_id, role_name, permissions, created_date, modified_date)
VALUES (3, 'Employee', '*', current_timestamp::timestamp, current_timestamp::timestamp);
