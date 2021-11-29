import UseAsync from '../hooks/UseAsync.js';
import {useParams} from 'react-router-dom';
import axios from 'axios';
import Post from '../components/Post.js';
import styled from 'styled-components';
const PostViewContainer = styled.div`
        display: flex;
        flex-direction: column;
        height: 500px;
    `;
const BoardHeader = styled.div`
    height: 80px;
    text-align: center;
`;
const HeaderTitle = styled.h2`
    margin-top: 26px;
`;

function NoticePostView(){
    const params= useParams();
    const postId = params.id;

    const getPostData = async() => {
        const res = await axios.get(`/api/notice/${postId}`)
        return res;
    }
    //eslint-disable-next-line
    const [postState, postRefetch] = UseAsync(getPostData, []);
    const {loading : postLoading, data : postData, error : postError} = postState;

    if(postLoading) return <div>Loading</div>;
    if(postError) return <div>error page</div>;
    if(!postData) return <div>loading</div>


    const post = postData.data.result_data.account;

    return(
        <PostViewContainer>
            <BoardHeader>
                <HeaderTitle>Notice</HeaderTitle>
            </BoardHeader>
            <Post postData={post} WhatFor="board" view="NoticePostView"></Post>
        </PostViewContainer>
        
    )
}
export default NoticePostView;