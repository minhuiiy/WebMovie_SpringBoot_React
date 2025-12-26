import { useEffect, useState } from "react";
import { iphimApi } from "../api/iphim";
import IphimCard from "../components/IphimCard";

export default function Home() {
    const [data, setData] = useState(null);

    useEffect(() => {
        iphimApi.latest(1).then(res => setData(res.data)).catch(console.error);
    }, []);

    const items = data?.items || [];

    return (
        <div style={{ padding: 16 }}>
            <h2>Phim mới cập nhật</h2>
            {!data ? "Loading..." : (
                <div style={{ display: "grid", gridTemplateColumns: "repeat(5, 1fr)", gap: 12 }}>
                    {items.map((m, i) => (
                        <IphimCard key={m.slug || i} m={m} />
                    ))}
                </div>
            )}
        </div>
    );
}
