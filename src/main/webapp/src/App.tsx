import React from 'react';
import logo from './skatteetaten-logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <div className={"App-header-wrapper"}>
          <img src={logo} className="App-logo" alt="logo"/>
          <h2 style={{ display: "inline-block" }}>Omregistrering-kalkulator</h2>
          <hr />
        </div>
      </header>
      <main>
        {/* TODO */}
      </main>
      <footer>Skatteetaten 2023</footer>
    </div>
  );
}

export default App;
