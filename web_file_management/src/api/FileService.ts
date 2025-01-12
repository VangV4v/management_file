import AxiosInstance from "../core/axios-instance";

export const getFiles = (token: any, payload: {name: string})  => {

    return AxiosInstance.get(`/api/v1/list?name=${payload.name}`, {
        headers : {
            Authorization : token
        }
    })
};

export const uploadFile = (config: any, payload: any) => {

    return AxiosInstance.post('/api/v1/upload', payload, {
        headers: config
    })
};