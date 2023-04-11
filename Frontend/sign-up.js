document.addEventListener('DOMContentLoaded', () => {
  const form = document.querySelector('#signup-form');
  form.addEventListener('submit', (event) => {
    event.preventDefault(); // prevent default form submission behavior
    const name = document.querySelector('#name').value;
    const email = document.querySelector('#email').value;
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
    fetch('http://localhost:8080/api/professor', options)
      .then(response => response.json())
      .then(data => {
        console.log(data);
        window.location.href = 'add-course.html'; 
      })
      .catch(error => console.error(error));
  });
});
