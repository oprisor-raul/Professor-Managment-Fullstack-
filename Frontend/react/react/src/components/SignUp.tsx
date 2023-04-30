import React, { useState } from 'react';
import { Form, Button, Container, Modal } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

function SignUp() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const navigate = useNavigate();
  const [showModal, setShowModal] = useState(false);

  const handleCloseModal = () => setShowModal(false);

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const data = {
      name: name,
      email: email
    };

    const options = {
      method: 'POST',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
      }
    };

    try {
      const response = await fetch('http://localhost:8080/api/professor', options);

      if (response.status === 409) {
        setShowModal(true);
        return;
      }

      const result = await response.json();
      console.log(result);
      navigate('/add-course');
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <Container className="d-flex align-items-center justify-content-center" style={{ minHeight: '100vh', backgroundColor: '#f5f5f5' }}>
        <Modal show={showModal} onHide={handleCloseModal}>
          <Modal.Header closeButton>
            <Modal.Title>Error</Modal.Title>
          </Modal.Header>
          <Modal.Body>Email is already in use. Please use a different email address.</Modal.Body>
          <Modal.Footer>
            <Button variant="secondary" onClick={handleCloseModal}>
              Close
            </Button>
          </Modal.Footer>
        </Modal>

        <Form onSubmit={handleSubmit} style={{
          width: '80%',
          maxWidth: '400px',
          padding: '20px',
          backgroundColor: '#ffffff',
          borderRadius: '10px',
          boxShadow: '0px 2px 10px rgba(0, 0, 0, 0.3)',
        }}>
          <h1 className="text-center mb-4">Professor Sign-Up</h1>
          <Form.Group controlId="name">
            <Form.Label>Name</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter your name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            />
          </Form.Group>
          <Form.Group controlId="email">
            <Form.Label>Email</Form.Label>
            <Form.Control
              type="email"
              placeholder="Enter your email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </Form.Group>
          <div className="d-flex justify-content-center" style={{ marginTop: '20px' }}>
            <Button variant="primary" type="submit">
              Sign Up
            </Button>
          </div>
        </Form>
      </Container>
    </div>
  );
}

export default SignUp;
