document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('#add-course-form');
    form.addEventListener('submit', (event) => {
      event.preventDefault(); // prevent default form submission behavior
      const professorID = document.querySelector('#professor-id').value;
      const dayOfWeek = document.querySelector('#day-of-week').value.toUpperCase();
      const localTime = document.querySelector('#local-time').value;
      const name = document.querySelector('#name').value;
      const data = {
        professorID: professorID,
        dayOfWeek: dayOfWeek,
        localTime: localTime,
        name: name
      };
  
      const options = {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
          'Content-Type': 'application/json'
        }
      };
  
      fetch(`http://localhost:8080/api/course/${professorID}/professor`, options)
        .then(response => response.json())
        .then(data => {
          console.log(data);
          window.location.href = 'display-course.html'; 
        })
        .catch(error => console.error(error));
    });
  });
  