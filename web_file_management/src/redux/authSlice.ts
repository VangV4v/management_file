import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import ILoginModel from '../model/ILoginModel'
import { login } from '../api/AuthService';
import { RootState } from './store';

export const authUser = createAsyncThunk(
    'user/login',
    async (payload: ILoginModel) => {
        const response = await login(payload);
        return response.data;
    },
);

const initialState = {
    user: localStorage.getItem('jwt') || ''
};

const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers : {

    },
    extraReducers(builder) {
        builder.addCase(authUser.fulfilled, (state, action) => {
            state.user = action.payload;
        })
    },
});
export const selectCount = (state: RootState) => state.auth.user
export default userSlice.reducer;