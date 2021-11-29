import PropTypes from 'prop-types';
import styled from 'styled-components';
import ThumbUpAltIcon from '@mui/icons-material/ThumbUpAlt';
import ThumbDownAltIcon from '@mui/icons-material/ThumbDownAlt';
import { Tooltip } from '@mui/material';
import KeyboardArrowUpIcon from '@mui/icons-material/KeyboardArrowUp';
import InsertCommentIcon from '@mui/icons-material/InsertComment';
import CloseIcon from '@mui/icons-material/Close';
import ErrorIcon from '@mui/icons-material/Error';
const PostContainer = styled.div`
        display: flex;
        flex-direction: column;
        gap: 10px;
        background-color: white;
        box-shadow: 0 1px 6px rgb(32 33 36 / 10%);
        border: 1px solid rgba(70, 77, 82, 0.082);
        border-radius: 7px;
        width: 750px;
        margin-top: 12px;
        @media screen and (max-width: 650px) {
        width: 100%;
    }
    `;
const PostHeader = styled.div`
    display:flex;
    flex-direction: row;
    justify-content: space-between;
    padding-left: 30px;
    padding-top: 10px;
`;
const PostHeaderLeft = styled.div`
    display:flex;
    flex-direction: row;
    justify-content: flex-start;
`
const PostNickname = styled.div`
    font-weight: bold;
    font-size: 12px;
`;
const PostDate = styled.div`
    font-weight: lighter;
    font-size: 14px;
    padding-left: 35px;
`;
const PostHits = styled.div`
    margin-right: 10px;
    font-weight: 200;
`;
const PostBody = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: flex-start;

`;
const PostTitle = styled.div`
    font-weight: bold;
    display: flex;
    justify-content: flex-start;
    padding-left: 30px;
    font-size: 15px;
`;
const PostContent = styled(PostTitle)`
    font-size: 13px;
    font-weight: normal;
    padding-top: 10px;
    padding-right: 30px;
`;
const PostFooter = styled.div`
    border-top: 1px solid #f6f6f6;
    display: flex;
    flex-direction: row;
    padding: 4px 3px 3px 3px;
    align-items: center;
    justify-content: space-between;
    /* pointer-events: none; 
    cursor: default; */
`;
const FooterRight = styled.div`
    display: flex;
    flex-direction: row;
    button{
        background-color: unset;
        border: 0;
    }
`;
const FooterLeft = styled(FooterRight)`
`;
const Voting = styled.div`
    display: flex;
    flex-direction: row;
    border-right: 1px solid #f6f6f6;
    padding: 0px 10px 0px 25px;
    gap: 8px;
    align-items: center;
    button{
        background-color: unset;
        border: 0;
    }
`;
const RecommendStats = styled.div`
    display: flex;
    border-right: 1px solid #f6f6f6;
    padding : 0px 10px 0px 10px;
    gap: 2px;
    align-items: center;
`;
const Cnt = styled.div`
font-size: 13px;
font-weight: 300;
`;
const CommentStats = styled(RecommendStats)`
    justify-content: center;
    align-items: center;
    gap: 4px;
`;

function Post({postData, WhatFor, view}){

    const onRecommend = async(e) => {
        fetch(`/api/${WhatFor}/${e.currentTarget.value}/recommend`)
        .then((res) => res.json())
        .then((json) => {
            if(json.error !== undefined) alert(json.error);
            else {
                alert("추천 하였습니다.");
                window.location.reload();
            }
        })
        .catch((error) => {console.log(error)});
    }
    const onDeprecate = async(e) => {
        fetch(`/api/${WhatFor}/${e.currentTarget.value}/deprecate`)
        .then((res) => res.json())
        .then((json) => {
            if(json.error !== undefined) alert(json.error)
            else {
                alert("비 추천 하였습니다.");
                window.location.reload();
            }
        })
        .catch((error) => {console.log(error)});
    }
    return(
        <>
            <PostContainer>
                <PostHeader>
                    <PostHeaderLeft>
                        <PostNickname>{postData.nickname === undefined ? <>관리자</>: (postData.nickname.length > 11? postData.nickname.slice(0,11) : postData.nickname)}</PostNickname>
                        <PostDate>{postData.createdDate? postData.createdDate : postData.createDate}</PostDate>
                    </PostHeaderLeft>
                    {postData.hit? <PostHits>{postData.hit} views</PostHits> : null }
                </PostHeader>
                <PostBody>
                    <PostTitle>{postData.title && postData.title.length > 50? postData.title.slice(0,50) : postData.title}</PostTitle>
                    <PostContent>{
                    view === "Community" || view === "Notice"? 
                    (postData.content && postData.content.length > 75? postData.content.slice(0,75) + "..." : (postData.content))
                    :
                    postData.content}
                    </PostContent>
                    </PostBody>
                {view === "NoticePostView" || view === "Notice"?
                <PostFooter></PostFooter>
                :
                    <PostFooter>
                        <FooterLeft>
                            <Voting>
                                <button value={postData.id}  onClick={onRecommend}>
                                    <Tooltip title="추천" arrow>{<ThumbUpAltIcon style={{ fill: 'rgba(0,0,0,0.45)' }}fontSize="10px" />}</Tooltip>
                                </button>
                                <button value={postData.id}  onClick={onDeprecate}>
                                    <Tooltip title="비추천" arrow>{<ThumbDownAltIcon style={{ fill: 'rgba(0,0,0,0.45)' }} fontSize="10px"/>}</Tooltip>
                                </button>
                                <button>
                                    <Tooltip title="신고" arrow>
                                        <ErrorIcon sx={{pt:0.06}} style={{ fill: 'rgba(0,0,0,0.45)', fontSize:"14px"}}/>
                                    </Tooltip>
                                </button>
                            </Voting>
                            <RecommendStats>
                                <Tooltip title="추천 수" arrow><KeyboardArrowUpIcon style={{ fill: 'rgba(0,0,0,0.45)'}}fontSize="small"/></Tooltip>
                                <Cnt>{postData.recommend} </Cnt>
                            </RecommendStats>
                            <CommentStats>
                                {postData.review_count? 
                                    <><Tooltip title="댓글 수" arrow><InsertCommentIcon  style={{ fill: 'rgba(0,0,0,0.45)' }} fontSize="6px"/></Tooltip>
                                    <Cnt>{postData.review_count}</Cnt></>
                                    : null}
                            </CommentStats>
                        </FooterLeft>
                        <FooterRight>
                            <button>
                                <Tooltip title="삭제" arrow>
                                    <CloseIcon style={{ fill: 'rgba(0,0,0,0.45)' }} fontSize="6px"/>
                                </Tooltip>
                            </button>
                        </FooterRight>
                    </PostFooter>
                }
            </PostContainer>
        </>
    )
}
Post.propTypes = {
    postData : PropTypes.object.isRequired,
    WhatFor: PropTypes.string.isRequired,
    view: PropTypes.string.isRequired
}
export default Post;
