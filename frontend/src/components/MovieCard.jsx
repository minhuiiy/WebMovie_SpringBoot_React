import { Link } from "react-router-dom";

export default function MovieCard({ m }) {
    const img = m.poster_path ? `https://image.tmdb.org/t/p/w300${m.poster_path}` : "";
    return (
    <div style={{ border:"1px solid #eee", borderRadius:8, padding:10 }}>
        <Link to={`/movie/${m.id}`} style={{ textDecoration:"none", color:"inherit" }}>
            {img && <img src={img} alt={m.title} style={{ width:"100%", borderRadius:8 }} />}
            <div style={{ fontWeight:600, marginTop:8 }}>{m.title}</div>
            <div style={{ opacity:0.7, fontSize:13 }}>⭐ {m.vote_average}</div>
        </Link>
    </div>
    );
}