import { useState, useEffect, useCallback } from 'react';
import axios, { AxiosError, AxiosResponse } from 'axios';


interface UsePostRequestResult<T> {
    response: AxiosResponse<T> | null;
    error: string | null;
    loading: boolean;
    post: (data: T) => Promise<void>;
    reset: () => void;
    success: boolean
}


export function usePostRequest<T>(url: string): UsePostRequestResult<T> {
    const [response, setResponse] = useState<AxiosResponse<T> | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
    const [success, setSuccess] = useState(false);

    const post = useCallback(async (data: T) => {
        setLoading(true);
        try {
            const response = await axios.post<T>(url, data);
            setResponse(response);
            setSuccess(true);
        } catch (error: any) {
            console.log(error)
            if (error instanceof AxiosError) {
                setError(error.response?.data?.data?.message)
            } else {
                setError(error.message);
            }
        } finally {
            setLoading(false);
        }
    }, [url]);

    const reset = () => {
        setResponse(null);
        setSuccess(false);
        setError("")
    }

    return { response, error, loading, post, reset, success };
}
