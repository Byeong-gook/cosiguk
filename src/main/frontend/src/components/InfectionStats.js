import PropTypes from 'prop-types';
import styled from 'styled-components';

const StatsContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-items: center;
    width: 420px;
    margin-top:10px;
    flex-wrap: wrap;
`;
const AboveStatsContainer = styled.div`
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-items : center;
    justify-content: center;
    box-shadow: 0 0.1px 2px rgb(32 33 36 / 10%);
    border: 1px solid rgba(70, 77, 82, 0.2);
    border-radius: 7px;
    background: white;
    width: 400px;
    padding: 15px;
    
`;
const AboveStatsBlock = styled.div`
    display: flex;
    flex-direction: column;
    align-items : center;
    justify-content: center;
    box-shadow: 0 0.1px 2px rgb(32 33 36 / 10%);
    border: 1px solid rgba(70, 77, 82, 0.2);
    border-radius: 3px;
    width: 130px;
    height: 130px;
    background-color: #f7f7f7;
`;
const AboveStatsTitle = styled.div`
    font-size: 14px;
    border: 0px black;
    border-radius: 10px;
`;
const AboveStatsNum = styled.div`
    font-size: 18px;
    font-weight: 800;
    color: ${(props)=> props.textColor};
`;

const AboveStatsName = styled.div`
    font-size: 13px;
`;
const CompareStats = styled.div(AboveStatsName);

const StatFooter = styled.div`
    display: flex;
    flex-direction: row;
    font-size: 12px;
    box-shadow: 0 1px 6px rgb(32 33 36 / 28%);
    border: 1px solid rgba(70, 77, 82, 0.082);
    border-radius: 30px;
    margin-left: 8px;
    width: 380px;
    height: 50px;
    margin-bottom: 10px;
    align-items: center;
    justify-content: center;
    background-color: rgb(68,61, 61);
`;

const FooterItem = styled.div`
    display:flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding-left: 10px;
    padding-right: 10px;
`;
const FooterStatTitle = styled.div`
    font-size: 12px;
    margin-bottom: 3px;
    font-weight: 500;
    color: white;
`;
const FooterStatNum = styled.div`
    color: ${(props)=> props.textColor};
    background-color: ${(props)=> props.backgroundColor};
    border: 0px;
    border-radius: 10px;
    text-align: center;
    font-weight:600;
    width: 50px;

`;

function InfectionStats ({todayCnt, yesterdayCnt}){
    const todayStats = [
        todayCnt.decideCnt,
        todayCnt.clearCnt,
        todayCnt.deathCnt,
        todayCnt.accExamCnt,
        todayCnt.examCnt,
        todayCnt.resutlNegCnt
    ]
    const yesterStats = [
        yesterdayCnt.decideCnt,
        yesterdayCnt.clearCnt,
        yesterdayCnt.deathCnt,
        yesterdayCnt.accExamCnt,
        yesterdayCnt.examCnt,
        yesterdayCnt.resutlNegCnt
    ]
    const statsName = ["확진자", "격리 해제" ,"사망자", "누적 검사자", "검사 중", "음성 판정"];
    return(
            <StatsContainer>
                <StatFooter>
                    <FooterItem>
                        <FooterStatTitle>격리 중</FooterStatTitle>
                        <FooterStatNum textColor="black" backgroundColor="#d1d1d1">{(todayCnt.decideCnt - todayCnt.clearCnt - todayCnt.deathCnt).toLocaleString()}</FooterStatNum>
                    </FooterItem>
                    <FooterItem>
                        <FooterStatTitle>오늘 확진자</FooterStatTitle>
                        <FooterStatNum textColor="#e91010" backgroundColor="#ffbbbb">{(todayCnt.decideCnt - yesterdayCnt.decideCnt).toLocaleString()}</FooterStatNum>
                        </FooterItem>
                    <FooterItem>
                        <FooterStatTitle>일일 완치자</FooterStatTitle>
                        <FooterStatNum textColor="#002bd1" backgroundColor="#c8c8ff">{(todayCnt.clearCnt - yesterdayCnt.clearCnt).toLocaleString()}</FooterStatNum>
                    </FooterItem>
                </StatFooter>
                <AboveStatsContainer>
                    {todayStats.map((stat, i)=>
                        <AboveStatsBlock key={i}>
                            <AboveStatsTitle>{statsName[i]}</AboveStatsTitle>
                            <AboveStatsNum>{stat.toLocaleString()}</AboveStatsNum>
                            <AboveStatsName>{(stat - yesterStats[i]).toLocaleString()}</AboveStatsName>
                        </AboveStatsBlock>
                    )}
                </AboveStatsContainer>
                
            </StatsContainer>          
    )
}

InfectionStats.propTypes = {
    todayCnt: PropTypes.object.isRequired,
    yesterdayCnt: PropTypes.object.isRequired
}
export default InfectionStats;