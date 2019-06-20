-- Tables --

CREATE TABLE RegularUser (
user_id int(16) unsigned NOT NULL AUTO_INCREMENT,
email varchar(250) NOT NULL,
first_name varchar(20) NOT NULL,
last_name varchar(20) NOT NULL,
pin varchar(4) NOT NULL,
PRIMARY KEY (user_id),
UNIQUE KEY email (email)
);

CREATE TABLE Category (
cat_name varchar(20) NOT NULL,
PRIMARY KEY (cat_name)
);

CREATE TABLE Corkboard (
corkboard_id int(16) unsigned NOT NULL AUTO_INCREMENT,
user_id int(16) unsigned NOT NULL,
creation_dtime datetime NOT NULL,
last_dtime datetime NOT NULL,
visibility varchar(8) NOT NULL,
password varchar(4) NOT NULL,
title varchar(250) NOT NULL,
PRIMARY KEY (corkboard_id)
);

CREATE TABLE PushPin(
pushpin_id int(16) unsigned NOT NULL AUTO_INCREMENT,
corkboard_id int(16) unsigned NOT NULL,
pp_dtime datetime NOT NULL,
description varchar(500) DEFAULT NULL,
url_image varchar(500) NOT NULL,
PRIMARY KEY (pushpin_id)
);

CREATE TABLE PPin_Tag (
pushpin_id int(16) unsigned NOT NULL,
pp_dtime datetime NOT NULL,
tag varchar(50) NOT NULL,
PRIMARY KEY (pushpin_id, tag)
);

CREATE TABLE Follow(
user_id int(16) unsigned NOT NULL,
followed_user_id int(16) unsigned NOT NULL,
PRIMARY KEY (user_id, followed_user_id),
KEY followed_user_id (followed_user_id)
);

CREATE TABLE Categorize(
corkboard_id int(16) unsigned NOT NULL,
cat_name varchar(20) NOT NULL,
PRIMARY KEY (corkboard_id, cat_name),
KEY cat_name (cat_name)
);

CREATE TABLE Watch(
user_id int(16) unsigned NOT NULL,
corkboard_id int(16) unsigned NOT NULL,
PRIMARY KEY (user_id, corkboard_id)
);

CREATE TABLE User_Like(
user_id int(16) unsigned NOT NULL,
pushpin_id int(16) unsigned NOT NULL,
PRIMARY KEY (user_id, pushpin_id)
);

CREATE TABLE Comment(
user_id int(16) unsigned NOT NULL,
pushpin_id int(16) unsigned NOT NULL,
c_dtime datetime NOT NULL,
content varchar(500) DEFAULT NULL,
PRIMARY KEY (user_id, pushpin_id, c_dtime)
);

-- Constraints   Foreign Keys: 
/*FK_ChildTable_childColumn_ParentTable_parentColumn*/

ALTER TABLE CorkBoard
ADD CONSTRAINT fk_CorkBoard_user_id_RegularUser_user_id FOREIGN KEY 
(user_id) REFERENCES RegularUser (user_id);

ALTER TABLE Pushpin
ADD CONSTRAINT fk_Pushpin_corkboard_id_Corkboard_corkboard_id FOREIGN KEY
(corkboard_id) REFERENCES Corkboard (corkboard_id);

ALTER TABLE PPin_Tag
ADD CONSTRAINT fk_PPin_Tag_pushpin_id_Pushpin_pushpin_id FOREIGN KEY 
(pushpin_id) REFERENCES Pushpin (pushpin_id);

ALTER TABLE Follow
ADD CONSTRAINT fk_Follow_user_id_RegularUser_user_id FOREIGN KEY (user_id)
REFERENCES RegularUser (user_id) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT fk_Follow_followed_user_id_RegularUser_user_id FOREIGN KEY 
(followed_user_id) REFERENCES RegularUser (user_id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE Categorize
ADD CONSTRAINT fk_Categorize_corkboard_id_Corkboard_corkboard_id FOREIGN 
KEY (corkboard_id) REFERENCES Corkboard (corkboard_id);

ALTER TABLE Watch
ADD CONSTRAINT fk_Watch_user_id_RegularUser_user_id FOREIGN KEY (user_id)
REFERENCES RegularUser (user_id),
ADD CONSTRAINT fk_Watch_corkboard_id_Corkboard_corkboard_id FOREIGN KEY 
(corkboard_id) REFERENCES Corkboard (corkboard_id);

ALTER TABLE User_Like
ADD CONSTRAINT fk_Like_user_id_RegularUser_user_id FOREIGN KEY (user_id)
REFERENCES RegularUser (user_id),
ADD CONSTRAINT fk_Like_pushpin_id_Pushpin_pushpin_id FOREIGN KEY 
(pushpin_id) REFERENCES Pushpin (pushpin_id);

ALTER TABLE Comment
ADD CONSTRAINT fk_Comment_user_id_RegularUser_user_id FOREIGN KEY 
(user_id) REFERENCES RegularUser (user_id),
ADD CONSTRAINT fk_Comment_pushpin_id_Pushpin_pushpin_id FOREIGN KEY 
(pushpin_id) REFERENCES Pushpin (pushpin_id);
