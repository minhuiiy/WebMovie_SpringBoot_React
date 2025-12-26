import React from "react";

export default function IphimCard({ m }) {
  const img = m.poster_url || m.thumb_url || "";
  return (
    <div style={{ border: "1px solid #eee", borderRadius: 8, padding: 10 }}>
      {img && (
        <img
          src={img}
          alt={m.name}
          style={{ width: "100%", borderRadius: 8, objectFit: "cover" }}
        />
      )}
      <div style={{ fontWeight: 600, marginTop: 8 }}>{m.name}</div>
      <div style={{ opacity: 0.7, fontSize: 13 }}>
        {m.origin_name ? m.origin_name : ""} {m.year ? `| ${m.year}` : ""}
      </div>
    </div>
  );
}
