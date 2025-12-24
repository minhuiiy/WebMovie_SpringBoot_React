import { useEffect, useMemo, useState } from "react";
import { useParams } from "react-router-dom";
import { iphimApi } from "../api/iphim";
import { favoritesApi } from "../api/favorites";
import { commentsApi } from "../api/comments";

export default function MovieDetail() {
    const { id } = useParams();
    const movieId = Number(id);

    const [movie, setMovie] = useState(null);
    const [videos, setVideos] = useState(null);
    const [comments, setComments] = useState([]);
    const [content, setContent] = useState("");

    const token = localStorage.getItem("accessToken");

    const trailerKey = useMemo(() => {
    const list = videos?.results || [];
    const yt = list.find(v => v.site === "YouTube" && (v.type === "Trailer" || v.type === "Teaser"));
    return yt?.key;
    }, [videos]);

    const loadComments = () => {
    commentsApi.list(movieId).then(res => setComments(res.data)).catch(console.error);
    };

    useEffect(() => {
    moviesApi.detail(movieId).then(res => setMovie(res.data)).catch(console.error);
    moviesApi.videos(movieId).then(res => setVideos(res.data)).catch(console.error);
    loadComments();
    }, [movieId]);

    const addFav = async () => {
    try {
        await favoritesApi.add(movieId);
        alert("Added to favorites");
    } catch (e) {
        alert("Need login or error");
    }
    };

    const postComment = async () => {
    if (!token) return alert("Login to comment");
    if (!content.trim()) return;

    await commentsApi.add(movieId, { content });
    setContent("");
    loadComments();
    };

    if (!movie) return <div style={{ padding:16 }}>Loading...</div>;

    const img = movie.poster_path ? `https://image.tmdb.org/t/p/w400${movie.poster_path}` : "";

    return (
    <div style={{ padding:16, display:"grid", gridTemplateColumns:"300px 1fr", gap:16 }}>
        <div>
        {img && <img src={img} alt={movie.title} style={{ width:"100%", borderRadius:8 }} />}
        <button style={{ marginTop:12 }} onClick={addFav} disabled={!token}>
            Add Favorite
        </button>
        {!token && <div style={{ fontSize:12, opacity:0.7 }}>Login để dùng favorites</div>}
        </div>

        <div>
        <h2>{movie.title}</h2>
        <div style={{ opacity:0.7 }}>⭐ {movie.vote_average} | {movie.release_date}</div>
        <p>{movie.overview}</p>

        {trailerKey && (
            <div style={{ marginTop:12 }}>
            <h3>Trailer</h3>
            <iframe
                width="720"
                height="405"
                src={`https://www.youtube.com/embed/${trailerKey}`}
                title="Trailer"
                allowFullScreen
            />
            </div>
        )}

        <div style={{ marginTop:20 }}>
            <h3>Comments</h3>
            <div style={{ display:"flex", gap:8 }}>
            <input
                value={content}
                onChange={(e) => setContent(e.target.value)}
                placeholder="Nhập bình luận..."
                style={{ flex:1, padding:8 }}
            />
            <button onClick={postComment}>Post</button>
            </div>

            <div style={{ marginTop:12 }}>
            {comments.map(c => (
                <div key={c.id} style={{ borderBottom:"1px solid #eee", padding:"8px 0" }}>
                <div style={{ fontWeight:600 }}>{c.fullName || c.userEmail}</div>
                <div>{c.content}</div>
                <div style={{ fontSize:12, opacity:0.7 }}>{String(c.createdAt)}</div>
                </div>
            ))}
            </div>
        </div>

        </div>
    </div>
    );
}
