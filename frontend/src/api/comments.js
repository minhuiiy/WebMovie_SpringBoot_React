import { axiosClient } from "./axiosClient";

export const commentsApi = {
    list: (movieId) => axiosClient.get(`/api/comments?movieId=${movieId}`),
    add: (movieId, payload) => axiosClient.post(`/api/comments?movieId=${movieId}`, payload),
};