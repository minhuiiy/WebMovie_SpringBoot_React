import { useState } from "react";
import { authApi } from "../api/auth";
import { useNavigate } from "react-router-dom";

export default function Register() {
  const nav = useNavigate();
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const submit = async () => {
    try {
      const res = await authApi.register({ fullName, email, password });
      localStorage.setItem("accessToken", res.data.accessToken);
      nav("/");
    } catch (e) {
      alert("Register failed");
    }
  };

  return (
    <div style={{ padding:16, maxWidth:420 }}>
      <h2>Register</h2>
      <input value={fullName} onChange={(e)=>setFullName(e.target.value)} placeholder="Full name" style={{ width:"100%", padding:8, marginBottom:8 }} />
      <input value={email} onChange={(e)=>setEmail(e.target.value)} placeholder="Email" style={{ width:"100%", padding:8, marginBottom:8 }} />
      <input type="password" value={password} onChange={(e)=>setPassword(e.target.value)} placeholder="Password" style={{ width:"100%", padding:8, marginBottom:8 }} />
      <button onClick={submit}>Register</button>
    </div>
  );
}
