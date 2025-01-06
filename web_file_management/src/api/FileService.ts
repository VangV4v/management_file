import AxiosInstance from "../core/axios-instance";

export const getFiles = (token: any)  => {

    return AxiosInstance.get('/api/v1/list', {
        headers : {
            Authorization : token
        }
    })
};