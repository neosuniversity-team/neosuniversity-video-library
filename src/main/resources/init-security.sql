INSERT INTO VIDEODB.USER(id,username, password)
values (1,'user','$2a$10$LsG62RqmRHnJW9ovYUM.6e/kl7eIeI/6J7lQgq3n.l/H0gYWZ3c4W');

INSERT INTO VIDEODB.USER(id,username, password)
values (2,'admin','$2a$10$N2kcyD1Us1KCdlqOxy04kedoW0UJr68lj/yGjvBCFviyM0BJ2/Ijm');

INSERT INTO VIDEODB.ROLE(id, name) 
values (1, 'ROLE_USER');

INSERT INTO VIDEODB.ROLE(id, name) 
values (2, 'ROLE_ADMIN');

INSERT INTO VIDEODB.USER_ROLES (users_id, roles_id)
values (1,1);

INSERT INTO VIDEODB.USER_ROLES (users_id, roles_id)
values (2,2);

commit;