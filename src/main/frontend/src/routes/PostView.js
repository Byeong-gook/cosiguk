import UseAsync from '../hooks/UseAsync.js';
import {useParams} from 'react-router-dom';
import axios from 'axios';
import Post from '../components/Post.js';
import CommentWrite from '../components/CommentWrite.js'
import styled from 'styled-components';
const PostViewContainer = styled.div`
        display: flex;
        flex-direction: column;
        justify-content: center;
    `;
const BoardHeader = styled.div`
    height: 80px;
    text-align: center;
`;
const HeaderTitle = styled.h2`
    margin-top: 26px;
`;
const CommentCnt = styled.div`
    font-weight: 500;
    text-align: left;
    margin-top: 20px;
    margin-left: 10px;
`;
const CommentView = styled.div`
    display: flex;
    flex-direction: column;
    margin-top: 18px;
`;
function PostView(){
    const params= useParams();
    const postId = params.id;

    const getPostData = async() => {
        const res = await axios.get(`/api/board/${postId}`)
        return res;
    }
    const getCommentData = async() => {
        const res = await axios.get(`/api/chat/${postId}/chatList`)
        return res;
    }
    //eslint-disable-next-line
    const [postState, postRefetch] = UseAsync(getPostData, []);
    //eslint-disable-next-line
    const [commentState, commentRefetch] = UseAsync(getCommentData, []);
    const {loading : postLoading, data : postData, error : postError} = postState;
    const {loading : commentLoading, data : commentData, error : commentError} = commentState;

    if(postLoading || commentLoading) return <div>Loading</div>;
    if(postError || commentError) return <div>error page</div>;
    if(!postData || !commentData) return <div>loading</div>


    const post = postData.data.result_data.account;
    const comments = commentData.data.result_data.data;

    return(
        <PostViewContainer>
            <BoardHeader>
                <HeaderTitle>Community</HeaderTitle>
            </BoardHeader>
            <Post postData={post} WhatFor="board"></Post>
            <CommentCnt>{comments.length} comments</CommentCnt>
            <CommentView>
                {comments.map((com,i) => <Post id={i} postData={com} WhatFor="chat" view="PostView"></Post>)}
                <CommentWrite postId={postId}></CommentWrite>
            </CommentView>
        </PostViewContainer>
        
    )
}
export default PostView;