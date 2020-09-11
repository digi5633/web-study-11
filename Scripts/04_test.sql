SELECT NAME, USERID, PWD, EMAIL, PHONE, ADMIN, JOINDATE FROM MEMBER;

INSERT INTO MEMBER(NAME, USERID, PWD, EMAIL, PHONE, ADMIN) VALUES ('박규영', 'parkgy', '1234', 'pgy@naver.com', '010-1111-2222', 0);

-- login
SELECT NAME, USERID, EMAIL, PHONE, ADMIN, JOINDATE FROM MEMBER WHERE USERID = 'somi' AND pwd = '1234';

UPDATE MEMBER
   SET NAME = '문채원', PWD ='5678', EMAIL='mcw@gmail.com',
       PHONE='010-3333-5555', ADMIN=1, JOINDATE='2020-08-20' WHERE USERID = 'parkgy';
 
DELETE FROM MEMBER WHERE USERID = 'parkgy';

-- usercheck
SELECT PWD FROM MEMBER WHERE USERID = 'somi';

-- title
SELECT TITLE_NO, TITLE_NAME FROM TITLE ORDER BY TITLE_NO;

SELECT MAX(TITLE_NO)+1 FROM TITLE;

-- dept
SELECT DEPT_NO, DEPT_NAME, FLOOR FROM DEPARTMENT ORDER BY DEPT_NO;

INSERT INTO DEPARTMENT VALUES(5, '인사', 5);

UPDATE DEPARTMENT SET DEPT_NAME = '운영', FLOOR = 6 WHERE DEPT_NO = 5;

DELETE FROM DEPARTMENT WHERE DEPT_NO = 5;

SELECT MAX(DEPT_NO)+1 FROM DEPARTMENT;

SELECT DEPT_NO, DEPT_NAME, FLOOR FROM DEPARTMENT WHERE DEPT_NO = 1;

-- emp
SELECT * FROM EMPLOYEE;

CREATE OR REPLACE VIEW VW_EMPLOYEE_JOIN AS 
SELECT e.EMP_NO, e.EMP_NAME, e.TNO, e.MANAGER , e.SALARY, e.DNO, e.REGDATE, e.EMAIL , e.TEL , e.PIC_URL, 
       t.TITLE_NAME, d.DEPT_NAME, m.EMP_NAME MANAGER_NAME
  FROM EMPLOYEE e JOIN TITLE t ON e.TNO = t.TITLE_NO 
       LEFT JOIN EMPLOYEE m ON e.MANAGER = m.EMP_NO 
       JOIN DEPARTMENT d ON e.DNO = d.DEPT_NO;
      
SELECT EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, REGDATE, 
       EMAIL, TEL, PIC_URL, TITLE_NAME, DEPT_NAME, MANAGER_NAME 
  FROM VW_EMPLOYEE_JOIN;
 
SELECT *
 FROM EMPLOYEE;

SELECT *
  FROM VW_EMPLOYEE_JOIN;

INSERT INTO EMPLOYEE(EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, EMAIL, PASSWD, TEL)
VALUES (1004, '김자바', 5, 4377, 3500000, 1, 'test7@test.co.kr', '1234', '010-1234-1234');

DELETE FROM EMPLOYEE WHERE EMP_NO = 1004;

UPDATE EMPLOYEE SET EMP_NAME = , TNO = , MANAGER = , SALARY = , DNO = , EMAIL = , PASSWD = , TEL = WHERE EMP_NO = ;

SELECT EMP_NO, EMP_NAME, TNO, MANAGER, SALARY, DNO, EMAIL, REGDATE, TEL, TITLE_NAME, DEPT_NAME, MANAGER_NAME FROM VW_EMPLOYEE_JOIN WHERE EMP_NO = 1004;

---------------------------------------------
-----------------
------------------------

CREATE TABLE tri_test (
	NO NUMBER PRIMARY KEY,
	TXT VARCHAR2(20)
);

CREATE SEQUENCE tri_test_seq
	START WITH 1
	INCREMENT BY 1;

-- 트리거를 이용하여 자동으로 번호가 입력되도록
CREATE OR REPLACE TRIGGER TRI_TRI_TEST_AUTOSEQ
BEFORE INSERT ON tri_test
FOR EACH ROW
BEGIN
	IF inserting AND :NEW.NO IS NULL THEN
		SELECT tri_test_seq.NEXTVAL INTO :NEW.NO FROM DUAL;
	END IF;
END;

INSERT INTO tri_test VALUES(NULL, 'test');

SELECT *
  FROM tri_test;
 
INSERT INTO tri_test(txt) VALUES('test2');

--------------------------------------------------------------

SELECT CODE, NAME, PRICE, PICTUREURL, DESCRIPTION FROM PRODUCT;

SELECT CODE, NAME, PRICE, PICTUREURL, DESCRIPTION FROM PRODUCT WHERE CODE = 1;

INSERT INTO PRODUCT(NAME, PRICE, PICTUREURL, DESCRIPTION) VALUES ('개념', 27000, 'aa.jpg', 'aaaa');

DELETE FROM PRODUCT WHERE CODE = 10;

UPDATE PRODUCT SET NAME = '개념2', PRICE = 30000, PICTUREURL = 'bb.jpg', DESCRIPTION = 'bbbb' WHERE CODE = 7;