import React, { useState, useEffect } from 'react'
import axios from 'axios'

const HomePage = () => {
  const [userData, setUserData] = useState('')

  useEffect(() => {
    // Fetch user data from the protected API endpoint
    axios
      .get('http://localhost:8080/home/current-user', {
        headers: {
          Authorization: `Bearer ${localStorage.getItem('jwtToken')}`,
        },
      })
      .then((response) => {
        setUserData(response.data) // Set the response data as is
      })
      .catch((error) => {
        console.error('Failed to fetch user data:', error)
        // Handle errors, e.g., token expiration
      })
  }, [])

  return (
    <div>
      <h2>Home Page</h2>
      <p>Welcome to the protected home page!</p>
      <p>User Data: {userData}</p> {/* Display userData as a string */}
    </div>
  )
}

export default HomePage
