import styled from 'styled-components';
import {useState} from 'react';
import axios from 'axios';
import Button from '@mui/material/Button';
import SendIcon from '@mui/icons-material/Send';


const CommentContainer = styled.div`
    margin-top: 20px;
    margin-bottom: 20px;
`;
const CommentForm = styled.form.attrs({autocomplete:"on"})`
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
`;

const CommentUser = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
`;
const CommentUserInfo = styled.div`
    display: flex;
    flex-direction: column;
    
`;
const CommentRightSide = styled(CommentUserInfo)`
    align-items: flex-end;
`;

const CommentUserInput = styled.input.attrs({required:true})`
    width : 150px;
    height: 25px;
    background-color: white;
    border: 1px solid #e5e8ec;
    border-radius: 5px;
    line-height: 1.4375em;
    padding: 6px 10px;
    margin-right: 10px;
    &:hover {
    }
    &:focus {
    margin-right: 0px;
    width: 160px;
    outline-color: #3b7dff;
    transition: width 200ms ease-out;

  }`;
const CommentInput = styled.textarea`
    width: 550px;
    height: 62px;
    background-color: white;
    border: 1px solid #e5e8ec;
    border-radius: 5px;
    line-height: 1.4375em;
    padding: 6px 10px;
    margin-right: 10px;
    resize: none;

    &:focus {
    outline-color: #3b7dff;
    }
    &::-webkit-input-placeholder {

    }
`;
function CommentWrite({postId}){
    const [newComment, setNewComment] = useState({nickname:"", password:"",content:""});
    async function onPublish(event){
        event.preventDefault();
        const params = new URLSearchParams();
        params.append('nickname', newComment.nickname);
        params.append('password', newComment.password);
        params.append('content', newComment.content);
        const config = {headers : {'Content-Type': 'application/x-www-form-urlencoded'}}

        await axios.post(`/api/chat/${postId}/chatAdd`, params, config)
        .then((json) => {
            console.log(json);
            window.location.reload();
        }).catch((error) => {
            console.log(error);
        });
    }
    const handleChange = (e) => {
        setNewComment(prevObject => ({...prevObject, [e.target.name]: e.target.value}));
    }
    return(
            <CommentContainer>
                <CommentForm  onSubmit = {onPublish}>
                    <CommentUser>
                        <CommentUserInfo>
                            <CommentUserInput
                            autocomplete="name"
                            onChange={handleChange}
                            value={newComment.nickname || ''}
                            name="nickname" label="닉네임" placeholder="닉네임" type="text" minLength="3" maxLength="10">
                            </CommentUserInput>
                            <CommentUserInput
                            autocomplete="current-password"
                            onChange={handleChange}
                            name="password" label="패스워드" placeholder="비밀번호" type="password" minLength="5" maxLength="20">
                            </CommentUserInput>
                        </CommentUserInfo>
                        <CommentRightSide>
                            <CommentInput
                                onChange={handleChange}  
                                value={newComment.content || ''}
                                name="content" label="내용" placeholder="내용" type="text" maxLength="100"
                                multiline
                                >
                            </CommentInput>
                            <Button type="submit" variant="contained" endIcon={<SendIcon />} sx={{mt:1, mr:1.5}}>작성</Button>
                        </CommentRightSide>
                    </CommentUser>
                </CommentForm>
            </CommentContainer>
    )
    
}
export default CommentWrite;