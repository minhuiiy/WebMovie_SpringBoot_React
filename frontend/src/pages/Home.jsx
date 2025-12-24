import { useEffect, useState } from "react";
import { iphimApi } from "../api/iphim";

export default function Home() {
    const [data, setData] = useState(null);

    useEffect(() => {
    iphimApi.latest(1).then(res => setData(res.data)).catch(console.error);
    }, []);

    return (
    <div style={{ padding: 16 }}>
        <h2>Phim mới cập nhật</h2>
        {!data ? "Loading..." : (
        <pre style={{ whiteSpace: "pre-wrap" }}>
            {JSON.stringify(data, null, 2)}
        </pre>
        )}
    </div>
    );
}
