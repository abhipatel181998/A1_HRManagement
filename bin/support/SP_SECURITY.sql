--Creating package.
--Package Specs.
CREATE OR REPLACE PACKAGE p_security AS
    TYPE cur_empinfo IS REF CURSOR;
    FUNCTION f_security (
        p_secid       IN security.sec_id%TYPE,
        p_secpassword IN security.sec_password%TYPE
    ) RETURN NUMBER;

    PROCEDURE p_emp_info (
        p_employeeid IN employees.employee_id%TYPE,
        p_info       OUT cur_empinfo
    );

END p_security;
/

--Package Body.
CREATE OR REPLACE PACKAGE BODY p_security AS

    FUNCTION f_security (
        p_secid       IN security.sec_id%TYPE,
        p_secpassword IN security.sec_password%TYPE
    ) RETURN NUMBER AS

        v_secid       security.sec_id%TYPE := p_secid;
        v_secpassword security.sec_password%TYPE := p_secpassword;
        v_employee_id employees.employee_id%TYPE;
        
    BEGIN
        SELECT
            employee_id
        INTO v_employee_id
        FROM
            employees JOIN security USING (employee_id)
        WHERE
            sec_id = v_secid
            AND sec_password = v_secpassword
            AND sec_status = 'A';

        RETURN v_employee_id;
        
    EXCEPTION
        WHEN no_data_found THEN
            RETURN 0;
            
    END f_security;
    
    
    
    PROCEDURE p_emp_info (
        p_employeeid IN employees.employee_id%TYPE,
        p_info       OUT cur_empinfo
    ) AS
    
    v_employeeid employees.employee_id%TYPE := p_employeeid;
    
    BEGIN
        OPEN p_info FOR
        SELECT * FROM employees WHERE employee_id = v_employeeid;
        
    END p_emp_info;

END p_security;