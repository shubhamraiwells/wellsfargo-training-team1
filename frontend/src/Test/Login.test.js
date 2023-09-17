// Login.test.js
import React from 'react';
import { render, screen } from '@testing-library/react';
import Login from '../pages/Login'; // Adjust the path as needed

test('renders login page', () => {
  render(<Login />);
  const linkElement = screen.getByText(/Sign In/i);
  expect(linkElement).toBeInTheDocument();
});
