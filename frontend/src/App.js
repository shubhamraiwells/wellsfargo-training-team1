import logo from './logo.svg';
import './App.css';
import { Link, Route, Routes } from 'react-router-dom';
import About from './pages/About';
import Team from './pages/Team';
function App() {
  return (
    <div className="App">
      <header>
        <>
          <nav>
            <ul>
              <li><Link to="/About">About</Link></li>
              <li><Link to="/Team">Team</Link></li>
            </ul>
          </nav>
          <Routes>
            <Route path='/About' element={<About />} />
            <Route path='/Team' element={<Team />} />
          </Routes>
        </>
      </header>
    </div>
  );
}

export default App;
