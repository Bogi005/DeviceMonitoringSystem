import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import PageController from "./components/PageController.jsx";

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <PageController/>
  </StrictMode>,
)
