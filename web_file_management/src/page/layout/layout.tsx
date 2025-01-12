import ResponsiveAppBar from "./menu";
import LoginPage from "../auth/login-page";

export function MainLayout() {
    return (
        <>

            {
                window.location.pathname === '/login'
                    ? <LoginPage />
                    :
                    <>
                        <ResponsiveAppBar />
                    </>
            }

        </>
    );
}
