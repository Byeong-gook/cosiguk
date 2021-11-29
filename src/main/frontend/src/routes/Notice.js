import UseAsync from '../hooks/UseAsync.js';
import axios from 'axios';
import styled from 'styled-components';
import {Link} from 'react-router-dom';
import Post from '../components/Post.js';

const NoticeBoardContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
`;
const NoticeBoardHeader = styled.div`
    height: 80px;
    text-align: center;
`;
const NoticeHeaderTitle = styled.h2``;
const NoticeBoardBody = styled.div``;

async function getNoticeData() {
    const response =  await axios.get('/api/notice/noticeList')
    return response.data;
}

function Notice(){
//eslint-disable-next-line
const [state, refetch] = UseAsync(getNoticeData, []);
const {loading, data, error} = state;
if(loading) return <div>Loading</div>;
if(error) return <div>error page</div>;
if(!data) return <div>loading</div>

const noticeBoardData = data.result_data.data;

return(
    <>
    <NoticeBoardContainer>
        <NoticeBoardHeader>
            <NoticeHeaderTitle>공지사항</NoticeHeaderTitle>
        </NoticeBoardHeader>
        <NoticeBoardBody>
            {noticeBoardData.map((item)=>
            <Link key={item.id} to={`/notice/${item.id}`}>
                <Post postData={item} WhatFor="board" view="Notice"/>         
            </Link>
            )}
        </NoticeBoardBody>      
    </NoticeBoardContainer>
    </>
    )
}

export default Notice;