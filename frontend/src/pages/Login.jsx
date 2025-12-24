import { useState } from "react";
import { authApi } from "../api/auth";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const nav = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const submit = async () => {
    try {
      const res = await authApi.login({ email, password });
      localStorage.setItem("accessToken", res.data.accessToken);
      nav("/");
    } catch (e) {
      alert("Login failed");
    }
  };

  return (
    <div style={{ padding:16, maxWidth:420 }}>
      <h2>Login</h2>
      <input value={email} onChange={(e)=>setEmail(e.target.value)} placeholder="Email" style={{ width:"100%", padding:8, marginBottom:8 }} />
      <input type="password" value={password} onChange={(e)=>setPassword(e.target.value)} placeholder="Password" style={{ width:"100%", padding:8, marginBottom:8 }} />
      <button onClick={submit}>Login</button>
    </div>
  );
}
