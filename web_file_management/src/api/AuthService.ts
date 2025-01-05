import AxiosInstance from "../core/axios-instance";
import ILoginModel from "../model/ILoginModel";

export const login = (payload: ILoginModel) => {

    return AxiosInstance.post('/api/v1/login', payload);
}

// const LoginService = {
//     login(payload: ILoginModel) {

//         return AxiosInstance.post(`/api/v1/login`, payload);
//     }
// };

// export default LoginService;