import './App.css';
import {
  BrowserRouter as Router,
  Routes,
  Route,
} from "react-router-dom";
import styled from 'styled-components';
import Home from "./routes/Home.js"
import Community from "./routes/Community.js"
import Publish from "./routes/Publish.js"
import PostView from "./routes/PostView.js"
import NoticePostView from './routes/NoticePostView.js'
import Notice from "./routes/Notice.js"
import NavBar from "./components/NavBar.js"
import SubNavBar from './components/SubNavBar';
import MenuIcon from '@mui/icons-material/Menu';

const AppContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: flex-start;
  padding-top: 40px;
  padding-bottom: 100px;
  color:#363636;
`;
const Container = styled.div`
  display: flex;
  flex-direction: column;
`;  
function App() {  
  return (
    <AppContainer>
      <Router>
        <NavBar/>
        <Container>
          <Routes>
            <Route path="/" element={<Home/>}></Route>
            <Route path="/board" element={<Community/>}></Route>
            <Route path="/board/post" element={<Publish/>}></Route>
            <Route path="/board/:id" element={<PostView/>}></Route>
            <Route path="/notice" element={<Notice/>}></Route>
            <Route path="/notice/:id" element={<NoticePostView/>}></Route>
          </Routes>
        </Container>
        <SubNavBar/>
      </Router>
    </AppContainer>
  );
}

export default App;
