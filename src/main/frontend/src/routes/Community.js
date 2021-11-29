import axios from 'axios';
import styled from 'styled-components';
import Post from "../components/Post.js";
import UseAsync from "../hooks/UseAsync.js";
import {Link} from 'react-router-dom';
import EditHeader from '../components/Editheader';
const BoardViewHeader = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  margin-left: 50px;
  height: 80px;
`;
const BoardViewTitle = styled.h2`
`;
const BoardViewWriteLink = styled(Link)`
  div{
    margin-left: 20px;
  }
`;
const BoardViewContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;
const BoardViewBody = styled.div`
`;
async function getBoardData() {
  const body = {
    page: 0, //page 변동처리
    size: 10 
  };
  var formBody = [];
  for (var property in body) {
  var encodedKey = encodeURIComponent(property);
  var encodedValue = encodeURIComponent(body[property]);
  formBody.push(encodedKey + "=" + encodedValue);
  }
  formBody = formBody.join("&");

  const response = await axios({
    method: 'post',
    url: '/api/board/boardList',
    data: formBody
  })
  return response.data;
}

function Community(){
  //eslint-disable-next-line
  const [state, refetch] = UseAsync(getBoardData, []);
  const {loading, data, error} = state;
  if(loading) return <h1>Loading</h1>
  if(error) return <div>error page</div>;
  if(!data) return <div>loading</div>
  const boardDataList = data.result_data.data;
  
  return(
      <> 
      <BoardViewContainer>
        <BoardViewHeader>
          <BoardViewTitle>커뮤니티</BoardViewTitle>
          <BoardViewWriteLink to="./post"><EditHeader/></BoardViewWriteLink>
        </BoardViewHeader>
        <BoardViewBody>
          {boardDataList.map((item)=>
            <Link key={item.id} to={`/board/${item.id}`}>
              <Post postData={item} WhatFor="board" view="Community"/>         
            </Link>
          )}
        </BoardViewBody>
      </BoardViewContainer>      
     </>)
}
export default Community;