import styled from 'styled-components';
import {NavBarData} from './NavBarData.js'
import {Link} from "react-router-dom";

const NavContainer = styled.div`
    position: sticky;
    top: 17%;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    margin: 0px 30px 0px 0px;
    @media screen and (max-width: 650px) {
        display: none;
    }
`;
const LogoTitle = styled.div`
    width:235px;
    height: 50px;
    box-shadow: 0 1px 6px rgb(32 33 36 / 10%);
    border: 1px solid rgba(70, 77, 82, 0.082);
    border-radius: 7px;
    display:flex;
    align-items: center;
    justify-content: center;
    background-color: white;
`;
const LogoText = styled.div`
    font-weight: bold;
    font-size: 14px;
    color: black;
`;
const Nav = styled.nav`
    background-color: white;
    flex-direction: column;
    box-shadow: 0 1px 6px rgb(32 33 36 / 10%);
    border: 1px solid rgba(70, 77, 82, 0.082);
    border-radius: 7px;
    width:235px;
    margin: 15px 0px 30px 0;
`;
const NavMenuItems = styled.ul`
    padding-left: 0;
    margin: 0;
`;
const NavText= styled.li`
    display: flex;
    justify-content: flex-start;
    list-style: none;
    align-items: center;
    padding: 10px 0px 10px 39px;
    height: 30px;
    
    a{
        font-size: 14px;
    }
    &:hover{
        background-color: #ededed;

    }`;
const NavIcon = styled.span`
    flex-shrink: 0;
    width: 30px;
`;
const NavTitle =  styled.span`
    margin-left: 9px;
    flex-grow: 1;
`;
//useState로 버튼 활성화 상태 관리하기

function NavBar (){
    return(
        <NavContainer>
            <LogoTitle>
                <Link to ={"/"}>
                    <LogoText>코로나19 현황</LogoText>
                </Link>
            </LogoTitle>
            <Nav>
                <NavMenuItems>
                    {NavBarData.map((item, index)=>{
                    return(
                        <NavText key={index}>
                            <Link to = {item.path}>
                                <NavIcon>{item.icon}</NavIcon>
                                <NavTitle>{item.title}</NavTitle>
                            </Link>
                        </NavText>
                    )})}
                </NavMenuItems>
            </Nav>
            </NavContainer>
    )
}
export default NavBar;
