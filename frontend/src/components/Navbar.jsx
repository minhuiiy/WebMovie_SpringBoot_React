import { Link, useNavigate } from "react-router-dom";

export default function Navbar() {
    const nav = useNavigate();
    const token = localStorage.getItem("accessToken");

    const logout = () => {
        localStorage.removeItem("accessToken");
        nav("/login");
    };

    return (
        <div style={{ display:"flex", gap:12, padding:12, borderBottom: "1px solid #ddd"}}>
            <Link to="/">Home</Link>
            <Link to="/movies">Popular</Link>
            {token && <Link to="/favorites">Favorites</Link>}
            <div style={{ marginLeft:"auto", display:"flex", gap:12 }}>
                {!token ? (
                    <>
                        <Link to="/login">Login</Link>
                        <Link to="/register">Register</Link>
                    </>
                ) : (
                    <button onClick={logout}>Logout</button>
                )}
            </div>
        </div> 
    )
}