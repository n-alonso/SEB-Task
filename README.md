# SEB - Technical Task

## Description

I went with a plan Java approach with no API design, it would work more like a tool that can be executed from the command line or with automation.  

## Further Improvements
Could include:
- Read input from the command line arguments (file name, file path, data source config, etc)
- If appropriate it can be refactored as an API where files can be uploaded or the JSON payload sent directly
The current approach was chosen due to time constraints.

## Testing

Due to time constraints no tests were made.

## Usage

1. You can spin a local testing database with Docker:
```
docker run --name test-postgres -e POSTGRES_USER=testuser -e POSTGRES_PASSWORD=testpass -e POSTGRES_DB=testdb -p 5432:5432 -d postgres
```
2. Connect to it with your tool of choice (Ie. psql, dbeaver, etc) and create the necessary tables:
```
CREATE TABLE departments ( department_id SERIAL PRIMARY KEY, department_name VARCHAR(255) NOT NULL ); 
```
And:
```
CREATE TABLE employees ( employee_id SERIAL PRIMARY KEY, name VARCHAR(255) NOT NULL, department_id INT REFERENCES departments(department_id) );
```
(If you create them both at the same time or in the wrong order you will hit a constraint violation on the `department_id`).
3. Place the file in the root directory with the name `data.json`.
4. Run the app.

## SQL View

A SQL View is also required which can be obtained running the following script using the same tool as before (Ie. psql, dbeaver, etc):
```
CREATE VIEW EmployeeDepartments AS
SELECT 
    departments.department_id,
    departments.department_name,
    employees.employee_id,
    employees.name AS employee_name
FROM 
    employees
RIGHT JOIN 
    departments ON employees.department_id = departments.department_id;
```