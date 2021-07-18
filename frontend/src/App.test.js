import { render, screen } from '@testing-library/react';
import App from './App';
import React from 'react';

test('renders "Create a new test button"', () => {
  render(<App />);
  const linkElement = screen.getByText(/create a new test/i);
  expect(linkElement).toBeInTheDocument();
});
