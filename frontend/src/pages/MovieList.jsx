import { useEffect, useState } from "react";
import { iphimApi } from "../api/iphim";
import MovieCard from "../components/MovieCard";

export default function MovieList() {
    const [page, setPage] = useState(1);
    const [data, setData] = useState(null);

    useEffect(() => {
    moviesApi.popular(page).then(res => setData(res.data)).catch(console.error);
    }, [page]);

    return (
    <div style={{ padding:16 }}>
        <h2>Popular (page {page})</h2>

        <div style={{ display:"flex", gap:8, marginBottom:12 }}>
        <button disabled={page <= 1} onClick={() => setPage(p => p - 1)}>Prev</button>
        <button onClick={() => setPage(p => p + 1)}>Next</button>
        </div>

        {!data ? "Loading..." : (
        <div style={{ display:"grid", gridTemplateColumns:"repeat(5, 1fr)", gap:12 }}>
            {data.results?.map(m => <MovieCard key={m.id} m={m} />)}
        </div>
        )}
    </div>
    );
}
