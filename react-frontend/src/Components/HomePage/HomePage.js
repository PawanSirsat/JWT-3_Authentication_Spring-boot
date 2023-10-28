import React, { useState, useEffect } from 'react'
import axios from 'axios'
import { Link, useNavigate } from 'react-router-dom'

const HomePage = () => {
  const [userData, setUserData] = useState(null)
  const navigate = useNavigate()

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const token = localStorage.getItem('jwtToken')
        if (token) {
          const response = await axios.get(
            'http://localhost:8080/home/current-user',
            {
              headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${token}`,
              },
            }
          )
          if (response.status === 200) {
            setUserData(response.data)
          } else {
            console.log('200')
          }
        } else {
          console.log('no tokken')
          navigate('/login')
        }
      } catch (error) {
        console.error('Failed to fetch user data:', error.toJSON().message)
        // Handle errors, e.g., token expiration
      }
    }

    fetchUserData()
  }, [])

  return (
    <div>
      <h2>Home Page</h2>
      <p>Welcome to the protected home page!</p>
      <p>User : {userData}</p> {/* Display userData object directly */}
    </div>
  )
}

export default HomePage
