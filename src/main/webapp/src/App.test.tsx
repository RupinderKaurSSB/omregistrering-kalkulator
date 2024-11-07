import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './App';

test('viser Skatteetaten 2022', () => {
  render(<App />);
  const linkElement = screen.getByText(/Skatteetaten 2022/i);
  expect(linkElement).toBeInTheDocument();
});
