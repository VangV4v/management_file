import { Backdrop, CircularProgress } from "@mui/material";

export interface IBackDropProps {
    open: boolean
}

export function BackDropPage(props: IBackDropProps) {
    return (
        <Backdrop
            sx={(theme) => ({ color: '#fff', zIndex: theme.zIndex.drawer + 1 })}
            open={props.open}
        >
            <CircularProgress color="inherit" />
        </Backdrop>
    );
}