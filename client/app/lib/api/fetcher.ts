import axios, { AxiosRequestConfig, AxiosResponse } from "axios";

export const fetcher = <P>(url: string, params?: P) => {
    const config: AxiosRequestConfig = {
        params,
    };
    return axios.get(url, config).then((res) => res.data);
};

export interface APIResponse<T> {
    statusCode: number;
    message: string;
    data: T
}