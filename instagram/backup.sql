;              
CREATE USER IF NOT EXISTS SA SALT 'ca5a98c4180fcb28' HASH 'a3c4fc20b7845c0842f836cad3aac1af44c2d6c5205889659ad055665808569b' ADMIN;            
CREATE SEQUENCE PUBLIC.HIBERNATE_SEQUENCE START WITH 240;      
CREATE CACHED TABLE PUBLIC.BOARD(
    BOARD_ID BIGINT NOT NULL,
    CONTENT CLOB,
    CREATED_DATE_TIME TIMESTAMP,
    HIT INTEGER DEFAULT 0 NOT NULL,
    MODIFIED_DATE_TIME TIMESTAMP,
    TITLE VARCHAR(255) NOT NULL,
    MEMBER_ID BIGINT
);              
ALTER TABLE PUBLIC.BOARD ADD CONSTRAINT PUBLIC.CONSTRAINT_3 PRIMARY KEY(BOARD_ID);             
-- 11 +/- SELECT COUNT(*) FROM PUBLIC.BOARD;   
INSERT INTO PUBLIC.BOARD(BOARD_ID, CONTENT, CREATED_DATE_TIME, HIT, MODIFIED_DATE_TIME, TITLE, MEMBER_ID) VALUES
(2, STRINGDECODE('\uc124\ud604\uc0ac\uc9c4\ub0b4\uc6a9'), TIMESTAMP '2022-05-30 20:09:06.434591', 0, TIMESTAMP '2022-05-30 20:09:06.434591', STRINGDECODE('\uc124\ud604 \uc0ac\uc9c4'), 1),
(4, STRINGDECODE('\uc288\ud654\uc640 \uc5ec\uc790\uc544\uc774\ub4e4 \uc0ac\uc9c4\uc785\ub2c8\ub2e4!'), TIMESTAMP '2022-05-30 20:09:44.401893', 0, TIMESTAMP '2022-05-30 20:09:44.401893', STRINGDECODE('\uc5ec\uc790\uc544\uc774\ub4e4'), 1),
(7, STRINGDECODE('\ud504\ub85c\ubbf8\uc2a4\ub098\uc778 \ub2e8\uccb4\uc0ac\uc9c4\uc785\ub2c8\ub2e4!'), TIMESTAMP '2022-05-30 20:10:08.212448', 0, TIMESTAMP '2022-05-30 20:10:08.212448', STRINGDECODE('\ud504\ub85c\ubbf8\uc2a4\ub098\uc778'), 1),
(9, STRINGDECODE('\uc544\uc774\uc720 \uace0\ud654\uc9c8 \uc0ac\uc9c4'), TIMESTAMP '2022-06-05 14:59:30.581376', 0, TIMESTAMP '2022-06-05 14:59:30.581376', STRINGDECODE('\uc544\uc774\uc720!'), 1),
(12, STRINGDECODE('\uac15\ubbfc\uacbd \uace0\ud654\uc9c8 \uc774\ubbf8\uc9c0!'), TIMESTAMP '2022-06-05 14:59:59.337399', 0, TIMESTAMP '2022-06-05 14:59:59.337399', STRINGDECODE('\uac15\ubbfc\uacbd'), 1),
(14, STRINGDECODE('\ub204\uad70\uc9c0 \ubaa8\ub974\uaca0\ub124'), TIMESTAMP '2022-06-05 15:00:43.380039', 0, TIMESTAMP '2022-06-05 15:00:43.380039', STRINGDECODE('\ud2b8\uc640\uc774\uc2a4'), 1),
(16, STRINGDECODE('\uc544\uc774\uc720 \ud654\ubcf4 \ubaa8\uc74c\uc9d1\uc785\ub2c8\ub2e4!'), TIMESTAMP '2022-06-05 15:43:53.322584', 0, TIMESTAMP '2022-06-05 15:43:53.322584', STRINGDECODE('\uc544\uc774\uc720 \uc0ac\uc9c4!'), 1),
(142, STRINGDECODE('\uc704\uc5d0 \ub2e4\ub78c\uc950\ub294 \ubd81\uc544\uba54\ub9ac\uce74\uc640 \ub3d9\uc544\uc2dc\uc544 \ubd81\ub3d9\ubd80\uc5d0\uc11c \uc0ac\ub294 \uc124\uce58\ub958\uc758 \ud55c \uc885\ub958\uc774\ub2e4. \ub4f1\uc5d0 \uc904\ubb34\ub2ac\uac00 \uc788\ub294 \uac83\uc774 \ud2b9\uc9d5\uc774\ub2e4.[10] \ub300\ubd80\ubd84\uc758 \uc885\uc774 \ubd81\uc544\uba54\ub9ac\uce74\uc5d0\uc11c \uc0b4\uba70, \ub3d9\uc544\uc2dc\uc544\uc640 \ub7ec\uc2dc\uc544\uc5d0\uc11c \uc0ac\ub294 \uc2dc\ubca0\ub9ac\uc544 \ub2e4\ub78c\uc950\ub294 \uc804 \ub2e4\ub78c\uc950 \uc911\uc5d0\uc11c \uc720\uc77c\ud558\uac8c \ubd81\uc544\uba54\ub9ac\uce74 \uc774\uc678\uc758 \uc9c0\uc5ed\uc5d0 \uc0ac\ub294 \uc885\uc774\ub2e4. \uc5b4\uc6d0\uc740 ''\u1103\u119e\u1105\u119e\u11b7+\uc950''\ub85c, ''\u1103\u119e\u1105\u119e\u11b7''\uc740 ''\ub2ec\ub9ac\ub2e4(\u8d70)''\ub77c\ub294 \ub73b\uc778 ''\u1103\u119e\u11ae\ub2e4''\uc758 \uba85\uc0ac\ud615\uc774\ub2e4. \uc7ac\ube60\ub974\uac8c \uc798 \ub2ec\ub9ac\ub294 \uc950\ub77c\ub294 \ub73b\uc778\ub370, \ud604\ub300\uad6d\uc5b4\uc2dd\uc73c\ub85c \ubc14\uafb8\uba74 ''\ub2ec\ub9bc\uc950'' \ub610\ub294 ''\ub2ec\ub9ac\uae30\uc950'' \ub77c\uace0 \ud560 \uc218 \uc788\uaca0\ub2e4.  \uc791\uc740 \ub3d9\ubb3c\uc774 \ub300\ubd80\ubd84 \uadf8\ub807\ub4ef\uc774 \uacbd\uacc4\uc2ec\uc774 \ub9ce\uace0 \uc0c9 \uc790\uccb4\uac00 \ubcf4\ud638\uc0c9\uc744 \ub760\uace0 \uc788\uae30 \ub54c\ubb38\uc5d0 \uc5bc\ud54f \uc9c0\ub098\uce58\uba74 \ubcf4\uae30 \ud798\ub4e4\uc9c0\ub9cc, \uc0ac\uc2e4\uc740 \ub3d9\ub124 \ub4b7\uc0b0\uc5d0\ub9cc \uac00\ub3c4 \uc11c\uc2dd\ud560 \uc815\ub3c4\ub85c \ub110\ub9ac\uace0 \ub110\ub9b0 \ub3d9\ubb3c\uc774\ub2e4. \uc0ac\ub78c\uc744 \uc790\uc8fc \ubd10\uc11c \uac81\uc744 \ub0b4\uc9c0 \uc54a\ub294 \ub2e4\ub78c\uc950\ub4e4\uc740 \uc624\ud788\ub824 \ub4f1\uc0b0\uac1d\uc758 \uac04\uc2dd\uc744 \ub178\ub9ac\uace0 \ub2e4\uac00\uc624\uae30\ub3c4 \ud55c\ub2e4. \ud558\uc9c0\ub9cc \uc6ec\ub9cc\ud558\uba74 \uba39\uc744 \uac83\uc744 \uc8fc\uc9c0 \ub9d0\uc790. \uadc0\uc5fd\ub2e4\uace0 \uc790\uafb8 \uc8fc\uac8c \ub418\uba74 \ub2e4\ub78c\uc950\uac00 \uc0ac\ub78c\uc774 \uc8fc\ub294 \uba39\uc774\uc5d0 \uc775\uc219\ud574\uc838 \ub2e4\ub978 \uba39\uc774\ub97c \uad6c\ud558\uc9c0 \uc54a\uac8c \ub418\uace0, \uaca8\uc6b8\uc744 \ubc84\ud2f0\ub294 \ub2a5\ub825\uc774 \ub5a8\uc5b4\uc9c0\uac8c \ub41c\ub2e4. \uba39\ub294 \ubaa8\uc2b5\uc774 \uadc0\uc5fd\ub354\ub77c\ub3c4 \uac00\ub2a5\ud558\uba74 \ub208\uc73c\ub85c\ub9cc \ubc14\ub77c\ubcf4\uc790.  ''\uc0b0\uace8\uc9dd\uc758 \ub2e4\ub78c\uc950 \uc544\uae30 \ub2e4\ub78c\uc950''\ub77c\ub294 \ub17c\ub780(?)\uc758 \ub3d9\uc694\ub85c\ub3c4 \uce5c\uc219\ud558\ub2e4. \uad6d\ub9bd\uad6d\uc5b4\uc6d0 \uacf5\uc2dd \ud648\ud398\uc774\uc9c0\uc5d0 ''\uc0b0\uace8\uc9dd\uc758, \uc0b0\uace8\uc9dd\uc5d0, \uc0b0\uace8\uc9dc\uae30 \ub2e4\ub78c\uc950 \uc5b4\ub5a4\uac8c \ub9de\ub098\uc694?''\ub780 \uc9c8\ubb38\uacfc \ub2f5\ubcc0\uc774 \uc62c\ub77c\uc640 \uc788\uc744 \uc815\ub3c4. \ub2f5\ubcc0\uc5d0\ub294 ''\uc0b0\uace8\uc9dd\uc758 \ub2e4\ub78c\uc950''\uac00 \ub9de\ub2e4\uace0 \ud55c\ub2e4.# \ud558\uc9c0\ub9cc \uc778\ud130\ub137\uc5d0 \uc774 \ub3d9\uc694\ub97c \uac80\uc0c9\ud574\ubcf4\uba74, ''\uc0b0\uace8\uc9dc\uae30 \ub2e4\ub78c\uc950'' ''\uc0b0\uace8\uc9dd\uc5d0 \ub2e4\ub78c\uc950'' \ub4f1 \uc911\uad6c\ub09c\ubc29\uc73c\ub85c \ub098\uc624\ub294\ub370, ''\uc0b0\uace8\uc9dd\uc758''\ubcf4\ub2e4\ub294 ''\uc0b0\uace8\uc9dd\uc5d0''\uac00 \ub354 \ub9ce\ub2e4. \uadf8 \uc774\uc720\ub294 ''\uc758''\uac00 \ubc1c\uc74c\uc0c1 ''\uc5d0''\ucc98\ub7fc \ub4e4\ub9ac\ub294 \ub370\ub2e4\uac00, \uc758\ubbf8\uc0c1\uc73c\ub85c\ub3c4 \ud06c\uac8c \ud2c0\ub9ac\uc9c0 \uc54a\uc73c\ub2c8 \uadf8\ub7f0 \ub4ef. \ud558\uc9c0\ub9cc 2019\ub144 \uc791\uace1\uac00\uc778 \ubc15\uc7ac\ud6c8 \ubaa9\uc0ac\uc758 \uadfc\ud669\uc744 \uc804\ud558\ub294 \uae30\uc0ac\uc5d0\uc11c\ub294 ''\uc0b0\uace8\uc9dd\uc758''\ub77c\uace0 \uc815\ud655\ud788 \uc37c\ub2e4.# \ud558\uc9c0\ub9cc \uc0b0\uace8\uc9dd\uc774\ub77c\ub294 \ub2e8\uc5b4 \uc790\uccb4\uac00 \uc0b0\uace8\uc9dc\uae30\uc758 \uc900\ub9d0\uc774\uae30 \ub54c\ubb38\uc5d0 \ub3d9\uc694\uc758 \uc81c\ubaa9\uacfc\ub294 \ubcc4\ub3c4\ub85c \uc0b0\uace8\uc9dc\uae30 \ub2e4\ub78c\uc950\ub77c\ub294 \ub9d0 \uc790\uccb4\ub294 \ubb38\ubc95\uc801\uc73c\ub85c \ud2c0\ub9b0\ub9d0\uc740 \uc544\ub2c8\ub2e4.  \uc8fc\uc2dd\uc740 \uacac\uacfc\ub958\ub098 \ub098\ubb34 \uc5f4\ub9e4\uc774\uc9c0\ub9cc, \uace4\ucda9\uacfc \ub3c4\ub9c8\ubc40\ub4e4\uc744 \uc7a1\uc544\uba39\ub294 \ubaa8\uc2b5\uc744 \ubcf4\uc778\ub2e4.[11] \uc0dd\uac01\ubcf4\ub2e4 \uc794\uc778\ud574\uc11c \ub4dc\ubb3c\uac8c \ub3d9\uc871\ub3c4 \uc7a1\uc544\uba39\uae30\ub3c4 \ud55c\ub2e4. \ub2e4\ub78c\uc950\ub294 \uc5c4\uc5f0\ud788 \uc7a1\uc2dd\uc131 \ub3d9\ubb3c\ub85c, \ub300\ubd80\ubd84\uc758 \ub3d9\ubb3c\ub4e4\uc740 \ud544\uc694\ud560 \ub54c\ub9cc \uba39\uc774\ub97c \uad6c\ud558\uc9c0\ub9cc, \ub2e4\ub78c\uc950\ub294 \uba39\uc774\ub97c \uc800\uc7a5\ud574 \ub450\ub294 \uac83\uc73c\ub85c \uc720\uba85\ud558\ub2e4. \ud2b9\ud788 \ubcfc\uc8fc\uba38\ub2c8\ub294 \ud0c4\ub825\uc774 \uc88b\uc544, \ub545\ucf69 7~8\uac1c \uc815\ub3c4\ub294 \uc27d\uac8c \ub4e4\uc5b4\uac04\ub2e4. \ub2e4\ub78c\uc950 \ubcfc\uc8fc\uba38\ub2c8\uc758 \ud0c4\ub825\uc740 \uc5c4\uccad\ub098\uc11c, \ub9cc\uc57d \uc778\uac04\uc774 \ub2e4\ub78c\uc950\uc758 \ubcfc\uc8fc\uba38\ub2c8\uac00 \uc788\ub2e4\uba74 \uc785 \uc548\uc5d0 \ub300\ud615\uacac \ud55c \ub9c8\ub9ac \uc815\ub3c4\ub97c \ud1b5\uc9f8\ub85c \uc9d1\uc5b4\ub123\uc744 \uc218 \uc788\uc744 \uc815\ub3c4\ub85c \ub298\uc5b4\ub0a0 \uac83\uc774\ub77c \ud55c\ub2e4. \uc774\ub807\uac8c \uc800\uc7a5\ud55c \uba39\uc774\ub294 \ub465\uc9c0\ub85c \ub098\ub974\uac70\ub098 \ub545\uc5d0 \ubb3b\uc5b4 \ubcf4\uad00\ud55c\ub2e4. \uc57c\uc0dd\uc774 \uc544\ub2cc \uc560\uc644\uc6a9 \ub2e4\ub78c\uc950\ub3c4 \uba39\uc774\ub97c \ubcfc \uc8fc\uba38\ub2c8\uc5d0 \ub123\uc5b4 \ub2e4\ub978 \uacf3\uc5d0 \uc800\uc7a5\ud558\ub294 \ud589\ub3d9\uc744 \ubcf4\uc774\uae30\ub3c4 \ud55c\ub2e4.[12]'), TIMESTAMP '2022-09-03 10:46:07.316796304', 0, TIMESTAMP '2022-09-03 10:46:07.316796304', STRINGDECODE('\ub2e4\ub78c\uc950'), 1);         
INSERT INTO PUBLIC.BOARD(BOARD_ID, CONTENT, CREATED_DATE_TIME, HIT, MODIFIED_DATE_TIME, TITLE, MEMBER_ID) VALUES
(145, STRINGDECODE('\uc2a4\ud2f0\uce58\uc758 \uc628 \ubab8\uc5d0\ub294 \ud478\ub978\uc0c9 \ud138\uc774 \ub36e\uc5ec\uc788\uace0 \ub450 \ub208\uc740 \uc0c8\uce74\ub9e3\ub2e4. \uc5bc\ud54f \ubcf4\uba74 \ucf54\uc54c\ub77c\uac19\uc774 \uc0dd\uacbc\uace0, \ubc14\ud034\ubc8c\ub808\ub97c \ubaa8\ud2f0\ube0c\ub85c \ub9cc\ub4e4\uc5b4\uc84c\ub2e4\ub294 \uc124\ub3c4 \uc788\uc5c8\uc73c\ub098 \uc9c4\uc9dc \ubaa8\ud2f0\ud504\ub294 \uc288\uac00\uae00\ub77c\uc774\ub354\ub77c\uace0 \ud55c\ub2e4. \uc545\ub3d9 \uac19\uace0 \uadc0\uc5ec\uc6b4 \uc678\ubaa8\uc640\ub294 \ub2e4\ub974\uac8c \ud3ec\uc545\ud588\uc73c\ub098 \ub9b4\ub85c\ub97c \ub9cc\ub09c \ud6c4 \uc628\uc21c\ud574\uc84c\ub2e4.  \uace4\ucda9\ucc98\ub7fc \uc721\uc9c0\uac00 \uc788\ub2e4. \ud314\uacfc \ub2e4\ub9ac \uc0ac\uc774\uc5d0 \ud314\uc774 \ud55c \uc30d \ub354 \uc788\ub294\ub370, \uc774\uac78 \ubab8\uc18d\uc73c\ub85c \uc228\uae30\uba74 \uc704 \uadf8\ub9bc\ucc98\ub7fc \ub41c\ub2e4. \uba38\ub9ac\uc5d0\ub294 \ub354\ub4ec\uc774, \ub4f1\uc5d0\ub294 \uc9c0\ub290\ub7ec\ubbf8 \uac19\uc740 \uac83\uc774 \ub3cb\uc544 \uc788\ub294\ub370 \uc774\uac83\ub3c4 \uc228\uae38 \uc218 \uc788\ub2e4. \ub2e4 \ud3bc\uce58\uba74 \uc880 \ub354 \uc678\uacc4\uc0dd\ubb3c\uc2a4\ub7ec\uc6b4 \ubaa8\uc2b5\uc774 \ub41c\ub2e4.  \uc81c\uc791\uc790\uc778 \uc8fc\ud0a4\ubc14 \ubc15\uc0ac\uc758 \ub9d0\uc5d0 \ub530\ub974\uba74 \"\ucd1d\uc54c\ub3c4 \ud295\uaca8\ub0b4\uace0 \ubd88\uc5d0\ub3c4 \ub044\ub5a1\uc5c6\uc73c\uba70, \uc218\ud37c\ucef4\ud4e8\ud130\ub97c \ub2a5\uac00\ud558\ub294 \uc9c0\ub2a5\uc5d0 \uccb4\uc911\uc758 \uc0bc\ucc9c \ubc30\uae4c\uc9c0 \ub4e4\uc5b4 \uc62c\ub9b4 \uc218 \uc788\ub2e4, \uac8c\ub2e4\uac00 \uc5b4\ub460 \uc18d\uc5d0\uc11c\ub3c4 \ubb3c\uccb4\ub97c \ubcfc\uc218 \uc788\ub2e4.\"\uace0 \ud55c\ub2e4. \uac8c\ub2e4\uac00 \ucc9c\uc131\uc801\uc73c\ub85c \ud30c\uad34\ub97c \uc88b\uc544\ud558\ub294 \uad34\ubb3c\uc774\ub77c\uace0. \uc601\ud654 \uc5d0\uc77c\ub9ac\uc5b8\uc5d0 \ub4f1\uc7a5\ud558\ub294 \uc81c\ub178\ubaa8\ud504\uc640 \ube44\uc2b7\ud55c \ub808\ubca8\uc778 \ub4ef. \uc2e4\uc81c\ub85c \uc791\uc911\uc5d0\uc11c \uc9c8\uc8fc\ud558\ub294 \ud2b8\ub808\uc77c\ub7ec \ud2b8\ub7ed\uc744 \ub9e8\ubab8\uc73c\ub85c \ubc1b\uc544\ub0b4\uac70\ub098 \uc720\uc870\ucc28\uc758 \ud3ed\ubc1c \uc18d\uc5d0\uc11c\ub3c4 \ub208 \ud558\ub098 \uae5c\uc9dd\ud558\uc9c0 \uc54a\ub294 \ub4f1 \uacbd\uc545\uc2a4\ub7ec\uc6b4 \uc0dd\uba85\ub825\uc744 \ub9ce\uc774 \ubcf4\uc5ec\uc900\ub2e4.'), TIMESTAMP '2022-09-03 14:07:50.340219274', 0, TIMESTAMP '2022-09-03 14:07:50.340219274', STRINGDECODE('\uc2a4\ud2f0\uce58 \ubaa8\uc74c\uc9d1'), 1),
(148, STRINGDECODE('\ud584\uc2a4\ud130 \ubc081'), TIMESTAMP '2022-09-04 18:58:05.413397152', 0, TIMESTAMP '2022-09-04 18:58:05.413397152', 'whyMyMouseIsNotWorking', 1),
(230, STRINGDECODE('\uc774\uc6d0\uc8fc'), TIMESTAMP '2022-09-09 23:56:45.831008904', 0, TIMESTAMP '2022-09-09 23:56:45.831008904', STRINGDECODE('\uc57c'), 1);    
CREATE CACHED TABLE PUBLIC.BOARD_IMAGE(
    ID BIGINT NOT NULL,
    URL VARCHAR(255),
    BOARD_BOARD_ID BIGINT
);             
ALTER TABLE PUBLIC.BOARD_IMAGE ADD CONSTRAINT PUBLIC.CONSTRAINT_6 PRIMARY KEY(ID);             
-- 18 +/- SELECT COUNT(*) FROM PUBLIC.BOARD_IMAGE;             
INSERT INTO PUBLIC.BOARD_IMAGE(ID, URL, BOARD_BOARD_ID) VALUES
(3, STRINGDECODE('20220930200904_\uc124\ud604.jpg'), 2),
(5, STRINGDECODE('20220930200942_\uc288\ud654.jpg'), 4),
(6, STRINGDECODE('20220930200943_\uc5ec\uc790\uc544\uc774\ub4e4.jpg'), 4),
(8, STRINGDECODE('20221030201006_\ud504\ub85c\ubbf8\uc2a4\ub098\uc778.jpg'), 7),
(10, '20225905145928_9934CD33598AF94F21.jpeg', 9),
(11, '20225905145930_999035445FC1044D12.jpeg', 9),
(13, STRINGDECODE('20225905145957_\uac15\ubbfc\uacbd.jpg'), 12),
(15, '20220005150041_i13279845812.jpg', 14),
(17, '20224305154349_076f4066a9021abffe6b8f5e82692ecd_1552209880_1882.jpeg', 16),
(18, '20224305154351_bc64c3c93c3633c9.orig.jpg', 16),
(19, '20224305154352_17ae8bf4d985207ac.jpg', 16),
(20, STRINGDECODE('20224305154352_\uc544\uc774\uc720 \ub274\ubc1c\ub780\uc2a4 \uc0c8\uacf5\uac1c_005.jpg'), 16),
(143, STRINGDECODE('20224603104605_\ub2e4\ub78c\uc950.jpg'), 142),
(144, STRINGDECODE('20224603104606_\ub2e4\ub78c\uc9502.jpg'), 142),
(146, '20220703140747_20210730_232430.jpg', 145),
(147, '20220703140749_20220104_170154.jpg', 145),
(149, '20225804185803_ItisHamster.jpg', 148),
(231, STRINGDECODE('20225609235644_\ucea1\ucc98.PNG'), 230);              
CREATE CACHED TABLE PUBLIC.BOARD_LIKE(
    ID BIGINT NOT NULL,
    BOARD_ID BIGINT,
    MEMBER_ID BIGINT
);    
ALTER TABLE PUBLIC.BOARD_LIKE ADD CONSTRAINT PUBLIC.CONSTRAINT_8 PRIMARY KEY(ID);              
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.BOARD_LIKE;               
INSERT INTO PUBLIC.BOARD_LIKE(ID, BOARD_ID, MEMBER_ID) VALUES
(235, 148, 1),
(239, 9, 1);      
CREATE CACHED TABLE PUBLIC.MEMBER(
    MEMBER_ID BIGINT NOT NULL,
    ABOUT VARCHAR(255),
    EMAIL VARCHAR(255) NOT NULL,
    ENABLED BOOLEAN NOT NULL,
    IMAGE VARCHAR(255),
    JOB VARCHAR(255),
    NAME VARCHAR(20) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    ROLE VARCHAR(255),
    USERNAME VARCHAR(20) NOT NULL
);          
ALTER TABLE PUBLIC.MEMBER ADD CONSTRAINT PUBLIC.CONSTRAINT_87 PRIMARY KEY(MEMBER_ID);          
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.MEMBER;   
INSERT INTO PUBLIC.MEMBER(MEMBER_ID, ABOUT, EMAIL, ENABLED, IMAGE, JOB, NAME, PASSWORD, ROLE, USERNAME) VALUES
(1, STRINGDECODE('\uadc0\uc5ec\uc6b4  \uc2a4\ud2f0\uce58'), '1234@gmail.com', TRUE, '', STRINGDECODE('\ub180\uae30'), STRINGDECODE('\uc2a4\ud2f0\uce58'), '{bcrypt}$2a$12$40L6NBPiSnhYHI2JdKdKu.VgPbxYTJFNPb1TQnzzra06HzNeJaFee', 'ROLE_MEMBER', 'poby123'),
(2, '', 'test@email.com', TRUE, '', STRINGDECODE('\uc7a1'), STRINGDECODE('\uc774\ub984!'), 'password', 'ROLE_MEMBER', 'testname'); 
CREATE CACHED TABLE PUBLIC.MEMBER_FOLLOW(
    ID BIGINT NOT NULL,
    FOLLOW_ID BIGINT,
    MEMBER_ID BIGINT
);
ALTER TABLE PUBLIC.MEMBER_FOLLOW ADD CONSTRAINT PUBLIC.CONSTRAINT_E PRIMARY KEY(ID);           
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.MEMBER_FOLLOW;            
CREATE CACHED TABLE PUBLIC.COMMENT(
    COMMENT_ID BIGINT NOT NULL,
    CONTENT VARCHAR(100) NOT NULL,
    BOARD_BOARD_ID BIGINT,
    MEMBER_ID BIGINT,
    BOARD_ID BIGINT
); 
ALTER TABLE PUBLIC.COMMENT ADD CONSTRAINT PUBLIC.CONSTRAINT_63 PRIMARY KEY(COMMENT_ID);        
-- 38 +/- SELECT COUNT(*) FROM PUBLIC.COMMENT; 
INSERT INTO PUBLIC.COMMENT(COMMENT_ID, CONTENT, BOARD_BOARD_ID, MEMBER_ID, BOARD_ID) VALUES
(139, STRINGDECODE('2\ubc88 \uac8c\uc2dc\ubb3c\uc5d0 \ub300\ud55c \ub313\uae00 \ud14c\uc2a4\ud2b8 \uccab\ubc88\uc9f8!'), NULL, 1, 2),
(141, STRINGDECODE('2\ubc88 \uac8c\uc2dc\ubb3c\uc5d0 \ub300\ud55c \ub313\uae00 \ud14c\uc2a4\ud2b8 \uc138\ubc88\uc9f8!'), NULL, 1, 2),
(150, STRINGDECODE('\ub313\uae00 \ud14c\uc2a4\ud2b81'), NULL, 1, 142),
(151, STRINGDECODE('\uc5ec\uc790\uc544\uc774\ub4e4 \ub313\uae001'), NULL, 1, 4),
(152, STRINGDECODE('\uc5ec\uc790\uc544\uc774\ub4e4 \ub313\uae002'), NULL, 1, 4),
(153, STRINGDECODE('\uc88b\uc544\uc694 0\uac1c \uc704\uc5d0 \ub2e4\ub78c\uc950\ub294 \ubd81\uc544\uba54\ub9ac\uce74\uc640 \ub3d9\uc544\uc2dc\uc544 \ubd81\ub3d9\ubd80\uc5d0\uc11c \uc0ac\ub294 \uc124\uce58\ub958\uc758 \ud55c \uc885\ub958\uc774\ub2e4.'), NULL, 1, 142),
(154, STRINGDECODE('\uc5ec\uc790\uc544\uc774\ub4e4 \ub313\uae003'), NULL, 1, 4),
(155, STRINGDECODE('\uc5ec\uc790\uc544\uc774\ub4e4 \ub313\uae004'), NULL, 1, 4),
(156, STRINGDECODE('\uc5ec\uc790\uc544\uc774\ub4e4 \ub313\uae005'), NULL, 1, 4),
(157, STRINGDECODE('\uc5ec\uc790\uc544\uc774\ub4e4 \ub313\uae006'), NULL, 1, 4),
(158, STRINGDECODE('\uc5ec\uc790\uc544\uc774\ub4e4 \ub313\uae007'), NULL, 1, 4),
(159, STRINGDECODE('\uc124\ud604 \ub313\uae001'), NULL, 1, 2),
(160, STRINGDECODE('\uc124\ud604 \ub313\uae002'), NULL, 1, 2),
(161, STRINGDECODE('\uc124\ud604 \ub313\uae003'), NULL, 1, 2),
(162, STRINGDECODE('\uc124\ud604 \ub313\uae004'), NULL, 1, 2),
(163, STRINGDECODE('\uc124\ud604 \ub313\uae005'), NULL, 1, 2),
(164, STRINGDECODE('\uc124\ud604 \ub313\uae006'), NULL, 1, 2),
(165, STRINGDECODE('\uc124\ud604 \ud14c\uc2a4\ud2b8 \ub313\uae00'), NULL, 1, 2),
(166, STRINGDECODE('\uc544\uc774\uc720 \ub313\uae001'), NULL, 1, 9),
(167, STRINGDECODE('\uc544\uc774\uc720 \ub313\uae002'), NULL, 1, 9),
(168, STRINGDECODE('\uc544\uc774\uc720 \ub313\uae003'), NULL, 1, 9),
(169, STRINGDECODE('\uc544\uc774\uc720 \ub313\uae004'), NULL, 1, 9),
(170, STRINGDECODE('\uc544\uc774\uc720 \ub313\uae005'), NULL, 1, 9),
(171, STRINGDECODE('\uc544\uc774\uc720 \ub313\uae006'), NULL, 1, 9),
(172, STRINGDECODE('\uc544\uc774\uc720 \ub313\uae007'), NULL, 1, 9),
(173, STRINGDECODE('\ud14c\uc2a4\ud2b8 \ub313\uae00'), NULL, 1, 2),
(174, 'aadsfasdf', NULL, 1, 2),
(175, STRINGDECODE('\ud504\ub85c\ubbf8\uc2a4 \ub098\uc778 \ub2e8\uccb4\uc0ac\uc9c4!'), NULL, 1, 7),
(176, 'fromise9 comment1', NULL, 1, 7),
(177, 'color success', NULL, 1, 7),
(178, 'new comment', NULL, 1, 7),
(200, STRINGDECODE('\uc544\uc774\uc720 \ud654\ubcf4 \ubaa8\uc74c \ub313\uae001'), NULL, 1, 16),
(201, STRINGDECODE('\uc544\uc774\uc720 \ud654\ubcf4 \ubaa8\uc74c \ub313\uae002'), NULL, 1, 16),
(204, STRINGDECODE('\ud584\uc2a4\ud1301'), NULL, 1, 148),
(205, STRINGDECODE('\ud584\uc2a4\ud1302'), NULL, 1, 148),
(227, STRINGDECODE('\uae40\uc120\uc6b0 \ubc14\ubcf4'), NULL, 1, 145),
(228, STRINGDECODE('\uc7a0\ub9cc\ubcf4\ub294 \uc548\ub9cc\ub4e4\uc5b4\uc8fc\uc2dc\ub098\uc694?'), NULL, 1, 145),
(229, STRINGDECODE('\uc5b4\uca54'), NULL, 1, 145);             
ALTER TABLE PUBLIC.MEMBER ADD CONSTRAINT PUBLIC.UK_GC3JMN7C2ABYO3WF6SYLN5T2I UNIQUE(USERNAME); 
ALTER TABLE PUBLIC.BOARD_LIKE ADD CONSTRAINT PUBLIC.UKC9LA0M8KFC21JFW85QNUN4PN4 UNIQUE(MEMBER_ID, BOARD_ID);   
ALTER TABLE PUBLIC.COMMENT ADD CONSTRAINT PUBLIC.FKMRRRPI513SSU63I2783JYIV9M FOREIGN KEY(MEMBER_ID) REFERENCES PUBLIC.MEMBER(MEMBER_ID) NOCHECK;               
ALTER TABLE PUBLIC.BOARD_IMAGE ADD CONSTRAINT PUBLIC.FK7V630EOL4DNVOLWHXFFMR29G1 FOREIGN KEY(BOARD_BOARD_ID) REFERENCES PUBLIC.BOARD(BOARD_ID) NOCHECK;        
ALTER TABLE PUBLIC.BOARD_LIKE ADD CONSTRAINT PUBLIC.FKK7RXM8VL1PTQQHWDJ2SJMLPVQ FOREIGN KEY(BOARD_ID) REFERENCES PUBLIC.BOARD(BOARD_ID) NOCHECK;               
ALTER TABLE PUBLIC.COMMENT ADD CONSTRAINT PUBLIC.FK9TL7MC4O8S8A9O1IPXIPVPI22 FOREIGN KEY(BOARD_BOARD_ID) REFERENCES PUBLIC.BOARD(BOARD_ID) NOCHECK;            
ALTER TABLE PUBLIC.BOARD ADD CONSTRAINT PUBLIC.FKSDS8OX89WWF6AIHINAR49RMFY FOREIGN KEY(MEMBER_ID) REFERENCES PUBLIC.MEMBER(MEMBER_ID) NOCHECK; 
ALTER TABLE PUBLIC.COMMENT ADD CONSTRAINT PUBLIC.FKLIJ9OOR1NAV89JEAT35S6KBP1 FOREIGN KEY(BOARD_ID) REFERENCES PUBLIC.BOARD(BOARD_ID) NOCHECK;  
ALTER TABLE PUBLIC.BOARD_LIKE ADD CONSTRAINT PUBLIC.FKTIGEW3M6H54WECU5UDC0P3QIK FOREIGN KEY(MEMBER_ID) REFERENCES PUBLIC.MEMBER(MEMBER_ID) NOCHECK;            
ALTER TABLE PUBLIC.MEMBER_FOLLOW ADD CONSTRAINT PUBLIC.FKC9VUX75WRYPGT9EJ8YG0K6VYA FOREIGN KEY(MEMBER_ID) REFERENCES PUBLIC.MEMBER(MEMBER_ID) NOCHECK;         
ALTER TABLE PUBLIC.MEMBER_FOLLOW ADD CONSTRAINT PUBLIC.FK9ELQK855UGMT9JSL2TG0T7BV4 FOREIGN KEY(FOLLOW_ID) REFERENCES PUBLIC.MEMBER(MEMBER_ID) NOCHECK;         
