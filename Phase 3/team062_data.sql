
-- Regularuser --
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (1,'michael@bluthco.com','Michael','Bluth','michael123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (2,'rtaylor@gatech.edu','Robert','Taylor', 'password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (3,'dwilliamson@gatech.edu','Daniel','Williamson','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (4,'llopez@gatech.edu','Luis','Lopez','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (5,'pestrada@gatech.edu','Patrick','Estrada','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (6,'rwalker@gatech.edu','Rodney','Walker','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (7,'shenry@gatech.edu','Sean','Henry','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (8,'tfox@gatech.edu','Thomas','Fox','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (9,'jflowers@gatech.edu','Jacob','Flowers','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (10,'braymond@gatech.edu','Bianca','Raymond','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (11,'jcarroll@gatech.edu','Julie','Carroll','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (12,'jhernandez@gatech.edu','Jane','Hernandez','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (13,'crogers@gatech.edu','Colleen','Rogers','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (14,'efrank@gatech.edu','Elizabeth','Frank','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (15,'ahess@gatech.edu','Alicia','Hess','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (16,'tjohnson@gatech.edu','Tara','Johnson','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (17,'cchavez@gatech.edu','Christine','Chavez','password123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (18,'admin01@gtonline.com','Sharon','Lowery','admin123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (19,'admin02@gtonline.com','Gary','Turner', 'admin123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (20,'admin03@gtonline.com','Kristina','Scott','admin123');
INSERT INTO regularuser (user_id,email,first_name,last_name,pin) VALUES (21,'admin04@gtonline.com','Charles','James','admin123');

-- Category  --
INSERT INTO Category (cat_name ) VALUES ('Education');
INSERT INTO Category (cat_name) VALUES ('Architecture');
INSERT INTO Category (cat_name) VALUES ('Home & Garden');
INSERT INTO Category (cat_name) VALUES ('People');
INSERT INTO Category (cat_name) VALUES ('Travel');
INSERT INTO Category (cat_name) VALUES ('Photography');
INSERT INTO Category (cat_name) VALUES ('Sports');
INSERT INTO Category (cat_name) VALUES ('Pets');
INSERT INTO Category (cat_name) VALUES ('Technology');
INSERT INTO Category (cat_name) VALUES ('Other');
INSERT INTO Category (cat_name) VALUES ('Food & Drink');
INSERT INTO Category (cat_name) VALUES ('Art');

-- Corkboard   --
INSERT INTO Corkboard (corkboard_id,user_id,creation_dtime,last_dtime,visibility,password,title ) VALUES ('1',7,'2012-01-17 01:43:32','2012-01-18 13:49:32','public',null,'Pools');
INSERT INTO Corkboard (corkboard_id,user_id,creation_dtime,last_dtime,visibility,password,title ) VALUES ('2',1,'2012-01-17 02:43:32','2012-01-19 01:55:32','public',null,'Gardens I love');
INSERT INTO Corkboard (corkboard_id,user_id,creation_dtime,last_dtime,visibility,password,title ) VALUES ('3',9,'2012-01-16 01:43:32','2012-01-20 03:23:32','private','fd5456','Birthday Ideas');
INSERT INTO Corkboard (corkboard_id,user_id,creation_dtime,last_dtime,visibility,password,title ) VALUES ('4',4,'2012-01-14 01:43:32','2012-01-21 06:43:32','public',null,'Vacation Spots');
INSERT INTO Corkboard (corkboard_id,user_id,creation_dtime,last_dtime,visibility,password,title ) VALUES ('5',1,'2012-01-17 03:43:32','2012-01-22 07:55:32','public',null,'Cats and their Antics');
INSERT INTO Corkboard (corkboard_id,user_id,creation_dtime,last_dtime,visibility,password,title ) VALUES ('6',1,'2012-01-17 04:43:32','2012-01-30 09:50:32','private','1234','Vacation Ideas');

-- PushhPin   --
INSERT INTO PushPin (pushpin_id,corkboard_id,pp_dtime,description,url_image ) VALUES ('1','2','2012-01-30 09:50:32','Great looking lantern','http://inmyownstyle.com/images/2011/06/Candle-La');

-- PPin_Tag    --
INSERT INTO PPin_Tag  (pushpin_id,pp_dtime,tag ) VALUES ('1','2012-01-30 09:50:32','lantern, light, garden, stylish');

-- Follow    --
INSERT INTO Follow  (user_id,followed_user_id) VALUES ('1','2');
INSERT INTO Follow  (user_id,followed_user_id) VALUES ('1','3');
INSERT INTO Follow  (user_id,followed_user_id) VALUES ('1','4');

-- Categorize    --
INSERT INTO Categorize  (corkboard_id,cat_name) VALUES ('1','Architecture');
INSERT INTO Categorize  (corkboard_id,cat_name) VALUES ('2','Home & Garden');
INSERT INTO Categorize  (corkboard_id,cat_name) VALUES ('3','Photography');
INSERT INTO Categorize  (corkboard_id,cat_name) VALUES ('4','Sports');
INSERT INTO Categorize  (corkboard_id,cat_name) VALUES ('5','Pets');
INSERT INTO Categorize  (corkboard_id,cat_name) VALUES ('6','Travel');

-- Watch    --
INSERT INTO Watch  (user_id,corkboard_id) VALUES ('1','3');
INSERT INTO Watch  (user_id,corkboard_id) VALUES ('2','4');
INSERT INTO Watch  (user_id,corkboard_id) VALUES ('3','5');
INSERT INTO Watch  (user_id,corkboard_id) VALUES ('1','4');
INSERT INTO Watch  (user_id,corkboard_id) VALUES ('1','1');

-- User_Like    --
INSERT INTO User_Like  (user_id,pushpin_id ) VALUES ('2','1');
INSERT INTO User_Like  (user_id,pushpin_id ) VALUES ('4','1');
INSERT INTO User_Like  (user_id,pushpin_id ) VALUES ('8','1');

-- Comment    --
INSERT INTO Comment  (user_id,pushpin_id,c_dtime,content ) VALUES ('3','1','2012-01-30 09:50:32','Is that a hot tub in the corner?');
INSERT INTO Comment  (user_id,pushpin_id,c_dtime,content ) VALUES ('5','1','2012-03-20 10:50:32','Why not!?');


