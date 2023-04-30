import React, { useState } from 'react';
import { Form, Button, Container } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

function AddCourse() {
  const navigate = useNavigate();
  const [professorId, setProfessorId] = useState('');
  const [dayOfWeek, setDayOfWeek] = useState('');
  const [localTime, setLocalTime] = useState('');
  const [courseName, setCourseName] = useState('');

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const data = {
      professorID: professorId,
      dayOfWeek: dayOfWeek.toUpperCase(),
      localTime: localTime,
      name: courseName,
    };

    const options = {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json',
      },
    };

    try {
      const response = await fetch(
        `http://localhost:8080/api/course/${professorId}/professor`,
        options
      );
      const responseData = await response.json();
      console.log(responseData);

      // After successful submission, redirect to the ViewCourses component
      navigate('/view-courses');
    } catch (error) {
      console.error(error);
    }
  };

  
  return (
    <Container className="d-flex align-items-center justify-content-center" style={{ minHeight: '100vh', backgroundColor: '#f5f5f5' }}>
      <Form onSubmit={handleSubmit} style={{
        width: '80%',
        maxWidth: '800px',
        padding: '40px',
        backgroundColor: '#ffffff',
        borderRadius: '10px',
        boxShadow: '0px 2px 10px rgba(0, 0, 0, 0.3)',
      }}>
        <h1 className="text-center mb-4">Add Course</h1>
        <Form.Group controlId="professorId">
          <Form.Label>Professor ID</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter Professor ID"
            value={professorId}
            onChange={(e) => setProfessorId(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group controlId="dayOfWeek">
          <Form.Label>Day of the Week</Form.Label>
          <Form.Control
            as="select"
            value={dayOfWeek}
            onChange={(e) => setDayOfWeek(e.target.value)}
            required
          >
            <option value="" disabled>
              Select a day of the week
            </option>
            <option value="Monday">Monday</option>
            <option value="Tuesday">Tuesday</option>
            <option value="Wednesday">Wednesday</option>
            <option value="Thursday">Thursday</option>
            <option value="Friday">Friday</option>
            <option value="Saturday">Saturday</option>
            <option value="Sunday">Sunday</option>
          </Form.Control>
        </Form.Group>
        <Form.Group controlId="localTime">
          <Form.Label>Local Time</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter local time"
            value={localTime}
            onChange={(e) => setLocalTime(e.target.value)}
            required
          />
        </Form.Group>
        <Form.Group controlId="courseName">
          <Form.Label>Course Name</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter Course Name"
            value={courseName}
            onChange={(e) => setCourseName(e.target.value)}
            required
          />
        </Form.Group>
        <div className="d-flex justify-content-center mt-4">
        <Button variant="primary" type="submit">
            Add Course
          </Button>
        </div>
      </Form>
    </Container>
  );
}

export default AddCourse;
