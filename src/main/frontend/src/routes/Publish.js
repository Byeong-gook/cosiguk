import styles from "./Post.module.css";
import styled from 'styled-components';
import axios from 'axios';
import {useState} from "react";
import Box from '@mui/material/Box';
import {TextField} from '@mui/material';
import Button from '@mui/material/Button';
import SendIcon from '@mui/icons-material/Send';

const PostContainer = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`;
const PostHeader = styled.div`
    width: 750px;
    height: 90px;
    font-weight: bold;
    font-size: 35px;
    text-align: center;
`;
const PostUserInfo =styled.div`
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    margin-bottom: 15px;
`;
const PostContents = styled.form`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 750px;
`;
const PostTitle = styled.input`
    width: 500px;
    height: 5%;    
    background-color: white;
    border: 1px solid #e5e8ec;
    border-radius: 5px;
    line-height: 1.4375em;
    padding: 6px 10px;
    resize: none;

    &:focus {
    outline-color: #3b7dff;
    }
    &::-webkit-input-placeholder {

}
`;
const Post = styled.textarea`
    margin-top: 8px;
    margin-bottom: 10px;
    width: 500px;
    height: 150px;    
    background-color: white;
    border: 1px solid #e5e8ec;
    border-radius: 5px;
    line-height: 1.4375em;
    padding: 6px 10px;
    resize: none;
    
    &:focus {
    outline-color: #3b7dff;
    }
    &::-webkit-input-placeholder {
    }
`;
function Publish () {

    const [newPost, setNewPost] = useState({ title:"", nickname:"", password:"",content:""});
    async function onPublish(event){
        event.preventDefault();        

        const params = new URLSearchParams();
        params.append('nickname', newPost.nickname);
        params.append('password', newPost.password);
        params.append('title', newPost.title);
        params.append('content', newPost.content);
        const config = {headers : {'Content-Type': 'application/x-www-form-urlencoded'}}

        await axios.post("/api/board/boardAdd", params, config)
        .then((json) => {
            console.log(json);
        }).catch((error) => {
            console.log(error);
        });
        
    }
    const handleChange = (e) => {
        setNewPost(prevObject => ({...prevObject, [e.target.name]: e.target.value}));
    }
    return(
        <PostContainer>
            <PostHeader>
                글쓰기
            </PostHeader>
            <PostContents onSubmit={onPublish}>
            <PostUserInfo>
                    <TextField
                        required
                        id="outlined-required"
                        value={newPost.nickname || ''}
                        onChange={handleChange}
                        label="닉네임"
                        name="nickname"
                        helperText="10자 이하로 입력해주세요"
                        inputProps={{ 
                            maxLength: 10,
                            style: { backgroundColor: "white"},
                            className: styles.postNickname,
                        }}
                        size="small"
                        type="text"
                        />
                    <TextField
                        sx={{fill: 'white'}}
                        required
                        value={newPost.password || ''}
                        onChange={handleChange}
                        id="outlined-required"
                        name="password"
                        label="비밀번호"
                        type="password"
                        helperText="게시글 삭제와 수정에 이용됩니다."
                        inputProps={{maxLength: 20, style: { backgroundColor: "white"}}}
                        size="small"
                        />
                        <Button type="submit" variant="contained" endIcon={<SendIcon />} sx={{ml:5, mb:3}}>작성</Button>
                    </PostUserInfo>
                    <PostTitle
                        onChange={handleChange}
                        value={newPost.title || ''}
                        label="제목" name="title" required placeholder="제목" type="text"
                        rows="1"
                    >
                    </PostTitle>
                    <Post
                        onChange={handleChange}  
                        value={newPost.content || ''}
                        name="content" label="내용" placeholder="내용" type="text" maxLength="100"
                        multiline   
                        >
                    </Post>
                    
            </PostContents>
            <div className={styles.postFooter}>
            </div>
        </PostContainer>
    )
}
export default Publish;