import { Card } from 'react-bootstrap';
import { Link } from 'react-router-dom';

function CourseCards() {
    return (
        <div className="d-flex justify-content-center align-items-center flex-wrap" style={{ backgroundColor: '#f5f5f5' }}>
        <Card className="m-3 p-5" style={{ boxShadow: "0px 2px 10px rgba(0,0,0,0.3)", width: "40%" }}>
          <Card.Body>
            <Card.Title className="text-center mb-4" style={{ fontSize: "3rem" }}>My Courses</Card.Title>
            <Card.Text className="text-center mb-4" style={{ fontSize: "2rem" }}>
              View all of your courses here.
            </Card.Text>
            <div className="d-flex justify-content-center">
              <Link to="/view-courses" className="btn btn-primary" style={{ fontSize: "2rem" }}>View Courses</Link>
            </div>
          </Card.Body>
        </Card>
  
  
        <Card className="m-3 p-5" style={{ boxShadow: "0px 2px 10px rgba(0,0,0,0.3)", width: "40%" }}>
          <Card.Body>
            <Card.Title className="text-center mb-4" style={{ fontSize: "3rem" }}>Add Course</Card.Title>
            <Card.Text className="text-center mb-4" style={{ fontSize: "2rem" }}>
              Add a new course.
            </Card.Text>
            <div className="d-flex justify-content-center">
            <Link to="/add-course" className="btn btn-primary" style={{ fontSize: "2rem" }}>Add Course</Link>
            </div>
          </Card.Body>
        </Card>
  
        <Card className="m-3 p-5" style={{ boxShadow: "0px 2px 10px rgba(0,0,0,0.3)", width: "82%" }}>
          <Card.Body>
            <Card.Title className="text-center mb-4" style={{ fontSize: "3rem" }}>Sign Up</Card.Title>
            <Card.Text className="text-center mb-4" style={{ fontSize: "2rem" }}>
              Sign up today and get started with our courses.
            </Card.Text>
            <div className="d-flex justify-content-center">
            <Link to="/sign-up" className="btn btn-primary" style={{ fontSize: "2rem" }}>Sign Up</Link>
            </div>
          </Card.Body>
        </Card>
      </div>
    );
  }
  
  export default CourseCards;
  