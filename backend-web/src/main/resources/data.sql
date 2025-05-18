INSERT INTO TEAM (team_name, project_code, description, created_at, foundation_date)
VALUES ('개발1팀', 'PROJ-DEV1', '핵심 서비스 개발 담당', '2023-03-15T10:00:00', '2022-01-10T00:00:00'),
       ('기획팀', 'PROJ-PLAN', '신규 서비스 기획 및 전략 수립', '2023-02-20T11:00:00', '2021-11-05T00:00:00'),
       ('운영지원팀', 'PROJ-OPS', '서비스 운영 및 고객 지원', '2023-04-01T09:30:00', '2022-05-20T00:00:00'),
       ('마케팅팀', 'PROJ-MKT', '제품 홍보 및 시장 분석', '2023-05-10T14:00:00', '2022-08-15T00:00:00');
INSERT INTO USERS (username, password, email, first_name, last_name, role, is_active, login_attempts, registration_date,
                   birth_date, team_id)
VALUES ('dev_master', '$2a$10$dummyPasswordHash1', 'master@devone.com', '김', '철수', 'ROLE_ADMIN', true, 0,
        '2023-03-15T10:05:00', '1985-05-15', 1),
       ('dev_senior', '$2a$10$dummyPasswordHash2', 'senior@devone.com', '이', '영희', 'ROLE_USER', true, 1,
        '2023-03-16T11:10:00', '1990-11-20', 1),
       ('dev_junior', '$2a$10$dummyPasswordHash3', 'junior@devone.com', '박', '민준', 'ROLE_USER', true, 0,
        '2023-04-01T14:30:00', '1995-02-10', 1);
INSERT INTO USERS (username, password, email, first_name, last_name, role, is_active, login_attempts, registration_date,
                   birth_date, team_id)
VALUES ('plan_lead', '$2a$10$dummyPasswordHash4', 'lead@planning.com', '최', '지우', 'ROLE_ADMIN', true, 0,
        '2023-02-20T11:05:00', '1988-07-01', 2),
       ('plan_staff', '$2a$10$dummyPasswordHash5', 'staff@planning.com', '정', '서윤', 'ROLE_USER', true, 2,
        '2023-03-01T09:45:00', '1992-09-25', 2);
INSERT INTO USERS (username, password, email, first_name, last_name, role, is_active, login_attempts, registration_date,
                   birth_date, team_id)
VALUES ('ops_manager', '$2a$10$dummyPasswordHash6', 'manager@ops.com', '윤', '하준', 'ROLE_ADMIN', true, 0,
        '2023-04-01T10:00:00', '1982-03-12', 3);
INSERT INTO USERS (username, password, email, first_name, last_name, role, is_active, login_attempts, registration_date,
                   birth_date, team_id)
VALUES ('guest_user', '$2a$10$dummyPasswordHash7', 'guest@example.com', '강', '소라', 'ROLE_GUEST', false, 5,
        '2023-06-01T16:00:00', '1998-12-30', NULL);
INSERT INTO USERS (username, password, email, first_name, last_name, role, is_active, login_attempts, registration_date,
                   birth_date, team_id)
VALUES ('mkt_expert', '$2a$10$dummyPasswordHash8', 'expert@marketing.com', '송', '은채', 'ROLE_USER', true, 0,
        '2023-05-11T10:20:00', '1993-06-08', 4);