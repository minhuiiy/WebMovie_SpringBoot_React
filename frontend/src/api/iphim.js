import { axiosClient } from "./axiosClient";

export const iphimApi = {
    latest: (page = 1) => axiosClient.get(`/api/iphim/latest?page=${page}`),
    list: (slug, page = 1) => axiosClient.get(`/api/iphim/list/${slug}?page=${page}`),
      genre: (slug, page = 1) => axiosClient.get(`/api/iphim/genre/${slug}?page=${page}`),
      country: (slug, page = 1) => axiosClient.get(`/api/iphim/country/${slug}?page=${page}`),
      detail: (slug) => axiosClient.get(`/api/iphim/movie/${slug}`),
      search: (keyword) => axiosClient.get(`/api/iphim/search?keyword=${encodeURIComponent(keyword)}`),
};