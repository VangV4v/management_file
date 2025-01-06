import { Box, Button, Grid2, IconButton, Stack } from "@mui/material";
import bgrImg from '../../assets/image/login_brg.png';
import { ITextField } from "../../components/input/input-field";
import { useForm } from "react-hook-form";
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import { IPasswordField } from "../../components/input/password-field";
import ILoginModel from "../../model/ILoginModel";
import { useAppDispatch } from "../../redux/hooks";
import { authUser } from "../../redux/authSlice";
import CircularProgress from '@mui/material/CircularProgress';
import { useRef, useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import { useTranslation } from "react-i18next";
import { ChangeLanguage } from "../../components/language/change-lang";

export default function LoginPage() {

    const { t } = useTranslation();
    const dispatch = useAppDispatch();
    const ref = useRef<any>();
    const [isShowProgress, setShowProgress] = useState<Boolean>(false);
    const schema = yup
        .object({
            username: yup.string().required(t('err.field.username')),
            password: yup.string().required(t('err.field.password')),
        })
        .required();
    const form = useForm({
        defaultValues: {
            username: '',
            password: ''
        },
        resolver: yupResolver(schema),
    });

    const Msg = () => (
        <>
            {t('err.loginfail')}
        </>
    );

    const handleLoadingProgress = () => {
        ref.current = setTimeout(() => {
            setShowProgress(false);
            window.location.replace('/test');
        }, 3000);
    };

    const handleSubmitForm = (param: ILoginModel) => {
        const actionLogin = authUser(param);
        dispatch(actionLogin)
            .unwrap()
            .then(res => {

                localStorage.setItem('jwt', 'Bearer ' + res)
                setShowProgress(true);
                handleLoadingProgress();
            }).catch(err => {

                toast.error(Msg, { theme: "colored" });
                console.log(err);
            });
    };

    return (

        <Box className='login-bgr'>
            <Box className='login-box1'>
                <Grid2 container spacing={1}>
                    <Grid2 size={{ xs: 12, xl: 6, sm: 6 }} className='login-gr1'>
                        {
                            isShowProgress &&
                            <Box className='login-gr1-progress'>
                                <CircularProgress />
                            </Box>
                        }
                        <form onSubmit={form.handleSubmit(handleSubmitForm)}>
                            <Stack spacing={5}>
                                <ChangeLanguage></ChangeLanguage>
                                <ITextField name="username" label={t('field.username')} control={form.control} />
                                <IPasswordField name="password" label={t('field.password')} control={form.control} />
                                <Button type="submit" variant="contained" endIcon={<ArrowForwardIcon />}>{t('login.login')}</Button>
                                <ToastContainer />
                            </Stack>
                        </form>
                    </Grid2>
                    <Grid2 size={{ xs: 12, xl: 6, sm: 6 }}>
                        <img className='img-bgr' src={bgrImg} />
                    </Grid2>
                </Grid2>
            </Box>
        </Box>
    );
}