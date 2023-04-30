import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Header from "./components/Header";
import CourseCards from "./components/CourseCards";
import ViewCourses from "./components/ViewCourses";
import AddCourse from "./components/AddCourse";
import SignUp from "./components/SignUp";
import ChangeTitle from "./components/Title"; // Import your new component

function App() {
  return (
    <Router>
      <>
        <Header />
        <Routes>
          <Route
            path="/"
            element={
              <>
                <ChangeTitle newTitle="Home" />
                <CourseCards />
              </>
            }
          />
          <Route
            path="/view-courses"
            element={
              <>
                <ChangeTitle newTitle="View Courses" />
                <ViewCourses />
              </>
            }
          />
          <Route
            path="/add-course"
            element={
              <>
                <ChangeTitle newTitle="Add Course" />
                <AddCourse />
              </>
            }
          />
          <Route
            path="/sign-up"
            element={
              <>
                <ChangeTitle newTitle="Sign Up" />
                <SignUp />
              </>
            }
          />
        </Routes>
      </>
    </Router>
  );
}

export default App;
