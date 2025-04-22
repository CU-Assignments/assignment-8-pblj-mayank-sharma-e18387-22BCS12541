<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Student Attendance Form</title>
</head>
<body>
    <h2>Student Attendance Form</h2>
    <form action="saveAttendance" method="post">
        Student ID: <input type="number" name="student_id" required><br><br>
        Date: <input type="date" name="date" required><br><br>
        Status:
        <select name="status" required>
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br><br>
        <input type="submit" value="Submit Attendance">
    </form>
</body>
</html>
