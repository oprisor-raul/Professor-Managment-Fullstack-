import React, { useState } from "react";
import { Container, Form, Table, Button, Modal } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrashAlt, faEdit } from "@fortawesome/free-solid-svg-icons";
import axios from "axios";

const ViewCourses: React.FC = () => {
  const [professorID, setProfessorID] = useState("");
  const [courses, setCourses] = useState<any[]>([]);
  const [showTable, setShowTable] = useState(false);
  const [setShowErrorModal] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [selectedCourse, setSelectedCourse] = useState<any>(null);
  const [selectedDayOfWeek, setSelectedDayOfWeek] = useState("");
  const [selectedLocalTime, setSelectedLocalTime] = useState("");
  const [selectedCourseName, setSelectedCourseName] = useState("");

  // const handleCloseErrorModal = () => setShowErrorModal(false);
  const handleOpenEditModal = (course: any) => {
    setSelectedCourse(course);
    setSelectedDayOfWeek(course.dayOfWeek);
    setSelectedLocalTime(course.localTime);
    setSelectedCourseName(course.name);
    setShowEditModal(true);
  };

  const handleCloseEditModal = () => {
    setSelectedCourse(null);
    setShowEditModal(false);
  };

  const updateCourse = async (updatedCourse: any) => {
    updatedCourse.dayOfWeek = selectedDayOfWeek;
    updatedCourse.localTime = selectedLocalTime;
    updatedCourse.name = selectedCourseName;

    try {
      const response = await axios.put(
        `http://localhost:8080/api/course/${updatedCourse.id}`,
        updatedCourse
      );
      if (response.status === 200) {
        // Fetch the updated courses
        fetchCourses(professorID);
      }
    } catch (error) {
      console.error("Error updating course:", error);
    }
  };

  const fetchCourses = async (id: string) => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/course/${id}/professor`
      );

      if (response.status === 404) {
        setShowErrorModal(true);
        return;
      }

      const data = await response.json();
      setCourses(data);
      setShowTable(true);
    } catch (error) {
      console.error(error);
    }
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    fetchCourses(professorID);
  };

  const deleteCourse = async (courseId: number) => {
    try {
      const response = await axios.delete(
        `http://localhost:8080/api/course/${courseId}`
      );
      if (response.status === 200) {
        // Remove the deleted course from the state
        setCourses(courses.filter((course) => course.id !== courseId));
      }
    } catch (error) {
      console.error("Error deleting course:", error);
    }
  };

  return (
    <Container style={{ minHeight: "100vh", backgroundColor: "#f5f5f5" }}>
      <Modal show={showEditModal} onHide={handleCloseEditModal}>
        <Modal.Header closeButton>
          <Modal.Title>Edit Course</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {selectedCourse && (
            <Form
              id="editCourseForm" // Add the ID here
              onSubmit={(event) => {
                event.preventDefault();
                updateCourse(selectedCourse);
                handleCloseEditModal();
              }}
            >
              <Form.Group controlId="dayOfWeek">
                <Form.Label>Day of the Week</Form.Label>
                <Form.Control
                  as="select"
                  value={selectedDayOfWeek}
                  onChange={(e) => setSelectedDayOfWeek(e.target.value)}
                  required
                >
                  <option value="MONDAY">Monday</option>
                  <option value="TUESDAY">Tuesday</option>
                  <option value="WEDNESDAY">Wednesday</option>
                  <option value="THURSDAY">Thursday</option>
                  <option value="FRIDAY">Friday</option>
                  <option value="SATURDAY">Saturday</option>
                  <option value="SUNDAY">Sunday</option>
                </Form.Control>
              </Form.Group>
              <Form.Group controlId="localTime">
                <Form.Label>Local Time</Form.Label>
                <Form.Control
                  type="text"
                  value={selectedLocalTime}
                  onChange={(e) => setSelectedLocalTime(e.target.value)}
                  required
                />
              </Form.Group>
              <Form.Group controlId="courseName">
                <Form.Label>Course Name</Form.Label>
                <Form.Control
                  type="text"
                  value={selectedCourseName}
                  onChange={(e) => setSelectedCourseName(e.target.value)}
                  required
                />
              </Form.Group>
            </Form>
          )}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseEditModal}>
            Close
          </Button>
          <Button variant="primary" type="submit" form="editCourseForm">
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>

      {!showTable ? (
        <div
          style={{
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            minHeight: "100vh",
          }}
        >
          <div
            style={{
              maxWidth: "600px",
              padding: "20px",
              backgroundColor: "#ffffff",
              borderRadius: "10px",
              boxShadow: "0px 2px 10px rgba(0, 0, 0, 0.3)",
              margin: "50px auto",
            }}
          >
            <Form onSubmit={handleSubmit} className="w-100">
              <h1 className="text-center mb-4">View Courses</h1>
              <Form.Group controlId="professorID">
                <Form.Label>
                  Enter your Professor ID to view your courses:
                </Form.Label>
                <Form.Control
                  type="text"
                  value={professorID}
                  onChange={(e) => setProfessorID(e.target.value)}
                  required
                />
              </Form.Group>
              <div
                className="d-flex justify-content-center"
                style={{ marginTop: "20px" }}
              >
                <Button variant="primary" type="submit">
                  View Courses
                </Button>
              </div>
            </Form>
          </div>
        </div>
      ) : (
        <>
          {courses.length > 0 && (
            <Table striped bordered hover>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Day of Week</th>
                  <th>Local Time</th>
                  <th>Name</th>
                  <th>Action</th>{" "}
                  {/* Add a new column header for the delete button */}
                </tr>
              </thead>
              <tbody>
                {courses.map((course) => (
                  <tr key={course.id}>
                    <td>{course.id}</td>
                    <td>{course.dayOfWeek}</td>
                    <td>{course.localTime}</td>
                    <td>{course.name}</td>
                    <td>
                      <Button
                        variant="danger"
                        onClick={() => deleteCourse(course.id)}
                        className="mr-2"
                      >
                        <FontAwesomeIcon icon={faTrashAlt} />
                      </Button>
                      <Button
                        variant="info"
                        onClick={() => handleOpenEditModal(course)}
                      >
                        <FontAwesomeIcon icon={faEdit} />
                      </Button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          )}
        </>
      )}
    </Container>
  );
};

export default ViewCourses;
