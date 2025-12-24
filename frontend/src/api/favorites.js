import { axiosClient } from "./axiosClient";

export const favoritesApi = {
    list: () => axiosClient.get("/api/favorites"),
    add: (movieId) => axiosClient.post(`/api/favorites/${movieId}`),
    remove: (movieId) => axiosClient.delete(`/api/favorites/${movieId}`),
};