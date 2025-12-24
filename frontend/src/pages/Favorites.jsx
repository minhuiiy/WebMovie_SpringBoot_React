import { useEffect, useState } from "react";
import { favoritesApi } from "../api/favorites";

export default function Favorites() {
  const [items, setItems] = useState([]);

  useEffect(() => {
    favoritesApi.list().then(res => setItems(res.data)).catch(console.error);
  }, []);

  // backend trả List<String> (JSON movie detail), parse để render
  const parsed = items.map((s, idx) => {
    try { return JSON.parse(s); } catch { return { id: idx, title: "Parse error" }; }
  });

  return (
    <div style={{ padding:16 }}>
      <h2>Favorites</h2>
      {parsed.map(m => (
        <div key={m.id} style={{ borderBottom:"1px solid #eee", padding:"10px 0" }}>
          <b>{m.title}</b> <span style={{ opacity:0.7 }}>⭐ {m.vote_average}</span>
        </div>
      ))}
    </div>
  );
}
