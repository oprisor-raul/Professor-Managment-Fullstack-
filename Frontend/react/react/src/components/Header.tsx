import { Navbar } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faChalkboardUser } from '@fortawesome/free-solid-svg-icons';
import { Link } from 'react-router-dom';

function Header() {
  return (
    <Navbar expand="lg" >
      <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', width: '100%', padding: '10px 15px'}}>
        <div style={{ display: 'flex', alignItems: 'center' }}>
          <Link to="/" className="navbar-brand">
            <FontAwesomeIcon icon={faChalkboardUser} />
            {' '}
          </Link>
        </div>
        <div>
          <Link to="/sign-up" className="btn btn-primary">Sign Up</Link>
        </div>
      </div>
    </Navbar>
  );
}

export default Header;
