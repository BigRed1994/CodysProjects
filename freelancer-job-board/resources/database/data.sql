INSERT INTO category (name) VALUES
    ('Web Development'),
    ('Graphic Design'),
    ('Writing & Translation'),
    ('Marketing'),
    ('Data Entry');

INSERT INTO skill (name) VALUES
    ('Java'),
    ('React'),
    ('Photoshop'),
    ('Copywriting'),
    ('Excel');

INSERT INTO "user" (name, email, password, role) VALUES
    ('cali', 'cali@example.com', 'cali', 'client'),
    ('marissa', 'marissa@example.com', 'marissa', 'freelancer');

INSERT INTO job (title, description, posted_by, category_id) VALUES
    ('Build a Portfolio Website', 'Need a personal portfolio built in HTML/CSS', 1, 1),
    ('Logo Designs for Startup', 'Create a modern logo for a tech startup', 1, 2);

INSERT INTO application (job_id, freelancer_id, cover_letter) VALUES
(1, 2, 'I have experience building responsive websites.', 'Pending'),
(2, 2, 'I can design logos using modern design tools.', 'Pending');


INSERT INTO user_skill (user_id, skill_id) VALUES
    (2, 1),
    (2, 3);
