import React from "react";
import ReactDOM from "react-dom/client";

import App from "./App";
import StartRound from "./StartRound"
import "./index.css";


const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
      {/*<Prompt/>*/}
      {/*  <App />*/}
  <StartRound />
  </React.StrictMode>
);
