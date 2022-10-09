import axios from 'axios';
import { baseURL, requestHeader } from '../constants/requestConstants';

export const doGetBoards = async (authUtils) => {
    return await axios.get('/api/boards', { headers: requestHeader(authUtils), baseURL: baseURL });
}

export const doGetBoard = async (boardId, authUtils) => {
    return await axios.get(`/api/boards/${boardId}`, { headers: requestHeader(authUtils), baseURL: baseURL })
}

export const doLikeBoard = async (boardId, authUtils) => {
    return await axios.post(`/api/boards/like/${boardId}`, null, { headers: requestHeader(authUtils), baseURL: baseURL });
}

export const doPostComment = async (commentDto, authUtils) => {
    return await axios.post(`/api/comment`, commentDto, { headers: requestHeader(authUtils), baseURL: baseURL })
}