# Security_REST

## Для корректной работы:

1) Запустить проект и перейти по адресу http://localhost:8080/admin, чтобы появилась форма авторизации 

2) Выполнить последовательно в консоли БД:
```SQL
1.
INSERT INTO role (name_roles)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

2.
INSERT INTO users (name, password, last_name, department, salary)
VALUES ('Admin', 1, 'Adminich', 'IT', 1000);

3.
INSERT INTO user_roles (user_id, role_id)
VALUES ((SELECT id FROM users WHERE name = 'Admin'),
        (SELECT id FROM role WHERE name_roles = 'ROLE_ADMIN'));
```

3) Перезапустить программу

4) Перейти по адресу http://localhost:8080/admin
5) Ввести login и password:
Admin 
1
