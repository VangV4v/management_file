import { Box, Container, Grid2 } from "@mui/material";

interface ILoginPageProps {
}

export default function LoginPage(props: ILoginPageProps) {
    return (
        <Box className='login-bgr'>
            <Box className='login-box1'>
                <Grid2 container spacing={1}>
                    <Grid2 size={{xs: 12, xl: 7, sm: 6}}>
                        A
                    </Grid2>
                    <Grid2 size={{xs: 12, xl: 5, sm: 6}}>
                        B
                    </Grid2>
                </Grid2>
            </Box>
        </Box>
    );
}