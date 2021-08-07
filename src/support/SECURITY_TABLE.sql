--Creating security table

CREATE TABLE security 
(
    employee_id NUMBER(6,0),
    sec_id VARCHAR2(20) PRIMARY KEY,
    sec_password VARCHAR(10) NOT NULL,
    sec_status CHAR(1) CHECK (sec_status IN ('I','A')) NOT NULL, --A:Active, I:Inactive 
    FOREIGN KEY(employee_id) REFERENCES employees(employee_id)
        ON DELETE CASCADE
);


--Inserting rows into the security table.

INSERT INTO security VALUES(201,'michael','michael123','A');
INSERT INTO security VALUES(202,'pat','pat123','A');
INSERT INTO security VALUES(203,'susan','susan123','A');
INSERT INTO security VALUES(204,'hermann','hermann123','I');
INSERT INTO security VALUES(205,'shelley','shelley123','A');

-- Commit.
COMMIT;