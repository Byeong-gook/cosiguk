import CoronavirusIcon from '@mui/icons-material/Coronavirus';
import ArticleIcon from '@mui/icons-material/Article';
import AssignmentLateIcon from '@mui/icons-material/AssignmentLate';

export const NavBarData = [
    {
        title: "확진 현황",
        path: "/",
        icon: <CoronavirusIcon style={{ fill: 'rgba(0,0,0,0.45)', fontSize:"20px"}}/>,
        
    },
    {
        title: "게시판",
        path: "/board",
        icon: <ArticleIcon style={{ fill: 'rgba(0,0,0,0.45)', fontSize:"20px"}}/>,
        
    },
    {
        title: "공지사항",
        path: "/notice",
        icon: <AssignmentLateIcon style={{ fill: 'rgba(0,0,0,0.45)', fontSize:"20px"}}/>,
        
    }
];