import { Box, Button, Grid2, Stack } from "@mui/material";
import bgrImg from '../../assets/image/login_brg.png';
import { ITextField } from "../../components/input/input-field";
import { useForm } from "react-hook-form";
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
import { yupResolver } from "@hookform/resolvers/yup"
import * as yup from "yup"
import { IPasswordField } from "../../components/input/password-field";
import ILoginModel from "../../model/ILoginModel";
import { login } from "../../api/AuthService";
import { useAppDispatch } from "../../redux/hooks";
import { authUser } from "../../redux/authSlice";

const schema = yup
  .object({
    username: yup.string().required('Username is not empty'),
    password: yup.string().required('Password is not empty'),
  })
  .required()

interface ILoginPageProps {
}

export default function LoginPage(props: ILoginPageProps) {

    const dispatch = useAppDispatch();
    const form = useForm({
        defaultValues: {
            username: '',
            password: ''
        },
        resolver: yupResolver(schema),
    });

    const handleSubmitForm = (param: ILoginModel) => {
        const actionLogin = authUser(param);
        dispatch(actionLogin)
            .unwrap()
            .then(res => {

                console.log(res);
            }).catch(err => {

                console.log(err);
            });
    };

    return (
        <Box className='login-bgr'>
            <Box className='login-box1'>
                <Grid2 container spacing={1}>
                    <Grid2 size={{xs: 12, xl: 6, sm: 6}} className='login-gr1'>
                        <form onSubmit={form.handleSubmit(handleSubmitForm)}>
                            <Stack spacing={5}>
                                <ITextField name="username" label="User name" control={form.control} />
                                <IPasswordField name="password" label="Pass word" control={form.control} />
                                <Button type="submit" variant="contained" endIcon={<ArrowForwardIcon/>}>LOGIN</Button>
                            </Stack>
                        </form>
                    </Grid2>
                    <Grid2 size={{xs: 12, xl: 6, sm: 6}}>
                        <img className='img-bgr' src={bgrImg}/>
                    </Grid2>
                </Grid2>
            </Box>
        </Box>
    );
}