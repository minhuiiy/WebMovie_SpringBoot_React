import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import Login from "./pages/Login";
import MovieList from "./pages/MovieList";
import MovieDetail from "./pages/MovieDetail";
import Register from "./pages/Register";
import ProtectedRoute from "./components/ProtectedRoute";
import Favorites from "./pages/Favorites";
export default function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/movies" element={<MovieList />} />
        <Route path="/movies/:id" element={<MovieDetail />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />

        <Route
          path="/favorites"
          element={
            <ProtectedRoute>
              <Favorites />
            </ProtectedRoute>
          }
        /> 
      </Routes>
    </BrowserRouter>
  );
}