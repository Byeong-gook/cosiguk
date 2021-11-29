import styled from 'styled-components'
import EmailIcon from '@mui/icons-material/Email';
const NavContainer = styled.nav`
    display: flex;
    background-color: white;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    box-shadow: 0 1px 6px rgb(32 33 36 / 10%);
    border: 1px solid rgba(70, 77, 82, 0.082);
    border-radius: 7px;
    margin: 0px 0px 0px 25px;
    width:235px;
    height: 80px;
    position: sticky;
    top: 17%;
    @media screen and (max-width: 650px) {
        display: none;
    }
`;
const NavText = styled.div`
    font-size: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 30px;
`;
const ContactIcons = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
`;
function SubNavBar(){

    return(
        <>
            <NavContainer>
                <NavText><h2>Contact</h2></NavText>
                <ContactIcons>
                    <EmailIcon style={{ fill: 'rgba(0,0,0,0.7)' }} fontSize="small"/>
                </ContactIcons>
            </NavContainer>
        </>
    );
}

export default SubNavBar;