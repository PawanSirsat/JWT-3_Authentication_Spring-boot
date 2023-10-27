import React, { useState } from 'react'
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from 'react-router-dom'

import HomePage from './Components/HomePage/HomePage'
import Navigation from './Components/Navbar/Navigation'
import LoginPage from './Components/User/LoginPage'
import Signup from './Components/User/Signup'
import UserListPage from './Components/User/UserListPage'

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false)

  // Function to set isAuthenticated to true upon successful login
  const handleSuccessfulLogin = () => {
    setIsAuthenticated(true)
  }
  return (
    <Router>
      <div className='App'>
        <Navigation isAuthenticated={isAuthenticated} />
        <Routes>
          <Route
            path='/login'
            element={<LoginPage setIsAuthenticated={handleSuccessfulLogin} />}
          />
          <Route path='/home' element={<HomePage />} />
          <Route path='/signup' element={<Signup />} />
          <Route path='/userlist' element={<UserListPage />} />
        </Routes>
      </div>
    </Router>
  )
}

export default App
