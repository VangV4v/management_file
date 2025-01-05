import { Routes, Route } from "react-router";
import LoginPage from "../auth/login-page";
import ResponsiveAppBar from "./menu";
export function MainLayout() {
    return (
        <>
            <>
                <Routes>
                    <Route path='/login' element={<LoginPage />} />
                    <Route path='/' element={<ResponsiveAppBar/>}></Route>
                </Routes>
            </>
        </>
    );
}
